package fr.isika.projetfinal.microservice_interestpoint.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.isika.projetfinal.microservice_interestpoint.model.LocationGeographical;
import fr.isika.projetfinal.microservice_interestpoint.model.InterestPoint;

@Repository
public interface InterestPointRepository extends JpaRepository<InterestPoint, Long>{

	boolean existsByLocationGeographical(LocationGeographical locationGeographical);

	Optional<InterestPoint> findByNomDuLieu(String nomDuLieu);



	
	
	
}
