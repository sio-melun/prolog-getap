package org.ldv.sio.getap.app.service;

import java.util.List;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.DemandeConsoTempsAccPers;
import org.ldv.sio.getap.app.Role;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserSearchCriteria;

/**
 * Contrat de services pour les cas d'utilisation
 * 
 * @author VINSII - projet GeTAP - SIO MELUN
 * 
 */
public interface IFManagerGeTAP {

	// CRUD DCTAP
	public List<DemandeConsoTempsAccPers> getAllDCTAPByEleve(User eleve);

	public List<DemandeConsoTempsAccPers> getAllDCTAPByProfInterv(User profi);

	public List<DemandeConsoTempsAccPers> getAllDCTAPByProfPrinc(User profp);

	public List<DemandeConsoTempsAccPers> getAllDCTAPByClasse(String classe);

	public int getAllDCTAPByEtat(int etat, Long id);

	public DemandeConsoTempsAccPers getDCTAPById(Long id);

	public void addDCTAP(DemandeConsoTempsAccPers dctap);

	public void updateDCTAP(DemandeConsoTempsAccPers dctap);

	public void deleteDCTAP(DemandeConsoTempsAccPers dctap);

	public boolean deleteDCTAPById(Long id);

	// CRUD User
	public List<User> getAllProf();

	public List<User> getAllProfInter();

	public List<User> getAllProfPrinc();

	public List<User> getAllEleve();

	public User getUserById(Long id);

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	// CRUD de AP
	public List<AccPersonalise> getAllAP();

	public AccPersonalise getAPById(int id);

	public AccPersonalise getAPByNom(String nom);

	public void addAP(AccPersonalise ap);

	public void upDateAP(AccPersonalise ap);

	public void deleteAP(AccPersonalise ap);

	// CRUD de classe
	public List<Classe> getAllClasse();

	public Classe getClasseById(int id);

	public void addClasse(Classe classe);

	public void upDateClasse(Classe classe);

	public void deleteClasse(Classe classe);

	// CRUD des roles
	public List<Role> getAllRole();

	// Opération sur annee scolaire (mise à jour lors d'une importation)
	public String getCurrentAnneeScolaire();

	public List<String> getAllAnneeScolaire();

	// Opération Search User
	public List<User> search(UserSearchCriteria userSearchCriteria);

	public List<User> searchProf(UserSearchCriteria userSearchCriteria);

	public List<User> searchClasse(UserSearchCriteria userSearchCriteria);

	public List<DemandeConsoTempsAccPers> searchDctap(
			UserSearchCriteria userSearchCriteria);

	public List<DemandeConsoTempsAccPers> searchDctapClasse(
			UserSearchCriteria userSearchCriteria);

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

}
