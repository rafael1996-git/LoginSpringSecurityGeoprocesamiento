package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Control;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;
import com.springmvc.model.info;

public interface UserService {

	public int register(UserControl user);
	public int regisRemesa(Remesa remesa);
	public int register(Control inserta);
	public List<info> validate(String entidad,String anio,String semana);
	public List<User> list();
	public List<UserControl> lista();
	public String  buscarRemesa() ;
	public String  buscarAdmin();
	public List<UserControl> findByUserControlAndPassword(String correo, String password);
	public List<UserControl> findById_TipoUserAndPassword(String correo, String password ,int id_tipo_usuario);
	public UserControl findBycorreo(String correo);
	public User findByUsercorreo(String correo);
	public void delete(String correo);


}
