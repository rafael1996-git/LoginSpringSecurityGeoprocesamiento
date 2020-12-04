package com.springmvc.dao.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.User;

public class UserMapper implements RowMapper<User> {
	
	private final Logger logger = LoggerFactory.getLogger(UserMapper.class);
	
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUsuario(rs.getString("usuario"));
		user.setPassword(rs.getString("password"));
		user.setCorreo(rs.getString("correo"));
		user.setEntidad(rs.getInt("entidad"));
		user.setDistrito(rs.getInt("distrito"));
		user.setStatus(rs.getBoolean("status"));
		user.setId_rol(rs.getInt("id_rol"));
		user.setNombre(rs.getString("nombre"));
		user.setApe_pat(rs.getString("ape_pat"));
		user.setApe_mat(rs.getString("ape_mat"));
		logger.info("UserMapper: "+user.toString());
		return user;
	}

}
