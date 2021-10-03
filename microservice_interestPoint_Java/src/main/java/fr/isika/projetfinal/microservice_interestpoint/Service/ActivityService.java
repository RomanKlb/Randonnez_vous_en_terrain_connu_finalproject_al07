package fr.isika.projetfinal.microservice_interestpoint.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.projetfinal.microservice_interestpoint.Repository.ActivityRepository;
import fr.isika.projetfinal.microservice_interestpoint.model.Activity;
import fr.isika.projetfinal.microservice_interestpoint.model.DTO.InterestPointDTOadd;
import fr.isika.projetfinal.microservice_interestpoint.model.enumModel.EActivity;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository activityRepository;


	public Set<Activity> selectedActivityToTheInterestPoint(@Valid InterestPointDTOadd interestPointDTOaddData) {

		final Optional<Activity> findRandonnee = activityRepository.findByActivity(EActivity.RANDONNEE);
		final Optional<Activity> findAlpinisme = activityRepository.findByActivity(EActivity.ALPINISME);
		final Optional<Activity> findVisit = activityRepository.findByActivity(EActivity.VISITE);
		final Optional<Activity> findTourism = activityRepository.findByActivity(EActivity.TOURISME);
		final Optional<Activity> findBaignade = activityRepository.findByActivity(EActivity.BAIGNADE);
		final Optional<Activity> findPaddle = activityRepository.findByActivity(EActivity.SUP_PADDLE);
		final Optional<Activity> findKayak = activityRepository.findByActivity(EActivity.CANOE_KAYAK);
		final Optional<Activity> findEscalade = activityRepository.findByActivity(EActivity.ESCALADE);
		final Optional<Activity> findLuge = activityRepository.findByActivity(EActivity.LUGE);
		final Optional<Activity> findSnow = activityRepository.findByActivity(EActivity.SNOWBOARD);
		final Optional<Activity> findSki = activityRepository.findByActivity(EActivity.SKI);

		Set<Activity> listOfActivity = new HashSet<Activity>();

		compareAndFindOrCreateBaignadeActivityInDB(interestPointDTOaddData, findBaignade, listOfActivity);
		compareAndFindOrCreateRandonneeActivityInDB(interestPointDTOaddData, findRandonnee, listOfActivity);
		compareAndFindOrCreateAlpinismeActivityInDB(interestPointDTOaddData, findAlpinisme, listOfActivity);
		compareAndFindOrCreateVisitActivityInDB(interestPointDTOaddData, findVisit, listOfActivity);
		compareAndFindOrCreateTourismActivityInDB(interestPointDTOaddData, findTourism, listOfActivity);
		compareAndFindOrCreatePaddleActivityInDB(interestPointDTOaddData, findPaddle, listOfActivity);
		compareAndFindOrCreateKayakActivityInDB(interestPointDTOaddData, findKayak, listOfActivity);
		compareAndFindOrCreateEscaladeActivityInDB(interestPointDTOaddData, findEscalade, listOfActivity);
		compareAndFindOrCreateLugeActivityInDB(interestPointDTOaddData, findLuge, listOfActivity);
		compareAndFindOrCreateSnowActivityInDB(interestPointDTOaddData, findSnow, listOfActivity);
		compareAndFindOrCreateSkiActivityInDB(interestPointDTOaddData, findSki, listOfActivity);

		
		return listOfActivity;
	}
	
	private void compareAndFindOrCreateSkiActivityInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Activity> findSki,
			Set<Activity> listOfActivity) {
		Activity ski = new Activity();
		ski.setActivity(EActivity.SKI);
		if(interestPointDTOaddData.getListActivity().contains(ski))
			if(findSki.isPresent())
				listOfActivity.add(activityRepository.findByActivity(EActivity.SKI).get());
			else
				listOfActivity.add(addActivitySkiInDB());
	}
	
	private void compareAndFindOrCreateSnowActivityInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Activity> findSnow,
			Set<Activity> listOfActivity) {
		Activity snow = new Activity();
		snow.setActivity(EActivity.SNOWBOARD);
		if(interestPointDTOaddData.getListActivity().contains(snow))
			if(findSnow.isPresent())
				listOfActivity.add(activityRepository.findByActivity(EActivity.SNOWBOARD).get());
			else
				listOfActivity.add(addActivitySnowInDB());
	}
	
	private void compareAndFindOrCreateLugeActivityInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Activity> findLuge,
			Set<Activity> listOfActivity) {
		Activity luge = new Activity();
		luge.setActivity(EActivity.LUGE);
		if(interestPointDTOaddData.getListActivity().contains(luge))
			if(findLuge.isPresent())
				listOfActivity.add(activityRepository.findByActivity(EActivity.LUGE).get());
			else
				listOfActivity.add(addActivityLugeInDB());
	}
	
	
	private void compareAndFindOrCreateKayakActivityInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Activity> findKayak,
			Set<Activity> listOfActivity) {
		Activity kayak = new Activity();
		kayak.setActivity(EActivity.CANOE_KAYAK);
		if(interestPointDTOaddData.getListActivity().contains(kayak))
			if(findKayak.isPresent())
				listOfActivity.add(activityRepository.findByActivity(EActivity.SUP_PADDLE).get());
			else
				listOfActivity.add(addActivityKayakInDB());
	}
	
	private void compareAndFindOrCreateEscaladeActivityInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Activity> findEscalade,
			Set<Activity> listOfActivity) {
		Activity escalade = new Activity();
		escalade.setActivity(EActivity.ESCALADE);
		if(interestPointDTOaddData.getListActivity().contains(escalade))
			if(findEscalade.isPresent())
				listOfActivity.add(activityRepository.findByActivity(EActivity.ESCALADE).get());
			else
				listOfActivity.add(addActivityEscaladeInDB());
	}
	
	private void compareAndFindOrCreatePaddleActivityInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Activity> findPaddle,
			Set<Activity> listOfActivity) {
		Activity paddle = new Activity();
		paddle.setActivity(EActivity.SUP_PADDLE);
		if(interestPointDTOaddData.getListActivity().contains(paddle))
			if(findPaddle.isPresent())
				listOfActivity.add(activityRepository.findByActivity(EActivity.SUP_PADDLE).get());
			else
				listOfActivity.add(addActivityPaddleInDB());
	}

	private void compareAndFindOrCreateTourismActivityInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Activity> findTourism,
			Set<Activity> listOfActivity) {
		Activity tourism = new Activity();
		tourism.setActivity(EActivity.TOURISME);
		if(interestPointDTOaddData.getListActivity().contains(tourism))
			if(findTourism.isPresent())
				listOfActivity.add(activityRepository.findByActivity(EActivity.TOURISME).get());
			else
				listOfActivity.add(addActivityTourismInDB());
	}

	private void compareAndFindOrCreateVisitActivityInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Activity> findVisit,
			Set<Activity> listOfActivity) {
		Activity visit = new Activity();
		visit.setActivity(EActivity.VISITE);
		if(interestPointDTOaddData.getListActivity().contains(visit))
			if(findVisit.isPresent())
				listOfActivity.add(activityRepository.findByActivity(EActivity.VISITE).get());
			else
				listOfActivity.add(addActivityVisitInDB());
	}

	private void compareAndFindOrCreateAlpinismeActivityInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Activity> findAlpinisme,
			Set<Activity> listOfActivity) {
		Activity alpinisme = new Activity();
		alpinisme.setActivity(EActivity.ALPINISME);
		if(interestPointDTOaddData.getListActivity().contains(alpinisme))
			if(findAlpinisme.isPresent())
				listOfActivity.add(activityRepository.findByActivity(EActivity.ALPINISME).get());
			else
				listOfActivity.add(addActivityAlpinismeInDB());
	}

	private void compareAndFindOrCreateRandonneeActivityInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Activity> findRandonnee,
			Set<Activity> listOfActivity) {
		Activity randonnee = new Activity();
		randonnee.setActivity(EActivity.RANDONNEE);
		if(interestPointDTOaddData.getListActivity().contains(randonnee))
			if(findRandonnee.isPresent())
				listOfActivity.add(activityRepository.findByActivity(EActivity.RANDONNEE).get());
			else
				listOfActivity.add(addActivityRandonneeInDB());
	}

	private void compareAndFindOrCreateBaignadeActivityInDB(InterestPointDTOadd interestPointDTOaddData, final Optional<Activity> findBaignade,
			Set<Activity> listOfActivity) {
		Activity baignade = new Activity();
		baignade.setActivity(EActivity.BAIGNADE);
		if(interestPointDTOaddData.getListActivity().contains(baignade))
			if(findBaignade.isPresent())
				listOfActivity.add(activityRepository.findByActivity(EActivity.BAIGNADE).get());
			else
				listOfActivity.add(addActivityBaignadeInDB());
	}

	
	private Activity addActivityTourismInDB() {
		Activity newActivityTourism = new Activity();
		newActivityTourism.setActivity(EActivity.TOURISME);
		activityRepository.save(newActivityTourism);
		return newActivityTourism;
	}

	private Activity addActivityVisitInDB() {
		Activity newActivityVisit = new Activity();
		newActivityVisit.setActivity(EActivity.VISITE);
		activityRepository.save(newActivityVisit);
		return newActivityVisit;
	}

	private Activity addActivityAlpinismeInDB() {
		Activity newActivityAlpinisme = new Activity();
		newActivityAlpinisme.setActivity(EActivity.ALPINISME);
		activityRepository.save(newActivityAlpinisme);
		return newActivityAlpinisme;
	}

	private Activity addActivityRandonneeInDB() {
		Activity newActivityRandonnee = new Activity();
		newActivityRandonnee.setActivity(EActivity.RANDONNEE);
		activityRepository.save(newActivityRandonnee);
		return newActivityRandonnee;
	}
	
	private Activity addActivityBaignadeInDB() {
		Activity newActivityBaignade = new Activity();
		newActivityBaignade.setActivity(EActivity.BAIGNADE);
		activityRepository.save(newActivityBaignade);
		return newActivityBaignade;
	}
	
	private Activity addActivityPaddleInDB() {
		Activity newActivityPaddle = new Activity();
		newActivityPaddle.setActivity(EActivity.SUP_PADDLE);
		activityRepository.save(newActivityPaddle);
		return newActivityPaddle;
	}
	
	private Activity addActivityKayakInDB() {
		Activity newActivityKayak = new Activity();
		newActivityKayak.setActivity(EActivity.CANOE_KAYAK);
		activityRepository.save(newActivityKayak);
		return newActivityKayak;
	}
	
	private Activity addActivityEscaladeInDB() {
		Activity newActivityEscalade = new Activity();
		newActivityEscalade.setActivity(EActivity.ESCALADE);
		activityRepository.save(newActivityEscalade);
		return newActivityEscalade;
	}

	private Activity addActivitySkiInDB() {
		Activity newActivitySki = new Activity();
		newActivitySki.setActivity(EActivity.SKI);
		activityRepository.save(newActivitySki);
		return newActivitySki;
	}
	
	private Activity addActivitySnowInDB() {
		Activity newActivitySnow = new Activity();
		newActivitySnow.setActivity(EActivity.SNOWBOARD);
		activityRepository.save(newActivitySnow);
		return newActivitySnow;
	}
	
	private Activity addActivityLugeInDB() {
		Activity newActivityLuge = new Activity();
		newActivityLuge.setActivity(EActivity.LUGE);
		activityRepository.save(newActivityLuge);
		return newActivityLuge;
	}
	
	public Optional<Activity> searchingActivity(String nomActivity) {
		return activityRepository.findAll().stream()
				.filter(t -> t.getActivity().equals(EActivity.valueOf(nomActivity)))
				.findAny();
	}





}
