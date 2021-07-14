package com.springmvc.model;

import java.io.Serializable;


public class Role implements Serializable {

	private Long id;
	private Long user_id_tipo;
	private String authority;

	public Long getId() {
		return id;
	}

	public Long getUser_id_tipo() {
		return user_id_tipo;
	}

	public void setUser_id_tipo(Long user_id_tipo) {
		this.user_id_tipo = user_id_tipo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
