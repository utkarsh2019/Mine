package tech.mineapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import tech.mineapp.entity.UserEntity;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.FileUploadService;
import tech.mineapp.service.UserService;

/**
 * @author utkarsh
 *
 */

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
		
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
					@RequestParam(name = "file") MultipartFile file) {
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("PUT");
		response.setEndpoint("/user/me/password");
		
		try {
			if (!userService.checkVerificationByUserId(userPrincipal.getUserId())) {
	     		response.setStatus("FAIL");
	     		response.setErrorMessage("Unverified user.");
	     		return ResponseEntity.badRequest().body(response);
	     	}
			UserEntity user = userService.findUserById(userPrincipal.getUserId());
			if (!userService.isLocalUser(user)) {
				response.setStatus("FAIL");
	     		response.setErrorMessage("Not a local user.");
	     		return ResponseEntity.badRequest().body(response);
			}
			fileUploadService.store(file, String.valueOf(userPrincipal.getUserId()));
			userService.updateUserProfilePic(user, "http://api.mineapp.tech/user/me/pic");
			response.setStatus("SUCCESS");
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			logger.error(e.getMessage());

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
			if (!userService.checkVerificationByUserId(userPrincipal.getUserId())) {
	     		response.setStatus("FAIL");
	     		response.setErrorMessage("Unverified user.");
	     		return ResponseEntity.badRequest().body(response);
	     	}
			UserEntity user = userService.findUserById(userPrincipal.getUserId());
			if (!userService.isLocalUser(user)) {
				response.setStatus("FAIL");
	     		response.setErrorMessage("Not a local user.");
	     		return ResponseEntity.badRequest().body(response);
			}
			fileUploadService.delete(String.valueOf(userPrincipal.getUserId()));
			userService.updateUserProfilePic(user, null);
			
			response.setStatus("SUCCESS");
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			logger.error(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}

	}

}
