package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springmvc.dao.UserDao;
import com.springmvc.model.Login;
import com.springmvc.model.LoginControl;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;

public class UserServiceImpl implements UserService {

  @Autowired
  public UserDao userDao;

  public int register(UserControl user) {
    return userDao.register(user);
  }

  public User validateUser(Login login,Login login2) {
    return userDao.validateUser(login,login2);
  }

@Override
public List<User> list() {
	// TODO Auto-generated method stub
	return userDao.list();
}

@Override
public UserControl validateUserControl(LoginControl loginC,LoginControl loginC2) {
	// TODO Auto-generated method stub
	return userDao.validateUserControl(loginC,loginC2);
}

@Override
public List<UserControl> lista() {
	// TODO Auto-generated method stub
	return userDao.lista();
}



@Override
public int regisRemesa(Remesa remesa) {
	// TODO Auto-generated method stub
	return userDao.regisRemesa(remesa);
}


@Override
public String  buscarRemesa() {
	// TODO Auto-generated method stub
	return userDao.buscarRemesa();
}



}
