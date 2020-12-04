package com.springmvc.dao.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.Remesa;

public class Userautorizacion implements RowMapper<Remesa> {

	private final Logger logger = LoggerFactory.getLogger(Userautorizacion.class);

	public Remesa mapRow(ResultSet rs, int arg1) throws SQLException {
		Remesa user = new Remesa();
		user.setId_usuario(rs.getInt("id_usuario"));
		user.setToken(rs.getString("token"));
		user.setEntidad_remesa(rs.getString("entidad_remesa"));
		user.setFecha_hora(rs.getDate("fecha_hora"));
		user.setId_status(rs.getInt("id_status"));
		logger.info("UserautorizacionMapper "+user.toString());
		return user;
	}

}
