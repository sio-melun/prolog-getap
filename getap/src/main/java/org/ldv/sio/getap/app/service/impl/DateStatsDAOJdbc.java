package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.DateStats;
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
			typeApParMois.setNomProf(rs.getString("nomProf"));
			typeApParMois.setPrenomProf(rs.getString("prenomProf"));
			typeApParMois.setDateAP(rs.getString("dateAction"));
			typeApParMois.setTypeAP(rs.getString("typeAP"));
			typeApParMois.setNbParticipant(rs.getInt("nbParticipant"));

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
				.query("SELECT user.nom as nomProf, user.prenom as prenomProf, dctap.dateAction as dateAction, ap.libelle as typeAP,"
						+ "count(dctap.idEleve) as nbParticipant"
						+ " FROM user, dctap, ap"
						+ " WHERE dctap.idProf = user.id"
						+ " AND dctap.idAP = ap.id"
						+ " AND dctap.anneeScolaire = '"
						+ annee
						+ "' AND MONTH(dctap.dateAction) = '"
						+ mois
						+ "' AND (dctap.Etat = 0 OR dctap.Etat = 4 OR dctap.Etat > 1023)"
						+ " GROUP BY dctap.dateAction, ap.libelle, user.nom, user.prenom"
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
}
