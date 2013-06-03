package org.ldv.sio.getap.app.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.DemandeValidationConsoTempsAccPers;
import org.ldv.sio.getap.app.Discipline;
import org.ldv.sio.getap.app.Role;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserSearchCriteria;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.ldv.sio.getap.app.service.dao.IFAccPersonnaliseDAO;
import org.ldv.sio.getap.app.service.dao.IFDisciplineDAO;
import org.ldv.sio.getap.app.service.dao.IFDvctapDAO;
import org.ldv.sio.getap.app.service.dao.IFUserDAO;
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

	private IFDisciplineDAO disciplineDao;

	@Autowired
	public void setDisciplineDao(IFDisciplineDAO dao) {
		this.disciplineDao = dao;
	}

	private IFDvctapDAO dvctapDao;

	@Autowired
	public void setDvctapDao(IFDvctapDAO dao) {
		this.dvctapDao = dao;
	}

	private IFUserDAO userDao;

	@Autowired
	public void setUserDao(IFUserDAO dao) {
		this.userDao = dao;
	}

	private IFAccPersonnaliseDAO accPersonnaliseDao;

	@Autowired
	public void setAccPersonnaliseDao(IFAccPersonnaliseDAO dao) {
		this.accPersonnaliseDao = dao;
	}

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByEleve(
			User eleve) {
		return this.dvctapDao.getAllDVCTAPByEleve(eleve);
	}

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByProfInterv(
			User profi) {
		return this.dvctapDao.getAllDVCTAPByProfInterv(profi);
	}

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByProfPrinc(
			User profp) {
		return this.dvctapDao.getAllDVCTAPByProfPrinc(profp);
	}

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByClasse(
			String nomClasse) {
		return this.dvctapDao.getAllDVCTAPByClasse(nomClasse);
	}

	public int getAllDVCTAPByEtat(int etat, Long id) {
		return this.dvctapDao.getAllDVCTAPByEtat(etat, id);
	}

	public int getAllDVCTAPModifByEtat(Long id) {
		return this.dvctapDao.getAllDVCTAPModifByEtat(id);
	}

	public DemandeValidationConsoTempsAccPers getDVCTAPById(Long id) {
		return this.dvctapDao.getDVCTAPById(id);
	}

	public void addDVCTAP(DemandeValidationConsoTempsAccPers dctap) {
		this.dvctapDao.addDVCTAP(dctap);
	}

	public void updateDVCTAP(DemandeValidationConsoTempsAccPers dctap) {
		this.dvctapDao.updateDVCTAP(dctap);
	}

	public void deleteDVCTAP(DemandeValidationConsoTempsAccPers dctap) {
		this.dvctapDao.deleteDVCTAP(dctap);
	}

	public boolean deleteDVCTAPById(Long id) {
		return this.dvctapDao.deleteDVCTAPById(id);
	}

	public List<User> getAllProf() {
		return this.userDao.getAllProf();
	}

	public List<User> getAllProfInter() {
		return this.userDao.getAllProfInter();
	}

	public List<User> getAllProfPrinc() {
		return this.userDao.getAllProfPrinc();
	}

	public List<User> getAllEleve() {
		return this.userDao.getAllEleve();
	}

	public List<User> getAllEleveByClasse() {
		return this.userDao.getAllEleveByClasse();
	}

	public List<User> getAllEleveByPP(User user) {
		return this.userDao.getAllEleveByPP(user);
	}

	// TODO régler le problème
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

	public User addUser(User user) {
		return this.userDao.addUser(user);
	}

	// TODO à retirer plus tard
	public static String getEncodedPassword(String key) {
		byte[] uniqueKey = key.getBytes();
		byte[] hash = null;
		try {
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		StringBuffer hashString = new StringBuffer();
		for (int i = 0; i < hash.length; ++i) {
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			} else {
				hashString.append(hex.substring(hex.length() - 2));
			}
		}
		return hashString.toString();
	}

	public void updatePass(User user) {
		this.userDao.updatePass(user);
	}

	public void updateUser(User user) {
		this.userDao.updateUser(user);
	}

	public void updateProfil(User user) {
		this.userDao.updateProfil(user);
	}

	// TODO delete user ne fonctionne pas...
	public void deleteUser(User user) {
		Long id = user.getId();

		if (user.getRole().equals("prof-principal")) {
			this.jdbcTemplate
					.update("delete from prof_principal where idUser = ? and idClasse = ?",
							new Object[] { id, user.getClasse().getId() });
		}
		this.jdbcTemplate.update("delete from user where id = ?",
				new Object[] { id });

	}

	public List<AccPersonalise> getAllAPForAdmin() {
		return this.accPersonnaliseDao.getAllAPForAdmin();
	}

	public List<AccPersonalise> getAllAPForProf() {
		return this.accPersonnaliseDao.getAllAPForProf();
	}

	public List<AccPersonalise> getAllAPForEleve() {
		return this.accPersonnaliseDao.getAllAPForEleve();
	}

	/*
	 * Ancienne requête, la nouvelle ne marche pas chez moi un modif de la base
	 * de données ??
	 * 
	 * 
	 * 
	 * 
	 * public List<AccPersonalise> getAllAP() { User user =
	 * UtilSession.getUserInSession(); Long id = user.getId(); return
	 * this.jdbcTemplate.query(
	 * "select * from ap where (origineEtat = 0 or (origineEtat = 1 and idUser = "
	 * + id + "))", new AccMapper()); }
	 */

	// TODO ne fonctionne pas en DAO
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
		return this.accPersonnaliseDao.getAPByNom(nom);
	}

	public void addAP(AccPersonalise ap) {
		this.accPersonnaliseDao.addAP(ap);
	}

	public void upDateAP(AccPersonalise ap) {
		this.accPersonnaliseDao.upDateAP(ap);
	}

	public void deleteAP(AccPersonalise ap) {
		this.accPersonnaliseDao.deleteAP(ap);
	}

	public List<AccPersonalise> getApByType() {
		return this.accPersonnaliseDao.getApByType();
	}

	public List<Classe> getAllClasse() {
		return this.jdbcTemplate
				.query("select * from classe where libelle != 'null' order by libelle",
						new ClasseMapper());
	}

	public Classe getClasseById(int id) {
		Classe classe;
		try {
			classe = this.jdbcTemplate.queryForObject(
					"select * from classe where id = ?", new Object[] { id },
					new ClasseMapper());
		} catch (EmptyResultDataAccessException e) {
			classe = null;
		}

		return classe;
	}

	public int countClasses() {
		int count = this.jdbcTemplate.queryForInt(
				"select count(id) from classe order by libelle",
				new Object[] {});
		return count;
	}

	public List<Classe> getAllClasseByProf(Long id) {
		return this.jdbcTemplate.query(
				"select classe.* from user, classe, prof_principal p where user.id = "
						+ id + " and p.idClasse = classe.id and p.idUser = "
						+ id + " order by libelle", new ClasseMapper());
	}

	public void addClasse(Classe classe) {
		String libelle = classe.getNom();
		this.jdbcTemplate.update("insert into classe(libelle) values(?)",
				new Object[] { libelle });

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
					"select * from param_annee order by id desc limit 0,1",
					new Object[] {}, new StringMapper());
		} catch (EmptyResultDataAccessException e) {
			annee = null;
		}

		return annee;
	}

	public List<String> getAllAnneeScolaire() {
		return null;
	}

	public User getUserByLogin(String login, String pw) {
		User user;
		try {
			String hash = getEncodedPassword(pw);
			user = this.jdbcTemplate.queryForObject(
					"select * from user where login = ? and hash = ?",
					new Object[] { login, hash }, new UserMapper());

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
			user.setHash(rs.getString("hash"));
			try {
				user.setDureeTotal(rs.getInt("dureeTotal"));
			} catch (SQLException ex) {

			}

			DBManagerGeTAP manager = new DBManagerGeTAP();
			DisciplineDAOJdbc disciplineDao = new DisciplineDAOJdbc();
			Classe classe = manager.getClasseById(rs.getInt("idClasse"));
			Discipline dis = disciplineDao.getDisciplineById(rs
					.getInt("idDiscipline"));
			user.setDiscipline(dis);
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

	private static final class ArrayStringMapper implements
			RowMapper<ArrayList<String>> {
		public ArrayList<String> mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			ArrayList<String> string = new ArrayList<String>();
			string.add(rs.getString("img"));
			string.add(rs.getString("logo"));
			string.add(rs.getString("titre"));
			string.add(rs.getString("texte"));
			return string;
		}
	}

	private static final class DisciplineMapper implements
			RowMapper<Discipline> {
		public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
			Discipline dis = new Discipline();
			dis.setId(rs.getInt("id"));
			dis.setNom(rs.getString("libelle"));
			return dis;
		}
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

	public List<User> searchEleve(UserSearchCriteria userSearchCriteria) {
		String query = userSearchCriteria.getQuery();
		return this.jdbcTemplate.query(
				"select * from user where role = 'eleve' and nom like " + "'"
						+ query + "%'", new UserMapper());
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
				"select * from user u, classe c where u.idClasse = c.id and c.libelle = "
						+ "'" + query + "'", new UserMapper());
	}

	public List<DemandeValidationConsoTempsAccPers> searchDctap(
			UserSearchCriteria userSearchCriteria) {
		String query = userSearchCriteria.getQuery();
		return this.jdbcTemplate
				.query("select * from user u, dctap d where (u.id = d.idEleve or u.id = d.idProf) and nom like "
						+ "'" + query + "%'", new DemandeMapper());
	}

	public List<DemandeValidationConsoTempsAccPers> searchDctapClasse(
			UserSearchCriteria userSearchCriteria) {
		String query = userSearchCriteria.getQuery();
		return this.jdbcTemplate
				.query("SELECT dctap.* FROM classe, user, dctap  where classe.id=user.idClasse and user.id=dctap.idEleve and classe.libelle = "
						+ "'" + query + "'", new DemandeMapper());
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

	public List<Discipline> getAllDiscipline() {
		return this.disciplineDao.getAllDiscipline();
	}

	public Discipline getDisciplineById(int id) {
		return this.disciplineDao.getDisciplineById(id);
	}

	public void addDiscipline(Discipline dis) {
		this.disciplineDao.addDiscipline(dis);
	}

	public void upDateDiscipline(Discipline dis) {
		this.disciplineDao.upDateDiscipline(dis);
	}

	public void deleteDiscipline(Discipline dis) {
		this.disciplineDao.deleteDiscipline(dis);
	}

	public void addAccueil(String img, String logo, String titre, String texte) {
		int count = this.jdbcTemplate.queryForInt(
				"select count(img) from param_accueil", new Object[] {});

		if (count == 0) {
			this.jdbcTemplate
					.update("insert into param_accueil(img, logo, titre, texte) values(?,?,?,?)",
							new Object[] { img, logo, titre, texte });
		} else {
			this.jdbcTemplate
					.update("update param_accueil set img = ?, logo = ?, titre = ?, texte = ?",
							new Object[] { img, logo, titre, texte });
		}
	}

	public List<String> getInfoAccueil() {
		List<String> infos;
		try {
			infos = this.jdbcTemplate.queryForObject(
					"select * from param_accueil", new Object[] {},
					new ArrayStringMapper());

		} catch (EmptyResultDataAccessException e) {
			infos = null;
		}
		return infos;
	}

	public void logUser(User user) {
		String classe = (user.getClasse() == null) ? "N/A" : user.getClasse()
				.getNom();
		this.jdbcTemplate
				.update("insert into log(nom, prenom, role, classe) values (?, ?, ?, ?)",
						new Object[] { user.getNom(), user.getPrenom(),
								user.getRole(), classe });
	}

}
