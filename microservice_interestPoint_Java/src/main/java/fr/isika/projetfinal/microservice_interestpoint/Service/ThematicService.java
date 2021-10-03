package fr.isika.projetfinal.microservice_interestpoint.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.projetfinal.microservice_interestpoint.Repository.ThematicRepository;
import fr.isika.projetfinal.microservice_interestpoint.model.Thematic;
import fr.isika.projetfinal.microservice_interestpoint.model.DTO.InterestPointDTOadd;
import fr.isika.projetfinal.microservice_interestpoint.model.enumModel.EThematic;

@Service
public class ThematicService {

	@Autowired
	private ThematicRepository thematicRepository;


	public Set<Thematic> selectedThematicToTheInterestPoint(@Valid InterestPointDTOadd interestPointDTOaddData) {

		final Optional<Thematic> findCascade = thematicRepository.findByNomThematic(EThematic.CASCADE);
		final Optional<Thematic> findLac = thematicRepository.findByNomThematic(EThematic.LAC);
		final Optional<Thematic> findPanorama = thematicRepository.findByNomThematic(EThematic.PANORAMA);
		final Optional<Thematic> findPatrimoine = thematicRepository.findByNomThematic(EThematic.PATRIMOINE);
		final Optional<Thematic> findStation = thematicRepository.findByNomThematic(EThematic.STATION);
		final Optional<Thematic> findSport = thematicRepository.findByNomThematic(EThematic.SPORT);

		Set<Thematic> listOfThematic = new HashSet<Thematic>();

		compareAndFindOrCreateCascadeThematicInDB(interestPointDTOaddData, findCascade, listOfThematic);
		compareAndFindOrCreateLacThematicInDB(interestPointDTOaddData, findLac, listOfThematic);
		compareAndFindOrCreatePatrimoineThematicInDB(interestPointDTOaddData, findPatrimoine, listOfThematic);
		compareAndFindOrCreatePanoramaThematicInDB(interestPointDTOaddData, findPanorama, listOfThematic);
		compareAndFindOrCreateStationThematicInDB(interestPointDTOaddData, findStation, listOfThematic);
		compareAndFindOrCreateSportThematicInDB(interestPointDTOaddData, findSport, listOfThematic);

		return listOfThematic;
	}
	private void compareAndFindOrCreateSportThematicInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Thematic> findSport,
			Set<Thematic> listOfThematic) {
		Thematic sport = new Thematic();
		sport.setNomThematic(EThematic.SPORT);
		if(interestPointDTOaddData.getListThematic().contains(sport))
			if(findSport.isPresent())
				listOfThematic.add(thematicRepository.findByNomThematic(EThematic.SPORT).get());
			else
				listOfThematic.add(addThematicSportInDB());
	}

	private void compareAndFindOrCreateStationThematicInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Thematic> findStation,
			Set<Thematic> listOfThematic) {
		Thematic station = new Thematic();
		station.setNomThematic(EThematic.STATION);
		if(interestPointDTOaddData.getListThematic().contains(station))
			if(findStation.isPresent())
				listOfThematic.add(thematicRepository.findByNomThematic(EThematic.STATION).get());
			else
				listOfThematic.add(addThematicStationInDB());
	}

	private void compareAndFindOrCreatePanoramaThematicInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Thematic> findPanorama,
			Set<Thematic> listOfThematic) {
		Thematic panorama = new Thematic();
		panorama.setNomThematic(EThematic.PANORAMA);
		if(interestPointDTOaddData.getListThematic().contains(panorama))
			if(findPanorama.isPresent())
				listOfThematic.add(thematicRepository.findByNomThematic(EThematic.PANORAMA).get());
			else
				listOfThematic.add(addThematicPanoramainDB());
	}

	private void compareAndFindOrCreatePatrimoineThematicInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Thematic> findPatrimoine,
			Set<Thematic> listOfThematic) {
		Thematic patrimoine = new Thematic();
		patrimoine.setNomThematic(EThematic.PATRIMOINE);
		if(interestPointDTOaddData.getListThematic().contains(patrimoine))
			if(findPatrimoine.isPresent())
				listOfThematic.add(thematicRepository.findByNomThematic(EThematic.PATRIMOINE).get());
			else
				listOfThematic.add(addThematicPatrimoineInDB());
	}

	private void compareAndFindOrCreateLacThematicInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Thematic> findLac,
			Set<Thematic> listOfThematic) {
		Thematic lac = new Thematic();
		lac.setNomThematic(EThematic.LAC);
		if(interestPointDTOaddData.getListThematic().contains(lac))
			if(findLac.isPresent())
				listOfThematic.add(thematicRepository.findByNomThematic(EThematic.LAC).get());
			else
				listOfThematic.add(addThematicLacInDB());
	}

	private void compareAndFindOrCreateCascadeThematicInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Thematic> findCascade,
			Set<Thematic> listOfThematic) {
		Thematic cascade = new Thematic();
		cascade.setNomThematic(EThematic.CASCADE);
		if(interestPointDTOaddData.getListThematic().contains(cascade))
			if(findCascade.isPresent())
				listOfThematic.add(thematicRepository.findByNomThematic(EThematic.CASCADE).get());
			else
				listOfThematic.add(addThematicCascadeInDB());
	}

	// TODO generic method
//	private void addThematicStationInDB() {
//		Arrays.stream( EThematic.values()).forEach( value -> {
//			Thematic newThematicStation = new Thematic();
//			newThematicStation.setNomThematic(value);
//			thematicRepository.save(newThematicStation);
//		});
//	}

	private Thematic addThematicStationInDB() {
		Thematic newThematicStation = new Thematic();
		newThematicStation.setNomThematic(EThematic.STATION);
		thematicRepository.save(newThematicStation);
		return newThematicStation;
	}

	private Thematic addThematicPanoramainDB() {
		Thematic newThematicPanorama = new Thematic();
		newThematicPanorama.setNomThematic(EThematic.PANORAMA);
		thematicRepository.save(newThematicPanorama);
		return newThematicPanorama;
	}

	private Thematic addThematicPatrimoineInDB() {
		Thematic newThematicPatrimoine = new Thematic();
		newThematicPatrimoine.setNomThematic(EThematic.PATRIMOINE);
		thematicRepository.save(newThematicPatrimoine);
		return newThematicPatrimoine;
	}

	private Thematic addThematicLacInDB() {
		Thematic newThematicLac = new Thematic();
		newThematicLac.setNomThematic(EThematic.LAC);
		thematicRepository.save(newThematicLac);
		return newThematicLac;
	}

	private Thematic addThematicCascadeInDB() {
		Thematic newThematicCascade = new Thematic();
		newThematicCascade.setNomThematic(EThematic.CASCADE);
		thematicRepository.save(newThematicCascade);
		return newThematicCascade;
	}
	
	private Thematic addThematicSportInDB() {
		Thematic newThematicSport = new Thematic();
		newThematicSport.setNomThematic(EThematic.SPORT);
		thematicRepository.save(newThematicSport);
		return newThematicSport;
	}

	public Optional<Thematic> searchingThematic(String nomThematic){
		return thematicRepository.findAll().stream()
				.filter(t -> t.getNomThematic().equals(EThematic.valueOf(nomThematic)))
				.findAny();
	}



}
