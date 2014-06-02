package org.ldv.sio.getap.app.service.impl;

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
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.ldv.sio.getap.app.service.dao.IFAccPersonnaliseDAO;
import org.ldv.sio.getap.app.service.dao.IFClasseDAO;
import org.ldv.sio.getap.app.service.dao.IFDisciplineDAO;
import org.ldv.sio.getap.app.service.dao.IFDvctapDAO;
import org.ldv.sio.getap.app.service.dao.IFSearchUserDAO;
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

	public List<Integer> getAllAPForStatsProf() {
		return this.accPersonnaliseDao.getAllAPForStatsProf();
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

	public List<Classe> getAllClasse() {
		return this.classeDao.getAllClasse();
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

}
