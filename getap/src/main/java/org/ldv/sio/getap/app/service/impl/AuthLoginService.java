package org.ldv.sio.getap.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserLoginCriteria;
import org.ldv.sio.getap.app.service.IFHauthLoginService;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Stub implementation of a AuthLoginService using a predefined set of Users
 */
@Service("serviceAuth")
public class AuthLoginService implements IFHauthLoginService {

	@Autowired
	@Qualifier("DBServiceManager")
	IFManagerGeTAP managerGeTAP;

	List<User> users;

	public void setManagerEleve(IFManagerGeTAP serviceManager) {
		this.managerGeTAP = serviceManager;
	}

	public AuthLoginService() {

	}

	public User getAuthUser(UserLoginCriteria user) {
		User userdb = managerGeTAP.getUserByLogin(user.getLogin(),
				user.getPassword());
		return userdb;
		//
		// List<User> users = this.getStubUsers();
		// for (User userIndb : users) {
		// if (userIndb.getNom().equals(user.getLogin()))
		// return userIndb;
		// }
		// return null;
	}

	private List<User> getStubUsers() {
		users = new ArrayList<User>();
		users.addAll(managerGeTAP.getAllEleve());
		users.addAll(managerGeTAP.getAllProf());
		return users;
	}

}