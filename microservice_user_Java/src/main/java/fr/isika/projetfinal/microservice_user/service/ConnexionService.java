package fr.isika.projetfinal.microservice_user.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.isika.projetfinal.microservice_user.dao.UserRepository;
import fr.isika.projetfinal.microservice_user.model.User;
import fr.isika.projetfinal.microservice_user.model.DTO.LoginForm;
import fr.isika.projetfinal.microservice_user.model.DTO.UserResponse;
import fr.isika.projetfinal.microservice_user.model.exception.UserNotFoundException;

@Service
public class ConnexionService {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<UserResponse> getAuthenticateUser(LoginForm loginForm) throws UserNotFoundException {
		
		Optional<User> findUser = userRepository.findByEmail(loginForm.getEmail());
			
		if(!findUser.isPresent()) {
			throw new UserNotFoundException();
		}
			log.info("" + findUser);
		
		if(!passwordEncoder.matches(loginForm.getPassword(), findUser.get().getPassword())) 
			return ResponseEntity.badRequest().build();
		
		UserResponse userResponse = new UserResponse();
		userResponse.setId(findUser.get().getId());
		userResponse.setEmail(findUser.get().getEmail());
		userResponse.setName(findUser.get().getName());
		userResponse.setSurname(findUser.get().getSurname());
		userResponse.setRole(findUser.get().getRole().getTypeRole().toString());
		userResponse.setDateMaj(findUser.get().getDateMaj().toString());
		userResponse.setPassword(findUser.get().getPassword());
		userResponse.setIsAdmin(findUser.get().getIsAdmin());
		log.info(""+ userResponse);
		
		
		return ResponseEntity.ok(userResponse);	
		
	}
	
	
}
