package fr.isika.projetfinal.microservice_interestpoint.model.DTO.messageFile;

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
@ToString(of = {"message"} )
@EqualsAndHashCode(of = {"message"})
public class ResponseMessage {
	 
	private String message;
	 
	 
}
