package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class AdminDao {

	private JdbcTemplate template;
	
	@Autowired  
	public AdminDao( ComboPooledDataSource dataSource ) {
		this.template = new JdbcTemplate(dataSource);
	}

	public int workerCheck(String workId, String workPwd) {
		int result = 0;
		
		String sql = "select pwd from worker where id=?";
		
		List<String> list = template.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String pwd = rs.getString("pwd");
				return pwd;
			}
		}, workId);
		
		if( list.size() == 0) result=-1;
		else if( workPwd.equals( list.get(0) ) ) result=1;
		else result = 0;		
		
		return result;
	}

	
}