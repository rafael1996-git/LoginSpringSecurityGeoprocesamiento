package com.springmvc.model;

import java.util.List;


public class UserControl {
	private Integer id_usuario;
	private String nombre ;
	private String ape_pat ;
	private String ape_mat ;
	
	private String puesto ;
	private int entidad;
	
	private int id_tipo_usuario;
	
	private String correo ;
	
	private String password ;
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id_tipo")
	private List<Role> roles;
	public Integer getId_usuario() {
		return id_usuario;
	}


	public List<Role> getRoles() {
		
		
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}




	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
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







	public String getPuesto() {
		return puesto;
	}







	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}







	public int getEntidad() {
		return entidad;
	}







	public void setEntidad(int entidad) {
		this.entidad = entidad;
	}







	public int getId_tipo_usuario() {
		return id_tipo_usuario;
	}







	public void setId_tipo_usuario(int id_tipo_usuario) {
		this.id_tipo_usuario = id_tipo_usuario;
	}







	public String getCorreo() {
		return correo;
	}







	public void setCorreo(String correo) {
		this.correo = correo;
	}







	public String getPassword() {
		return password;
	}







	public void setPassword(String password) {
		this.password = password;
	}







	
	@Override
	public String toString() {
		return "UserControl [id_usuario=" + id_usuario + ", nombre=" + nombre + ", ape_pat=" + ape_pat + ", ape_mat="
				+ ape_mat + ", puesto=" + puesto + ", entidad=" + entidad + ", id_tipo_usuario=" + id_tipo_usuario
				+ ", correo=" + correo + ", password=" + password + "]";
	}



	
	
	
	
public  UserControl() {
		
	}
	
	
}
