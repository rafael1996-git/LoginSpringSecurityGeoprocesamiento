package com.springmvc.dao.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.Control;

public class ControlMapper implements RowMapper<Control> {
	
	private final Logger logger = LoggerFactory.getLogger(ControlMapper.class);
	
	public Control mapRow(ResultSet rs, int arg1) throws SQLException {
		Control itera = new Control();
		itera.setId_control(rs.getInt("id_control"));
		itera.setEntidad(rs.getInt("entidad"));
		itera.setRemesa(rs.getInt("remesa"));
		itera.setFecha_hora(rs.getDate("fecha_hora"));
		itera.setId_usuario(rs.getInt("id_usuario"));
		itera.setId_operacion(rs.getInt("id_operacion"));
		itera.setId_status(rs.getInt("id_status"));
		itera.setError(rs.getString("error"));
		logger.info("ControlMapper "+itera.toString());
		return itera;
	}

}
