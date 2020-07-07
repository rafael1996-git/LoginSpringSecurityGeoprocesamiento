package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.Fecha;
import com.springmvc.model.Login;
import com.springmvc.model.LoginControl;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;

public interface UserDao {

	public int register(UserControl user);
	
	public int regisRemesa(Remesa remesa);
  User validateUser(Login login,Login login2);
  UserControl validateUserControl(LoginControl loginC,LoginControl loginC2);
  
  public List<User> list();
  public List<UserControl> lista();
  public String  buscarRemesa() ;
  
}
