package com.avhwServer.avapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.avhwServer.avapi.entity.Enrollees;

@Repository
public class EnrolleesRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	class EnrolleesRowMapper implements RowMapper<Enrollees> {
		@Override
		public Enrollees mapRow(ResultSet rs, int rowNum) throws SQLException {
			Enrollees enr = new Enrollees();
			enr.setId(rs.getInt("id"));
			enr.setUserId(rs.getString("user_id"));
			enr.setFirstName(rs.getString("first_name"));
			enr.setLastName(rs.getString("last_name"));
			enr.setVersion(rs.getInt("version"));
			enr.setCompany(rs.getString("company"));
			return enr;
		}

	}

    public Enrollees findByUserId(String userId) {
		return jdbcTemplate.queryForObject("select * from enrollees where user_id=?", new Object[] { userId },
				new BeanPropertyRowMapper<Enrollees>(Enrollees.class));
	}

    public int checkIfUserIdExists(String userId) throws SQLException {
		int cnt = 0;
        cnt = jdbcTemplate.queryForObject("select count(*) from enrollees where user_id=?", Integer.class, new Object[] { userId });
        return cnt;
	}

	public List<Enrollees> findByCompany(String company) {
		return jdbcTemplate.query("select * from enrollees where company=? order by last_name, first_name asc", new EnrolleesRowMapper(), new Object[] { company });
	}

	public List<String> findAllCompany() {
		return jdbcTemplate.queryForList("select distinct(company) from enrollees", String.class);
	}

    public void insert(Enrollees enr) {
		jdbcTemplate.update("insert into enrollees (id, user_id, first_name, last_name, version, company) values(?, ?, ?, ?, ?, ?)",
				new Object[] { enr.getId(), enr.getUserId(), enr.getFirstName(), enr.getLastName(), enr.getVersion(), enr.getCompany() });
	}

    public void update(Enrollees enr) {
		jdbcTemplate.update("update enrollees set version = ? where user_id = ?",
				new Object[] { enr.getVersion(), enr.getUserId() });
	}

}