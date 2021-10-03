package fr.isika.projetfinal.microservice_interestpoint.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND, reason = "Location of interest point already exist")
public class InterestPointLocationFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
