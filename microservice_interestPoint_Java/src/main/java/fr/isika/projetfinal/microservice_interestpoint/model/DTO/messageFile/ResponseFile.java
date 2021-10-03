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
@ToString(of = {"name", "url", "type", "size"} )
@EqualsAndHashCode(of = {"name", "url", "type",  "size"})
public class ResponseFile {

	  private String name;
	  private String url;
	  private String type;
	  private long size;
}
