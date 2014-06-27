package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.DemandeValidationConsoTempsAccPers;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.dao.IFDvctapDAO;
import org.ldv.sio.getap.utils.UtilSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("dvctapDAO")
public class DvctapDAOJdbc implements IFDvctapDAO {

	private static JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class DemandeMapper implements
			RowMapper<DemandeValidationConsoTempsAccPers> {
		public DemandeValidationConsoTempsAccPers mapRow(ResultSet rs,
				int rowNum) throws SQLException {
			DemandeValidationConsoTempsAccPers dctap = new DemandeValidationConsoTempsAccPers();
			dctap.setId(rs.getLong("id"));
			dctap.setAnneeScolaire(rs.getString("anneeScolaire"));
			dctap.setDateAction(rs.getDate("dateAction"));
			dctap.setMinutes(rs.getInt("dureeAP"));
			dctap.setEtat(rs.getInt("Etat"));

			Long idProf = rs.getLong("idProf");
			Long idEleve = rs.getLong("idEleve");
			int idAP = rs.getInt("idAP");

			UserDAOJdbc userDao = new UserDAOJdbc();
			AccPersonnaliseDAOJdbc accPersonnalise = new AccPersonnaliseDAOJdbc();
			User prof = userDao.getUserById(idProf);
			User eleve = userDao.getUserById(idEleve);
			AccPersonalise ap = accPersonnalise.getAPById(idAP);

			dctap.setProf(prof);
			dctap.setEleve(eleve);
			dctap.setAccPers(ap);

			return dctap;
		}
	}

	public AccPersonalise getAPByNom(String nom) {
		AccPersonalise acc;
		try {
			acc = this.jdbcTemplate.queryForObject(
					"select * from ap where libelle = ?", new Object[] { nom },
					new AccMapper());
		} catch (EmptyResultDataAccessException e) {
			acc = null;
		}

		return acc;
	}

	private static final class AccMapper implements RowMapper<AccPersonalise> {
		public AccPersonalise mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			AccPersonalise acc = new AccPersonalise();
			acc.setId(rs.getInt("id"));
			acc.setNom(rs.getString("libelle"));
			acc.setOrigineEtat(rs.getInt("origineEtat"));
			acc.setIdUser(rs.getLong("idUser"));
			try {
				acc.setCount(rs.getInt("apByType"));
				acc.setIdEleve(rs.getInt("idEleve"));
			} catch (SQLException ex) {

			}
			return acc;
		}
	}

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByEleve(
			User eleve) {
		Long id = eleve.getId();
		return this.jdbcTemplate.query("select * from dctap where idEleve = "
				+ id, new DemandeMapper());
	}

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByProfInterv(
			User profi) {
		Long id = profi.getId();
		return this.jdbcTemplate.query(
				"select * from dctap where anneeScolaire = '"
						+ UtilSession.getAnneeScolaireInSession()
						+ "' AND idProf = " + id, new DemandeMapper());
	}

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByProfPrinc(
			User profp, String annee) {
		Long id = profp.getId();
		return this.jdbcTemplate.query(
				"select * from dctap where anneeScolaire = '" + annee
						+ "' AND idProf = " + id, new DemandeMapper());
	}

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByProfPrinc(
			User profp) {
		return getAllDVCTAPByProfPrinc(profp,
				UtilSession.getAnneeScolaireInSession());
	}

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByClasse(
			String nomClasse) {
		return this.jdbcTemplate
				.query("select * from dctap d, user u, classe c where d.anneeScolaire = '"
						+ UtilSession.getAnneeScolaireInSession()
						+ "' AND d.idEleve = u.id and u.idClasse = c.id and libelle = 'nomClasse' ",
						new DemandeMapper());
	}

	public int getAllDVCTAPByEtat(int etat, Long id) {
		int count = this.jdbcTemplate.queryForInt(
				"select count(id) from dctap where anneeScolaire = '"
						+ UtilSession.getAnneeScolaireInSession()
						+ "' AND Etat = ? and (idProf = ? or idEleve = ?)",
				new Object[] { etat, id, id });
		return count;
	}

	public int getAllDVCTAPModifByEtat(Long id) {
		int count = this.jdbcTemplate.queryForInt(
				"select count(id) from dctap where anneeScolaire = '"
						+ UtilSession.getAnneeScolaireInSession()
						+ "' AND Etat >=1024 and (idProf = ? or idEleve = ?)",
				new Object[] { id, id });
		return count;
	}

	public DemandeValidationConsoTempsAccPers getDVCTAPById(Long id) {
		return this.jdbcTemplate.queryForObject(
				"select * from dctap where id = ?", new Object[] { id },
				new DemandeMapper());
	}

	public void addDVCTAP(DemandeValidationConsoTempsAccPers dctap) {
		String anneeScolaire = dctap.getAnneeScolaire();
		Date dateAction = dctap.getDateAction();
		int dureeAP = dctap.getMinutes();
		int etat = dctap.getEtat();
		Long idProf = dctap.getProf().getId();
		Long idEleve = dctap.getEleve().getId();
		int idAP;
		if (dctap.getAccPers().getId() != null) {
			idAP = dctap.getAccPers().getId();
		} else {
			idAP = this.getAPByNom(dctap.getAccPers().getNom()).getId();
		}

		this.jdbcTemplate
				.update("insert into dctap(anneeScolaire, dateAction, dureeAP, Etat, idProf, idEleve, idAP) values(?,?,?,?,?,?,?)",
						new Object[] { anneeScolaire, dateAction, dureeAP,
								etat, idProf, idEleve, idAP });

	}

	public void updateDVCTAP(DemandeValidationConsoTempsAccPers dctap) {
		Long id = dctap.getId();
		String anneeScolaire = dctap.getAnneeScolaire();
		Date dateAction = dctap.getDateAction();
		int dureeAP = dctap.getMinutes();
		int etat = dctap.getEtat();
		Long idProf = dctap.getProf().getId();
		Long idEleve = dctap.getEleve().getId();
		int idAP;
		if (dctap.getAccPers().getId() != null) {
			idAP = dctap.getAccPers().getId();
		} else {
			idAP = this.getAPByNom(dctap.getAccPers().getNom()).getId();
		}

		this.jdbcTemplate
				.update("update dctap set anneeScolaire = ?, dateAction = ?, dureeAP = ?, Etat = ?, idProf = ?, idEleve = ?, idAP = ? where id = ?",
						new Object[] { anneeScolaire, dateAction, dureeAP,
								etat, idProf, idEleve, idAP, id });

	}

	public void deleteDVCTAP(DemandeValidationConsoTempsAccPers dctap) {
		Long id = dctap.getId();
		this.jdbcTemplate.update("delete from dctap where id = ?",
				new Object[] { id });

	}

	public boolean deleteDVCTAPById(Long id) {
		int result = this.jdbcTemplate
				.queryForInt("select count(id) from dctap where id = ?",
						new Object[] { id });
		if (result == 0)
			return false;
		else
			return true;
	}

}
