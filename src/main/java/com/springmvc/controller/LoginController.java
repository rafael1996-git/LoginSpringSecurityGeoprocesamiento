package com.springmvc.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.uuid.Generators;
import com.springmvc.model.Login;
import com.springmvc.model.LoginControl;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;
import com.springmvc.service.UserService;

@Controller
public class LoginController {

	protected static final Log logger = LogFactory.getLog(LoginController.class.getName());
	@Autowired
	public UserService userService;

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		// To do something
		model.setViewName("users/admin");
		return model;

	}

	@RequestMapping(value = "/atras", method = RequestMethod.GET)
	public ModelAndView atrasPage() {

		ModelAndView model = new ModelAndView();
		// To do something
		model.setViewName("/users/admin");
		return model;

	}

	@RequestMapping(value = "/Avance", method = RequestMethod.GET)
	public ModelAndView AvancePage() {

		ModelAndView model = new ModelAndView();
		// To do something
		model.setViewName("/users/mostrarAvance");

		return model;

	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView WelcomePage(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		List<User> listaPersonas = userService.list();
		request.setAttribute("lista", listaPersonas);

		// To do something
		model.setViewName("/users/adminC");
		return model;

	}

	@RequestMapping(value = { "/", "/users/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "wrongcaptcha", required = false) String wrongcaptcha, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView();
		model.addObject("loginForm", new Login());
		model.addObject("loginForm", new UserControl());
		if (wrongcaptcha != null) {
			model.addObject("error", "Invalid captcha!");
		}

		if (error != null) {
			model.addObject("error", "Invalid username or password!");
		}

		if (logout != null) {
			model.addObject("message", "You've been logged out successfully.");
		}
		model.setViewName("/users/login");

		return model;
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(LoginControl loginC, LoginControl loginC2, HttpServletRequest request,
			@RequestParam String correo, Login login, @RequestParam String password, Login login2) {
		ModelAndView mav =null;
		List<User> listaPersonas =userService.list();

		System.out.println("en home");
		logger.info("Mensaje de vista:******************************** ");

		try {
			User user = userService.validateUser(login, login2);
			UserControl userControl = userService.validateUserControl(loginC, loginC2);

			if (loginC.equals(correo) == true || loginC2.equals(password) == true || userControl != null) {
				

				HttpSession session = request.getSession();
				session.setAttribute("firstname", userControl.getCorreo());
				session.setAttribute("id", userControl.getId_usuario());
				session.setAttribute("entidad", userControl.getEntidad());

				request.setAttribute("lista", listaPersonas);
				mav = new ModelAndView("/users/adminC");
				System.out.println("USUARIO VALIDO DE LOGIN CONTROL______________________: "+userControl.getCorreo());

			} else if (login.equals(correo) == true || login2.equals(password) == true || user != null) {
				user = userService.validateUser(login, login2);
				HttpSession session = request.getSession();
				session.setAttribute("firstname", user.getCorreo());
				
				mav = new ModelAndView("/users/admin");
				System.out.println("USUARIO VALIDO DE LOGIN :::::::::::::::::::::::::::: "+user.getCorreo());
			} else {

				mav = new ModelAndView("/users/login");
				mav.addObject("error", "Invalid username and password!");
			}

		} catch (UsernameNotFoundException ex) {
			mav = new ModelAndView("/users/login");
			mav.addObject("error", "Invalid username and password!");
		} catch (Exception ex) {
			mav = new ModelAndView("/users/login");
			mav.addObject("error", "Invalid username and password!");
		}

		return mav;

	}

	@RequestMapping(value = "/estatus", method = { RequestMethod.POST })
	@ResponseBody
	public String ejecutaEstatus(Model model,HttpServletRequest reques) throws NumberFormatException, ParseException {
		UUID token = Generators.randomBasedGenerator().generate();
		Date objDate = new Date();
		// Mostrar la fecha y la hora usando toString ()
		System.out.println(objDate.toString());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		objDate.equals(dateFormat.format(objDate));
		HttpSession session = reques.getSession();
		int id = (int) session.getAttribute("id");
		int entidadUsuario = (int) session.getAttribute("entidad");
		String opj;
		opj = userService.buscarRemesa();
		System.out.println("query service buscarRemesa: "+opj);

			Remesa opjRemesa = new Remesa();
			opjRemesa.setId_status(1);
			opjRemesa.setFecha_hora(objDate);
			opjRemesa.setToken(token.toString());
			opjRemesa.setId_usuario(id);
			opjRemesa.setEntidad_remesa(opj.toString());
			userService.regisRemesa(opjRemesa);
			
		String listaEstatus = getEstatusRemesa(entidadUsuario, Integer.parseInt(opj));
		return listaEstatus;
	}

	public String getEstatusRemesa(int entidad, int remesa) throws ParseException {

		String requestUri = "http://localhost:8080/GenerarRemesa/dce/GenerarEntidad/status?entidad={entidad}&remesa={remesa}";
		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("entidad", Integer.toString(entidad));
		urlParameters.put("remesa", Long.toString(remesa));

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> entity = template.getForEntity(requestUri, String.class, urlParameters);

		System.out.println("Estatus del servicio : " + entity.getStatusCodeValue());
		System.out.println("Estatus del servicio body : " + entity.getBody());
		System.out.println("Estatus del servicio toString : " + entity.toString());
		List<Integer> grafica = new ArrayList<Integer>();
		if (entity.getStatusCode().value() == 200) {
			String s = entity.getBody();
			JSONObject js = new JSONObject(entity.getBody());
			System.out.println("Estatus del servicio : " + js.toString());
			if(js!=null||!js.isEmpty()) {
			for (Object o : js.getJSONArray("operacionesEjecutadas")) {
				JSONObject ca = (JSONObject) o;
				JSONObject span = (JSONObject) ca.get("operaciones");
				int idOp = span.getInt("idOperacion");
				grafica.add(idOp);
			}

			return grafica.toString();
				
			}else {
				return null;
			}
		} else {
			System.out.println("Estatus del servicio : " + entity.getStatusCode().toString());
			return entity.getStatusCode().getReasonPhrase() ;
		}

	}
}
