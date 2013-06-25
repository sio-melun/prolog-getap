package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.service.dao.IFClasseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("classeDAO")
public class ClasseDAOJdbc implements IFClasseDAO {

	private static JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class ClasseMapper implements RowMapper<Classe> {
		public Classe mapRow(ResultSet rs, int rowNum) throws SQLException {
			Classe classe = new Classe();
			classe.setId(rs.getInt("id"));
			classe.setNom(rs.getString("libelle"));
			return classe;
		}
	}

	public List<Classe> getAllClasse() {
		return this.jdbcTemplate
				.query("select * from classe where libelle != 'null' order by libelle",
						new ClasseMapper());
	}

	public Classe getClasseById(int id) {
		Classe classe;
		try {
			classe = this.jdbcTemplate.queryForObject(
					"select * from classe where id = ?", new Object[] { id },
					new ClasseMapper());
		} catch (EmptyResultDataAccessException e) {
			classe = null;
		}

		return classe;
	}

	public int countClasses() {
		int count = this.jdbcTemplate.queryForInt(
				"select count(id) from classe order by libelle",
				new Object[] {});
		return count;
	}

	public List<Classe> getAllClasseByProf(Long id) {
		return this.jdbcTemplate.query(
				"select classe.* from user, classe, prof_principal p where user.id = "
						+ id + " and p.idClasse = classe.id and p.idUser = "
						+ id + " order by libelle", new ClasseMapper());
	}

	public void addClasse(Classe classe) {
		String libelle = classe.getNom();
		this.jdbcTemplate.update("insert into classe(libelle) values(?)",
				new Object[] { libelle });

	}

	public void upDateClasse(Classe classe) {
		int id = classe.getId();
		String libelle = classe.getNom();
		this.jdbcTemplate.update("update classe set libelle = ? where id = ?",
				new Object[] { libelle, id });

	}

	public void deleteClasse(Classe classe) {
		int id = classe.getId();
		String libelle = classe.getNom();
		this.jdbcTemplate.update(
				"delete from classe where id = ? and libelle = ?",
				new Object[] { id, libelle });

	}

}
