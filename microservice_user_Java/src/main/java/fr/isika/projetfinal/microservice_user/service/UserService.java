package fr.isika.projetfinal.microservice_user.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.isika.projetfinal.microservice_user.dao.UserRepository;
import fr.isika.projetfinal.microservice_user.model.User;
import fr.isika.projetfinal.microservice_user.model.exception.UserFoundException;
import fr.isika.projetfinal.microservice_user.model.exception.UserNotFoundException;

@Service
public class UserService {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;



	public ResponseEntity<String> emailAlreadyExist(User user){
		return ResponseEntity.badRequest().body("Error : Email is already taken!");
}

	public User saveUserBasicInDB(@Valid User userData) throws UserFoundException {
		if(userRepository.existsByEmail(userData.getEmail())) {
			emailAlreadyExist(userData);
			throw new UserFoundException();
		}
		User user = saveUserBasicInDBWithRoleRegistrered(userData);
		return user;
	}
	private User saveUserBasicInDBWithRoleRegistrered(User userData) {
		User user = new User();
		user.setEmail(userData.getEmail());
		user.setPassword(passwordEncoder.encode(userData.getPassword()));
		user.setName(userData.getName());
		user.setSurname(userData.getSurname());
//		user.setDateMaj(java.sql.Date.valueOf(LocalDate.now()));
		user.setDateMaj(Date.from(Instant.now()));
		roleService.roleOfUserRegistrered(user);
		userRepository.save(user);
		return user;
	}
	
	public User saveUserAdminInDB(@Valid User userData) throws UserFoundException {
		if(userRepository.existsByEmail(userData.getEmail())) {
			emailAlreadyExist(userData);
			throw new UserFoundException();
		}
		User user = saveUserAdminInDBWithRoleAdministrator(userData);
		return user;
	}
	private User saveUserAdminInDBWithRoleAdministrator(User userData) {
		User user = new User();
		user.setEmail(userData.getEmail());
		user.setPassword(passwordEncoder.encode(userData.getPassword()));
		user.setName(userData.getName());
		user.setSurname(userData.getSurname());
		user.setDateMaj(java.sql.Date.valueOf(LocalDate.now()));
		roleService.roleOfUserAdmin(user);
		userRepository.save(user);
		return user;
	}


	public Optional<User> findByEmailInDB(String email) throws UserNotFoundException{
		Optional<User> userSearching = userRepository.findByEmail(email);
		if(userSearching.isEmpty())
			throw new UserNotFoundException();
		return userSearching;
	}


	public Optional<User> findByIdInDB(Long id) throws UserNotFoundException {
		Optional<User> userSearching = userRepository.findById(id);
		if(userSearching.isEmpty())
			throw new UserNotFoundException();
		return userSearching;
	}


	public List<User> findAllInDB() throws UserNotFoundException {
		List<User> listOfUser = (List<User>) userRepository.findAll();
		if(listOfUser.isEmpty())
			throw new UserNotFoundException();
		return listOfUser;
	}
	
	public List<User> findAllAdminInDB() throws UserNotFoundException {
		List<User> listOfAllUsers = (List<User>) userRepository.findAll();
		List<User> listOfAllAdmin = new ArrayList<User>();
		if(listOfAllUsers.isEmpty())
			throw new UserNotFoundException();
		for(User user : listOfAllUsers) {
			if(user.getIsAdmin())
				listOfAllAdmin.add(user);
		}
		return listOfAllAdmin;
	}

	public void deleteUserInDB(Long id) throws UserNotFoundException {
		if(!userRepository.existsById(id))
			throw new UserNotFoundException();
		userRepository.deleteById(id);  
	}


	public void deleteAllUsersInDB() throws UserNotFoundException {
		List<User> listOfUsers = (List<User>) userRepository.findAll();
		if(listOfUsers.isEmpty())
			throw new UserNotFoundException();
		userRepository.deleteAll();   
	}

	public void editUserInDB(User user, Long id) throws UserNotFoundException {
		if(!userRepository.existsById(id))
			throw new UserNotFoundException();
			
		Optional<User> userOptional = userRepository.findById(id);
		User editUser = new User();
		
		editUser.setRole(userOptional.get().getRole());
		editUser.setId(userOptional.get().getId());
		editUser.setIsAdmin(userOptional.get().getIsAdmin());
		editUser.setDateMaj(Date.from(Instant.now()));
		editUser.setName(userOptional.get().getName());
		editUser.setSurname(userOptional.get().getSurname());	
		
		
		editUser.setEmail(user.getEmail());
		editUser.setPassword(passwordEncoder.encode(user.getPassword()));		
		userRepository.save(editUser);
	}

	






}
