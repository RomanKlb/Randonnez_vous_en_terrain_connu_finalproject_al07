package fr.isika.projetfinal.microservice_user.http.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.isika.projetfinal.microservice_user.model.User;
import fr.isika.projetfinal.microservice_user.model.exception.UserFoundException;
import fr.isika.projetfinal.microservice_user.model.exception.UserNotFoundException;
import fr.isika.projetfinal.microservice_user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//{"${URL_FRONT}"}
@CrossOrigin(origins = "*")
@Api(description = "API pour les opérations CRUD pour les user")       
@RestController
@RequestMapping(path="/user")  
public class UserController {


	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private UserService userService;

	
	@ApiOperation(value = "Ajout d'un user lambda")    
	@PostMapping(path="/addUser")
	public ResponseEntity<Void> addUser(@Valid @RequestBody User user) throws UserFoundException {
		log.info("addUser() est appelée");
		
		User addUser = userService.saveUserBasicInDB(user);
		if (addUser == null)
			return ResponseEntity.noContent().build();
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{email}")
				.buildAndExpand(addUser.getEmail())
				.toUri();
		return ResponseEntity.created(uri).build(); 
	}
	
	@ApiOperation(value = "Ajout d'un user admin")    
	@PostMapping(path="/addAdminUser")
	public ResponseEntity<Void> addAdminUser(@Valid @RequestBody User user) throws UserFoundException {
		log.info("addAdminUser() est appelée");

		User addAdminUser = userService.saveUserAdminInDB(user);
		if (addAdminUser == null)
			return ResponseEntity.noContent().build();
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{email}")
				.buildAndExpand(addAdminUser.getEmail())
				.toUri();
		return ResponseEntity.created(uri).build(); 
	}


	@ApiOperation(value = "Recherche un user grâce à son email à condition que celui-ci existe.")    
	@GetMapping(path="/searchUserMail/{email}")
	public Optional<User> searchUserByEmail(@PathVariable String email) throws UserNotFoundException {
		log.info("searchUserByEmail() est appelée");

		return userService.findByEmailInDB(email);
	}

	@ApiOperation(value = "Recherche un user grâce à son id à condition que celui-ci existe.")    
	@GetMapping(path="/searchUserId/{id}")
	public Optional<User> searchUserById(@PathVariable Long id) throws UserNotFoundException{
		log.info("searchUserById() est appelée");
		return userService.findByIdInDB(id);
	}


	@ApiOperation(value = "Recherche tous les users.")    
	@GetMapping(path="/searchUser")
	public List<User> searchAllUser() throws UserNotFoundException {
		log.info("searchAllUser() est appelée");
		return userService.findAllInDB();
	}
	
	@ApiOperation(value = "Recherche tous les users admin.")    
	@GetMapping(path="/searchAdmin")
	public List<User> searchAllAdmin() throws UserNotFoundException {
		log.info("searchAllAdmin() est appelée");
		return userService.findAllAdminInDB();
	}

	

	@ApiOperation(value = "Supprimer un user grâce à son ID à condition que celui-ci existe.")    
	@DeleteMapping (path="/deleteUser/{id}")     
	public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
		log.info("deleteUser() est appelée");
		userService.deleteUserInDB(id);
	}

	@ApiOperation(value = "Supprimer TOUS les users")    
	@DeleteMapping (path="/deleteUser/all")     
	public void deleteAllUsers() throws UserNotFoundException {
		log.info("deleteAllUsers() est appelée");
		userService.deleteAllUsersInDB();
	}


	@ApiOperation(value = "Modifier un user grâce à son ID à condition que celui-ci existe.")    
	@PutMapping (path="/editUser/{id}")    
	public void editUser(@RequestBody User user, @PathVariable Long id) throws UserNotFoundException {
		log.info("editUser() est appelée");
		userService.editUserInDB(user, id);
	}




}
