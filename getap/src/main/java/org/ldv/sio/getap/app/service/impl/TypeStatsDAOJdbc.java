package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.TypeStats;
import org.ldv.sio.getap.app.service.dao.IFTypeStatsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("typeStatsDao")
public class TypeStatsDAOJdbc implements IFTypeStatsDAO {

	private static JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class AccTypeStatsMapper implements
			RowMapper<TypeStats> {
		public TypeStats mapRow(ResultSet rs, int rowNum) throws SQLException {
			TypeStats typeStats = new TypeStats();
			typeStats.setLibelle(rs.getString("libelle"));
			typeStats.setCountap(rs.getInt("countap"));
			return typeStats;
		}
	}

	public List<TypeStats> getAllAPForEachType() {

		return this.jdbcTemplate
				.query("SELECT ap.libelle, count(dctap.id) AS countap FROM dctap, ap WHERE ap.id = dctap.idAP GROUP BY ap.libelle ORDER BY countap DESC, libelle",
						new AccTypeStatsMapper());
	}
}
