package com.springmvc.model;

import java.util.Date;

public class Remesa {
	
		private int id_usuario ;
		private String token ; 
	    private String entidad_remesa ;
	    private Date fecha_hora;
	    private int id_status;
	    
	    
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario2) {
		this.id_usuario = id_usuario2;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEntidad_remesa() {
		return entidad_remesa;
	}

	public void setEntidad_remesa(String entidad_remesa) {
		this.entidad_remesa = entidad_remesa;
	}

	public Date getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public int getId_status() {
		return id_status;
	}

	public void setId_status(int id_status) {
		this.id_status = id_status;
	}

	

   @Override
	public String toString() {
		return "Remesa [id_usuario=" + id_usuario + ", token=" + token + ", entidad_remesa=" + entidad_remesa
				+ ", fecha_hora=" + fecha_hora + ", id_status=" + id_status + "]";
	}
    
    
}
