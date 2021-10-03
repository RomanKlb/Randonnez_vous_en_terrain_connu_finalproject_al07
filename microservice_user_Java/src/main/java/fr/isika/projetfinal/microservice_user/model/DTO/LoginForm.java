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
@ToString(of = {"email","password"} )
@EqualsAndHashCode(of = {"email", "password"})
public class LoginForm {

	String email;
	String password;

}
