package fr.isika.projetfinal.microservice_interestpoint.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.projetfinal.microservice_interestpoint.Repository.EquipementRepository;
import fr.isika.projetfinal.microservice_interestpoint.model.Equipement;
import fr.isika.projetfinal.microservice_interestpoint.model.DTO.InterestPointDTOadd;
import fr.isika.projetfinal.microservice_interestpoint.model.enumModel.EEquipement;

@Service
public class EquipementService {

	@Autowired
	private EquipementRepository equipementRepository;

	public Set<Equipement> selectedEquipementToTheInterestPoint(@Valid InterestPointDTOadd interestPointDTOaddData) {


		final Optional<Equipement> findAppPhoto = equipementRepository.findByEquipement(EEquipement.EQUIPEMENT_PHOTOGRAPHIE);
		final Optional<Equipement> findAlpinism = equipementRepository.findByEquipement(EEquipement.EQUIPEMENT_ALPINISTE);
		final Optional<Equipement> findRandonnee = equipementRepository.findByEquipement(EEquipement.EQUIPEMENT_DE_RANDONNEE);
		final Optional<Equipement> findSki = equipementRepository.findByEquipement(EEquipement.EQUIPEMENT_DE_SKI);
		final Optional<Equipement> findNautique = equipementRepository.findByEquipement(EEquipement.EQUIPEMENT_NAUTIQUE);

		Set<Equipement> listOfEquipement = new HashSet<Equipement>();

		compareAndFindOrCreateAlpinismEquipementInDB(interestPointDTOaddData, findAlpinism, listOfEquipement);
		compareAndFindOrCreateRandonneeEquipementInDB(interestPointDTOaddData, findRandonnee, listOfEquipement);
		compareAndFindOrCreateSkiEquipementInDB(interestPointDTOaddData, findSki, listOfEquipement);
		compareAndFindOrCreateAppPhotoEquipementInDB(interestPointDTOaddData, findAppPhoto, listOfEquipement);
		compareAndFindOrCreateNautiqueEquipementInDB(interestPointDTOaddData, findNautique, listOfEquipement);

		return listOfEquipement;
	}

	
	private void compareAndFindOrCreateNautiqueEquipementInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Equipement> findNautique,
			Set<Equipement> listOfEquipement) {
		Equipement nautique = new Equipement();
		nautique.setEquipement(EEquipement.EQUIPEMENT_NAUTIQUE);
		if(interestPointDTOaddData.getListEquipement().contains(nautique))
			if(findNautique.isPresent())
				listOfEquipement.add(equipementRepository.findByEquipement(EEquipement.EQUIPEMENT_NAUTIQUE).get());
			else
				listOfEquipement.add(addEquipementNautiqueInDB());
	}


	private void compareAndFindOrCreateAlpinismEquipementInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Equipement> findAlpinism,
			Set<Equipement> listOfEquipement) {
		Equipement alpinisme = new Equipement();
		alpinisme.setEquipement(EEquipement.EQUIPEMENT_ALPINISTE);
		if(interestPointDTOaddData.getListEquipement().contains(alpinisme))
			if(findAlpinism.isPresent())
				listOfEquipement.add(equipementRepository.findByEquipement(EEquipement.EQUIPEMENT_ALPINISTE).get());
			else
				listOfEquipement.add(addEquipementAlpinismeInDB());
	}

	private void compareAndFindOrCreateRandonneeEquipementInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Equipement> findRandonnee,
			Set<Equipement> listOfEquipement) {
		Equipement equipementRandonnee = new Equipement();
		equipementRandonnee.setEquipement(EEquipement.EQUIPEMENT_DE_RANDONNEE);
		if(interestPointDTOaddData.getListEquipement().contains(equipementRandonnee))
			if(findRandonnee.isPresent())
				listOfEquipement.add(equipementRepository.findByEquipement(EEquipement.EQUIPEMENT_DE_RANDONNEE).get());
			else
				listOfEquipement.add(addEquipementRandoInDB());
	}

	private void compareAndFindOrCreateSkiEquipementInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Equipement> findSki,
			Set<Equipement> listOfEquipement) {
		Equipement equipementSki = new Equipement();
		equipementSki.setEquipement(EEquipement.EQUIPEMENT_DE_SKI);
		if(interestPointDTOaddData.getListEquipement().contains(equipementSki))
			if(findSki.isPresent())
				listOfEquipement.add(equipementRepository.findByEquipement(EEquipement.EQUIPEMENT_DE_SKI).get());
			else
				listOfEquipement.add(addEquipementSkiInDB());
	}

	private void compareAndFindOrCreateAppPhotoEquipementInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Equipement> findAppPhoto,
			Set<Equipement> listOfEquipement) {
		Equipement equipementPhoto = new Equipement();
		equipementPhoto.setEquipement(EEquipement.EQUIPEMENT_PHOTOGRAPHIE);
		if(interestPointDTOaddData.getListEquipement().contains(equipementPhoto))
			if(findAppPhoto.isPresent())
				listOfEquipement.add(equipementRepository.findByEquipement(EEquipement.EQUIPEMENT_PHOTOGRAPHIE).get());
			else
				listOfEquipement.add(addEquipementPhotoInDB());
	}


	private Equipement addEquipementPhotoInDB() {
		Equipement newEquipementPhoto = new Equipement();
		newEquipementPhoto.setEquipement(EEquipement.EQUIPEMENT_PHOTOGRAPHIE);
		equipementRepository.save(newEquipementPhoto);
		return newEquipementPhoto;
	}

	private Equipement addEquipementSkiInDB() {
		Equipement newEquipementSki = new Equipement();
		newEquipementSki.setEquipement(EEquipement.EQUIPEMENT_DE_SKI);
		equipementRepository.save(newEquipementSki);
		return newEquipementSki;
	}

	private Equipement addEquipementRandoInDB() {
		Equipement newEquipementRando = new Equipement();
		newEquipementRando.setEquipement(EEquipement.EQUIPEMENT_DE_RANDONNEE);
		equipementRepository.save(newEquipementRando);
		return newEquipementRando;
	}

	private Equipement addEquipementAlpinismeInDB() {
		Equipement newEquipementAlpinisme = new Equipement();
		newEquipementAlpinisme.setEquipement(EEquipement.EQUIPEMENT_ALPINISTE);
		equipementRepository.save(newEquipementAlpinisme);
		return newEquipementAlpinisme;
	}
	
	private Equipement addEquipementNautiqueInDB() {
		Equipement newEquipementNautique = new Equipement();
		newEquipementNautique.setEquipement(EEquipement.EQUIPEMENT_NAUTIQUE);
		equipementRepository.save(newEquipementNautique);
		return newEquipementNautique;
	}
}
