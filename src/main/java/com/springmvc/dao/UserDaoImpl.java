package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.Control;
import com.springmvc.model.Fecha;
import com.springmvc.model.Login;
import com.springmvc.model.LoginControl;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;
import com.springmvc.model.info;


public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplateuser;

	private JdbcTemplate jdbcTemplatecontrol;
	private JdbcTemplate jdbcTemplatebged17;

	public JdbcTemplate getJdbcTemplateuser() {
		return jdbcTemplateuser;
	}

	public void setJdbcTemplateuser(JdbcTemplate jdbcTemplateuser) {
		this.jdbcTemplateuser = jdbcTemplateuser;
	}

	public JdbcTemplate getJdbcTemplatecontrol() {
		return jdbcTemplatecontrol;
	}

	public void setJdbcTemplatecontrol(JdbcTemplate jdbcTemplatecontrol) {
		this.jdbcTemplatecontrol = jdbcTemplatecontrol;
	}
	

	public JdbcTemplate getJdbcTemplatebged17() {
		return jdbcTemplatebged17;
	}

	public void setJdbcTemplatebged17(JdbcTemplate jdbcTemplatebged17) {
		this.jdbcTemplatebged17 = jdbcTemplatebged17;
	}

	// **************************************************************************register
	// en construccion
	public int register(UserControl user) {

		String sql = "INSERT INTO public.usuario (id_usuario,nombre,ape_pat,ape_mat,puesto,entidad,id_tipo_usuario,correo,password) VALUES (?,?,?,?,?,?,?,?,?)";
		return jdbcTemplatecontrol.update(sql, new Object[] {user.getId_usuario(), user.getNombre(), user.getApe_pat(), user.getApe_mat(),
				user.getPuesto(), user.getEntidad(), user.getId_tipo_usuario(), user.getCorreo(), user.getPassword() });

	}

//********************************************************************validate login de usuarios.usuarios
	public User validateUser(Login login, Login login2) {
		String sql = "SELECT * FROM usuariosbged.usuarios WHERE correo = ? and password = ?";
		List<User> users = jdbcTemplateuser.query(sql, new Object[] { login.getCorreo(), login2.getPassword() },
				new UserMapper());

		return users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public List<User> list() {
		String sql = "SELECT nombre_completo,correo,usuario,activo FROM usuariosbged.usuarios";
		List<User> list = jdbcTemplateuser.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setNombre_completo(rs.getString("nombre_completo"));
				user.setCorreo(rs.getString("correo"));
				user.setUsuario(rs.getString("usuario"));
				user.setActivo(rs.getBoolean("activo"));

				return user;
			}

		});

		return list;

	}

	public UserControl validateUserControl(LoginControl loginC, LoginControl loginC2) {
		String sql = "SELECT * FROM public.usuario WHERE correo = ? and password = ?";
		List<UserControl> users = jdbcTemplatecontrol.query(sql,
				new Object[] { loginC.getCorreo(), loginC2.getPassword() }, new UserControl2());

		return users.size() > 0 ? users.get(0) : null;
	}

	public List<UserControl> lista() {
		String sql = "SELECT id_usuario,nombre,ape_pat,ape_mat,puesto,entidad,id_tipo_usuario FROM public.usuario";
		List<UserControl> list= jdbcTemplatecontrol.query(sql, new RowMapper<UserControl>() {

			@Override
			public UserControl mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserControl user = new UserControl();
				user.setId_usuario(rs.getInt("id_usuario"));
				user.setNombre(rs.getString("nombre"));
				user.setApe_pat(rs.getString("ape_pat"));
				user.setApe_mat(rs.getString("ape_mat"));
				user.setPuesto(rs.getString("puesto"));
				user.setEntidad(rs.getInt("entidad"));
				user.setEntidad(rs.getInt("id_tipo_usuario"));
				user.setCorreo(rs.getString("correo"));
				user.setPassword(rs.getString("password"));

				return user;
			}

		});

		return list;

	}

	public int regisRemesa(Remesa remesa) {

		String sql = "INSERT INTO public.autorizacion (id_usuario,token,entidad_remesa,fecha_hora,id_status) VALUES (?,?,?,?,?)";
		return jdbcTemplatecontrol.update(sql, new Object[] { remesa.getId_usuario(), remesa.getToken(),
				remesa.getEntidad_remesa(), remesa.getFecha_hora(), remesa.getId_status() });

	}
	@Override
	public int register(Control inserta) {
		String sql = "INSERT INTO public.control (entidad,remesa,fecha_hora,id_usuario,id_operacion,id_status) VALUES (?,?,?,?,?,?)";
		return jdbcTemplatecontrol.update(sql, new Object[] {inserta.getEntidad(),inserta.getRemesa(),
				inserta.getFecha_hora(),inserta.getId_usuario(),inserta.getId_operacion(),inserta.getId_status()});

	}

	public String buscarRemesa() {
		String sql = "SELECT remesa FROM public.cat_remesa WHERE now()::date between fecha_inicial and fecha_final ";
		return jdbcTemplatecontrol.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getString("remesa") : null;
			}
		});

	}

	@Override
	public List<info> validate(String entidad,String anio,String semana) {
		String sql = "SELECT * FROM app.config WHERE genera_remesa in(1,2,3) AND entidad = ? AND anio= ? AND semana  = ?";
		 return jdbcTemplatebged17.query(sql, new Object[] { entidad,anio,semana }, new bgedMapper());
	}

}

//********************************************************************mapper de user(base de usuarios)
class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setCargo(rs.getString("cargo"));
		user.setDistrito(rs.getInt("distrito"));
		user.setCorreo(rs.getString("correo"));
		user.setUsuario(rs.getString("usuario"));
		user.setPassword(rs.getString("password"));
		user.setEntidad(rs.getInt("entidad"));
		user.setMac(rs.getString("mac"));
		user.setActivo(rs.getBoolean("activo"));
		user.setId (rs.getInt("id"));
		user.setVrfejl(rs.getBoolean("vrfejl"));
		user.setAbreviatura(rs.getString("abreviatura"));
		user.setNombre_completo(rs.getString("nombre_completo"));



		return user;
	}
}

//********************************************************************mapper de user(base de control)
class UserControl2 implements RowMapper<UserControl> {

	public UserControl mapRow(ResultSet rs, int arg1) throws SQLException {
		UserControl user = new UserControl();

		user.setId_usuario(rs.getInt("id_usuario"));
		user.setNombre(rs.getString("nombre"));
		user.setApe_pat(rs.getString("ape_pat"));
		user.setApe_mat(rs.getString("ape_mat"));
		user.setPuesto(rs.getString("puesto"));
		user.setEntidad(rs.getInt("entidad"));
		user.setId_tipo_usuario(rs.getInt("id_tipo_usuario"));
		user.setCorreo(rs.getString("correo"));
		user.setPassword(rs.getString("password"));

		return user;
	}
}
//********************************************************************mapper de control(base  control)
class ControlMapper implements RowMapper<Control> {

	public Control mapRow(ResultSet rs, int arg1) throws SQLException {
		Control itera = new Control();
		itera.setId_control(rs.getInt("id_control"));
		itera.setEntidad(rs.getInt("entidad"));
		itera.setRemesa(rs.getInt("remesa"));
		itera.setFecha_hora(rs.getDate("fecha_hora"));
		itera.setId_usuario(rs.getInt("id_usuario"));
		itera.setId_operacion(rs.getInt("id_operacion"));
		itera.setId_status(rs.getInt("id_status"));
		itera.setError(rs.getString("error"));

		return itera;
	}
}
//********************************************************************mapper de control(base  control)
class bgedMapper implements RowMapper<info> {

	public info mapRow(ResultSet rs, int arg1) throws SQLException {
		info itera = new info();
		itera.setEntidad(rs.getString("entidad"));
		itera.setAnio(rs.getString("anio"));
		itera.setSemana(rs.getString("semana"));
		itera.setGenera_remesa(rs.getInt("genera_remesa"));
		itera.setRuta(rs.getString("ruta"));

		return itera;
	}
}


//********************************************************************mapper de user(base de autorizacion)
class Userautorizacion implements RowMapper<Remesa> {

	public Remesa mapRow(ResultSet rs, int arg1) throws SQLException {
		Remesa user = new Remesa();
		user.setId_usuario(rs.getInt("id_usuario"));
		user.setToken(rs.getString("token"));
		user.setEntidad_remesa(rs.getString("entidad_remesa"));
		user.setFecha_hora(rs.getDate("fecha_hora"));
		user.setId_status(rs.getInt("id_status"));

		return user;
	}
}

//********************************************************************mapper de user(tabla de Remesa)
class RemesaFecha implements RowMapper<Fecha> {

	public Fecha mapRow(ResultSet rs, int arg1) throws SQLException {
		Fecha user = new Fecha();
		user.setREMESA(rs.getString("REMESA"));
		user.setSEMANA(rs.getInt("SEMANA"));
		user.setFECHA_INICIAL(rs.getDate("FECHA_INICIAL"));
		user.setFECHA_FINAL(rs.getDate("FECHA_FINAL"));
		user.setFINAL_CORTE(rs.getDate("FINAL_CORTE"));
		user.setTIPO_CAMPAÑA(rs.getInt("TIPO_CAMPAÑA"));

		return user;
	}
}