package fr.isika.projetfinal.microservice_interestpoint.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.isika.projetfinal.microservice_interestpoint.model.Activity;
import fr.isika.projetfinal.microservice_interestpoint.model.enumModel.EActivity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	Optional<Activity> findByActivity(EActivity nom);

}
