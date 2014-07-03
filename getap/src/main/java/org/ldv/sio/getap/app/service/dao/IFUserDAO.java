package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.User;

public interface IFUserDAO {

	public List<User> getAllProf();

	public List<User> getAllProfInter();

	public List<User> getAllProfPrinc();

	public List<User> getAllEleve();

	public List<User> getAllEleveByClasse();

	public List<User> getAllEleveByPP(User user);

	public List<User> getAllEleveByAAP(String annee, String dateAction,
			int idProf, String ap);

	public User getUserById(Long id);

	public User addUser(User user);

	public void updatePass(User user);

	public void updateUser(User user);

	public void updateProfil(User user);

	public void deleteUser(User user);

	public void updateMailUser(User userModif);
}
