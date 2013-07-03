package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.User;

public interface IFSearchUserDAO {

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

}
