package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.DemandeConsoTempsAccPers;
import org.ldv.sio.getap.app.Role;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserSearchCriteria;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("DBServiceMangager")
public class DBManagerGeTAP implements IFManagerGeTAP {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<DemandeConsoTempsAccPers> getAllDCTAPByEleve(User eleve) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DemandeConsoTempsAccPers> getAllDCTAPByProfInterv(User profi) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DemandeConsoTempsAccPers> getAllDCTAPByProfPrinc(User profp) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DemandeConsoTempsAccPers> getAllDCTAPByClasse(String nomClasse) {
		// TODO Auto-generated method stub
		return null;
	}

	public DemandeConsoTempsAccPers getDCTAPById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addDCTAP(DemandeConsoTempsAccPers dctap) {
		// TODO Auto-generated method stub

	}

	public void updateDCTAP(DemandeConsoTempsAccPers dctap) {
		// TODO Auto-generated method stub

	}

	public void deleteDCTAP(DemandeConsoTempsAccPers dctap) {
		// TODO Auto-generated method stub

	}

	public boolean deleteDCTAPById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<User> getAllProf() {
		return this.jdbcTemplate.query(
				"select * from user where role like 'prof%'", new UserMapper());

	}

	public List<User> getAllProfInter() {
		return this.jdbcTemplate.query(
				"select * from user where role = 'prof-intervenant'",
				new UserMapper());
	}

	public List<User> getAllProfPrinc() {
		return this.jdbcTemplate.query(
				"select * from user where role = 'prof-principal'",
				new UserMapper());
	}

	public List<User> getAllEleve() {
		return this.jdbcTemplate.query(
				"select * from user where role = 'eleve'", new UserMapper());
	}

	public User getUserById(Long id) {
		User user;
		try {
			user = this.jdbcTemplate.queryForObject(
					"select * from user where id = ?", new Object[] { id },
					new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			user = null;
		}

		return user;
	}

	public void addUser(User user) {
		String nom = user.getNom();
		String prenom = user.getPrenom();
		String login = user.getPrenom().charAt(0) + user.getNom();
		String mdp = login;
		String role = user.getRole();
		int classe = user.getClasse().getId();

		this.jdbcTemplate
				.queryForObject(
						"insert into user(nom,prenom,login,mdp,role,idClasse) values(?,?,?,?,?,?)",
						new Object[] { nom, prenom, login, mdp, role, classe },
						new UserMapper());

	}

	public void updateUser(User user) {
		Long id = user.getId();
		String nom = user.getNom();
		String prenom = user.getPrenom();
		String role = user.getRole();

		this.jdbcTemplate.update(
				"update user set nom = ?, prenom = ?, role = ? where id = ?",
				new Object[] { nom, prenom, role, id });

	}

	public void deleteUser(User user) {
		Long id = user.getId();
		this.jdbcTemplate.update("delete from user where id = ?",
				new Object[] { id });

	}

	public List<AccPersonalise> getAllAP() {
		// TODO Auto-generated method stub
		return null;
	}

	public AccPersonalise getAPById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addAP(AccPersonalise ap) {
		// TODO Auto-generated method stub

	}

	public void upDateAP(AccPersonalise ap) {
		// TODO Auto-generated method stub

	}

	public void deleteAP(AccPersonalise ap) {
		// TODO Auto-generated method stub

	}

	public List<Classe> getAllClasse() {
		return this.jdbcTemplate.query("select * from Classe order by id",
				new ClasseMapper());
	}

	public Classe getClasseById(int id) {
		Classe classe;
		try {
			classe = this.jdbcTemplate.queryForObject(
					"select * from Classe where id = ?", new Object[] { id },
					new ClasseMapper());
		} catch (EmptyResultDataAccessException e) {
			classe = null;
		}

		return classe;
	}

	public void addClasse(Classe classe) {
		int id = classe.getId();
		String libelle = classe.getNom();
		this.jdbcTemplate.queryForObject(
				"insert into classe(id, libelle) values(?,?)", new Object[] {
						id, libelle }, new ClasseMapper());

	}

	public void upDateClasse(Classe classe) {
		// TODO Auto-generated method stub

	}

	public void deleteClasse(Classe classe) {
		int id = classe.getId();
		String libelle = classe.getNom();
		this.jdbcTemplate.queryForObject(
				"delete from classe where id = ? and libelle = ?",
				new Object[] { id, libelle }, new UserMapper());

	}

	public String getCurrentAnneeScolaire() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getAllAnneeScolaire() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByLogin(String login, String pw) {
		User user;
		try {
			user = this.jdbcTemplate.queryForObject(
					"select * from user where login = ? and mdp = ?",
					new Object[] { login, pw }, new UserMapper());

		} catch (EmptyResultDataAccessException e) {
			user = null;
		}
		return user;
	}

	// classe pour passage d'une ligne d'une table à un objet
	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setPrenom(rs.getString("prenom"));
			user.setNom(rs.getString("nom"));
			user.setRole(rs.getString("role"));
			// TODO relation avec Classe
			Classe classe = new Classe(1, "null");
			// if rs.getString("idClasse")
			// classe.setId(Integer.parseInt(rs.getString("idClasse")));
			// classe.setNom("bidon");
			user.setClasse(classe);
			return user;
		}
	}

	private static final class ClasseMapper implements RowMapper<Classe> {
		public Classe mapRow(ResultSet rs, int rowNum) throws SQLException {
			Classe classe = new Classe();
			classe.setId(rs.getInt("id"));
			classe.setNom(rs.getString("libelle"));
			return classe;
		}
	}

	public List<Role> getAllRole() {
		List<Role> listeRoles = new ArrayList<Role>();
		listeRoles.add(new Role(1, "eleve"));
		listeRoles.add(new Role(2, "prof-intervenant"));
		listeRoles.add(new Role(3, "prof-principal"));
		listeRoles.add(new Role(4, "admin"));

		return listeRoles;
	}

	public List<User> search(UserSearchCriteria userSearchCriteria) {
		String query = userSearchCriteria.getQuery();
		return this.jdbcTemplate.query("select * from user where nom like "
				+ "'" + query + "%' or prenom like " + "'" + query + "%'",
				new UserMapper());
	}

	public User getUser(Long id) {
		User user;
		try {
			user = this.jdbcTemplate.queryForObject(
					"select * from user where id = ?", new Object[] { id },
					new UserMapper());

		} catch (EmptyResultDataAccessException e) {
			user = null;
		}
		return user;
	}

}