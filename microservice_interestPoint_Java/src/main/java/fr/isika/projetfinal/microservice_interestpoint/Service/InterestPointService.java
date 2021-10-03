package fr.isika.projetfinal.microservice_interestpoint.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.projetfinal.microservice_interestpoint.Repository.InterestPointRepository;
import fr.isika.projetfinal.microservice_interestpoint.model.Activity;
import fr.isika.projetfinal.microservice_interestpoint.model.File;
import fr.isika.projetfinal.microservice_interestpoint.model.InterestPoint;
import fr.isika.projetfinal.microservice_interestpoint.model.LocationGeographical;
import fr.isika.projetfinal.microservice_interestpoint.model.Thematic;
import fr.isika.projetfinal.microservice_interestpoint.model.DTO.InterestPointDTOadd;
import fr.isika.projetfinal.microservice_interestpoint.model.exception.InterestPointLocationFoundException;
import fr.isika.projetfinal.microservice_interestpoint.model.exception.InterestPointNotFoundException;

@Service
public class InterestPointService {

	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterestPointRepository interestPointRepository;
	@Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private LocationGeographicalService locationGeographicalService;
	@Autowired
	private ThematicService thematicService;
	@Autowired
	private EquipementService equipementService;
	@Autowired
	private ActivityService activityService;


	public InterestPoint saveInterestPointInDB(@Valid InterestPointDTOadd interestPointDTOaddData) throws InterestPointLocationFoundException {
		File file = fileStorageService.findByIdInDB(interestPointDTOaddData);
//				fileRepo.findById(Long.parseLong(interestPointDTOaddData.getIdImage())).get();
		InterestPoint interestPointSaved = new InterestPoint();
		knowLatitudeAndLongitudeIfAreInDB(interestPointDTOaddData);
		setAllOfAttributesIP(interestPointDTOaddData, file, interestPointSaved);
		interestPointRepository.save(interestPointSaved);
		return interestPointSaved;
	}

	private void setAllOfAttributesIP(InterestPointDTOadd interestPointDTOaddData, File file, InterestPoint interestPointSaved) {
		interestPointSaved.setLocationGeographical(locationGeographicalService.addLocation(interestPointDTOaddData));
		interestPointSaved.setNomDuLieu(interestPointDTOaddData.getNomDuLieu());
		interestPointSaved.setText(interestPointDTOaddData.getText());
		interestPointSaved.setTempsActivite(interestPointDTOaddData.getTempsActivite());

		interestPointSaved.setListThematic(thematicService.selectedThematicToTheInterestPoint(interestPointDTOaddData));
		interestPointSaved.setListActivity(activityService.selectedActivityToTheInterestPoint(interestPointDTOaddData));
		interestPointSaved.setListEquipement(equipementService.selectedEquipementToTheInterestPoint(interestPointDTOaddData));

		interestPointSaved.setFile(file);
	}

	private void knowLatitudeAndLongitudeIfAreInDB(InterestPointDTOadd interestPointDTOaddData) throws InterestPointLocationFoundException {
		Optional<LocationGeographical> locationOptional = locationGeographicalService.findByLatitudeAndLongitude(interestPointDTOaddData.getLatitude(), interestPointDTOaddData.getLongitude());
		if(locationOptional.isPresent())
			throw new InterestPointLocationFoundException();
	}
	


	public List<InterestPoint> findByAllInDB() throws InterestPointNotFoundException {
		List<InterestPoint> listOfInterestPoint = (List<InterestPoint>) interestPointRepository.findAll();
		if(listOfInterestPoint.isEmpty())
			throw new InterestPointNotFoundException();
		return listOfInterestPoint;
	}

	public Optional<InterestPoint> findByIdInDB(Long id) throws InterestPointNotFoundException {
		Optional<InterestPoint> interestPointSearching = interestPointRepository.findById(id);
		if(interestPointSearching.isEmpty())
			throw new InterestPointNotFoundException();
		return interestPointSearching;
	}

	public Optional<InterestPoint> findByNameInDB(String nomDuLieu) throws InterestPointNotFoundException {
		Optional<InterestPoint> interestPointSearching = interestPointRepository.findByNomDuLieu(nomDuLieu);
		if(interestPointSearching.isEmpty())
			throw new InterestPointNotFoundException();
		return interestPointSearching;
	}


	public List<InterestPoint> findAllByThematicInDB(String nomThematic) throws InterestPointNotFoundException {
		Optional<Thematic> thematicSearching = thematicService.searchingThematic(nomThematic);
		return interestPointRepository.findAll().stream()
				.filter(ip -> ip.getListThematic().contains(thematicSearching.get()))
				.collect(Collectors.toList());
	}

	public List<InterestPoint> findAllByActivityInDB(String nomActivity) throws InterestPointNotFoundException {
		Optional<Activity> activitySearching = activityService.searchingActivity(nomActivity);
		return interestPointRepository.findAll().stream()
				.filter(ip -> ip.getListActivity().contains(activitySearching.get()))
				.collect(Collectors.toList());
	}


	public void deleteInterestPointByIdInDB(Long id) throws InterestPointNotFoundException {
		if(!interestPointRepository.existsById(id))
			throw new InterestPointNotFoundException();

		interestPointRepository.deleteById(id);  	

	}




	//	public void editInterestPointInDB(InterestPoint interestPoint, Long id) throws InterestPointNotFoundException {
	//		if(!interestPointRepository.existsById(id))
	//			throw new InterestPointNotFoundException();
	//
	//		Optional<InterestPoint> interestPointOptional = interestPointRepository.findById(id);
	//		interestPoint.setId(interestPointOptional.get().getId());
	//		interestPoint.setLocationGeographical(interestPointOptional.get().getLocationGeographical());
	//		interestPoint.setNomDuLieu(interestPoint.getNomDuLieu());
	//		interestPoint.setText(interestPoint.getText());			
	//		interestPoint.setTempsActivite(interestPoint.getTempsActivite());
	//
	//		interestPoint.setListThematic(thematicService.selectedThematicToTheInterestPoint(interestPoint));
	//		interestPoint.setListActivity(activityService.selectedActivityToTheInterestPoint(interestPoint));
	//		interestPoint.setListEquipement(equipementService.selectedEquipementToTheInterestPoint(interestPoint));
	//
	//		interestPointRepository.save(interestPoint);		
	//	}






































}
