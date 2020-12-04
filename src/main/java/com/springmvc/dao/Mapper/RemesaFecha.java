package com.springmvc.dao.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.Fecha;

public class RemesaFecha implements RowMapper<Fecha>{
	
	private final Logger logger = LoggerFactory.getLogger(RemesaFecha.class);
	
	public Fecha mapRow(ResultSet rs, int arg1) throws SQLException {
		Fecha user = new Fecha();
		user.setREMESA(rs.getString("REMESA"));
		user.setSEMANA(rs.getInt("SEMANA"));
		user.setFECHA_INICIAL(rs.getDate("FECHA_INICIAL"));
		user.setFECHA_FINAL(rs.getDate("FECHA_FINAL"));
		user.setFINAL_CORTE(rs.getDate("FINAL_CORTE"));
		user.setTIPO_CAMPAÑA(rs.getInt("TIPO_CAMPAÑA"));
		logger.info("RemesaFechaMapper: "+user.toString());
		return user;
	}

}
