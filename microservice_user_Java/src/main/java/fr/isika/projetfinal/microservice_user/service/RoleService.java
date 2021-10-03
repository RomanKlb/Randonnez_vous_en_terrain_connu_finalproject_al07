package fr.isika.projetfinal.microservice_user.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.projetfinal.microservice_user.dao.RoleRepository;
import fr.isika.projetfinal.microservice_user.model.Role;
import fr.isika.projetfinal.microservice_user.model.TypeRole;
import fr.isika.projetfinal.microservice_user.model.User;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;


	public User roleOfUserRegistrered(User user) {
		Role role = roleRegistreredExist();
		user.setRole(role);
		user.setIsAdmin(false);
		return user;
	}
	public Role roleRegistreredExist() {

		Optional<Role> role = roleRepository.findByTypeRole(TypeRole.REGISTRERED);

		if(!role.isPresent()) {
			Role nouveauRole = new Role();
			nouveauRole.setTypeRole(TypeRole.REGISTRERED);
			roleRepository.save(nouveauRole);
			return nouveauRole;
		}
		return role.get();
	}

	
	public User roleOfUserAdmin(User user) {

		Role role = roleAdminExist();
		user.setRole(role);
		user.setIsAdmin(true);
		return user;
	}
	public Role roleAdminExist() {

		Optional<Role> role = roleRepository.findByTypeRole(TypeRole.ADMINISTRATOR);

		if(!role.isPresent()) {
			Role nouveauRole = new Role();
			nouveauRole.setTypeRole(TypeRole.ADMINISTRATOR);
			roleRepository.save(nouveauRole);
			return nouveauRole;
		}
		return role.get();
	}
	


}
