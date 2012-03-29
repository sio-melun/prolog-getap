package org.ldv.sio.getap.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserLoginCriteria;
import org.ldv.sio.getap.app.service.IFHauthLoginService;
import org.springframework.stereotype.Service;

/**
 * Stub implementation of a AuthLoginService using a predefined set of Users
 */
@Service("serviceAuth")
public class StubAuthLoginService implements IFHauthLoginService {

	StubManagerGeTAP stubManagerGeTAP;
	List<User> users;

	public StubAuthLoginService() {
		stubManagerGeTAP = new StubManagerGeTAP();
	}

	public User getAuthUser(UserLoginCriteria user) {
		List<User> users = this.getStubUsers();
		for (User userIndb : users) {
			if (userIndb.getNom().equals(user.getLogin()))
				return userIndb;
		}
		return null;
	}

	private List<User> getStubUsers() {
		if (users != null)
			return users;

		users = new ArrayList<User>();
		users.addAll(stubManagerGeTAP.getListeProfs());
		users.addAll(stubManagerGeTAP.getListeEleves());
		users.add(new User(1L, "Dennis", "Ritchie", null, "admin"));
		return users;
	}

}