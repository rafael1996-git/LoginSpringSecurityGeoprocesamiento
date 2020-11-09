package com.springmvc.service;

import java.util.Date;
import java.util.List;

import com.springmvc.model.Control;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;
import com.springmvc.model.info;
import com.springmvc.model.statusError;

public interface UserService {

	public int register(UserControl user);
	public int register(statusError status);
	public int regisRemesa(Remesa remesa);
	public int register(Control inserta);
	public List<info> validate(String entidad,String anio,String semana);
	public List<info> validate01(String entidad,String anio,String semana);
	public List<info> validate02(String entidad,String anio,String semana);
	public List<info> validate03(String entidad,String anio,String semana);
	public List<info> validate04(String entidad,String anio,String semana);
	
	public List<info> validate05(String entidad,String anio,String semana);
	public List<info> validate06(String entidad,String anio,String semana);
	public List<info> validate07(String entidad,String anio,String semana);
	public List<info> validate08(String entidad,String anio,String semana);
	public List<info> validate09(String entidad,String anio,String semana);
	public List<info> validate10(String entidad,String anio,String semana);
	public List<info> validate11(String entidad,String anio,String semana);
	public List<info> validate12(String entidad,String anio,String semana);
	public List<info> validate13(String entidad,String anio,String semana);
	public List<info> validate14(String entidad,String anio,String semana);
	public List<info> validate15(String entidad,String anio,String semana);
	public List<info> validate16(String entidad,String anio,String semana);
	public List<info> validate17(String entidad,String anio,String semana);
	public List<info> validate18(String entidad,String anio,String semana);
	public List<info> validate19(String entidad,String anio,String semana);
	public List<info> validate20(String entidad,String anio,String semana);
	public List<info> validate21(String entidad,String anio,String semana);
	public List<info> validate22(String entidad,String anio,String semana);
	public List<info> validate23(String entidad,String anio,String semana);
	public List<info> validate24(String entidad,String anio,String semana);
	public List<info> validate25(String entidad,String anio,String semana);
	public List<info> validate26(String entidad,String anio,String semana);
	public List<info> validate27(String entidad,String anio,String semana);
	public List<info> validate28(String entidad,String anio,String semana);
	public List<info> validate29(String entidad,String anio,String semana);
	public List<info> validate30(String entidad,String anio,String semana);
	public List<info> validate31(String entidad,String anio,String semana);
	public List<info> validate32(String entidad,String anio,String semana);
	public List<User> list();
	public List<User> listaFiltrada(int entidad);
	public List<statusError> listaStatus(String fecha);
	public List<UserControl> lista();
	public String  buscarRemesa() ;
	public String  buscarAdmin();
	public List<UserControl> findByUserControlAndPassword(String correo, String password);
	public List<UserControl> findById_TipoUserAndPassword(String correo, String password ,int id_tipo_usuario);
	public UserControl findBycorreo(String correo);
	public statusError findByfecha(String fecha,String error);
	public statusError buscarfecha(String fecha);
	public User findByUsercorreo(String correo);
	public void delete(String correo);
	public void deleteAut(String token);


}
