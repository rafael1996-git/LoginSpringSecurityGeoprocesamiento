package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Control;
import com.springmvc.model.Fecha;
import com.springmvc.model.Login;
import com.springmvc.model.LoginControl;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;

public interface UserService {

	public int register(UserControl user);
	public int regisRemesa(Remesa remesa);
	public int register(Control inserta);
  User validateUser(Login login,Login login2);
  UserControl validateUserControl(LoginControl loginC,LoginControl loginC2);
  public List<User> list();
  public List<UserControl> lista();
  public String  buscarRemesa() ;

}
