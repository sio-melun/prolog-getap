package org.ldv.sio.getap.app.service;

import java.util.HashMap;
import java.util.List;

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

/**
 * Contrat de services pour les cas d'utilisation
 * 
 * @author VINSII - projet GeTAP - SIO MELUN
 * 
 */
public interface IFManagerGeTAP {

	// CRUD DVCTAP
	/**
	 * 
	 * @param eleve
	 * @return
	 */
	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByEleve(
			User eleve);

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByProfInterv(
			User profi);

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByProfPrinc(
			User profp);

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByProfPrinc(
			User profp, String annee);

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByClasse(
			String classe);

	public int getAllDVCTAPByEtat(int etat, Long id);

	public int getAllDVCTAPModifByEtat(Long id);

	public DemandeValidationConsoTempsAccPers getDVCTAPById(Long id);

	public void addDVCTAP(DemandeValidationConsoTempsAccPers dctap);

	public void updateDVCTAP(DemandeValidationConsoTempsAccPers dctap);

	public void deleteDVCTAP(DemandeValidationConsoTempsAccPers dctap);

	public boolean deleteDVCTAPById(Long id);

	// CRUD User
	public List<User> getAllProf();

	public List<User> getAllProfInter();

	public List<User> getAllProfPrinc();

	public List<User> getAllEleve();

	public List<User> getAllEleveByClasse();

	public List<User> getAllEleveByPP(User user);

	public User getUserById(Long id);

	public User addUser(User user);

	public void updatePass(User user);

	public void updateUser(User user);

	public void updateProfil(User user);

	public void deleteUser(User user);

	// CRUD de AP
	public List<AccPersonalise> getAllAPForEleve();

	public List<AccPersonalise> getAllAPForProf();

	public List<ProfStats> getAllStatsProfs(String annee);

	public List<ClasseStats> getAllAPByIdClasse(String idClasse, String annee);

	public List<ClasseStats> getAllProfesseursForOneClasse(String idClasse,
			String annee);

	public List<Integer> getAlldctapByClasse(String idClasse, String annee);

	public List<TypeStats> getAllAPForEachType();

	public List<AccPersonalise> getAllAPForAdmin();

	public int getFirstIdClasse();

	public List<DateStats> getAllDemandeByMois(String mois, String annee);

	public List<DateStats> getAllMois(String annee);

	public List<User> getAllEleveByAAP(String annee, String dateAction,
			int idProf, String ap);

	public int getFirstMois(String annee);

	public AccPersonalise getAPById(int id);

	public AccPersonalise getAPByNom(String nom);

	public void addAP(AccPersonalise ap);

	public void upDateAP(AccPersonalise ap);

	public void deleteAP(AccPersonalise ap);

	public List<AccPersonalise> getApByType();

	// CRUD Disciplines
	public List<Discipline> getAllDiscipline();

	public Discipline getDisciplineById(int id);

	public void addDiscipline(Discipline dis);

	public void upDateDiscipline(Discipline dis);

	public void deleteDiscipline(Discipline dis);

	// CRUD de classe
	public List<Classe> getAllClasses();

	public Classe getClasseById(int id);

	public void addClasse(Classe classe);

	public void upDateClasse(Classe classe);

	public void deleteClasse(Classe classe);

	public int countClasses();

	public List<Classe> getAllClasseByProf(Long id);

	// CRUD des roles
	public List<Role> getAllRole();

	// Opération sur annee scolaire (mise à jour lors d'une importation)
	public String getCurrentAnneeScolaire();

	public List<String> getAllAnneeScolaire();

	// Opération sur la personnalisation de l'accueil
	public void updateAccueil(String img, String logo, String titre,
			String texte);

	public List<HashMap<String, String>> getParameter();

	// Opération Search User
	public List<User> searchEleve(String queryNomEleve);

	public List<User> searchProf(String queryNomProf);

	public List<User> searchClasse(String queryClasse);

	// public List<DemandeValidationConsoTempsAccPers> searchDctap(
	// UserSearchCriteria userSearchCriteria);
	//
	// public List<DemandeValidationConsoTempsAccPers> searchDctapClasse(
	// UserSearchCriteria userSearchCriteria);

	public User getUser(Long id);

	/**
	 * 
	 * @param login
	 *            login to test
	 * @param pw
	 *            password to test
	 * @return User havin login and pw or null
	 */
	public User getUserByLogin(String login, String pw);

	/**
	 * inscrit l'utilisateur dans le système de log
	 * 
	 * @param user
	 *            utilisateur concerné
	 */
	public void logUser(User user);

	public List<LoginInfo> getLoginInfoById(String id);

	public List<AnneeScolaire> getAllYearsForStatsProf();

	public List<AnneeScolaire> getAllYearsForClasseStats();

	public List<ClasseStats> getAllClassesForStats();

	public List<Integer> getAllAPForStatsProf(String annee);

	public void updateMailUser(User userModif);

	public void setDateEnvoiMail(int id);

	public int getCountDateEnvoiMail(int idDvctap);

	public void sendMail(String destinataire, String sujet, String contenu);

}
