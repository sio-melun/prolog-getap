package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.ClasseStats;
import org.ldv.sio.getap.app.service.dao.IFParClasseStatsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("parClasseStatsDao")
public class ParClasseStatsDAOJdbc implements IFParClasseStatsDAO {
	private static JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class AccParClasseStatsMapper implements
			RowMapper<ClasseStats> {
		public ClasseStats mapRow(ResultSet rs, int rowNum) throws SQLException {
			ClasseStats statsEleveByClasse = new ClasseStats();
			statsEleveByClasse.setIdEleveSQL(rs.getInt("idEleveSQL"));
			statsEleveByClasse.setNomEleve(rs.getString("nomEleve"));
			statsEleveByClasse.setPrenomEleve(rs.getString("prenomEleve"));
			statsEleveByClasse.setCountApEleve(rs.getInt("countapEleve"));
			statsEleveByClasse.setDctapvalideEleve(rs
					.getInt("dctapvalideEleve"));
			statsEleveByClasse.setDctapattenteEleve(rs
					.getInt("dctapattenteEleve"));
			statsEleveByClasse.setDctaprefuseEleve(rs
					.getInt("dctaprefuseEleve"));
			statsEleveByClasse.setLastlogEleve(rs.getString("lastlogEleve"));
			statsEleveByClasse.setCountlogEleve(rs.getInt("countlogEleve"));
			return statsEleveByClasse;
		}
	}

	private static final class AccParClasseStatsMapperProfs implements
			RowMapper<ClasseStats> {
		public ClasseStats mapRow(ResultSet rs, int rowNum) throws SQLException {
			ClasseStats statsProfByClasse = new ClasseStats();
			statsProfByClasse.setIdProfSQL(rs.getInt("idProfSQL"));
			statsProfByClasse.setNomProf(rs.getString("nomProf"));
			statsProfByClasse.setPrenomProf(rs.getString("prenomProf"));
			statsProfByClasse.setCountapProf(rs.getInt("countapProf"));
			statsProfByClasse.setDctapvalideProf(rs.getInt("dctapvalideProf"));
			statsProfByClasse
					.setDctapattenteProf(rs.getInt("dctapattenteProf"));
			statsProfByClasse.setDctaprefuseProf(rs.getInt("dctaprefuseProf"));
			statsProfByClasse.setLastlogProf(rs.getString("lastlogProf"));
			statsProfByClasse.setCountlogProf(rs.getInt("countlogProf"));
			return statsProfByClasse;
		}
	}

	private static final class ClasseMapper implements RowMapper<ClasseStats> {
		public ClasseStats mapRow(ResultSet rs, int rowNum) throws SQLException {
			ClasseStats classe = new ClasseStats();
			classe.setIdClasse((rs.getInt("idClasse")));
			classe.setLibelleClasse(rs.getString("libelleClasse"));
			return classe;
		}
	}

	public List<Integer> getAlldctapByClasse(String idClasse) {
		List<Integer> statsTotalByClasse = new ArrayList<Integer>();
		statsTotalByClasse
				.add(0,
						this.jdbcTemplate
								.queryForInt("select count(*) AS countapTotal FROM user, dctap WHERE user.idClasse = '"
										+ idClasse
										+ "' AND user.id = dctap.idEleve  AND anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)"));
		statsTotalByClasse
				.add(1,
						this.jdbcTemplate
								.queryForInt("select count(*) AS dctapvalideTotal FROM user, dctap WHERE user.idClasse = '"
										+ idClasse
										+ "' AND user.id = dctap.idEleve  AND (Etat=1 OR Etat=32) AND anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)"));
		statsTotalByClasse
				.add(2,
						this.jdbcTemplate
								.queryForInt("select count(*) AS dctapattenteTotal FROM user, dctap WHERE user.idClasse = '"
										+ idClasse
										+ "' AND user.id = dctap.idEleve AND (Etat=0 OR Etat=4 OR Etat>1000) AND anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)"));
		statsTotalByClasse
				.add(3,
						this.jdbcTemplate
								.queryForInt("select count(*) AS dctaprefuseTotal FROM user, dctap WHERE user.idClasse = '"
										+ idClasse
										+ "' AND user.id = dctap.idEleve AND (Etat=2 OR Etat=8 OR Etat=64) AND anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)"));
		return statsTotalByClasse;
	}

	public List<ClasseStats> getAllAPByClasse(String idClasse) {

		return this.jdbcTemplate
				.query("Select "
						+ "user.id as idEleveSQL,"
						+ "user.nom as nomEleve,"
						+ "user.prenom as prenomEleve,"
						+ "(SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 1 OR dctap.Etat = 32) AND idEleve = user.id AND dctap.anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)) AS dctapvalideEleve, "
						+ "(SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 2 OR dctap.Etat = 8 OR dctap.Etat = 64) AND idEleve = user.id AND dctap.anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)) AS dctaprefuseEleve,"
						+ "(SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 0 OR dctap.Etat = 4 OR dctap.Etat > 1023) AND idEleve = user.id AND dctap.anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap)) AS dctapattenteEleve,"
						+ "(SELECT count(dctap.id) FROM dctap WHERE idEleve = user.id AND dctap.anneeScolaire = (SELECT MAX(anneeScolaire)  FROM dctap)) AS countapEleve,"
						+ "(SELECT MAX(datein) FROM log WHERE user.id = idUser) AS lastlogEleve,"
						+ "(SELECT count(datein) FROM log WHERE user.id = idUser) AS countlogEleve"
						+ " FROM "
						+ "user, dctap, classe"
						+ " WHERE "
						+ "dctap.idEleve = user.id AND classe.id = user.idClasse AND classe.id = "
						+ idClasse
						+ " GROUP BY user.id ORDER BY dctapvalideEleve DESC, user.nom;",
						new AccParClasseStatsMapper());
	}

	public List<ClasseStats> getAllProfesseursByClasse(String idClasse) {

		return this.jdbcTemplate
				.query("SELECT user.id as idProfSQL,"
						+ "user.nom as nomProf,"
						+ "user.prenom as prenomProf,"
						+ "(SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 1 OR dctap.Etat = 32) AND idProf = user.id AND dctap.anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap))"
						+ "AS dctapvalideProf,"
						+ "(SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 2 OR dctap.Etat = 8 OR dctap.Etat = 64) AND idProf = user.id AND dctap.anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap))"
						+ "AS dctaprefuseProf,"
						+ "(SELECT count(dctap.id) FROM dctap WHERE (dctap.Etat = 0 OR dctap.Etat = 4 OR dctap.Etat > 1023) AND idProf = user.id AND dctap.anneeScolaire = (SELECT MAX(anneeScolaire) FROM dctap))"
						+ "AS dctapattenteProf,"
						+ "(SELECT count(dctap.id) FROM dctap WHERE idProf = user.id AND dctap.anneeScolaire = (SELECT MAX(anneeScolaire)  FROM dctap))"
						+ "AS countapProf,"
						+ "(SELECT MAX(datein) FROM log WHERE user.id = idUser)"
						+ "AS lastlogProf,"
						+ "(SELECT count(datein) FROM log WHERE user.id = idUser)"
						+ "AS countlogProf"
						+ " FROM "
						+ "user, dctap, classe, prof_principal"
						+ " WHERE "
						+ "dctap.idProf = user.id AND prof_principal.idClasse = classe.id AND prof_principal.idUser = user.id AND (SELECT count(dctap.id) FROM dctap WHERE idProf = user.id AND dctap.anneeScolaire = (SELECT MAX(anneeScolaire)  FROM dctap)) > 0 AND classe.id = '"
						+ idClasse + "' GROUP BY user.id"
						+ " ORDER BY dctapvalideProf DESC, user.nom;",
						new AccParClasseStatsMapperProfs());
	}

	public List<String> getFirstClasse() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ClasseStats> getAllClassesForStats() {
		return this.jdbcTemplate
				.query("SELECT classe.id AS idClasse, classe.libelle AS libelleClasse FROM classe;",
						new ClasseMapper());
	}

}
