package org.ldv.sio.getap.app.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.ldv.sio.getap.app.LoginInfo;
import org.ldv.sio.getap.app.service.dao.IFLoginInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service("loginInfoDao")
public class LoginInfoDAOJdbc implements IFLoginInfoDAO {
	private static JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class AccProfStatsMapper implements
			RowMapper<LoginInfo> {
		public LoginInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setLastlog(rs.getString("lastlog"));
			loginInfo.setCountlog(rs.getString("countlog"));
			return loginInfo;
		}
	}

	public List<LoginInfo> getLoginInfoById(String id) {
		return this.jdbcTemplate
				.query("SELECT (SELECT datein FROM log WHERE idUser="
						+ id
						+ " ORDER BY id DESC LIMIT 1) AS lastlog, (SELECT count(*) FROM log WHERE idUser="
						+ id + ") AS countlog FROM log LIMIT 1;",
						new AccProfStatsMapper());
	}
}
