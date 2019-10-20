package tech.mineapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
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
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Resource> serveFile(@CurrentUser UserPrincipal userPrincipal) {
		Resource file = fileUploadService.loadAsResource(String.valueOf(userPrincipal.getUserId()));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@PutMapping("/user/me/pic")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ContainerResponseModel> storeFile(@CurrentUser UserPrincipal userPrincipal,
					@RequestParam("file") MultipartFile file) {
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("PUT");
		response.setEndpoint("/user/me/password");
		
		try {
			response.setStatus("SUCCESS");
			fileUploadService.store(file, String.valueOf(userPrincipal.getUserId()));
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@DeleteMapping("/user/me/pic")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ContainerResponseModel> deleteFile(@CurrentUser UserPrincipal userPrincipal) {
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("DELETE");
		response.setEndpoint("/user/me/password");
		
		try {
			response.setStatus("SUCCESS");
			fileUploadService.delete(String.valueOf(userPrincipal.getUserId()));
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}

	}

}
