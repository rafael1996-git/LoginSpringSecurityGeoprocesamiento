package com.springmvc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.uuid.Generators;
import com.springmvc.model.Fecha;
import com.springmvc.model.Remesa;
import com.springmvc.model.User;
import com.springmvc.model.UserControl;
import com.springmvc.service.UserService;
import com.springmvc.validator.UserFormValidator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Controller
public class RegistrationController {
	@Autowired
	public UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@RequestMapping(value = "/agregar", method = RequestMethod.GET)
	public ModelAndView addPage(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		List<User> listaPersonas =userService.list();
		request.setAttribute("lista", listaPersonas);
		model.setViewName("/users/add");

		return model;

	}
	// show update form
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showUpdateUserForm( HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		try {
			String	correo=request.getParameter("correo");
			System.out.println("Correo: "+correo);
			System.out.println("userService: "+userService);
			User user = userService.findByUsercorreo(correo);
			System.out.println("user: "+user);
			int id=user.getId();
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨: "+id);
			String email=user.getCorreo();
			String nombre=user.getNombre();
			String ape_pat=user.getApe_pat();
			String ape_mat=user.getApe_mat();
			int entidad=user.getEntidad();
			String puesto="Generador de Remesa";
			int tipo_user=user.getId_rol();
			String password=user.getPassword();
			
			UserControl control=userService.findBycorreo(email);

			if (control!=null) {
				
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!no se puede insertar por que el usuario ya esta registrado: ");

				List<User> listaPersonas =userService.list();
				request.setAttribute("lista", listaPersonas);
				model.addObject("msg","¡");
				model.setViewName("/users/add");
				
			}else {

				UserControl regis=new UserControl();
				regis.setId_usuario(id);
				regis.setNombre(nombre);
				regis.setApe_pat(ape_pat);
				regis.setApe_mat(ape_mat);
				regis.setPuesto(puesto);
				regis.setEntidad(entidad);
				regis.setId_tipo_usuario(tipo_user);
				regis.setCorreo(email);
				regis.setPassword(password);
				userService.register(regis);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!inserta 01: " + email);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!inserta 01: " + id);
				List<User> listaPersonas =userService.list();
				request.setAttribute("lista", listaPersonas);
				model.addObject("mensage","¡");
				model.setViewName("/users/add");
			}
				

			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Exception:"+e.getCause().toString());
			List<User> listaPersonas =userService.list();
			request.setAttribute("lista", listaPersonas);
			model.addObject("Exception","¡"+e.getCause().toString());
			model.setViewName("/users/add");
			
		}
		
			return model;

	}
	// delete user
	@RequestMapping(value="/delete",method = RequestMethod.GET)    
		public ModelAndView deleteUser( HttpServletRequest request, HttpServletResponse response) {
			ModelAndView model = new ModelAndView();
			try {
				String	correo=request.getParameter("correo");
				System.out.println("paso por aqui en eliminar de el metodo servlet " + correo);
						userService.delete(correo);
					List<UserControl> listaPersonas =userService.lista();
					request.setAttribute("lista", listaPersonas);
					model.addObject("msg","¡");
					model.setViewName("/users/admin");
					return model;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Exception:");
				List<UserControl> listaPersonas =userService.lista();
				request.setAttribute("lista", listaPersonas);
				model.addObject("msg1", "¡"+e.getCause().toString());
				model.setViewName("/users/admin");
				return model;			
				}
		}

	
}
