package fr.isika.projetfinal.microservice_interestpoint.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.isika.projetfinal.microservice_interestpoint.model.Equipement;
import fr.isika.projetfinal.microservice_interestpoint.model.enumModel.EEquipement;

@Repository
public interface EquipementRepository extends CrudRepository<Equipement, Long>{

	Optional<Equipement> findByEquipement(EEquipement nom);


	
}
