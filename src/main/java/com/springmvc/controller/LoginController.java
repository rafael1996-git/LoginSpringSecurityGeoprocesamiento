package com.springmvc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.User;
import com.springmvc.model.UserControl;
import com.springmvc.model.statusError;
import com.springmvc.service.UserService;

@RestController
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

	@RequestMapping(value = "/viewAdmin", method = RequestMethod.GET)
	public ModelAndView atrasPage(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		List<UserControl> listaPersonas = userService.lista();

		request.setAttribute("lista", listaPersonas);
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
	public ModelAndView WelcomePage(HttpServletRequest request) throws IOException {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		int entidadUsuario = (int) session.getAttribute("entidad");
		List<User> listaPersonas = userService.listaFiltrada(entidadUsuario);

		request.setAttribute("lista", listaPersonas);

		model.setViewName("/users/adminC");
		return model;

	}
	
	@RequestMapping("/MultiProcesos")
	public ModelAndView MultiProcesosPage(HttpServletRequest request) throws IOException {
		ModelAndView model = new ModelAndView();

		model.setViewName("/users/multiprocesos");
		System.out.println("multiproceso get: "+model.getViewName());

		return model;

	}

	@RequestMapping( "/statusError")
	public @ResponseBody List<statusError> statusPage(HttpServletRequest request,@RequestParam("datoE") String dato){
		ModelAndView model = new ModelAndView();
			 LocalDate myObj = LocalDate.now();  // Create a date object
			    System.out.println(myObj); 
				List<statusError>  lista = userService.listaStatus(myObj.toString());

							statusError buscafecha=userService.buscarfecha(myObj.toString());
							if (buscafecha!=null && dato!=null) {
								System.out.println("_______________si hay lista_____________________");

								return lista;								
								
							} else {	
								System.out.println("_______________null_____________________de statusError");

								return null;
							
							}

		

	}

	@RequestMapping(value = { "/", "/users/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "wrongcaptcha", required = false) String wrongcaptcha, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView();
		if (wrongcaptcha != null) {
			model.addObject("error", "Invalid captcha!");
		}

		if (error != null) {
			model.addObject("error", "Invalid username or passwor!");
		}

		if (logout != null) {
			model.addObject("message", "You've been logged out successfully.");
		}
		model.setViewName("/users/login");

		return model;
	}

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(@RequestParam String password, @RequestParam String correo,
			HttpServletRequest request) {
		ModelAndView mav = null;
		List<UserControl> listaP = userService.lista();

		System.out.println("en home");
		logger.info("Mensaje de vista:******************************** ");

		try {
			List<UserControl> usuarioControl = userService.findByUserControlAndPassword(correo, password);
			UserControl uControl = userService.findBycorreo(correo);
			
			if (usuarioControl != null && !usuarioControl.isEmpty()) {

				HttpSession session = request.getSession();
				session.setAttribute("firstname", uControl.getCorreo());
				session.setAttribute("id", uControl.getId_usuario());
				session.setAttribute("entidad", uControl.getEntidad());
				session.setAttribute("tipo", uControl.getId_tipo_usuario());
				List<User> listaPersonas = userService.listaFiltrada(uControl.getEntidad());
				request.setAttribute("lista", listaPersonas);
				mav = new ModelAndView("/users/adminC");

			} else if (uControl.getId_tipo_usuario() == 2) {
				HttpSession session = request.getSession();
				session.setAttribute("firstname", uControl.getCorreo());
				request.setAttribute("lista", listaP);
				mav = new ModelAndView("/users/admin");
			} else if (uControl.getId_tipo_usuario() == 3) {
				Map<Integer,String> listEntidadesActivas = userService.entidadesActivas();
				
				HttpSession session = request.getSession();
				session.setAttribute("firstname", uControl.getCorreo());
				session.setAttribute("id", uControl.getId_usuario());
				session.setAttribute("entidad", uControl.getEntidad());
				session.setAttribute("tipo", uControl.getId_tipo_usuario());
	            session.setAttribute("entidadesActivas",listEntidadesActivas);
				mav = new ModelAndView("/users/multiprocesos");
				
				
			}else {
				mav = new ModelAndView("/users/login");
				mav.addObject("error", "Invalid username and password!");
				
			}

		} catch (UsernameNotFoundException ex) {
			ex.printStackTrace();
			mav = new ModelAndView("/users/login");
			mav.addObject("error", "Invalid username and password!");
		} catch (Exception ex) {
			ex.printStackTrace();
			mav = new ModelAndView("/users/login");
			mav.addObject("error", "Invalid username and password! ");
		}
		System.out.println("_______________dataStore_____________________insert:");
		return mav;

	}

	@RequestMapping(value = "/estatus", method = { RequestMethod.POST })
	@ResponseBody
	public String ejecutaEstatus(HttpServletRequest reques) throws NumberFormatException, ParseException, IOException {
		ModelAndView model = new ModelAndView();
		statusError opjeto = new statusError();
		Date objDate = new Date();
		// Mostrar la fecha y la hora usando toString ()
		System.out.println(objDate.toString());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		objDate.equals(dateFormat.format(objDate));
		System.out.println(dateFormat.format(objDate));
		HttpSession session = reques.getSession();
		int id = (int) session.getAttribute("id");
		int entidadUsuario = (int) session.getAttribute("entidad");
		String opj = userService.buscarRemesa();
		System.out.println("query service buscarRemesa: " + opj);

		String listaEstatus = getEstatusRemesa(entidadUsuario, Integer.parseInt(opj));

		String listaEstatusError = getEstatusError(entidadUsuario, Integer.parseInt(opj));
		return "{\"errordata\":" + listaEstatusError.toString() + ",\"alejandro\":" + listaEstatus + "}";

	}

	public String getEstatusRemesa(int entidad, int remesa) throws ParseException {

		String requestUri = "http://localhost:8180/GenerarRemesa/dce/GenerarEntidad/status?entidad={entidad}&remesa={remesa}";
		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("entidad", Integer.toString(entidad));
		urlParameters.put("remesa", Long.toString(remesa));

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> entity = template.getForEntity(requestUri, String.class, urlParameters);

		List<Integer> grafica = new ArrayList<Integer>();

		if (entity.getStatusCode().value() == 200) {

			JSONObject js = new JSONObject(entity.getBody());
			if (js != null || !js.isEmpty()) {
				for (Object o : js.getJSONArray("operacionesEjecutadas")) {

					JSONObject ca = (JSONObject) o;
					JSONObject span = (JSONObject) ca.get("operaciones");
					int idOp = span.getInt("idOperacion");
					grafica.add(idOp);
					System.out.println(
							"*************************************************************************[operacionid"
									+ idOp);

				}

				return grafica.toString();

			} else {
				return null;
			}
		} else {

			System.out.println("Estatus del servicio : " + entity.getStatusCode().toString());
			return entity.getStatusCode().getReasonPhrase();
		}

	}

	@SuppressWarnings("null")
	public String getEstatusError(int entidad, int remesa) throws ParseException {
//		statusError opjeto = new statusError();
		Date objDate = new Date();
		// Mostrar la fecha y la hora usando toString ()
		System.out.println(objDate.toString());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		objDate.equals(dateFormat.format(objDate));
		System.out.println(dateFormat.format(objDate));
		String requestUri = "http://localhost:8180/GenerarRemesa/dce/GenerarEntidad/status?entidad={entidad}&remesa={remesa}";
		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("entidad", Integer.toString(entidad));
		urlParameters.put("remesa", Long.toString(remesa));
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> entity = template.getForEntity(requestUri, String.class, urlParameters);
		List<String> grafica = new ArrayList<String>();
		String string = entity.getBody();
		JSONObject Jobject = new JSONObject(string);
		JSONArray Jarray = Jobject.getJSONArray("operacionesConError");
			System.out.println("_______________vista--body-operacionesConError_____________________" + Jarray.toString());
			String fecha, err;
			int limit = Jarray.length();
			String dataStore[] = new String[limit];
			
			for (int i = 0; i < limit; i++) {
				JSONObject object = Jarray.getJSONObject(i);

				JSONObject ca = (JSONObject) object;
				JSONObject span = (JSONObject) ca.get("operaciones");
				int idOp = span.getInt("idOperacion");
				fecha = object.getString("sFechaHora");
				err = object.getString("error");
				int enti = object.getInt("entidad");
				int reme = object.getInt("remesa");
				statusError opjeto= new statusError();
				opjeto.setEntidad(enti);
				opjeto.setRemesa(reme);
				opjeto.setFecha(fecha);
				opjeto.setError(err);
				statusError status = userService.findByfecha(err.toString(), fecha.toString());
				if (status != null) {
					System.out.println("_______________idoperacion_____________________insert:"+idOp);

				} else if (opjeto != null) {
					userService.register(opjeto);
			
				}
				String var="=";
				System.out.println("_______________dataStore_____________________insert-opjeto:"+err.toString());
				dataStore[i] = "{\"iden\":\"" + enti +"\",\"idre\":\""+null+"\",\"idfe\":\""+null+"\",\"error\":\"Se ha Encontrado un Error Checkout"+var+"\"}";

			}

			// prove that the data was stored in the array
			for (String content : dataStore) {
				System.out.println("_______________dataStore_____________________ARRAY CONTENT:" + content);
			grafica.add(content);	
			}
			
			return grafica.toString();

	}
	
	
	
}
