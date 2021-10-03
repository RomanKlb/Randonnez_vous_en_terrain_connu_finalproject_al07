package fr.isika.projetfinal.microservice_interestpoint.model.DTO;


import java.util.List;


import fr.isika.projetfinal.microservice_interestpoint.model.Activity;
import fr.isika.projetfinal.microservice_interestpoint.model.Equipement;
import fr.isika.projetfinal.microservice_interestpoint.model.Thematic;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"nomDuLieu", "text", "tempsActivite","latitude", "longitude", "listThematic", "listActivity", "listEquipement"} )
@EqualsAndHashCode(of = {"nomDuLieu", "text", "tempsActivite","latitude", "longitude", "listThematic", "listActivity", "listEquipement"} )
public class InterestPointDTOadd {

	private Double latitude;
	private Double longitude;
	private String nomDuLieu;
	private String text;
	private String tempsActivite;
	private List<Thematic> listThematic;
	private List<Activity> listActivity;
	private List<Equipement> listEquipement;
	private String idImage;
	
	
}
