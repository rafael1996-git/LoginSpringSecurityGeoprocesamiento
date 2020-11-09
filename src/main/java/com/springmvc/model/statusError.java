package com.springmvc.model;

import java.util.Date;

public class statusError {
	
	private int entidad;
	private int remesa;
	private String fecha;
	private String error;
	@Override
	public String toString() {
		return "statusError [entidad=" + entidad + ", remesa=" + remesa + ", fecha=" + fecha + ", error=" + error + "]";
	}
	public int getEntidad() {
		return entidad;
	}
	public void setEntidad(int entidad) {
		this.entidad = entidad;
	}
	public int getRemesa() {
		return remesa;
	}
	public void setRemesa(int remesa) {
		this.remesa = remesa;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
