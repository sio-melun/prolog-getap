package org.ldv.sio.getap.app.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.DemandeValidationConsoTempsAccPers;
import org.ldv.sio.getap.app.Discipline;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.dao.IFSearchUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("searchUserDAO")
public class SearchUserDAOJdbc implements IFSearchUserDAO {

  private static JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  private static final class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      User user = new User();
      user.setId(rs.getLong("id"));
      user.setPrenom(rs.getString("prenom"));
      user.setNom(rs.getString("nom"));
      user.setRole(rs.getString("role"));
      user.setHash(rs.getString("hash"));
      try {
        user.setDureeTotal(rs.getInt("dureeTotal"));
      } catch (SQLException ex) {

      }

      DisciplineDAOJdbc disciplineDao = new DisciplineDAOJdbc();
      ClasseDAOJdbc classeDao = new ClasseDAOJdbc();
      Classe classe = classeDao.getClasseById(rs.getInt("idClasse"));
      Discipline dis = disciplineDao.getDisciplineById(rs
          .getInt("idDiscipline"));
      user.setDiscipline(dis);
      user.setClasse(classe);
      user.setLogin(rs.getString("login"));
      user.setPass(rs.getString("mdp"));
      user.setMail(rs.getString("mail"));
      return user;
    }
  }

  private static final class DemandeMapper implements
      RowMapper<DemandeValidationConsoTempsAccPers> {
    public DemandeValidationConsoTempsAccPers mapRow(ResultSet rs, int rowNum)
        throws SQLException {
      DemandeValidationConsoTempsAccPers dctap = new DemandeValidationConsoTempsAccPers();
      dctap.setId(rs.getLong("id"));
      dctap.setAnneeScolaire(rs.getString("anneeScolaire"));
      dctap.setDateAction(rs.getDate("dateAction"));
      dctap.setMinutes(rs.getInt("dureeAP"));
      dctap.setEtat(rs.getInt("Etat"));

      Long idProf = rs.getLong("idProf");
      Long idEleve = rs.getLong("idEleve");
      int idAP = rs.getInt("idAP");

      UserDAOJdbc userDao = new UserDAOJdbc();
      AccPersonnaliseDAOJdbc accPersonnalise = new AccPersonnaliseDAOJdbc();
      User prof = userDao.getUserById(idProf);
      User eleve = userDao.getUserById(idEleve);
      AccPersonalise ap = accPersonnalise.getAPById(idAP);

      dctap.setProf(prof);
      dctap.setEleve(eleve);
      dctap.setAccPers(ap);

      return dctap;
    }
  }

  public static String getEncodedPassword(String key) {
    byte[] uniqueKey = key.getBytes();
    byte[] hash = null;
    try {
      hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    StringBuffer hashString = new StringBuffer();
    for (int i = 0; i < hash.length; ++i) {
      String hex = Integer.toHexString(hash[i]);
      if (hex.length() == 1) {
        hashString.append('0');
        hashString.append(hex.charAt(hex.length() - 1));
      } else {
        hashString.append(hex.substring(hex.length() - 2));
      }
    }
    return hashString.toString();
  }

  public List<User> searchEleve(String queryNomEleve) {
    return jdbcTemplate.query(
        "select * from user where role = 'eleve' and nom like " + "'"
            + queryNomEleve + "%'", new UserMapper());
  }

  public List<User> searchProf(String queryNomProf) {
    return jdbcTemplate.query(
        "select * from user where role like 'prof%' and nom like " + "'"
            + queryNomProf + "%'", new UserMapper());
  }

  public List<User> searchClasse(String queryClasse) {
    return jdbcTemplate.query(
        "select * from user u, classe c where u.idClasse = c.id and c.libelle = "
            + "'" + queryClasse + "'", new UserMapper());
  }

  // public List<DemandeValidationConsoTempsAccPers> searchDctap(
  // UserSearchCriteria userSearchCriteria) {
  // String query = userSearchCriteria.getQuery();
  // return jdbcTemplate
  // .query("select * from user u, dctap d where (u.id = d.idEleve or u.id = d.idProf) and nom like "
  // + "'" + query + "%'", new DemandeMapper());
  // }
  //
  // public List<DemandeValidationConsoTempsAccPers> searchDctapClasse(
  // UserSearchCriteria userSearchCriteria) {
  // String query = userSearchCriteria.getQuery();
  // return jdbcTemplate
  // .query("SELECT dctap.* FROM classe, user, dctap  where classe.id=user.idClasse and user.id=dctap.idEleve and classe.libelle = "
  // + "'" + query + "'", new DemandeMapper());
  // }

  public User getUser(Long id) {
    User user;
    try {
      user = jdbcTemplate.queryForObject("select * from user where id = ?",
          new Object[] { id }, new UserMapper());

    } catch (EmptyResultDataAccessException e) {
      user = null;
    }
    return user;
  }

  public User getUserByLogin(String login, String pw) {
    User user;
    try {
      String hash = getEncodedPassword(pw);
      user = jdbcTemplate.queryForObject(
          "select * from user where login = ? and hash = ?", new Object[] {
              login, hash }, new UserMapper());

    } catch (EmptyResultDataAccessException e) {
      user = null;
    }
    return user;
  }

  public void logUser(User user) {
    String classe = (user.getClasse() == null) ? "N/A" : user.getClasse()
        .getNom();
    jdbcTemplate
        .update(
            "insert into log(idUser, nom, prenom, role, classe) values (?, ?, ?, ?, ?)",
            new Object[] { user.getId(), user.getNom(), user.getPrenom(),
                user.getRole(), classe });
  }

}
