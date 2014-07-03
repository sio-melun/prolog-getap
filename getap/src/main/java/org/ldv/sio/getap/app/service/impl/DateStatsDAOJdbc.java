package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.DateStats;
import org.ldv.sio.getap.app.Discipline;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.dao.IFDateStatsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("dateStatsDao")
public class DateStatsDAOJdbc implements IFDateStatsDAO {

	private static JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class AccDateStatsMapper implements
			RowMapper<DateStats> {
		public DateStats mapRow(ResultSet rs, int rowNum) throws SQLException {
			DateStats typeApParMois = new DateStats();

			String annee = org.ldv.sio.getap.utils.UtilSession
					.getAnneeScolaireInSession();

			typeApParMois.setNomProf(rs.getString("nomProf"));
			typeApParMois.setPrenomProf(rs.getString("prenomProf"));
			typeApParMois.setDateAP(rs.getString("dateAction"));
			typeApParMois.setTypeAP(rs.getString("typeAP"));
			typeApParMois.setNbSolicitant(rs.getInt("nbSolicitant"));
			typeApParMois.setListEleve(getAllEleveByAAP(annee,
					rs.getString("dateAction"), rs.getInt("idProf"),
					rs.getString("typeAP")));

			return typeApParMois;

		}
	}

	private static final class MoisMapper implements RowMapper<DateStats> {
		public DateStats mapRow(ResultSet rs, int rowNum) throws SQLException {
			DateStats mois = new DateStats();
			mois.setNumMois(rs.getString("mois"));
			mois.setMois(rs.getString("mois"));

			return mois;

		}
	}

	public List<DateStats> getAllDemandeByMois(String mois, String annee) {

		return this.jdbcTemplate
				.query("SELECT dctap.idProf as idProf, user.nom as nomProf, user.prenom as prenomProf, dctap.dateAction as dateAction, ap.libelle as typeAP,"
						+ "count(dctap.idEleve) as nbSolicitant"
						+ " FROM user, dctap, ap"
						+ " WHERE dctap.idProf = user.id"
						+ " AND dctap.idAP = ap.id"
						+ " AND dctap.anneeScolaire = '"
						+ annee
						+ "' AND MONTH(dctap.dateAction) = '"
						+ mois
						+ "' GROUP BY dctap.dateAction, typeAP, dctap.idProf"
						+ " ORDER BY dctap.dateAction",
						new AccDateStatsMapper());

	}

	public List<DateStats> getAllMois(String annee) {

		return this.jdbcTemplate.query(
				"SELECT  DISTINCT MONTH(dctap.dateAction) as mois"
						+ " FROM dctap" + " WHERE dctap.anneeScolaire ='"
						+ annee + "' ORDER BY mois;", new MoisMapper());

	}

	public int getFirstMois(String annee) {

		return this.jdbcTemplate
				.queryForInt("SELECT MIN(MONTH(dctap.dateAction)) as firstMois"
						+ " FROM dctap" + " WHERE dctap.anneeScolaire = '"
						+ annee + "';");

	}

	public static List<User> getAllEleveByAAP(String annee, String dateAction,
			int idProf, String ap) {
		return jdbcTemplate.query("SELECT user.* " + "FROM user, dctap, ap "
				+ "WHERE dctap.idEleve = user.id AND dctap.idAP = ap.id "
				+ "AND dctap.anneeScolaire = '" + annee + "' "
				+ "AND dctap.dateAction = '" + dateAction + "' "
				+ "AND ap.libelle = '" + ap + "' " + "AND dctap.idProf = "
				+ idProf + " " + "GROUP BY user.id "
				+ "ORDER BY dctap.dateAction", new UserMapper());
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

}
