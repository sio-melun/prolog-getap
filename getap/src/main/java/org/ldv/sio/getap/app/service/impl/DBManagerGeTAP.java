package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.AnneeScolaire;
import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.ClasseStats;
import org.ldv.sio.getap.app.DateStats;
import org.ldv.sio.getap.app.DemandeValidationConsoTempsAccPers;
import org.ldv.sio.getap.app.Discipline;
import org.ldv.sio.getap.app.LoginInfo;
import org.ldv.sio.getap.app.ProfStats;
import org.ldv.sio.getap.app.Role;
import org.ldv.sio.getap.app.TypeStats;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.ldv.sio.getap.app.service.dao.IFAccPersonnaliseDAO;
import org.ldv.sio.getap.app.service.dao.IFClasseDAO;
import org.ldv.sio.getap.app.service.dao.IFClasseStatsDAO;
import org.ldv.sio.getap.app.service.dao.IFDateStatsDAO;
import org.ldv.sio.getap.app.service.dao.IFDisciplineDAO;
import org.ldv.sio.getap.app.service.dao.IFDvctapDAO;
import org.ldv.sio.getap.app.service.dao.IFLoginInfoDAO;
import org.ldv.sio.getap.app.service.dao.IFProfStatsDAO;
import org.ldv.sio.getap.app.service.dao.IFSearchUserDAO;
import org.ldv.sio.getap.app.service.dao.IFTypeStatsDAO;
import org.ldv.sio.getap.app.service.dao.IFUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("DBServiceManager")
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

	private IFClasseDAO classeDao;

	@Autowired
	public void setClasseDao(IFClasseDAO dao) {
		this.classeDao = dao;
	}

	private IFSearchUserDAO searchUserDao;

	@Autowired
	public void setSearchUserDao(IFSearchUserDAO dao) {
		this.searchUserDao = dao;
	}

	private static final class ArrayStringMapper implements
			RowMapper<HashMap<String, String>> {
		public HashMap<String, String> mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			// TODO : A corriger : List<List<String>> reçu, en List<String>;

			HashMap<String, String> res = new HashMap<String, String>(1);
			res.put(rs.getString("keyName"), rs.getString("keyValue"));
			return res;
			/*
			 * List<String> string = new ArrayList<String>();
			 * 
			 * if (rs.getString("keyName").equals("img")) {
			 * string.add(rs.getString("keyValue")); } if
			 * (rs.getString("keyName").equals("logo")) {
			 * string.add(rs.getString("keyValue")); } if
			 * (rs.getString("keyName").equals("titre")) {
			 * string.add(rs.getString("keyValue")); } if
			 * (rs.getString("keyName").equals("texte")) {
			 * string.add(rs.getString("keyValue")); } if
			 * (rs.getString("keyName").equals("cronMail")) {
			 * string.add(rs.getString("keyValue")); }
			 * 
			 * return string;
			 */
		}
	}

	private static final class StringMapper implements RowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			String annee = null;
			annee = rs.getString("anneeScolaire");

			return annee;
		}
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

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByProfPrinc(
			User profp, String annee) {
		return this.dvctapDao.getAllDVCTAPByProfPrinc(profp, annee);
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

	public User getUserById(Long id) {
		return this.userDao.getUserById(id);
	}

	public User addUser(User user) {
		return this.userDao.addUser(user);
	}

	public void updatePass(User user) {
		this.userDao.updatePass(user);
	}

	public void updateUser(User user) {
		this.userDao.updateUser(user);
	}

	public void updateMailUser(User userModif) {
		this.userDao.updateMailUser(userModif);
	}

	public void updateProfil(User user) {
		this.userDao.updateProfil(user);
	}

	// TODO ne fonctionne pas...
	public void deleteUser(User user) {
		this.userDao.deleteUser(user);

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
	 * public List<AccPersonalise> getAllAP() { User user =
	 * UtilSession.getUserInSession(); Long id = user.getId(); return
	 * this.jdbcTemplate.query(
	 * "select * from ap where (origineEtat = 0 or (origineEtat = 1 and idUser = "
	 * + id + "))", new AccMapper()); }
	 */

	public AccPersonalise getAPById(int id) {
		return this.accPersonnaliseDao.getAPById(id);
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

	public Classe getClasseById(int id) {
		return this.classeDao.getClasseById(id);
	}

	public int countClasses() {
		return this.classeDao.countClasses();
	}

	public List<Classe> getAllClasseByProf(Long id) {
		return this.classeDao.getAllClasseByProf(id);
	}

	public void addClasse(Classe classe) {
		this.classeDao.addClasse(classe);
	}

	public void upDateClasse(Classe classe) {
		this.classeDao.upDateClasse(classe);
	}

	public void deleteClasse(Classe classe) {
		this.classeDao.deleteClasse(classe);

	}

	public User getUserByLogin(String login, String pw) {
		return this.searchUserDao.getUserByLogin(login, pw);
	}

	public List<User> searchEleve(String queryNomEleve) {
		return this.searchUserDao.searchEleve(queryNomEleve);
	}

	public List<User> searchProf(String queryNomProf) {
		return this.searchUserDao.searchProf(queryNomProf);
	}

	public List<User> searchClasse(String queryClasse) {
		return this.searchUserDao.searchClasse(queryClasse);
	}

	// public List<DemandeValidationConsoTempsAccPers> searchDctap(
	// UserSearchCriteria userSearchCriteria) {
	// return this.searchUserDao.searchDctap(userSearchCriteria);
	// }
	//
	// public List<DemandeValidationConsoTempsAccPers> searchDctapClasse(
	// UserSearchCriteria userSearchCriteria) {
	// return this.searchUserDao.searchDctapClasse(userSearchCriteria);
	// }

	public User getUser(Long id) {
		return this.searchUserDao.getUser(id);
	}

	public void logUser(User user) {
		this.searchUserDao.logUser(user);
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

	private IFProfStatsDAO profStatsDao;

	@Autowired
	public void setProfStatsDAO(IFProfStatsDAO dao) {
		this.profStatsDao = dao;
	}

	// public List<Integer> getAllAPForStatsProf() {
	// return this.profStatsDao.getAllAPForStatsProf();
	// }

	public List<Integer> getAllAPForStatsProf(String annee) {
		return this.profStatsDao.getAllAPForStatsProf(annee);
	}

	public List<ProfStats> getAllStatsProfs(String annee) {
		return this.profStatsDao.getAllAPForEachProf(annee);
	}

	public List<AnneeScolaire> getAllYearsForStatsProf() {
		return this.profStatsDao.getAllYearsForStatsProf();
	}

	private IFClasseStatsDAO parClasseStatsDao;

	@Autowired
	public void setParClasseStatsDAO(IFClasseStatsDAO dao) {
		this.parClasseStatsDao = dao;
	}

	public List<ClasseStats> getAllAPByIdClasse(String classe, String annee) {
		return this.parClasseStatsDao.getAllAPByClasse(classe, annee);
	}

	public List<ClasseStats> getAllClassesForStats() {
		return this.parClasseStatsDao.getAllClassesForStats();
	}

	public List<AnneeScolaire> getAllYearsForClasseStats() {
		return this.parClasseStatsDao.getAllYearsForClasseStats();
	}

	private IFTypeStatsDAO typeStatsDao;

	@Autowired
	public void setTypeStatsDAO(IFTypeStatsDAO dao) {
		this.typeStatsDao = dao;
	}

	public List<TypeStats> getAllAPForEachType() {
		return this.typeStatsDao.getAllAPForEachType();
	}

	private IFLoginInfoDAO loginInfoDao;

	@Autowired
	public void setLoginInfoDAO(IFLoginInfoDAO dao) {
		this.loginInfoDao = dao;
	}

	public List<LoginInfo> getLoginInfoById(String id) {
		return this.loginInfoDao.getLoginInfoById(id);
	}

	// TODO trouver un tri pour les méthodes suivantes.

	public List<Role> getAllRole() {
		List<Role> listeRoles = new ArrayList<Role>();
		listeRoles.add(new Role(1, "eleve"));
		listeRoles.add(new Role(2, "prof-intervenant"));
		listeRoles.add(new Role(3, "prof-principal"));
		listeRoles.add(new Role(4, "admin"));

		return listeRoles;
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

	public void updateAccueil(String img, String logo, String titre,
			String texte) {
		this.jdbcTemplate.update(
				"UPDATE parameter SET keyValue = ? WHERE keyName='img';",
				new Object[] { img });
		this.jdbcTemplate.update(
				"UPDATE parameter SET keyValue = ? WHERE keyName='logo';",
				new Object[] { logo });
		this.jdbcTemplate.update(
				"UPDATE parameter SET keyValue = ? WHERE keyName='titre';",
				new Object[] { titre });
		this.jdbcTemplate.update(
				"UPDATE parameter SET keyValue = ? WHERE keyName='texte';",
				new Object[] { texte });
	}

	public List<HashMap<String, String>> getParameter() {
		return this.jdbcTemplate.query(
				"SELECT keyName, keyValue FROM parameter",
				new ArrayStringMapper());
	}

	public int getFirstIdClasse() {
		int firstClasse = this.jdbcTemplate
				.queryForInt("SELECT MIN(classe.id) FROM classe;");
		return firstClasse;
	}

	public List<ClasseStats> getAllProfesseursForOneClasse(String classe,
			String annee) {
		return this.parClasseStatsDao.getAllProfesseursByClasse(classe, annee);
	}

	public List<Integer> getAlldctapByClasse(String idClasse, String annee) {
		return this.parClasseStatsDao.getAlldctapByClasse(idClasse, annee);
	}

	public List<Classe> getAllClasses() {
		return this.classeDao.getAllClasse();
	}

	private IFDateStatsDAO dateDao;

	@Autowired
	public void setDateStatsDAO(IFDateStatsDAO dao) {
		this.dateDao = dao;
	}

	public List<DateStats> getAllDemandeByMois(String mois, String annee) {
		return this.dateDao.getAllDemandeByMois(mois, annee);
	}

	public List<DateStats> getAllMois(String annee) {
		return this.dateDao.getAllMois(annee);
	}

	public int getFirstMois(String annee) {
		return this.dateDao.getFirstMois(annee);
	}

	public void setDateEnvoiMail(int idDvctap) {

		DateFormat dateFormatEnvoi = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatEnvoi.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		Date dateEnvoi = new Date();

		// Nouvelle entrée.
		this.jdbcTemplate.update(
				"insert into sendmail(idDvctap, dateEnvoi) values(?,?)",
				new Object[] { idDvctap, dateFormatEnvoi.format(dateEnvoi) });
	}

	public int getCountDateEnvoiMail(int idDvctap) {
		return this.jdbcTemplate
				.queryForInt("SELECT count(*) FROM sendmail WHERE idDvctap = '"
						+ idDvctap + "'");
	}

	public void sendMail(String destinataire, String sujet, String contenu) {
		String expediteur = "getap@getap.vinci-melun.org";

		System.out
				.println("--------------------------------------------------------\nMail généré !\n--------------------------------------------------------\n");
		System.out.println("Expediteur : " + expediteur);
		System.out.println("Destinataire : " + destinataire);
		System.out.println("Sujet : " + sujet);
		System.out.println("\nContenu : " + contenu);
	}
}
