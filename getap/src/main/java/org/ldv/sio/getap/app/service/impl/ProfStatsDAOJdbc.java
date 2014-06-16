package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.AnneeScolaire;
import org.ldv.sio.getap.app.ProfStats;
import org.ldv.sio.getap.app.service.dao.IFProfStatsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("profStatsDao")
public class ProfStatsDAOJdbc implements IFProfStatsDAO {
	private static JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class AccProfStatsMapper implements
			RowMapper<ProfStats> {
		public ProfStats mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProfStats profStats = new ProfStats();
			profStats.setId(rs.getInt("id"));
			profStats.setNom(rs.getString("nomProf"));
			profStats.setPrenom(rs.getString("prenomProf"));
			profStats.setCountap(rs.getInt("countap"));
			profStats.setDctapvalide(rs.getInt("dctapvalide"));
			profStats.setDctapattente(rs.getInt("dctapattente"));
			profStats.setDctaprefuse(rs.getInt("dctaprefuse"));
			return profStats;
		}
	}

	private static final class YearsMapper implements RowMapper<AnneeScolaire> {
		public AnneeScolaire mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			AnneeScolaire anneeScolaire = new AnneeScolaire();
			anneeScolaire.setAnneescolaire(rs.getString("anneeScolaire"));
			return anneeScolaire;
		}
	}

	public List<ProfStats> getAllAPForEachProf() {
		/*
		 * SELECT user.nom AS nomProf, user.prenom AS prenomProf,
		 * 
		 * (SELECT count( dctap.id ) FROM dctap WHERE dctap.Etat =1 AND idProf =
		 * user.id OR dctap.Etat =32 AND idProf = user.id ) AS dctapval,
		 * 
		 * (SELECT count( dctap.id ) FROM dctap WHERE dctap.Etat =2 AND idProf =
		 * user.id OR dctap.Etat =8 AND idProf = user.id OR dctap.Etat =64 AND
		 * idProf = user.id ) AS dctapref,
		 * 
		 * (SELECT count( dctap.id ) FROM dctap WHERE dctap.Etat =0 AND idProf =
		 * user.id OR dctap.Etat =4 AND idProf = user.id OR dctap.Etat >1023 AND
		 * idProf = user.id ) AS dctapatt,
		 * 
		 * count( dctap.id ) AS countap
		 * 
		 * FROM user, dctap
		 * 
		 * WHERE dctap.idProf = user.id
		 * 
		 * GROUP BY user.id
		 * 
		 * ORDER BY dctapval DESC , user.nom
		 */
		return this.jdbcTemplate
				.query("Select user.id, user.nom as nomProf, user.prenom as prenomProf, (SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 1 OR dctap.Etat = 32) AND idProf = user.id AND anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)) AS dctapvalide, (SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 2 OR dctap.Etat = 8 OR dctap.Etat = 64) AND idProf = user.id AND anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)) AS dctaprefuse, (SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 0 OR dctap.Etat = 4 OR dctap.Etat > 1023) AND idProf = user.id AND anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)) AS dctapattente, (SELECT count(dctap.id) FROM dctap WHERE idProf = user.id AND anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)) AS countap FROM user, dctap WHERE dctap.idProf = user.id GROUP BY user.id ORDER BY dctapvalide DESC, user.nom",
						new AccProfStatsMapper());
	}

	public List<ProfStats> getAllAPForEachProf(String annee) {
		/*
		 * SELECT user.nom AS nomProf, user.prenom AS prenomProf,
		 * 
		 * (SELECT count( dctap.id ) FROM dctap WHERE dctap.Etat =1 AND idProf =
		 * user.id OR dctap.Etat =32 AND idProf = user.id ) AS dctapval,
		 * 
		 * (SELECT count( dctap.id ) FROM dctap WHERE dctap.Etat =2 AND idProf =
		 * user.id OR dctap.Etat =8 AND idProf = user.id OR dctap.Etat =64 AND
		 * idProf = user.id ) AS dctapref,
		 * 
		 * (SELECT count( dctap.id ) FROM dctap WHERE dctap.Etat =0 AND idProf =
		 * user.id OR dctap.Etat =4 AND idProf = user.id OR dctap.Etat >1023 AND
		 * idProf = user.id ) AS dctapatt,
		 * 
		 * count( dctap.id ) AS countap
		 * 
		 * FROM user, dctap
		 * 
		 * WHERE dctap.idProf = user.id
		 * 
		 * GROUP BY user.id
		 * 
		 * ORDER BY dctapval DESC , user.nom
		 */

		return this.jdbcTemplate
				.query("Select user.id, user.nom as nomProf, user.prenom as prenomProf, (SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 1 OR dctap.Etat = 32) AND idProf = user.id AND anneeScolaire = '"
						+ annee
						+ "') AS dctapvalide, (SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 2 OR dctap.Etat = 8 OR dctap.Etat = 64) AND idProf = user.id AND anneeScolaire = '"
						+ annee
						+ "') AS dctaprefuse, (SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 0 OR dctap.Etat = 4 OR dctap.Etat > 1023) AND idProf = user.id AND anneeScolaire = '"
						+ annee
						+ "') AS dctapattente, (SELECT count(dctap.id) FROM dctap WHERE idProf = user.id AND anneeScolaire = '"
						+ annee
						+ "') AS countap FROM user, dctap WHERE dctap.idProf = user.id GROUP BY user.id ORDER BY dctapvalide DESC, user.nom",
						new AccProfStatsMapper());
	}

	public List<AnneeScolaire> getAllYearsForStatsProf() {
		/*
		 * La fonction peut être externalisée pour d'autres usages. Cependant,
		 * ce n'est pas nécessaire pour le moment.
		 */
		return this.jdbcTemplate.query(
				"SELECT DISTINCT anneeScolaire FROM dctap", new YearsMapper());
	}

	public List<Integer> getAllAPForStatsProf() {
		List<Integer> StatsProf = new ArrayList<Integer>();
		StatsProf
				.add(0,
						this.jdbcTemplate
								.queryForInt("select count(*) FROM dctap WHERE anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)"));
		StatsProf
				.add(1,
						this.jdbcTemplate
								.queryForInt("select count(*) FROM dctap WHERE (Etat=1 OR Etat=32) AND anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)"));
		StatsProf
				.add(2,
						this.jdbcTemplate
								.queryForInt("select count(*) FROM dctap WHERE (Etat=0 OR Etat=4 OR Etat>1000) AND anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)"));
		StatsProf
				.add(3,
						this.jdbcTemplate
								.queryForInt("select count(*) FROM dctap WHERE (Etat=2 OR Etat=8 OR Etat=64) AND anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)"));
		return StatsProf;
	}
}
