package com.springmvc.model;

import java.util.Date;

public class User {
	private int id;
	private String usuario;
	private String password;
	private String correo;
	private int entidad;
	private int distrito;
	private int id_rol;
	private String nombre;
	private String ape_pat;
	private String ape_mat;
	private int autorizado;
	private int status_portal;
	private int status_bged;
	private Date fecha_inicio;
	private Date fecha_terminacion;
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", usuario=" + usuario + ", password=" + password + ", correo=" + correo
				+ ", entidad=" + entidad + ", distrito=" + distrito +  ", id_rol=" + id_rol
				+ ", nombre=" + nombre + ", ape_pat=" + ape_pat + ", ape_mat=" + ape_mat + ", autorizado=" + autorizado
				+ ", status_portal=" + status_portal + ", status_bged=" + status_bged + ", fecha_inicio=" + fecha_inicio
				+ ", fecha_terminacion=" + fecha_terminacion + "]";
	}
	public int getAutorizado() {
		return autorizado;
	}
	public void setAutorizado(int autorizado) {
		this.autorizado = autorizado;
	}
	public int getStatus_portal() {
		return status_portal;
	}
	public void setStatus_portal(int status_portal) {
		this.status_portal = status_portal;
	}
	public int getStatus_bged() {
		return status_bged;
	}
	public void setStatus_bged(int status_bged) {
		this.status_bged = status_bged;
	}
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Date getFecha_terminacion() {
		return fecha_terminacion;
	}
	public void setFecha_terminacion(Date fecha_terminacion) {
		this.fecha_terminacion = fecha_terminacion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getEntidad() {
		return entidad;
	}
	public void setEntidad(int entidad) {
		this.entidad = entidad;
	}
	public int getDistrito() {
		return distrito;
	}
	public void setDistrito(int distrito) {
		this.distrito = distrito;
	}
	
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApe_pat() {
		return ape_pat;
	}
	public void setApe_pat(String ape_pat) {
		this.ape_pat = ape_pat;
	}
	public String getApe_mat() {
		return ape_mat;
	}
	public void setApe_mat(String ape_mat) {
		this.ape_mat = ape_mat;
	}
	


}
