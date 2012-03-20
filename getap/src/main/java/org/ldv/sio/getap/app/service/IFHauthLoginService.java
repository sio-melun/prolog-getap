package org.ldv.sio.getap.app.service;

import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserLoginCriteria;

/**
 * Service pour authentifier un user
 */
public interface IFHauthLoginService {

	/**
	 * Search for User with the criteria
	 * 
	 * @param user
	 *          the user to authenticate
	 * @return User if is authenticate or null if not
	 */
	public User getAuthUser(UserLoginCriteria user);

}
