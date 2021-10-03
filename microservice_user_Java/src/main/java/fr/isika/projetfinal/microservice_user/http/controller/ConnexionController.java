package fr.isika.projetfinal.microservice_user.http.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.isika.projetfinal.microservice_user.model.DTO.LoginForm;
import fr.isika.projetfinal.microservice_user.model.DTO.UserResponse;
import fr.isika.projetfinal.microservice_user.model.exception.UserNotFoundException;
import fr.isika.projetfinal.microservice_user.service.ConnexionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(description = "API pour les connexions User")       
@RestController
@RequestMapping(path="/login")  
public class ConnexionController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ConnexionService connexionService;


	@ApiOperation(value = "signIn")    
	@PostMapping(path="/signin")
	public ResponseEntity<UserResponse> authenticateUser(@RequestBody LoginForm loginForm) throws UserNotFoundException {
		log.info("authenticateUser() est appelée");
		
		return connexionService.getAuthenticateUser(loginForm);
	}
	
//	@ApiOperation(value = "signUp")    
//	@PostMapping(path="/signup")
//	public ResponseEntity<LoginForm> createUserForAuthenticate(@RequestBody User user) throws UserNotFoundException {
//		log.info("authenticateUser() est appelée");
//		
//		return connexionService.getCreateUserPlateforme(loginForm);
//	}
	
	



}
