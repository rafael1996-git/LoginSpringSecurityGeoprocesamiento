package com.springmvc.validator;



import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springmvc.model.UserControl;

@Service
public class UserFormValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserControl.class.equals(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		UserControl personaForm = (UserControl) target;
		validateNombre(personaForm, errors);
		validateApellidosP(personaForm, errors);
		validateApellidosM(personaForm, errors);
		validatePuesto(personaForm, errors);
		validateEntidad(personaForm, errors);
		validateId(personaForm, errors);
		validateTipo(personaForm, errors);
		validateCorreo(personaForm, errors);
		validatePasword(personaForm, errors);
	}
	
	private void validateNombre(UserControl personaForm, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", 
				"field.nombre.required", "El campo 'Nombre' es obligatorio defecto");
	}
	private void validateApellidosP(UserControl personaForm, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ape_pat", 
				"field.ape_pat.required","El campo 'apellido_paterno' es obligatorio defecto");
	}
	private void validateApellidosM(UserControl personaForm, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ape_mat", 
				"field.ape_mat.required","El campo 'apellido_materno' es obligatorio defecto");
	}
	private void validatePuesto(UserControl personaForm, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "puesto", 
				"field.puesto.required", "El campo 'puesto' es obligatorio defecto");
	}
	
	
	
	private void validateEntidad(UserControl personaForm, Errors errors){
		
		if ( personaForm.getEntidad() <=0 ) {
			errors.rejectValue("entidad", "field.anho.invalid", "EL *ENTIDAD* NO DEBE SER TIPO String ,CEROS O VACIOS****");
		}
	
			
		
	
	}
	private void validateTipo(UserControl personaForm, Errors errors){
		
		if ( personaForm.getEntidad() <=0 ) {
			errors.rejectValue("id_tipo_usuario", "field.anho.invalid", "EL *ID_TIPO_USUARIO* NO DEBE SER TIPO String ,CEROS O VACIOS****");
		}
			
		
		
	}
	
private void validateId(UserControl personaForm, Errors errors){
		
		if ( personaForm.getId_usuario() <=0 ) {
			errors.rejectValue("id_usuario", "field.anho.invalid", "EL *ID_USUARIO* NO DEBE SER TIPO String ,CEROS O VACIOS****");
		}
			
		
		
	}
	private void validateCorreo (UserControl personaForm, Errors errors){
		
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
	            Pattern.CASE_INSENSITIVE);
	      if (!(pattern.matcher(personaForm.getCorreo()).matches())) {
	    	  errors.rejectValue("correo", "user.correo.invalid","El Formato Email no cumple con la Regla Especifica");
	      }
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo", 
				"field.correo.required", "El campo 'puesto' es obligatorio defecto");
	}
	private void validatePasword(UserControl personaForm, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", 
				"field.password.required", "El campo 'puesto' es obligatorio defecto");
	}
	
	
	
}