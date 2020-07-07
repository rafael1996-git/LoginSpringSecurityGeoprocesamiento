package com.springmvc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class EmailValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String CORREO_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	

	public EmailValidator() {
		pattern = Pattern.compile(CORREO_PATTERN);


	}

	public boolean valid(final String correo) {

		matcher = pattern.matcher(correo);
		return matcher.matches();

	}
	
}


