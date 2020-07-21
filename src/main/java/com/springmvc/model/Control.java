package com.springmvc.model;

import java.util.Date;

public class Control {
	private Integer id_control;
	private Integer entidad ;
	private Integer remesa ;
	private Date fecha_hora ;
	private Integer id_usuario ;
	private Integer id_operacion;
	private Integer id_status;
	
	@Override
	public String toString() {
		return "Control [id_control=" + id_control + ", entidad=" + entidad + ", remesa=" + remesa + ", fecha_hora="
				+ fecha_hora + ", id_usuario=" + id_usuario + ", id_operacion=" + id_operacion + ", id_status="
				+ id_status + ", error=" + error + "]";
	}

	public Integer getId_control() {
		return id_control;
	}

	public void setId_control(Integer id_control) {
		this.id_control = id_control;
	}

	public Integer getEntidad() {
		return entidad;
	}

	public void setEntidad(Integer entidad) {
		this.entidad = entidad;
	}

	public Integer getRemesa() {
		return remesa;
	}

	public void setRemesa(Integer remesa) {
		this.remesa = remesa;
	}

	public Date getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Integer getId_operacion() {
		return id_operacion;
	}

	public void setId_operacion(Integer id_operacion) {
		this.id_operacion = id_operacion;
	}

	public Integer getId_status() {
		return id_status;
	}

	public void setId_status(Integer id_status) {
		this.id_status = id_status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	private String error ;
	
}
