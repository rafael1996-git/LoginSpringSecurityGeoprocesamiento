package com.springmvc.dao.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.info;

public class bgedMapper implements RowMapper<info> {

	private final Logger logger = LoggerFactory.getLogger(bgedMapper.class);

	public info mapRow(ResultSet rs, int arg1) throws SQLException {
		info itera = new info();
		itera.setEntidad(rs.getString("entidad"));
		itera.setAnio(rs.getString("anio"));
		itera.setSemana(rs.getString("semana"));
		itera.setEstado_remesa(rs.getInt("estado_remesa"));
		itera.setRuta(rs.getString("ruta"));
		logger.info("bgedMapper: " + itera.toString());
		return itera;
	}
}
