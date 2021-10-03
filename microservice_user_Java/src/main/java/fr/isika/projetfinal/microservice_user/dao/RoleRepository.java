package fr.isika.projetfinal.microservice_user.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.isika.projetfinal.microservice_user.model.Role;
import fr.isika.projetfinal.microservice_user.model.TypeRole;


public interface RoleRepository extends CrudRepository<Role, Long> {
 
	Optional<Role> findByTypeRole(TypeRole typeRole);
}
