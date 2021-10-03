package fr.isika.projetfinal.microservice_interestpoint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.isika.projetfinal.microservice_interestpoint.model.enumModel.EThematic;
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
@ToString(of = {"id","nomThematic"} )
@EqualsAndHashCode(of = {"id", "nomThematic"})
@Entity
@Table(name="thematic")
public class Thematic {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_thematic")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private EThematic nomThematic;
	
}
