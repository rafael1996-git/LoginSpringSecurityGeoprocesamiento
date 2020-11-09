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



@Override
public statusError buscarfecha(String fecha) {
	// TODO Auto-generated method stub
	return userDao.buscarfecha(fecha);
}



@Override
public List<info> validate09(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate09(entidad, anio, semana);
}



@Override
public List<info> validate14(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate14(entidad, anio, semana);
}



@Override
public List<info> validate15(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate15(entidad, anio, semana);
}



@Override
public List<info> validate30(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate30(entidad, anio, semana);
}



@Override
public List<info> validate01(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate01(entidad, anio, semana);
}



@Override
public List<info> validate02(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate02(entidad, anio, semana);
}



@Override
public List<info> validate03(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate03(entidad, anio, semana);
}



@Override
public List<info> validate04(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate04(entidad, anio, semana);
}



@Override
public List<info> validate05(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate05(entidad, anio, semana);
}



@Override
public List<info> validate06(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate06(entidad, anio, semana);
}



@Override
public List<info> validate07(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate07(entidad, anio, semana);
}



@Override
public List<info> validate08(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate08(entidad, anio, semana);
}



@Override
public List<info> validate10(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate10(entidad, anio, semana);
}



@Override
public List<info> validate11(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate11(entidad, anio, semana);
}



@Override
public List<info> validate12(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate12(entidad, anio, semana);
}



@Override
public List<info> validate13(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate13(entidad, anio, semana);
}



@Override
public List<info> validate16(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate16(entidad, anio, semana);
}



@Override
public List<info> validate17(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate17(entidad, anio, semana);
}



@Override
public List<info> validate18(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate18(entidad, anio, semana);
}



@Override
public List<info> validate19(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate19(entidad, anio, semana);
}



@Override
public List<info> validate20(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate20(entidad, anio, semana);
}



@Override
public List<info> validate21(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate21(entidad, anio, semana);
}



@Override
public List<info> validate22(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate22(entidad, anio, semana);
}



@Override
public List<info> validate23(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate23(entidad, anio, semana);
}



@Override
public List<info> validate24(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate24(entidad, anio, semana);
}



@Override
public List<info> validate25(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate25(entidad, anio, semana);
}



@Override
public List<info> validate26(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate26(entidad, anio, semana);
}



@Override
public List<info> validate27(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate27(entidad, anio, semana);
}



@Override
public List<info> validate28(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate28(entidad, anio, semana);
}



@Override
public List<info> validate29(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate29(entidad, anio, semana);
}



@Override
public List<info> validate31(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate31(entidad, anio, semana);
}



@Override
public List<info> validate32(String entidad, String anio, String semana) {
	// TODO Auto-generated method stub
	return userDao.validate32(entidad, anio, semana);
}





}
