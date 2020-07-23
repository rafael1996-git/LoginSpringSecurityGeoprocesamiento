package com.springmvc.model;

public class info {
	private String entidad ;
	private String anio ;
	private String semana ;
	private Integer genera_remesa ;
	private String ruta ;
	
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
	public Integer getGenera_remesa() {
		return genera_remesa;
	}
	public void setGenera_remesa(int genera_remesa) {
		this.genera_remesa = genera_remesa;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	@Override
	public String toString() {
		return "info [entidad=" + entidad + ", anio=" + anio + ", semana=" + semana + ", genera_remesa=" + genera_remesa
				+ ", ruta=" + ruta + "]";
	}
	
	    
}
