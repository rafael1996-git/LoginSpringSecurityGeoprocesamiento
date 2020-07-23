package com.springmvc.model;

public class User {
	private String cargo;
	private int distrito;
	private String correo;
	private String usuario;
	private String password;
	private int entidad;
	private String mac;
	private boolean activo;
	private int id;
	private boolean vrfejl;
	private String abreviatura;
	private String nombre_completo;

	public User() {

	}


	@Override
	public String toString() {
		return "User [cargo=" + cargo + ", distrito=" + distrito + ", correo=" + correo + ", usuario=" + usuario
				+ ", password=" + password + ", entidad=" + entidad + ", mac=" + mac + ", activo=" + activo + ", id="
				+ id + ", vrfejl=" + vrfejl + ", abreviatura=" + abreviatura + ", nombre_completo=" + nombre_completo
				+ "]";
	}









	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getDistrito() {
		return distrito;
	}

	public void setDistrito(int distrito) {
		this.distrito = distrito;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	public int getEntidad() {
		return entidad;
	}

	public void setEntidad(int entidad) {
		this.entidad = entidad;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isVrfejl() {
		return vrfejl;
	}

	public void setVrfejl(boolean vrfejl) {
		this.vrfejl = vrfejl;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

}
