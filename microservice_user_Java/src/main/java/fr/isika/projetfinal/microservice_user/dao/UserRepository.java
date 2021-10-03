package fr.isika.projetfinal.microservice_user.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.isika.projetfinal.microservice_user.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);


}
