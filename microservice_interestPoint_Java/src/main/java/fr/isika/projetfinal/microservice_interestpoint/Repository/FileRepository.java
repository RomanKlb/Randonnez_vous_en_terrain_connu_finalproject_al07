package fr.isika.projetfinal.microservice_interestpoint.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.isika.projetfinal.microservice_interestpoint.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
	
	
	

}
