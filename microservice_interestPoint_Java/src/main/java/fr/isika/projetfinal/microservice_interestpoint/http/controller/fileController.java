package fr.isika.projetfinal.microservice_interestpoint.http.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.isika.projetfinal.microservice_interestpoint.Service.FileStorageService;
import fr.isika.projetfinal.microservice_interestpoint.model.File;
import fr.isika.projetfinal.microservice_interestpoint.model.DTO.messageFile.ResponseFile;
import fr.isika.projetfinal.microservice_interestpoint.model.DTO.messageFile.ResponseMessage;
import fr.isika.projetfinal.microservice_interestpoint.model.exception.InterestPointNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/interestpoint")
public class fileController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileStorageService storageService;
	

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws InterestPointNotFoundException {
		String message = "";
		try {
			storageService.store(file);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder
					.fromCurrentContextPath()
					.path("/files/")
					.path(dbFile.getIdFile().toString())
					.toUriString();

			return new ResponseFile(
					dbFile.getName(),
					fileDownloadUri,
					dbFile.getType(),
					dbFile.getData().length);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/files/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		File file = storageService.getFile(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.body(file.getData());
	}
	
	@DeleteMapping (path="/deletefile/id={id}")     
	public void deleteFile(@PathVariable Long id) throws Exception {
		log.info("deleteFile() est appel??e");
		storageService.deleteFileByIdInDB(id);
	}
}

