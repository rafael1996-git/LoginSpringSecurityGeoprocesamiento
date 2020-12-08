package com.springmvc.model;

import java.util.List;

public class MultiGrafica {
	
	private Integer idEntidad;
	private String nombreEntidad;
	private List<Integer> idOperaciones;
	private String messaje;
	private String msgException;

	
	public Integer getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(Integer idEntidad) {
		this.idEntidad = idEntidad;
	}
	
	
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	public List<Integer> getIdOperaciones() {
		return idOperaciones;
	}
	public void setIdOperaciones(List<Integer> idOperaciones) {
		this.idOperaciones = idOperaciones;
	}
	public String getMessaje() {
		return messaje;
	}
	public void setMessaje(String messaje) {
		this.messaje = messaje;
	}
	public String getMsgException() {
		return msgException;
	}
	public void setMsgException(String msgException) {
		this.msgException = msgException;
	}
	@Override
	public String toString() {
		return "MultiGrafica [idEntidad=" + idEntidad + ", nombreEntidad=" + nombreEntidad + ", idOperaciones="
				+ idOperaciones + ", messaje=" + messaje + ", msgException=" + msgException + "]";
	}


}
