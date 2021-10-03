package fr.isika.projetfinal.microservice_interestpoint.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.isika.projetfinal.microservice_interestpoint.model.LocationGeographical;

@Repository
public interface LocationGeographicalRepository extends CrudRepository<LocationGeographical, Long>{

	Optional<LocationGeographical> findByLatitudeAndLongitude(Double latitude, Double longitude);

}
