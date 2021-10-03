package fr.isika.projetfinal.microservice_interestpoint.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.isika.projetfinal.microservice_interestpoint.model.Thematic;
import fr.isika.projetfinal.microservice_interestpoint.model.enumModel.EThematic;

@Repository
public interface ThematicRepository extends JpaRepository<Thematic, Long>{



	Optional<Thematic> findByNomThematic(EThematic nom);




}
