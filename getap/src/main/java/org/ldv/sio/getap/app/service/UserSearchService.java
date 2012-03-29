package org.ldv.sio.getap.app.service;

/**
 * @author qgeffard
 *
 */

import java.util.List;

import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserSearchCriteria;

/**
 * Service to search for Users
 */
public interface UserSearchService {

	/**
	 * Search for Users with the provided criteria
	 * 
	 * @param searchCriteria
	 *            The criteria to narrow the search
	 * @return List of Users
	 */
	public List<User> search(UserSearchCriteria userSearchCriteria);

	/**
	 * Select a specific User by its id, or null if no User is found
	 * 
	 * @param id
	 *            The id of the User to find
	 * @return The User
	 */
	public User getUser(Long id);

	public List<User> getStubUsers();

}
