package fr.isika.projetfinal.microservice_user.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND, reason = "user already exist")
public class UserFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
