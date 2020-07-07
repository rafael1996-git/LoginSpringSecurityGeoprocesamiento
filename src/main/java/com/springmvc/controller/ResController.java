package com.springmvc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.uuid.Generators;
import com.springmvc.model.LoginControl;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;
import com.springmvc.service.UserService;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
public class ResController {
	@Autowired
	public UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/remesa", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView RemesaPage(HttpServletRequest reques, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		UUID token = Generators.randomBasedGenerator().generate();
		Date objDate = new Date();
		// Mostrar la fecha y la hora usando toString ()
		System.out.println(objDate.toString());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		objDate.equals(dateFormat.format(objDate));
		System.out.println(dateFormat.format(objDate));
		try {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!UUID : " + token);
			HttpSession session = reques.getSession();
			int id = (int) session.getAttribute("id");
			int entidad = (int) session.getAttribute("entidad");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!id : " + id);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!entidad : " + entidad);
			String opj;
			opj = userService.buscarRemesa();
			if (opj.isEmpty() || opj == null) {
				List<User> listaPersonas = userService.list();
				reques.setAttribute("lista", listaPersonas);
				System.out.println("funcion Remesa no realizada por el rango de fechas: ");
				model.setViewName("/users/Remesa");
				model.addObject("mensaje2", "¡");
			} else {

				Remesa opjRemesa = new Remesa();
				opjRemesa.setId_status(1);
				opjRemesa.setFecha_hora(objDate);
				opjRemesa.setToken(token.toString());
				opjRemesa.setId_usuario(id);
				opjRemesa.setEntidad_remesa(opj.toString());
				userService.regisRemesa(opjRemesa);

				System.out.println("_______________________________________________[entidad : "+session.getAttribute("entidad").toString());

				System.out
						.println("*************************************************************************[HEADER : ");

				OkHttpClient client = new OkHttpClient().newBuilder().build();
				HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8080/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
				urlBuilder.addQueryParameter("entidad", session.getAttribute("entidad").toString());
				urlBuilder.addQueryParameter("remesa", opj.toString());
				String url = urlBuilder.build().toString();
				Request request = new Request.Builder().url(url).header("Authorization", token.toString()).build();

				try {
					Response respons = client.newCall(request).execute();
					String stringHeader = respons.headers().toString();
					String string = respons.body().string();
					System.out.println("_________________respuesta-coneccion-code___________________" + respons);
					System.out.println("_______________vista-previa-body_____________________" + string);
					System.out.println("________________string-Header____________________" + stringHeader);
				} catch (IOException e) {
					System.err.println("Failed scraping");
					e.printStackTrace();
				}

				System.out.println(
						"*************************************************************************[HEADER2 : ");

				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!inserta : " + opjRemesa);
				List<User> listaPersonas = userService.list();
				reques.setAttribute("lista", listaPersonas);
				model.setViewName("/users/Remesa");
				model.addObject("mensaje", "¡");
			}
		} catch (Exception ex) {
			List<User> listaPersonas = userService.list();
			reques.setAttribute("lista", listaPersonas);
			System.out.println("funcion Remesa no realizada :Exception  ");
			model.addObject("mensaje2", "¡");
			model.setViewName("/users/Remesa");
			ex.getMessage();
		}
		return model;
	}
}