package com.springmvc.model;

public class info {
	private String entidad ;
	private String anio ;
	private String semana ;
	private Integer estado_remesa ;
	private String ruta ;
	@Override
	public String toString() {
		return "info [entidad=" + entidad + ", anio=" + anio + ", semana=" + semana + ", estado_remesa=" + estado_remesa
				+ ", ruta=" + ruta + "]";
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getSemana() {
		return semana;
	}
	public void setSemana(String semana) {
		this.semana = semana;
	}
	public Integer getEstado_remesa() {
		return estado_remesa;
	}
	public void setEstado_remesa(Integer estado_remesa) {
		this.estado_remesa = estado_remesa;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	
	    
}
