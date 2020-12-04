package com.springmvc.dao.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.statusError;

public class ErrorMapper implements RowMapper<statusError>{
	
	private final Logger logger = LoggerFactory.getLogger(ErrorMapper.class);
	
	public statusError mapRow(ResultSet rs, int arg1) throws SQLException {
		statusError itera = new statusError();
		itera.setEntidad(rs.getInt("entidad"));
		itera.setRemesa(rs.getInt("remesa"));
		itera.setFecha(rs.getString("fecha"));
		itera.setError(rs.getString("error"));
		logger.info("ErrorMapper: "+itera.toString());
		return itera;
	}

}
