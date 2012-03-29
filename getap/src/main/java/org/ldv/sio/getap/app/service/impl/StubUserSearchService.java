package org.ldv.sio.getap.app.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserSearchCriteria;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.ldv.sio.getap.app.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mockServiceSearch")
public class StubUserSearchService implements UserSearchService {
	@Autowired
	private IFManagerGeTAP tools;

	public List<User> search(UserSearchCriteria userSearchCriteria) {
		List<User> users = this.getStubUsers();
		List<User> foundUsers = new ArrayList<User>();

		User user = null;

		Iterator<User> userIt = users.iterator();

		while (userIt.hasNext()) {
			user = userIt.next();
			if (user.getNom().contains(userSearchCriteria.getQuery())) {
				foundUsers.add(user);
			}
		}

		return foundUsers;
	}

	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getStubUsers() {
		// TODO Faire la fonction qui retourne tous les users

		List<User> users = new ArrayList<User>();
		users.addAll(tools.getAllProf());
		users.addAll(tools.getAllEleve());

		return users;

	}

}
