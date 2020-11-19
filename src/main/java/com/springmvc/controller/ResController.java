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
	@RequestMapping("/MultiRemesa")
	@ResponseBody
	public ModelAndView MultiRemesaPage(HttpServletRequest reques, HttpServletResponse response,@ModelAttribute("valor")Numero numero  )  {
		ModelAndView model = new ModelAndView();
		UUID token = Generators.randomBasedGenerator().generate();
		Date objDate = new Date();
		// Mostrar la fecha y la hora usando toString ()
		System.out.println(objDate.toString());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		objDate.equals(dateFormat.format(objDate));
		System.out.println(dateFormat.format(objDate));
		System.out
		.println("*************************************************************************[checked :antes del try ");
		try {
			HttpSession session = reques.getSession();
			int id = (int) session.getAttribute("id");
			 System.out
			.println("*************************************************************************[checked : "+numero.toString());
			String opj;
			opj = userService.buscarRemesa();
			String anio = opj.substring(0,4);
			String semana = opj.substring(4,6);
			System.out.println(anio);
			System.out.println(semana);
			
			if (numero.getNum17()!=null) {
			    System.out.println("checkbox is checked  "+numero.getNum17());
			    List<info> var =userService.validate( numero.getNum17(), anio, semana);
				System.out.println("-----------antes del if-----17"+""+""+numero.getNum17()+""+ anio+""+semana);
				if (var!=null && !var.isEmpty()&&userService.validate( numero.getNum17(), anio, semana)!=null) {
					System.out.println("despues del if----17"+""+""+numero.getNum17()+""+ anio+""+semana);
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
							opjControl.setEntidad(Integer.parseInt(numero.getNum17()));
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
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum17());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}

			} 
			if (numero.getNum15()!=null) {
				System.out.println("checkbox is checked2  "+numero.getNum15());
				List<info> var =userService.validate15( numero.getNum15(), anio, semana);
				System.out.println("-----------antes del if----15"+""+""+numero.getNum15()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate15( numero.getNum15(), anio, semana)!=null) {
					System.out.println("despues del if----15"+""+""+numero.getNum15()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 1; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum15()));
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
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum15());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum14()!=null) {
				System.out.println("checkbox is checked2  "+numero.getNum14());
				List<info> var =userService.validate14( numero.getNum14(), anio, semana);
				System.out.println("-----------antes del if----14"+""+""+numero.getNum14()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate14( numero.getNum14(), anio, semana)!=null) {
					System.out.println("despues del if---14"+""+""+numero.getNum14()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 1; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum14()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum14());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum9()!=null) {
				System.out.println("checkbox is checked2  "+numero.getNum9());
				List<info> var =userService.validate09( numero.getNum9(), anio, semana);
				System.out.println("-----------antes del if---9"+""+""+numero.getNum9()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate09( numero.getNum9(), anio, semana)!=null) {
					System.out.println("despues del if---9"+""+""+numero.getNum9()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 1; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum9()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum9());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum30()!=null) {
				System.out.println("checkbox is checked2  "+numero.getNum30());
				List<info> var =userService.validate30( numero.getNum30(), anio, semana);
				System.out.println("-----------antes del if--30"+""+""+numero.getNum30()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate30( numero.getNum30(), anio, semana)!=null) {
					System.out.println("despues del if----30"+""+""+numero.getNum30()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 1; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum30()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum30());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			
			if (numero.getNum1()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum1());
				List<info> var =userService.validate01( numero.getNum1(), anio, semana);
				System.out.println("-----------antes del if--01"+""+""+numero.getNum1()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate01( numero.getNum1(), anio, semana)!=null) {
					System.out.println("despues del if----01"+""+""+numero.getNum1()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum1()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum1());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum2()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum2());
				List<info> var =userService.validate02( numero.getNum2(), anio, semana);
				System.out.println("-----------antes del if--02"+""+""+numero.getNum2()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate02( numero.getNum2(), anio, semana)!=null) {
					System.out.println("despues del if----02"+""+""+numero.getNum2()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum2()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum2());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum3()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum3());
				List<info> var =userService.validate03( numero.getNum3(), anio, semana);
				System.out.println("-----------antes del if--03"+""+""+numero.getNum3()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate03( numero.getNum3(), anio, semana)!=null) {
					System.out.println("despues del if----03"+""+""+numero.getNum3()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum3()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum3());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum4()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum4());
				List<info> var =userService.validate04( numero.getNum4(), anio, semana);
				System.out.println("-----------antes del if--04"+""+""+numero.getNum4()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate04( numero.getNum4(), anio, semana)!=null) {
					System.out.println("despues del if----04"+""+""+numero.getNum4()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum4()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum4());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum5()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum5());
				List<info> var =userService.validate05( numero.getNum5(), anio, semana);
				System.out.println("-----------antes del if--05"+""+""+numero.getNum5()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate05( numero.getNum5(), anio, semana)!=null) {
					System.out.println("despues del if----05"+""+""+numero.getNum5()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum5()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum5());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum6()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum6());
				List<info> var =userService.validate06( numero.getNum6(), anio, semana);
				System.out.println("-----------antes del if--06"+""+""+numero.getNum6()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate06( numero.getNum6(), anio, semana)!=null) {
					System.out.println("despues del if----06"+""+""+numero.getNum6()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum6()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum6());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum7()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum7());
				List<info> var =userService.validate07( numero.getNum7(), anio, semana);
				System.out.println("-----------antes del if--07"+""+""+numero.getNum7()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate07( numero.getNum7(), anio, semana)!=null) {
					System.out.println("despues del if----07"+""+""+numero.getNum7()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum7()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum7());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum8()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum8());
				List<info> var =userService.validate08( numero.getNum8(), anio, semana);
				System.out.println("-----------antes del if--08"+""+""+numero.getNum8()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate08( numero.getNum8(), anio, semana)!=null) {
					System.out.println("despues del if----08"+""+""+numero.getNum8()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum8()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum8());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum10()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum10());
				List<info> var =userService.validate10( numero.getNum10(), anio, semana);
				System.out.println("-----------antes del if--10"+""+""+numero.getNum10()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate10( numero.getNum10(), anio, semana)!=null) {
					System.out.println("despues del if----10"+""+""+numero.getNum10()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum10()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum10());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum11()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum11());
				List<info> var =userService.validate11( numero.getNum11(), anio, semana);
				System.out.println("-----------antes del if--11"+""+""+numero.getNum11()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate11( numero.getNum11(), anio, semana)!=null) {
					System.out.println("despues del if----11"+""+""+numero.getNum11()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum11()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum11());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum12()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum12());
				List<info> var =userService.validate12( numero.getNum12(), anio, semana);
				System.out.println("-----------antes del if--12"+""+""+numero.getNum12()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate12( numero.getNum12(), anio, semana)!=null) {
					System.out.println("despues del if----12"+""+""+numero.getNum12()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 1; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum12()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum12());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum13()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum13());
				List<info> var =userService.validate13( numero.getNum13(), anio, semana);
				System.out.println("-----------antes del if--13"+""+""+numero.getNum13()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate13( numero.getNum13(), anio, semana)!=null) {
					System.out.println("despues del if----13"+""+""+numero.getNum13()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum13()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum13());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum16()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum16());
				List<info> var =userService.validate16( numero.getNum16(), anio, semana);
				System.out.println("-----------antes del if--16"+""+""+numero.getNum16()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate16( numero.getNum16(), anio, semana)!=null) {
					System.out.println("despues del if----16"+""+""+numero.getNum16()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum16()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum16());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum18()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum18());
				List<info> var =userService.validate18( numero.getNum18(), anio, semana);
				System.out.println("-----------antes del if--18"+""+""+numero.getNum18()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate18( numero.getNum18(), anio, semana)!=null) {
					System.out.println("despues del if----18"+""+""+numero.getNum18()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum18()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum18());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum19()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum19());
				List<info> var =userService.validate19( numero.getNum19(), anio, semana);
				System.out.println("-----------antes del if--19"+""+""+numero.getNum19()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate19( numero.getNum19(), anio, semana)!=null) {
					System.out.println("despues del if----19"+""+""+numero.getNum19()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum19()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum19());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum20()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum20());
				List<info> var =userService.validate20( numero.getNum20(), anio, semana);
				System.out.println("-----------antes del if--20"+""+""+numero.getNum20()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate20( numero.getNum20(), anio, semana)!=null) {
					System.out.println("despues del if----20"+""+""+numero.getNum20()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum20()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum20());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum21()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum21());
				List<info> var =userService.validate21( numero.getNum21(), anio, semana);
				System.out.println("-----------antes del if--21"+""+""+numero.getNum21()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate21( numero.getNum21(), anio, semana)!=null) {
					System.out.println("despues del if----21"+""+""+numero.getNum21()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum21()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum21());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum22()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum22());
				List<info> var =userService.validate22( numero.getNum22(), anio, semana);
				System.out.println("-----------antes del if--22"+""+""+numero.getNum22()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate22( numero.getNum22(), anio, semana)!=null) {
					System.out.println("despues del if----22"+""+""+numero.getNum22()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum22()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum22());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum23()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum23());
				List<info> var =userService.validate23( numero.getNum23(), anio, semana);
				System.out.println("-----------antes del if--23"+""+""+numero.getNum23()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate23( numero.getNum23(), anio, semana)!=null) {
					System.out.println("despues del if----23"+""+""+numero.getNum23()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum23()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum23());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum24()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum24());
				List<info> var =userService.validate24( numero.getNum24(), anio, semana);
				System.out.println("-----------antes del if--24"+""+""+numero.getNum24()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate24( numero.getNum24(), anio, semana)!=null) {
					System.out.println("despues del if----24"+""+""+numero.getNum24()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum24()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum24());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum25()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum25());
				List<info> var =userService.validate25( numero.getNum25(), anio, semana);
				System.out.println("-----------antes del if--____________________25"+""+""+numero.getNum25()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate25( numero.getNum25(), anio, semana)!=null) {
					System.out.println("despues del if----__________________25"+""+""+numero.getNum25()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum25()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum25());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum26()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum26());
				List<info> var =userService.validate26( numero.getNum26(), anio, semana);
				System.out.println("-----------antes del if--____________________26"+""+""+numero.getNum26()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate26( numero.getNum26(), anio, semana)!=null) {
					System.out.println("despues del if----__________________26"+""+""+numero.getNum26()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum26()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum26());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum27()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum27());
				List<info> var =userService.validate27( numero.getNum27(), anio, semana);
				System.out.println("-----------antes del if--____________________27"+""+""+numero.getNum27()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate27( numero.getNum27(), anio, semana)!=null) {
					System.out.println("despues del if----__________________27"+""+""+numero.getNum27()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum27()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum27());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum28()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum28());
				List<info> var =userService.validate28( numero.getNum28(), anio, semana);
				System.out.println("-----------antes del if--____________________28"+""+""+numero.getNum28()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate28( numero.getNum28(), anio, semana)!=null) {
					System.out.println("despues del if----__________________28"+""+""+numero.getNum28()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum28()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum28());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum29()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum29());
				List<info> var =userService.validate29( numero.getNum29(), anio, semana);
				System.out.println("-----------antes del if--____________________29"+""+""+numero.getNum29()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate29( numero.getNum29(), anio, semana)!=null) {
					System.out.println("despues del if----__________________29"+""+""+numero.getNum29()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum29()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum29());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum31()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum31());
				List<info> var =userService.validate31( numero.getNum31(), anio, semana);
				System.out.println("-----------antes del if--___________________31"+""+""+numero.getNum31()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate31( numero.getNum31(), anio, semana)!=null) {
					System.out.println("despues del if----__________________31"+""+""+numero.getNum31()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum31()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum31());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
			}
			if (numero.getNum32()!=null) {
				System.out.println("checkbox is checked  "+numero.getNum32());
				List<info> var =userService.validate32( numero.getNum32(), anio, semana);
				System.out.println("-----------antes del if--___________________32"+""+""+numero.getNum32()+""+ anio+""+semana);
				
				if (var!=null && !var.isEmpty()&&userService.validate32( numero.getNum32(), anio, semana)!=null) {
					System.out.println("despues del if----__________________32"+""+""+numero.getNum32()+""+ anio+""+semana);
						//****************************************************insertamos a la tabla control
						Control opjControl=new Control();
						for (int j = 0; j <=126; j++) {
							opjControl.setEntidad(Integer.parseInt(numero.getNum32()));
							opjControl.setRemesa(Integer.parseInt(opj.toString()));
							opjControl.setFecha_hora(objDate);
							opjControl.setId_usuario(id);
							opjControl.setId_operacion(j);
							opjControl.setId_status(1);
							userService.register(opjControl);
						}
						System.out
								.println("*************************************************************************[HEADER : ");
						OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(220, TimeUnit.SECONDS)
								.readTimeout(220, TimeUnit.SECONDS)
								.writeTimeout(220, TimeUnit.SECONDS).build();
						HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8180/GenerarRemesa/dce/GenerarEntidad?").newBuilder();
						urlBuilder.addQueryParameter("entidad", numero.getNum32());
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
								"*************************************************************************[HEADER2 : ");
						model.setViewName("/users/multiprocesos");
						model.addObject("Multimensaje", "¡");
	
				}else {				
				System.out.println("funcion Remesa no realizada :else  ");
				model.addObject("multimensaje2", "¡");
				model.setViewName("/users/multiprocesos");
				}
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
				String valor = itera[i];
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
