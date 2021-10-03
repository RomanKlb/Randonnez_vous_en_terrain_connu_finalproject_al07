package fr.isika.projetfinal.microservice_user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author roman
 *
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","email","password", "name", "surname", "dateMaj", "role", "isAdmin"} )
@EqualsAndHashCode(of = {"id", "email", "password", "name", "surname", "dateMaj", "role", "isAdmin"})
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	private Long id;

	//	@Email(regexp = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", message = "Regex email")
	@Column(nullable = false)
	private String email;

	@Length(min=6, message = "Le nombre de caractères du password doit être plus de 6 caractère.")  
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name="date_maj")
	@DateTimeFormat(pattern = "dd-MM-yyyy' 'HH:mm" )
	private Date dateMaj;
	
	@Column(name="is_admin")
	private Boolean isAdmin;
	
	@OneToOne
	private Role role;

}
