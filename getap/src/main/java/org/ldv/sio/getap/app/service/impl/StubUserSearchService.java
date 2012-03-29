package org.ldv.sio.getap.app.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserSearchCriteria;
import org.ldv.sio.getap.app.service.UserSearchService;
import org.springframework.stereotype.Service;

@Service("mockServiceSearch")
public class StubUserSearchService implements UserSearchService {

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

		Classe noclasse = null;
		Classe[] desClasses = { new Classe(1, "SIO12"), new Classe(2, "SIO11"),
				new Classe(3, "ES"), new Classe(4, "STI") };

		List<User> users = new ArrayList<User>();

		users.add(new User(1L, "Jean", "Ritchie", noclasse, "admin"));
		users.add(new User(2L, "Jean David", "Ichbiah", noclasse,
				"prof-intervenant"));
		users.add(new User(3L, "Guido ", "van Rossum", noclasse,
				"prof-intervenant"));
		users.add(new User(4L, "Gérard", "Berry", noclasse, "prof-intervenant"));
		users.add(new User(5L, "Ada", "Lovelace", noclasse, "prof-principal"));
		users.add(new User(6L, "Yukihiro", "Matsumoto", noclasse,
				"prof-intervenant"));

		users.add(new User(40L, "James", "Gosling", desClasses[0], "eleve"));
		users.add(new User(41L, "Bertrand", "Meyer", desClasses[0], "eleve"));
		users.add(new User(42L, "Niklaus", "Wirth", desClasses[1], "eleve"));
		users.add(new User(43L, "Rasmus", "Lerdorf", desClasses[1], "eleve"));

		return users;

	}
}
