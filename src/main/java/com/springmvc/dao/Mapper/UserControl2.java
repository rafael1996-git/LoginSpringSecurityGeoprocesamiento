package com.springmvc.dao.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.UserControl;

public class UserControl2 implements RowMapper<UserControl> {
	
	private final Logger logger = LoggerFactory.getLogger(UserControl2.class);

	public UserControl mapRow(ResultSet rs, int arg1) throws SQLException {
		UserControl user = new UserControl();

		user.setId_usuario(rs.getInt("id_usuario"));
		user.setNombre(rs.getString("nombre"));
		user.setApe_pat(rs.getString("ape_pat"));
		user.setApe_mat(rs.getString("ape_mat"));
		user.setPuesto(rs.getString("puesto"));
		user.setEntidad(rs.getInt("entidad"));
		user.setId_tipo_usuario(rs.getInt("id_tipo_usuario"));
		user.setCorreo(rs.getString("correo"));
		user.setPassword(rs.getString("password"));
		logger.info("UserControl2mapper: "+user.toString());
		return user;
	}

}
