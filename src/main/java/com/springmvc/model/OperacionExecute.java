package com.springmvc.model;

public class OperacionExecute {
	
	private Integer idOperacion;
	private String descripcion;
	
	
	public Integer getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "OperacionExecute [idOperacion=" + idOperacion + ", descripcion=" + descripcion + "]";
	}
	
	
	

}
