package fr.isika.projetfinal.microservice_interestpoint.Service;

import java.io.IOException;
import java.util.stream.Stream;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.isika.projetfinal.microservice_interestpoint.Repository.FileRepository;
import fr.isika.projetfinal.microservice_interestpoint.model.File;
import fr.isika.projetfinal.microservice_interestpoint.model.DTO.InterestPointDTOadd;

@Service
public class FileStorageService {

//	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileRepository fileRepository;

	public File store(MultipartFile fileUploaded) throws IOException {
		String fileName = StringUtils.cleanPath(fileUploaded.getOriginalFilename());
		File file = new File(fileName, fileUploaded.getContentType(), fileUploaded.getBytes());
		fileRepository.save(file);
		return file;
	}

	public File getFile(Long id) {
		return fileRepository.findById(id).get();
	}

	public Stream<File> getAllFiles() {
		return fileRepository.findAll().stream();
	}

	public void deleteFileByIdInDB(Long id) throws Exception  {
		if(!fileRepository.existsById(id))
			throw new Exception("Error file non trouvé");
		fileRepository.deleteById(id);  	
	}

	public File findByIdInDB(@Valid InterestPointDTOadd interestPointDTOaddData) {
		return fileRepository.findById(Long.parseLong(interestPointDTOaddData.getIdImage())).get();
	}
}