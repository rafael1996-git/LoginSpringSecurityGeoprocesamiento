package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.springmvc.dao.Mapper.ErrorMapper;
import com.springmvc.dao.Mapper.UserControl2;
import com.springmvc.dao.Mapper.UserMapper;
import com.springmvc.dao.Mapper.bgedMapper;
import com.springmvc.model.Control;
import com.springmvc.model.EntidadesEnum;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;
import com.springmvc.model.info;
import com.springmvc.model.statusError;

public class UserDaoImpl implements UserDao {

	private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private JdbcTemplate jdbcTemplateuser;

	private JdbcTemplate jdbcTemplatecontrol;

	private JdbcTemplate jdbcTemplatebged01;
	private JdbcTemplate jdbcTemplatebged02;
	private JdbcTemplate jdbcTemplatebged03;
	private JdbcTemplate jdbcTemplatebged04;
	private JdbcTemplate jdbcTemplatebged05;
	private JdbcTemplate jdbcTemplatebged06;

	private JdbcTemplate jdbcTemplatebged07;
	private JdbcTemplate jdbcTemplatebged08;
	private JdbcTemplate jdbcTemplatebged09;
	private JdbcTemplate jdbcTemplatebged10;
	private JdbcTemplate jdbcTemplatebged11;
	private JdbcTemplate jdbcTemplatebged12;
	private JdbcTemplate jdbcTemplatebged13;
	private JdbcTemplate jdbcTemplatebged14;
	private JdbcTemplate jdbcTemplatebged15;
	private JdbcTemplate jdbcTemplatebged16;
	private JdbcTemplate jdbcTemplatebged17;
	private JdbcTemplate jdbcTemplatebged18;
	private JdbcTemplate jdbcTemplatebged19;
	private JdbcTemplate jdbcTemplatebged20;
	private JdbcTemplate jdbcTemplatebged21;
	private JdbcTemplate jdbcTemplatebged22;

	private JdbcTemplate jdbcTemplatebged23;
	private JdbcTemplate jdbcTemplatebged24;
	private JdbcTemplate jdbcTemplatebged25;
	private JdbcTemplate jdbcTemplatebged26;
	private JdbcTemplate jdbcTemplatebged27;
	private JdbcTemplate jdbcTemplatebged28;

	private JdbcTemplate jdbcTemplatebged29;
	private JdbcTemplate jdbcTemplatebged30;
	private JdbcTemplate jdbcTemplatebged31;
	private JdbcTemplate jdbcTemplatebged32;

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

	public JdbcTemplate getJdbcTemplatebged09() {
		return jdbcTemplatebged09;
	}

	public void setJdbcTemplatebged09(JdbcTemplate jdbcTemplatebged09) {
		this.jdbcTemplatebged09 = jdbcTemplatebged09;
	}

	public JdbcTemplate getJdbcTemplatebged14() {
		return jdbcTemplatebged14;
	}

	public void setJdbcTemplatebged14(JdbcTemplate jdbcTemplatebged14) {
		this.jdbcTemplatebged14 = jdbcTemplatebged14;
	}

	public JdbcTemplate getJdbcTemplatebged15() {
		return jdbcTemplatebged15;
	}

	public void setJdbcTemplatebged15(JdbcTemplate jdbcTemplatebged15) {
		this.jdbcTemplatebged15 = jdbcTemplatebged15;
	}

	public JdbcTemplate getJdbcTemplatebged30() {
		return jdbcTemplatebged30;
	}

	public void setJdbcTemplatebged30(JdbcTemplate jdbcTemplatebged30) {
		this.jdbcTemplatebged30 = jdbcTemplatebged30;
	}

	public JdbcTemplate getJdbcTemplatebged01() {
		return jdbcTemplatebged01;
	}

	public void setJdbcTemplatebged01(JdbcTemplate jdbcTemplatebged01) {
		this.jdbcTemplatebged01 = jdbcTemplatebged01;
	}

	public JdbcTemplate getJdbcTemplatebged02() {
		return jdbcTemplatebged02;
	}

	public void setJdbcTemplatebged02(JdbcTemplate jdbcTemplatebged02) {
		this.jdbcTemplatebged02 = jdbcTemplatebged02;
	}

	public JdbcTemplate getJdbcTemplatebged03() {
		return jdbcTemplatebged03;
	}

	public void setJdbcTemplatebged03(JdbcTemplate jdbcTemplatebged03) {
		this.jdbcTemplatebged03 = jdbcTemplatebged03;
	}

	public JdbcTemplate getJdbcTemplatebged04() {
		return jdbcTemplatebged04;
	}

	public void setJdbcTemplatebged04(JdbcTemplate jdbcTemplatebged04) {
		this.jdbcTemplatebged04 = jdbcTemplatebged04;
	}

	public JdbcTemplate getJdbcTemplatebged05() {
		return jdbcTemplatebged05;
	}

	public void setJdbcTemplatebged05(JdbcTemplate jdbcTemplatebged05) {
		this.jdbcTemplatebged05 = jdbcTemplatebged05;
	}

	public JdbcTemplate getJdbcTemplatebged06() {
		return jdbcTemplatebged06;
	}

	public void setJdbcTemplatebged06(JdbcTemplate jdbcTemplatebged06) {
		this.jdbcTemplatebged06 = jdbcTemplatebged06;
	}

	public JdbcTemplate getJdbcTemplatebged07() {
		return jdbcTemplatebged07;
	}

	public void setJdbcTemplatebged07(JdbcTemplate jdbcTemplatebged07) {
		this.jdbcTemplatebged07 = jdbcTemplatebged07;
	}

	public JdbcTemplate getJdbcTemplatebged08() {
		return jdbcTemplatebged08;
	}

	public void setJdbcTemplatebged08(JdbcTemplate jdbcTemplatebged08) {
		this.jdbcTemplatebged08 = jdbcTemplatebged08;
	}

	public JdbcTemplate getJdbcTemplatebged10() {
		return jdbcTemplatebged10;
	}

	public void setJdbcTemplatebged10(JdbcTemplate jdbcTemplatebged10) {
		this.jdbcTemplatebged10 = jdbcTemplatebged10;
	}

	public JdbcTemplate getJdbcTemplatebged11() {
		return jdbcTemplatebged11;
	}

	public void setJdbcTemplatebged11(JdbcTemplate jdbcTemplatebged11) {
		this.jdbcTemplatebged11 = jdbcTemplatebged11;
	}

	public JdbcTemplate getJdbcTemplatebged12() {
		return jdbcTemplatebged12;
	}

	public void setJdbcTemplatebged12(JdbcTemplate jdbcTemplatebged12) {
		this.jdbcTemplatebged12 = jdbcTemplatebged12;
	}

	public JdbcTemplate getJdbcTemplatebged13() {
		return jdbcTemplatebged13;
	}

	public void setJdbcTemplatebged13(JdbcTemplate jdbcTemplatebged13) {
		this.jdbcTemplatebged13 = jdbcTemplatebged13;
	}

	public JdbcTemplate getJdbcTemplatebged16() {
		return jdbcTemplatebged16;
	}

	public void setJdbcTemplatebged16(JdbcTemplate jdbcTemplatebged16) {
		this.jdbcTemplatebged16 = jdbcTemplatebged16;
	}

	public JdbcTemplate getJdbcTemplatebged18() {
		return jdbcTemplatebged18;
	}

	public void setJdbcTemplatebged18(JdbcTemplate jdbcTemplatebged18) {
		this.jdbcTemplatebged18 = jdbcTemplatebged18;
	}

	public JdbcTemplate getJdbcTemplatebged19() {
		return jdbcTemplatebged19;
	}

	public void setJdbcTemplatebged19(JdbcTemplate jdbcTemplatebged19) {
		this.jdbcTemplatebged19 = jdbcTemplatebged19;
	}

	public JdbcTemplate getJdbcTemplatebged20() {
		return jdbcTemplatebged20;
	}

	public void setJdbcTemplatebged20(JdbcTemplate jdbcTemplatebged20) {
		this.jdbcTemplatebged20 = jdbcTemplatebged20;
	}

	public JdbcTemplate getJdbcTemplatebged21() {
		return jdbcTemplatebged21;
	}

	public void setJdbcTemplatebged21(JdbcTemplate jdbcTemplatebged21) {
		this.jdbcTemplatebged21 = jdbcTemplatebged21;
	}

	public JdbcTemplate getJdbcTemplatebged22() {
		return jdbcTemplatebged22;
	}

	public void setJdbcTemplatebged22(JdbcTemplate jdbcTemplatebged22) {
		this.jdbcTemplatebged22 = jdbcTemplatebged22;
	}

	public JdbcTemplate getJdbcTemplatebged23() {
		return jdbcTemplatebged23;
	}

	public void setJdbcTemplatebged23(JdbcTemplate jdbcTemplatebged23) {
		this.jdbcTemplatebged23 = jdbcTemplatebged23;
	}

	public JdbcTemplate getJdbcTemplatebged24() {
		return jdbcTemplatebged24;
	}

	public void setJdbcTemplatebged24(JdbcTemplate jdbcTemplatebged24) {
		this.jdbcTemplatebged24 = jdbcTemplatebged24;
	}

	public JdbcTemplate getJdbcTemplatebged25() {
		return jdbcTemplatebged25;
	}

	public void setJdbcTemplatebged25(JdbcTemplate jdbcTemplatebged25) {
		this.jdbcTemplatebged25 = jdbcTemplatebged25;
	}

	public JdbcTemplate getJdbcTemplatebged26() {
		return jdbcTemplatebged26;
	}

	public void setJdbcTemplatebged26(JdbcTemplate jdbcTemplatebged26) {
		this.jdbcTemplatebged26 = jdbcTemplatebged26;
	}

	public JdbcTemplate getJdbcTemplatebged27() {
		return jdbcTemplatebged27;
	}

	public void setJdbcTemplatebged27(JdbcTemplate jdbcTemplatebged27) {
		this.jdbcTemplatebged27 = jdbcTemplatebged27;
	}

	public JdbcTemplate getJdbcTemplatebged28() {
		return jdbcTemplatebged28;
	}

	public void setJdbcTemplatebged28(JdbcTemplate jdbcTemplatebged28) {
		this.jdbcTemplatebged28 = jdbcTemplatebged28;
	}

	public JdbcTemplate getJdbcTemplatebged29() {
		return jdbcTemplatebged29;
	}

	public void setJdbcTemplatebged29(JdbcTemplate jdbcTemplatebged29) {
		this.jdbcTemplatebged29 = jdbcTemplatebged29;
	}

	public JdbcTemplate getJdbcTemplatebged31() {
		return jdbcTemplatebged31;
	}

	public void setJdbcTemplatebged31(JdbcTemplate jdbcTemplatebged31) {
		this.jdbcTemplatebged31 = jdbcTemplatebged31;
	}

	public JdbcTemplate getJdbcTemplatebged32() {
		return jdbcTemplatebged32;
	}

	public void setJdbcTemplatebged32(JdbcTemplate jdbcTemplatebged32) {
		this.jdbcTemplatebged32 = jdbcTemplatebged32;
	}

	// **************************************************************************register
	// en construccion
	public int register(UserControl user) {

		String sql = "INSERT INTO public.usuario (id_usuario,nombre,ape_pat,ape_mat,puesto,entidad,id_tipo_usuario,correo,password) VALUES (?,?,?,?,?,?,?,?,?)";
		return jdbcTemplatecontrol.update(sql,
				new Object[] { user.getId_usuario(), user.getNombre(), user.getApe_pat(), user.getApe_mat(),
						user.getPuesto(), user.getEntidad(), user.getId_tipo_usuario(), user.getCorreo(),
						user.getPassword() });

	}

	@Override
	public List<User> list() {
		String sql = "SELECT * FROM usuarios.usuario where entidad>0 and entidad <33";
		List<User> list = jdbcTemplateuser.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsuario(rs.getString("usuario"));
				user.setPassword(rs.getString("password"));
				user.setCorreo(rs.getString("correo"));
				user.setEntidad(rs.getInt("entidad"));
				user.setDistrito(rs.getInt("distrito"));
				user.setStatus(rs.getBoolean("status"));
				user.setId_rol(rs.getInt("id_rol"));
				user.setNombre(rs.getString("nombre"));
				user.setApe_pat(rs.getString("ape_pat"));
				user.setApe_mat(rs.getString("ape_mat"));
				return user;
			}

		});

		return list;

	}

	public List<UserControl> lista() {
		String sql = "SELECT * FROM public.usuario where entidad>0 and entidad <33 and id_tipo_usuario in (1)";
		List<UserControl> list = jdbcTemplatecontrol.query(sql, new RowMapper<UserControl>() {

			@Override
			public UserControl mapRow(ResultSet rs, int rowNum) throws SQLException {
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
		return jdbcTemplatecontrol.update(sql, new Object[] { inserta.getEntidad(), inserta.getRemesa(),
				inserta.getFecha_hora(), inserta.getId_usuario(), inserta.getId_operacion(), inserta.getId_status() });

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
	public List<info> validate(String entidad, String anio, String semana) {

		EntidadesEnum enumEntidades = null;

		String sql = "";
		String peticion = "peticion entidad: " + entidad + " anio: " + anio + " semana: " + semana;
		logger.info("metodo validate DAO: " + peticion);
        
		List<info> lisInfoException = new ArrayList<info>();
		try {

			if (entidad.equals(enumEntidades.ENTIDAD01.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD01.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ?  AND anio= ? AND semana  = ?";
				return jdbcTemplatebged01.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}

			if (entidad.equals(enumEntidades.ENTIDAD02.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD02.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged02.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}

			if (entidad.equals(enumEntidades.ENTIDAD03.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD03.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged03.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}

			if (entidad.equals(enumEntidades.ENTIDAD04.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD04.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged04.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}

			if (entidad.equals(enumEntidades.ENTIDAD05.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD05.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged05.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}

			if (entidad.equals(enumEntidades.ENTIDAD06.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD06.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged06.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}

			if (entidad.equals(enumEntidades.ENTIDAD07.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD07.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged07.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD08.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD08.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged08.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}

			if (entidad.equals(enumEntidades.ENTIDAD09.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD09.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged09.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD10.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD10.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged10.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD11.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD11.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged11.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD12.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD12.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged12.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD13.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD13.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged13.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}

			if (entidad.equals(enumEntidades.ENTIDAD14.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD14.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged14.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD15.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD15.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged15.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD16.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD16.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged16.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD17.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD17.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged17.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD18.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD18.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged18.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD19.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD19.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged19.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD20.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD20.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged20.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD21.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD21.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged21.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD22.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD22.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged22.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD23.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD23.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged23.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD24.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD24.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged24.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD25.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD25.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged25.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD26.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD26.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged26.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD27.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD27.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged27.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD28.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD28.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged28.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD29.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD29.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged29.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD30.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD30.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged30.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}
			if (entidad.equals(enumEntidades.ENTIDAD31.getEntidadId())) {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD31.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged31.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			} else {
				System.out.println("Enum  Value is: " + enumEntidades.ENTIDAD32.getEntidadId());
				sql = "SELECT * FROM app.config WHERE estado_remesa in(2,3) AND entidad = ? AND anio= ? AND semana  = ?";
				return jdbcTemplatebged32.query(sql, new Object[] { entidad, anio, semana }, new bgedMapper());
			}

		} catch (DataAccessException e) {
			info exCeption = new info();
			logger.error("DataAccessException: " + e);

			exCeption.setMsgException(e.getMessage());
			lisInfoException.add(exCeption);
			return lisInfoException;
		}

	}

	@Override
	public List<UserControl> findByUserControlAndPassword(String correo, String password) {
		correo = "%" + correo + "%";
		password = "%" + password + "%";
		String sql = "SELECT * FROM public.usuario WHERE TRIM(correo) like ? and TRIM(password) like ? and id_tipo_usuario in (1)";
		System.out.println("sql"+sql);
		return jdbcTemplatecontrol.query(sql, new Object[] { correo, password }, new UserControl2());
		
	}

	@Override
	public UserControl findBycorreo(String correo) {
		correo = "%" + correo + "%";
		String sql = "SELECT * FROM public.usuario WHERE correo like ?";
		List<UserControl> users = jdbcTemplatecontrol.query(sql, new Object[] { correo }, new UserControl2());

		return users.size() > 0 ? users.get(0) : null;

	}

	@Override
	public String buscarAdmin() {
		String sql = "SELECT id_tipo_usuario FROM public.usuario WHERE and id_tipo_usuario in (1)";
		return jdbcTemplatecontrol.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getString("id_tipo_usuario") : null;
			}
		});
	}

	@Override
	public List<UserControl> findByUserAndPassword(String correo, String password) {
		correo = "%" + correo + "%";
		password = "%" + password + "%";
		String sql = "SELECT * FROM public.usuario WHERE TRIM(correo) like ? and TRIM(password) like ?  ";
		System.out.println("sql2"+sql);
		return jdbcTemplatecontrol.query(sql, new Object[] { correo, password }, new UserControl2());
	}

	@Override
	public User findByUsercorreo(String correo) {
		correo = "%" + correo + "%";
		String sql = "SELECT * FROM usuarios.usuario WHERE correo like ?";
		List<User> users = jdbcTemplateuser.query(sql, new Object[] { correo }, new UserMapper());
		return users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public void delete(String correo) {
		correo = "%" + correo + "%";
		String sql = "DELETE FROM public.usuario WHERE CORREO  like ? and id_tipo_usuario in (1)";
		jdbcTemplatecontrol.update(sql, new Object[] { correo });

	}

	@Override
	public void deleteAut(String token) {
		String sql = "DELETE FROM public.autorizacion WHERE token= ?";
		jdbcTemplatecontrol.update(sql, new Object[] { token });

	}

	@Override
	public List<User> listaFiltrada(int entidad) {
		String sql = "SELECT * FROM usuarios.usuario WHERE entidad = ?";
		return jdbcTemplateuser.query(sql, new Object[] { entidad }, new UserMapper());
	}

	@Override
	public List<statusError> listaStatus(String fecha) {
		fecha = "%" + fecha + "%";
		String sql = "SELECT distinct*  FROM public.statuserror where fecha like ? ORDER BY fecha DESC LIMIT 1";
		return jdbcTemplatecontrol.query(sql, new Object[] { fecha }, new ErrorMapper());

	}

	@Override
	public int register(statusError status) {
		String sql = "INSERT INTO public.statusError (entidad,remesa,fecha,error) VALUES (?,?,?,?)";
		return jdbcTemplatecontrol.update(sql,
				new Object[] { status.getEntidad(), status.getRemesa(), status.getFecha(), status.getError() });

	}

	@Override
	public statusError findByfecha(String error, String fecha) {
		error = "%" + error + "%";
		fecha = "%" + fecha + "%";
		String sql = "SELECT distinct * FROM public.statuserror where fecha is not null and error like ? and fecha like ?";
		List<statusError> users = jdbcTemplatecontrol.query(sql, new Object[] { error, fecha }, new ErrorMapper());
		return users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public statusError buscarfecha(String fecha) {
		fecha = "%" + fecha + "%";
		String sql = "SELECT distinct * FROM public.statuserror where  fecha like ? ORDER BY fecha DESC LIMIT 1";
		List<statusError> users = jdbcTemplatecontrol.query(sql, new Object[] { fecha }, new ErrorMapper());
		return users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public Map<Integer, String> entidadesActivas() {
		String sql = "select entidad,nombre from public.entidad where activa in (0) ";
		Map entiades = new HashMap();
		entiades = jdbcTemplatecontrol.query(sql, new ResultSetExtractor<Map>() {
			@Override
			public Map extractData(ResultSet rs) throws SQLException, DataAccessException {
				HashMap<Integer, String> mapRet = new HashMap<Integer, String>();
				while (rs.next()) {
					mapRet.put(rs.getInt("entidad"), rs.getString("nombre"));
				}
				return mapRet;
			}
		});

		return entiades;
	}

	@Override
	public String obtieneNombreEntidad(String idEntidad) {
		String sql = "select nombre from public.entidad where entidad=:entidad";
		Integer claveEntida = Integer.parseInt(idEntidad);
		SqlParameterSource param = new MapSqlParameterSource("entidad", claveEntida);

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplatecontrol);
		String nombreEntidad = template.query(sql, param, new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				String nombre = "";
				while (rs.next()) {
					nombre = rs.getString("nombre");
				}
				return nombre;
			}
		});
		return nombreEntidad;
	}

}
