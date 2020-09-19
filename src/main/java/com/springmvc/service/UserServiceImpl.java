package com.springmvc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.UserDao;
import com.springmvc.model.Control;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;
import com.springmvc.model.info;
import com.springmvc.model.statusError;

public class UserServiceImpl implements UserService {

  @Autowired
  public UserDao userDao;

  public int register(UserControl user) {
    return userDao.register(user);
  }

 

@Override
public List<User> list() {
	// TODO Auto-generated method stub
	return userDao.list();
}



@Override
public List<UserControl> lista() {
	// TODO Auto-generated method stub
	return userDao.lista();
}
@Override
public int register(Control inserta) {
	return userDao.register(inserta);
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

@Override
public List<info> validate(String entidad,String anio,String semana) {
	return userDao.validate( entidad, anio, semana);
}


@Override
public List<UserControl> findByUserControlAndPassword(String correo, String password) {
	return userDao.findByUserControlAndPassword(correo, password);
}



@Override
public UserControl findBycorreo(String correo) {
	return userDao.findBycorreo(correo);
}



@Override
public String buscarAdmin() {
	return userDao.buscarAdmin();
}



@Override
public List<UserControl> findById_TipoUserAndPassword(String correo, String password, int id_tipo_usuario) {
	
	return userDao.findById_TipoUserAndPassword(correo, password, id_tipo_usuario);
}



@Override
public User findByUsercorreo(String correo) {
	// TODO Auto-generated method stub
	return userDao.findByUsercorreo(correo);
}



@Override
public void delete(String correo) {
	userDao.delete(correo);
}



@Override
public List<User> listaFiltrada(int entidad) {
	
	return userDao.listaFiltrada(entidad);
}



@Override
public void deleteAut(String token) {
	userDao.deleteAut(token);	
}



@Override
public int register(statusError status) {
	// TODO Auto-generated method stub
	return userDao.register(status);
}



@Override
public List<statusError> listaStatus(String fecha) {
	// TODO Auto-generated method stub
	return userDao.listaStatus(fecha);
}



@Override
public statusError findByfecha(String fecha,String error) {
	// TODO Auto-generated method stub
	return userDao.findByfecha(fecha,error);
}





}
