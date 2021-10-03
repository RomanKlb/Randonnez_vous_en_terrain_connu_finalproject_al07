package fr.isika.projetfinal.microservice_user.model.DTO;

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
@ToString(of = {"id","email", "password", "role", "name", "surname", "dateMaj", "isAdmin"} )
@EqualsAndHashCode(of = {"id","email", "password", "role", "name", "surname", "dateMaj", "isAdmin"})
public class UserResponse {

	Long id;
	String email;
	String password;
	String role;
	String name;
	String surname;
	String dateMaj;
	Boolean isAdmin;

}
