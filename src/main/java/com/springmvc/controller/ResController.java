package com.springmvc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.uuid.Generators;
import com.springmvc.model.Control;
import com.springmvc.model.Numero;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.info;
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
	public ModelAndView RemesaPage(HttpServletRequest reques, HttpServletResponse response)  {
		ModelAndView model = new ModelAndView();
		UUID token = Generators.randomBasedGenerator().generate();
		Date objDate = new Date();
		// Mostrar la fecha y la hora usando toString ()
		System.out.println(objDate.toString());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		objDate.equals(dateFormat.format(objDate));
		System.out.println(dateFormat.format(objDate));
		try {
			HttpSession session = reques.getSession();
			int id = (int) session.getAttribute("id");
			int entidad = (int) session.getAttribute("entidad");
			String opj;
			opj = userService.buscarRemesa();
			String anio = opj.substring(0,4);
			String semana = opj.substring(4,6);
			System.out.println(anio);
			System.out.println(semana);

			List<info> var =userService.validate( session.getAttribute("entidad").toString(), anio, semana);
			System.out.println("-----------antes del if"+""+""+session.getAttribute("entidad").toString()+""+ anio+""+semana);
			
			if (var!=null && !var.isEmpty()&&userService.validate( session.getAttribute("entidad").toString(), anio, semana)!=null) {
				System.out.println("despues del if"+""+""+session.getAttribute("entidad").toString()+""+ anio+""+semana);
					//****************************************************insertamos a la tabla autorizacion
					Remesa opjRemesa = new Remesa();
					opjRemesa.setId_status(1);
					opjRemesa.setFecha_hora(objDate);
					opjRemesa.setToken(token.toString());
					opjRemesa.setId_usuario(id);
					opjRemesa.setEntidad_remesa(opj.toString());
					userService.regisRemesa(opjRemesa);
					
					//****************************************************insertamos a la tabla control
					Control opjControl=new Control();
					
					for (int j = 0; j <=126; j++) {
						opjControl.setEntidad(entidad);
						opjControl.setRemesa(Integer.parseInt(opj.toString()));
						opjControl.setFecha_hora(objDate);
						opjControl.setId_usuario(id);
						opjControl.setId_operacion(j);
						opjControl.setId_status(1);
						userService.register(opjControl);
					}

					System.out
							.println("*************************************************************************[HEADER : ");

					OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(120, TimeUnit.SECONDS)
							.readTimeout(120, TimeUnit.SECONDS)
							.writeTimeout(120, TimeUnit.SECONDS).build();
					HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
					urlBuilder.addQueryParameter("entidad", session.getAttribute("entidad").toString());
					urlBuilder.addQueryParameter("remesa", opj.toString());
					String url = urlBuilder.build().toString();
					Request request = new Request.Builder().url(url).header("Authorization", token.toString()).build();

					try {
						Response respons = client.newCall(request).execute();
						String stringHeader = respons.headers().toString();
						String string = respons.body().string();
					} catch (IOException e) {
						System.err.println("Failed scraping");
						List<User> listaPersonas =userService.listaFiltrada(entidad);
						reques.setAttribute("lista", listaPersonas);
						System.out.println("funcion Remesa no realizada por que no esta levantado el servicio : ");
						model.addObject("mensaje1", "¡"+e.getCause().toString());
						model.setViewName("/users/Remesa");
						e.printStackTrace();
					}

					System.out.println(
							"*************************************************************************[HEADER2 : ");

					List<User> listaPersonas =userService.listaFiltrada(entidad);
					reques.setAttribute("lista", listaPersonas);
					model.setViewName("/users/Remesa");
					model.addObject("mensaje", "¡");
			
				
			}else {				
			System.out.println("funcion Remesa no realizada :else  ");
			List<User> listaPersonas =userService.listaFiltrada(entidad);
			reques.setAttribute("lista", listaPersonas);
			model.addObject("mensaje2", "¡");
			model.setViewName("/users/Remesa");
			}
		} catch (Exception e) {
			e.printStackTrace();
			HttpSession session = reques.getSession();
			int entidad = (int) session.getAttribute("entidad");
			List<User> listaPersonas =userService.listaFiltrada(entidad);
			reques.setAttribute("lista", listaPersonas);
			System.out.println("funcion Remesa no realizada :Exception  ");
			model.addObject("mensaje1", "¡"+e.getCause().toString());
			model.setViewName("/users/Remesa");
			e.getMessage();
		}
		return model;
	}
	
	@RequestMapping("/multiremesaExecute")
	@ResponseBody
	public ModelAndView editCustomer(HttpServletRequest reques, HttpServletResponse response,@ModelAttribute("valor")Numero numero) 
	{
		ModelAndView model = new ModelAndView();
		UUID token = Generators.randomBasedGenerator().generate();
		Date objDate = new Date();
		// Mostrar la fecha y la hora usando toString ()
		System.out.println(objDate.toString());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		objDate.equals(dateFormat.format(objDate));
		System.out.println(dateFormat.format(objDate));
		System.out
		.println("******************************************************************************************************************[checked :antes del try ");
		try {
			HttpSession session = reques.getSession();
			int id = (int) session.getAttribute("id");
			String opj;
			opj = userService.buscarRemesa();
			String anio = opj.substring(0,4);
			String semana = opj.substring(4,6);
			System.out.println(anio);
			System.out.println(semana);
			String [] itera= {numero.getNum1(),numero.getNum2(),numero.getNum3(),numero.getNum4(),numero.getNum5(),
					          numero.getNum6(),numero.getNum7(),numero.getNum8(),numero.getNum9(),numero.getNum10(),
					          numero.getNum11(),numero.getNum12(),numero.getNum13(),numero.getNum14(),numero.getNum15(),
					          numero.getNum16(),numero.getNum17(),numero.getNum18(),numero.getNum19(),numero.getNum20(),
					          numero.getNum21(),numero.getNum22(),numero.getNum23(),numero.getNum24(),numero.getNum25(),
					          numero.getNum26(),numero.getNum27(),numero.getNum28(),numero.getNum29(),numero.getNum30(),
					          numero.getNum31(),numero.getNum32()};
			for (int i = 0; i <=31; i++) {
				if (itera[i]!=null) {
					
					    System.out.println("checkbox is checked  "+itera[i]);
					    List<info> var =userService.validate( itera[i], anio, semana);
						System.out.println("---------------------------------------------------------------------------antes del if-----"+""+""+itera[i]+""+ anio+""+semana);
						if (var!=null && !var.isEmpty()&&userService.validate( itera[i], anio, semana)!=null) {
							System.out.println("despues del if---------------------------------------------------------"+""+""+itera[i]+""+ anio+""+semana);
								//****************************************************insertamos a la tabla autorizacion
								Remesa opjRemesa = new Remesa();
								opjRemesa.setId_status(1);
								opjRemesa.setFecha_hora(objDate);
								opjRemesa.setToken(token.toString());
								opjRemesa.setId_usuario(id);
								opjRemesa.setEntidad_remesa(opj.toString());
								userService.regisRemesa(opjRemesa);
								//****************************************************insertamos a la tabla control
								Control opjControl=new Control();
								for (int j = 1; j <=126; j++) {
									opjControl.setEntidad(Integer.parseInt(itera[i]));
									opjControl.setRemesa(Integer.parseInt(opj.toString()));
									opjControl.setFecha_hora(objDate);
									opjControl.setId_usuario(id);
									opjControl.setId_operacion(j);
									opjControl.setId_status(1);
									userService.register(opjControl);
								}
								System.out
										.println("*******************************************************************************************************************[HEADER : ");
								OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(120, TimeUnit.SECONDS)
										.readTimeout(120, TimeUnit.SECONDS)
										.writeTimeout(120, TimeUnit.SECONDS).build();
								HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
								urlBuilder.addQueryParameter("entidad", itera[i]);
								urlBuilder.addQueryParameter("remesa", opj.toString());
								String url = urlBuilder.build().toString();
								Request request = new Request.Builder().url(url).header("Authorization", token.toString()).build();
								try {
									Response respons = client.newCall(request).execute();
									String stringHeader = respons.headers().toString();
									String string = respons.body().string();
								} catch (IOException e) {
									System.err.println("Failed scraping");
									System.out.println("funcion Remesa no realizada por que no esta levantado el servicio : ");
									model.addObject("multimensaje1", "¡"+e.getCause().toString());
									model.setViewName("/users/multiprocesos");
									e.printStackTrace();
								}

								System.out.println(
										"**********************************************************************************************************************[HEADER2 : ");
								model.setViewName("/users/multiprocesos");
								model.addObject("Multimensaje", "¡");	
						}else {				
						System.out.println("funcion Remesa no realizada :else  ");
						model.addObject("multimensaje2", "¡");
						model.setViewName("/users/multiprocesos");
						}

					}
				
					System.out.println("valor encontrado  "+itera[i]);

				} 
				
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("funcion Remesa no realizada :Exception  ");
			model.addObject("multimsj", "¡"+e.getCause().toString());
			model.setViewName("/users/multiprocesos");
			e.getMessage();
		}
		return model;

	}
}
