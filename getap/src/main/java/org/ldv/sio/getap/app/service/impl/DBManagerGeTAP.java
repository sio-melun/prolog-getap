package org.ldv.sio.getap.app.service.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.DemandeConsoTempsAccPers;
import org.ldv.sio.getap.app.Role;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserSearchCriteria;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("DBServiceMangager")
public class DBManagerGeTAP implements IFManagerGeTAP {

	private static JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<DemandeConsoTempsAccPers> getAllDCTAPByEleve(User eleve) {
		Long id = eleve.getId();
		return this.jdbcTemplate.query("select * from DCTAP where idEleve = "
				+ id, new DemandeMapper());
	}

	public List<DemandeConsoTempsAccPers> getAllDCTAPByProfInterv(User profi) {
		Long id = profi.getId();
		return this.jdbcTemplate.query("select * from DCTAP where idProf = "
				+ id, new DemandeMapper());
	}

	public List<DemandeConsoTempsAccPers> getAllDCTAPByProfPrinc(User profp) {
		Long id = profp.getId();
		return this.jdbcTemplate.query("select * from DCTAP where idProf = "
				+ id, new DemandeMapper());
	}

	public List<DemandeConsoTempsAccPers> getAllDCTAPByClasse(String nomClasse) {
		return this.jdbcTemplate
				.query("select * from DCTAP d, user u, Classe c where d.idEleve = u.id and u.idClasse = c.id and libelle = 'nomClasse' ",
						new DemandeMapper());
	}

	public int getAllDCTAPByEtat(int etat, Long id) {
		int count = this.jdbcTemplate.queryForInt(
				"select count(id) from DCTAP where Etat = ? and idProf = ?",
				new Object[] { etat, id });
		return count;
	}

	public DemandeConsoTempsAccPers getDCTAPById(Long id) {
		return this.jdbcTemplate.queryForObject(
				"select * from DCTAP where id = ?", new Object[] { id },
				new DemandeMapper());
	}

	public void addDCTAP(DemandeConsoTempsAccPers dctap) {
		String anneeScolaire = dctap.getAnneeScolaire();
		Date dateAction = dctap.getDateAction();
		int dureeAP = dctap.getMinutes();
		int etat = dctap.getEtat();
		Long idProf = dctap.getProf().getId();
		Long idEleve = dctap.getEleve().getId();
		int idAP = dctap.getAccPers().getId();

		this.jdbcTemplate
				.update("insert into DCTAP(anneeScolaire, dateAction, dureeAP, Etat, idProf, idEleve, idAP) values(?,?,?,?,?,?,?)",
						new Object[] { anneeScolaire, dateAction, dureeAP,
								etat, idProf, idEleve, idAP });

	}

	public void updateDCTAP(DemandeConsoTempsAccPers dctap) {
		Long id = dctap.getId();
		String anneeScolaire = dctap.getAnneeScolaire();
		Date dateAction = dctap.getDateAction();
		int dureeAP = dctap.getMinutes();
		int etat = dctap.getEtat();
		Long idProf = dctap.getProf().getId();
		Long idEleve = dctap.getEleve().getId();
		int idAP = dctap.getAccPers().getId();

		this.jdbcTemplate
				.update("update DCTAP set anneeScolaire = ?, dateAction = ?, dureeAP = ?, Etat = ?, idProf = ?, idEleve = ?, idAP = ? where id = ?",
						new Object[] { anneeScolaire, dateAction, dureeAP,
								etat, idProf, idEleve, idAP, id });

	}

	public void deleteDCTAP(DemandeConsoTempsAccPers dctap) {
		Long id = dctap.getId();
		this.jdbcTemplate.update("delete from DCTAP where id = ?",
				new Object[] { id });

	}

	public boolean deleteDCTAPById(Long id) {
		int result = this.jdbcTemplate
				.queryForInt("select count(id) from DCTAP where id = ?",
						new Object[] { id });
		if (result == 0)
			return false;
		else
			return true;
	}

	public List<User> getAllProf() {
		return this.jdbcTemplate.query(
				"select * from user where role like 'prof%'", new UserMapper());

	}

	public List<User> getAllProfInter() {
		return this.jdbcTemplate.query(
				"select * from user where role = 'prof-intervenant'",
				new UserMapper());
	}

	public List<User> getAllProfPrinc() {
		return this.jdbcTemplate.query(
				"select * from user where role = 'prof-principal'",
				new UserMapper());
	}

	public List<User> getAllEleve() {
		return this.jdbcTemplate.query(
				"select * from user where role = 'eleve'", new UserMapper());
	}

	public User getUserById(Long id) {
		User user;
		try {
			user = this.jdbcTemplate.queryForObject(
					"select * from user where id = ?", new Object[] { id },
					new UserMapper());

		} catch (EmptyResultDataAccessException e) {
			user = null;
		}
		return user;
	}

	public void addUser(User user) {
		String nom = user.getNom();
		String prenom = user.getPrenom();
		String login = user.getPrenom().charAt(0) + user.getNom();
		String mail = user.getMail();
		try {
			User user2 = this.jdbcTemplate
					.queryForObject(
							"select * from user where login like "
									+ "'"
									+ login
									+ "%'"
									+ " and nom = ? and prenom = ? order by id desc limit 0,1",
							new Object[] { nom, prenom }, new UserMapper());

			if (user2 != null) {
				int max = 2;
				String log = user2.getLogin();
				String sNb = log.charAt(log.length() - 1) + "";

				if (isInteger(sNb)) {
					int nb = Integer.parseInt(sNb);
					max = nb + 1;
				}
				String sMax = String.valueOf(max);
				login += sMax;
			}
		} catch (EmptyResultDataAccessException e) {

		}
		String mdp = login;
		String role = user.getRole();
		int classe = user.getClasse().getId();

		this.jdbcTemplate
				.update("insert into user(nom,prenom,login,mdp,role,idClasse, mail) values(?,?,?,?,?,?,?)",
						new Object[] { nom, prenom, login, mdp, role, classe,
								mail });

		if (role.equals("prof-principal")) {
			User user3 = this.jdbcTemplate
					.queryForObject(
							"select * from user where login = ? and mdp = ? order by id desc limit 0,1",
							new Object[] { login, mdp }, new UserMapper());
			Long idUser = user3.getId();
			System.out.println(idUser);
			this.jdbcTemplate.update(
					"insert into ProfPrincipal(idUser,idClasse) values(?,?)",
					new Object[] { idUser, classe });
		}

	}

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public void updateUser(User user) {
		Long id = user.getId();
		String nom = user.getNom();
		String prenom = user.getPrenom();
		String role = user.getRole();
		int idClasse = user.getClasse().getId();
		String login = user.getLogin();
		String pass = user.getPass();
		String mail = user.getMail();

		if (!role.equals("prof-principal")) {
			this.jdbcTemplate
					.update("delete from ProfPrincipal where idUser = ? and idClasse = ?",
							new Object[] { id, idClasse });
		} else {
			int result = this.jdbcTemplate.queryForInt(
					"select count(idUser) from ProfPrincipal where idUser = ?",
					new Object[] { id });
			if (result == 0) {
				this.jdbcTemplate
						.update("insert into ProfPrincipal(idUser, idClasse) values(?,?)",
								new Object[] { id, idClasse });
			} else {
				this.jdbcTemplate
						.update("update ProfPrincipal set idClasse = ? where idUser = ?",
								new Object[] { idClasse, id });
			}
		}

		this.jdbcTemplate
				.update("update user set nom = ?, prenom = ?, role = ?, idClasse = ?, login = ?, mdp = ?, mail = ? where id = ?",
						new Object[] { nom, prenom, role, idClasse, login,
								pass, mail, id });

	}

	public void deleteUser(User user) {
		Long id = user.getId();

		if (user.getRole().equals("prof-principal")) {
			this.jdbcTemplate
					.update("delete from ProfPrincipal where idUser = ? and idClasse = ?",
							new Object[] { id, user.getClasse().getId() });
		}
		this.jdbcTemplate.update("delete from user where id = ?",
				new Object[] { id });

	}

	public List<AccPersonalise> getAllAP() {
		return this.jdbcTemplate.query("select * from AP", new AccMapper());
	}

	public AccPersonalise getAPById(int id) {
		AccPersonalise acc;
		try {
			acc = this.jdbcTemplate.queryForObject(
					"select * from AP where id = ?", new Object[] { id },
					new AccMapper());
		} catch (EmptyResultDataAccessException e) {
			acc = null;
		}

		return acc;
	}

	public void addAP(AccPersonalise ap) {
		String libelle = ap.getNom();
		int origineEtat = ap.getOrigineEtat();
		int idUser = ap.getIdUser();

		this.jdbcTemplate.update(
				"insert into AP(libelle, origineEtat, idUser) values(?,?,?)",
				new Object[] { libelle, origineEtat, idUser });

	}

	public void upDateAP(AccPersonalise ap) {
		int id = ap.getId();
		String libelle = ap.getNom();
		int origineEtat = ap.getOrigineEtat();

		this.jdbcTemplate
				.update("update AP set libelle = ?, origineEtat = ? where id = ? values(?,?,?)",
						new Object[] { libelle, origineEtat, id });

	}

	public void deleteAP(AccPersonalise ap) {
		int id = ap.getId();
		this.jdbcTemplate.update("delete from AP where id = ?",
				new Object[] { id });
	}

	public List<Classe> getAllClasse() {
		return this.jdbcTemplate.query("select * from Classe order by id",
				new ClasseMapper());
	}

	public Classe getClasseById(int id) {
		Classe classe;
		try {
			classe = this.jdbcTemplate.queryForObject(
					"select * from Classe where id = ?", new Object[] { id },
					new ClasseMapper());
		} catch (EmptyResultDataAccessException e) {
			classe = null;
		}

		return classe;
	}

	public void addClasse(Classe classe) {
		int id = classe.getId();
		String libelle = classe.getNom();
		this.jdbcTemplate.update("insert into classe(id, libelle) values(?,?)",
				new Object[] { id, libelle });

	}

	public void upDateClasse(Classe classe) {
		int id = classe.getId();
		String libelle = classe.getNom();
		this.jdbcTemplate.update("update classe set libelle = ? where id = ?",
				new Object[] { libelle, id });

	}

	public void deleteClasse(Classe classe) {
		int id = classe.getId();
		String libelle = classe.getNom();
		this.jdbcTemplate.update(
				"delete from classe where id = ? and libelle = ?",
				new Object[] { id, libelle });

	}

	public String getCurrentAnneeScolaire() {
		String annee;
		try {
			annee = this.jdbcTemplate.queryForObject(
					"select * from paramAnnee order by id desc limit 0,1",
					new Object[] {}, new StringMapper());
		} catch (EmptyResultDataAccessException e) {
			annee = null;
		}

		return annee;
	}

	public List<String> getAllAnneeScolaire() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByLogin(String login, String pw) {
		User user;
		try {
			user = this.jdbcTemplate.queryForObject(
					"select * from user where login = ? and mdp = ?",
					new Object[] { login, pw }, new UserMapper());

		} catch (EmptyResultDataAccessException e) {
			user = null;
		}
		return user;
	}

	// classe pour passage d'une ligne d'une table à un objet
	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setPrenom(rs.getString("prenom"));
			user.setNom(rs.getString("nom"));
			user.setRole(rs.getString("role"));

			DBManagerGeTAP manager = new DBManagerGeTAP();
			Classe classe = manager.getClasseById(rs.getInt("idClasse"));
			user.setClasse(classe);
			user.setLogin(rs.getString("login"));
			user.setPass(rs.getString("mdp"));
			user.setMail(rs.getString("mail"));
			return user;
		}
	}

	private static final class ClasseMapper implements RowMapper<Classe> {
		public Classe mapRow(ResultSet rs, int rowNum) throws SQLException {
			Classe classe = new Classe();
			classe.setId(rs.getInt("id"));
			classe.setNom(rs.getString("libelle"));
			return classe;
		}
	}

	private static final class AccMapper implements RowMapper<AccPersonalise> {
		public AccPersonalise mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			AccPersonalise acc = new AccPersonalise();
			acc.setId(rs.getInt("id"));
			acc.setNom(rs.getString("libelle"));
			acc.setOrigineEtat(rs.getInt("origineEtat"));
			acc.setIdUser(rs.getInt("idUser"));
			return acc;
		}
	}

	private static final class DemandeMapper implements
			RowMapper<DemandeConsoTempsAccPers> {
		public DemandeConsoTempsAccPers mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			DemandeConsoTempsAccPers dctap = new DemandeConsoTempsAccPers();
			dctap.setId(rs.getLong("id"));
			dctap.setAnneeScolaire(rs.getString("anneeScolaire"));
			dctap.setDateAction(rs.getDate("dateAction"));
			dctap.setMinutes(rs.getInt("dureeAP"));
			dctap.setEtat(rs.getInt("Etat"));

			Long idProf = rs.getLong("idProf");
			Long idEleve = rs.getLong("idEleve");
			int idAP = rs.getInt("idAP");

			DBManagerGeTAP manager = new DBManagerGeTAP();
			User prof = manager.getUserById(idProf);
			User eleve = manager.getUserById(idEleve);
			AccPersonalise ap = manager.getAPById(idAP);

			dctap.setProf(prof);
			dctap.setEleve(eleve);
			dctap.setAccPers(ap);

			return dctap;
		}
	}

	private static final class StringMapper implements RowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			String annee = null;
			annee = rs.getString("anneeScolaire");

			return annee;
		}
	}

	public List<Role> getAllRole() {
		List<Role> listeRoles = new ArrayList<Role>();
		listeRoles.add(new Role(1, "eleve"));
		listeRoles.add(new Role(2, "prof-intervenant"));
		listeRoles.add(new Role(3, "prof-principal"));
		listeRoles.add(new Role(4, "admin"));

		return listeRoles;
	}

	public List<User> search(UserSearchCriteria userSearchCriteria) {
		String query = userSearchCriteria.getQuery();
		return this.jdbcTemplate.query("select * from user where nom like "
				+ "'" + query + "%'", new UserMapper());
	}

	public List<User> searchProf(UserSearchCriteria userSearchCriteria) {
		String query = userSearchCriteria.getQuery();
		return this.jdbcTemplate.query(
				"select * from user where role like 'prof%' and nom like "
						+ "'" + query + "%'", new UserMapper());
	}

	public List<User> searchClasse(UserSearchCriteria userSearchCriteria) {
		String query = userSearchCriteria.getQuery();
		return this.jdbcTemplate.query(
				"select * from user u, Classe c where u.idClasse = c.id and c.libelle = "
						+ "'" + query + "'", new UserMapper());
	}

	public User getUser(Long id) {
		User user;
		try {
			user = this.jdbcTemplate.queryForObject(
					"select * from user where id = ?", new Object[] { id },
					new UserMapper());

		} catch (EmptyResultDataAccessException e) {
			user = null;
		}
		return user;
	}

}