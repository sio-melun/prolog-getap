package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.Discipline;
import org.ldv.sio.getap.app.service.dao.IFDisciplineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("disciplineDAO")
public class DisciplineDAOJdbc implements IFDisciplineDAO {

	private static JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class DisciplineMapper implements
			RowMapper<Discipline> {
		public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
			Discipline dis = new Discipline();
			dis.setId(rs.getInt("id"));
			dis.setNom(rs.getString("libelle"));
			return dis;
		}
	}

	public List<Discipline> getAllDiscipline() {

		return this.jdbcTemplate.query(
				"select * from discipline order by libelle",
				new DisciplineMapper());
	}

	public Discipline getDisciplineById(int id) {
		Discipline dis;
		try {
			dis = this.jdbcTemplate.queryForObject(
					"select * from discipline where id = ?",
					new Object[] { id }, new DisciplineMapper());

		} catch (EmptyResultDataAccessException e) {
			dis = null;
		}
		return dis;
	}

	public void addDiscipline(Discipline dis) {
		String libelle = dis.getNom();

		this.jdbcTemplate.update("insert into discipline(libelle) values(?)",
				new Object[] { libelle });

	}

	public void upDateDiscipline(Discipline dis) {
		int id = dis.getId();
		String libelle = dis.getNom();

		this.jdbcTemplate.update(
				"update discipline set libelle = ? where id = ?", new Object[] {
						libelle, id });

	}

	public void deleteDiscipline(Discipline dis) {
		int id = dis.getId();
		this.jdbcTemplate.update("delete from discipline where id = ?",
				new Object[] { id });
	}

}
