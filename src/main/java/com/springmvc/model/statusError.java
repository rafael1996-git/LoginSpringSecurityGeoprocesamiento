package com.springmvc.model;


public class statusError {
	private String entidad ; 
	private String remesa;
	private String fecha ; 
	private String error;
	@Override
	public String toString() {
		return "statusError [ entidad=" + entidad + ", remesa=" + remesa + ", fecha="
				+ fecha + ", error=" + error + "]";
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getRemesa() {
		return remesa;
	}
	public void setRemesa(String remesa) {
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
