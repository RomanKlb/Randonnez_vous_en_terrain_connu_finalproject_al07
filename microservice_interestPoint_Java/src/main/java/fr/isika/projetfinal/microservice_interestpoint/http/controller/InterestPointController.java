package fr.isika.projetfinal.microservice_interestpoint.http.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.slf4j.Logger;

import fr.isika.projetfinal.microservice_interestpoint.Service.FileStorageService;
import fr.isika.projetfinal.microservice_interestpoint.Service.InterestPointService;
import fr.isika.projetfinal.microservice_interestpoint.model.File;
import fr.isika.projetfinal.microservice_interestpoint.model.InterestPoint;
import fr.isika.projetfinal.microservice_interestpoint.model.DTO.InterestPointDTOadd;
import fr.isika.projetfinal.microservice_interestpoint.model.exception.InterestPointLocationFoundException;
import fr.isika.projetfinal.microservice_interestpoint.model.exception.InterestPointNotFoundException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/interestpoint")  
public class InterestPointController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterestPointService interestPointService;
	@Autowired
	private FileStorageService fileStorageService;


	@PostMapping(path="/addImage")
	public File addImage(@RequestParam("file") MultipartFile file) throws InterestPointLocationFoundException, IOException {
		return fileStorageService.store(file);
	}

	@PostMapping(path="/addPoint")
	public ResponseEntity<InterestPoint> addPpoint(@Valid @RequestBody InterestPointDTOadd interestPoint) throws InterestPointLocationFoundException, IOException {

		InterestPoint addInterestPoint = interestPointService.saveInterestPointInDB(interestPoint);

		if (addInterestPoint == null)
			return ResponseEntity.noContent().build();


		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{nomDuLieu}")
				.buildAndExpand(addInterestPoint.getNomDuLieu())
				.toUri();
		return ResponseEntity.created(uri).build(); 
	}

	@GetMapping(path="/search/all")
	public List<InterestPoint> searchAllInterestPoint() throws InterestPointNotFoundException {
		log.info("searchAllInterestPoint() est appelée");
		return interestPointService.findByAllInDB();
	}


	@GetMapping(path="/search/id={id}")
	public Optional<InterestPoint> searchInterestPointById(@PathVariable Long id) throws InterestPointNotFoundException {
		log.info("searchInterestPointById() est appelée");

		return interestPointService.findByIdInDB(id);
	}

	@GetMapping(path="/search/lieu={nomDuLieu}")
	public Optional<InterestPoint> searchInterestPointByNomDuLieu(@PathVariable String nomDuLieu) throws InterestPointNotFoundException {
		log.info("searchInterestPointByNomDuLieu() est appelée");
		return interestPointService.findByNameInDB(nomDuLieu);
	}


	@GetMapping(path="/search/thematic={nomThematic}")
	public List<InterestPoint> searchAllByThematic(@PathVariable String nomThematic) throws InterestPointNotFoundException {
		log.info("searchAllByThematic() est appelée");
		log.info("ici c'est paris mais dans java :)" + nomThematic);
		return interestPointService.findAllByThematicInDB(nomThematic);
	}

	@GetMapping(path="/search/activity={nomActivity}")
	public List<InterestPoint> searchAllByActivite(@PathVariable String nomActivity) throws InterestPointNotFoundException {
		log.info("searchAllByActivite() est appelée");
		return interestPointService.findAllByActivityInDB(nomActivity);
	}


	@DeleteMapping (path="/delete/id={id}")     
	public void deleteInterestPoint(@PathVariable Long id) throws InterestPointNotFoundException {
		log.info("deleteInterestPoint() est appelée");
		interestPointService.deleteInterestPointByIdInDB(id);
	}


	//	@PutMapping (path="/edit/id={id}")    
	//	public void editInterestPoint(@RequestBody InterestPoint interestPoint, @PathVariable Long id) throws InterestPointNotFoundException {
	//		log.info("editInterestPoint() est appelée");
	//		interestPointService.editInterestPointInDB(interestPoint, id);
	//	}
	//	



}