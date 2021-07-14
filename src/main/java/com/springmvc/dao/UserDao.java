package com.springmvc.dao;

import java.util.List;
import java.util.Map;



import com.springmvc.model.Control;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;
import com.springmvc.model.info;
import com.springmvc.model.statusError;

public interface UserDao {

	public int register(UserControl user);

	public int register(statusError status);

	public int register(Control inserta);

	public int regisRemesa(Remesa remesa);

	public List<info> validate(String entidad, String anio, String semana);

	public List<User> list();

	public List<User> listaFiltrada(int entidad);

	public List<UserControl> lista();

	public List<statusError> listaStatus(String fecha);

	public String buscarRemesa();

	public List<UserControl> findByUserControlAndPassword(String correo, String password);

	public List<UserControl> findByUserAndPassword(String correo, String password);

	public UserControl findBycorreo(String correo);
	public UserControl findByAuthority(String correo);
	public statusError findByfecha(String fecha, String error);

	public statusError buscarfecha(String fecha);

	public User findByUsercorreo(String correo);

	public String buscarAdmin();

	public void delete(String correo);

	public void deleteAut(String token);

	public Map<Integer, String> entidadesActivas();

	public String obtieneNombreEntidad(String idEntidad);

	

}
