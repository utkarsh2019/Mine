package tech.mineapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.service.FileUploadService;

/**
 * @author utkarsh
 *
 */

@RestController
public class FileUploadController {
	
	private final FileUploadService fileUploadService;
	
	@Autowired
	public FileUploadController(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}
	
	@GetMapping("/user/me/pic")
	public ResponseEntity<Resource> serveFile() {
		Resource file = fileUploadService.loadAsResource("kk");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@PutMapping("/user/me/pic")
	public ResponseEntity<ContainerResponseModel> serveFile(@RequestParam("file") MultipartFile file) {
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("PUT");
		response.setEndpoint("/user/me/password");
		
		try {
			response.setStatus("SUCCESS");
			fileUploadService.store(file);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}
		
	}

}
