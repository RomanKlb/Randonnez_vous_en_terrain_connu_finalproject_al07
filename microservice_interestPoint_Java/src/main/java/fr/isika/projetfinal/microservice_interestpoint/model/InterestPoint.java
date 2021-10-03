package fr.isika.projetfinal.microservice_interestpoint.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@ToString(of = {"id", "nomDuLieu", "tempsActivite", "locationGeographical", "listEquipement", "listActivity", "listThematic"} )
@EqualsAndHashCode(of = {"id", "nomDuLieu", "tempsActivite", "locationGeographical", "listEquipement", "listActivity", "listThematic"})
@Entity
@Table(name="interest_point")
public class InterestPoint {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_interest_point")
	private Long id;
	
	@Column(nullable = false)
	private String nomDuLieu;
	
	@Column(nullable = false, length = 1000)
	private String text;
	
	@Column(nullable = true)
	private String tempsActivite;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_geographical_id")
	private LocationGeographical locationGeographical;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "ip_equipement",
				joinColumns = @JoinColumn(name = "interestPoint_id"),
				inverseJoinColumns = @JoinColumn(name= "equipement_id"))
	private Set<Equipement> listEquipement  = new HashSet<Equipement>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "ip_activity",
				joinColumns = @JoinColumn(name = "interestPoint_id"),
				inverseJoinColumns = @JoinColumn(name= "activity_id"))
	private Set<Activity> listActivity  = new HashSet<Activity>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "ip_thematic",
				joinColumns = @JoinColumn(name = "interestPoint_id"),
				inverseJoinColumns = @JoinColumn(name= "thematic_id"))
	private Set<Thematic> listThematic = new HashSet<Thematic>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="file_id")
	private File file;
	
	
}
