package com.springmvc.dao.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.Role;

public class RoleMapper implements RowMapper<Role> {
	private final Logger logger = LoggerFactory.getLogger(RoleMapper.class);

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();	
		role.setId(rs.getLong("id"));
		role.setUser_id_tipo(rs.getLong("user_id_tipo"));
		role.setAuthority(rs.getString("authority"));
		logger.info("Role: "+role.toString());
		return role;
	}

}
