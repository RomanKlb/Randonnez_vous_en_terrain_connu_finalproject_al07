package fr.isika.projetfinal.microservice_interestpoint.Service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.projetfinal.microservice_interestpoint.Repository.LocationGeographicalRepository;
import fr.isika.projetfinal.microservice_interestpoint.model.LocationGeographical;
import fr.isika.projetfinal.microservice_interestpoint.model.DTO.InterestPointDTOadd;

@Service
public class LocationGeographicalService {

	@Autowired
	private LocationGeographicalRepository locationGeographicalRepository;

	public LocationGeographical addLocation(@Valid InterestPointDTOadd interestPointDTOaddData) {

		LocationGeographical newlocation = new LocationGeographical();
		newlocation.setLatitude(interestPointDTOaddData.getLatitude());
		newlocation.setLongitude(interestPointDTOaddData.getLongitude());
		return newlocation;
	}

	public Optional<LocationGeographical> findByLatitudeAndLongitude(Double latitude, Double longitude) {
		return locationGeographicalRepository.findByLatitudeAndLongitude(latitude, longitude);
	}

}