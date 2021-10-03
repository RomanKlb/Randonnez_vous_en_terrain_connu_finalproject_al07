package fr.isika.projetfinal.microservice_interestpoint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.isika.projetfinal.microservice_interestpoint.model.enumModel.EEquipement;
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
@ToString(of = {"id","equipement"} )
@EqualsAndHashCode(of = {"id", "equipement"})
@Entity
@Table(name="equipement")
public class Equipement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_equipement")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private EEquipement equipement;
	
}