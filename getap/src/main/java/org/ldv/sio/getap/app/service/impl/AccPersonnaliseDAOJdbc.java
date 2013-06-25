package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.dao.IFAccPersonnaliseDAO;
import org.ldv.sio.getap.utils.UtilSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("accPersonnaliseDAO")
public class AccPersonnaliseDAOJdbc implements IFAccPersonnaliseDAO {

	private static JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
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

	public List<AccPersonalise> getAllAPForAdmin() {
		return this.jdbcTemplate.query(
				"select * from ap where origineEtat = 0", new AccMapper());
	}

	public List<AccPersonalise> getAllAPForProf() {
		User user = UtilSession.getUserInSession();
		Long id = user.getId();
		return this.jdbcTemplate
				.query("select distinct ap.id, ap.libelle, ap.origineEtat, ap.idUser from ap, dctap where origineEtat = 0 or (origineEtat = 1 and dctap.idAP = ap.id and dctap.idEleve = ap.idUser and dctap.idProf = "
						+ id + ")", new AccMapper());
	}

	public List<AccPersonalise> getAllAPForEleve() {
		User user = UtilSession.getUserInSession();
		Long id = user.getId();
		return this.jdbcTemplate.query(
				"select * from ap where origineEtat = 0 or (origineEtat = 1 and idUser = "
						+ id + ")", new AccMapper());
	}

	public AccPersonalise getAPById(int id) {
		AccPersonalise acc;
		try {
			acc = this.jdbcTemplate.queryForObject(
					"select * from ap where id = ?", new Object[] { id },
					new AccMapper());
		} catch (EmptyResultDataAccessException e) {
			acc = null;
		}

		return acc;
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

	public void addAP(AccPersonalise ap) {
		String libelle = ap.getNom();
		int origineEtat = ap.getOrigineEtat();
		Long idUser = ap.getIdUser();

		this.jdbcTemplate.update(
				"insert into ap(libelle, origineEtat, idUser) values(?,?,?)",
				new Object[] { libelle, origineEtat, idUser });

	}

	public void upDateAP(AccPersonalise ap) {
		int id = ap.getId();
		String libelle = ap.getNom();
		int origineEtat = ap.getOrigineEtat();

		this.jdbcTemplate.update(
				"update ap set libelle = ?, origineEtat = ? where id = ?",
				new Object[] { libelle, origineEtat, id });

	}

	public void deleteAP(AccPersonalise ap) {
		int id = ap.getId();
		this.jdbcTemplate.update("delete from ap where id = ?",
				new Object[] { id });
	}

	public List<AccPersonalise> getApByType() {
		return this.jdbcTemplate
				.query("select dctap.idEleve as idEleve, count(dctap.id) as apByType, ap.* from dctap, ap where dctap.idAP = ap.id and (dctap.Etat = 1 or dctap.Etat = 5) group by dctap.idEleve, ap.libelle",
						new AccMapper());
	}
}
