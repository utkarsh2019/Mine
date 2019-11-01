package tech.mineapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@PutMapping("/user/me/pic")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ContainerResponseModel> storeFile(@CurrentUser UserPrincipal userPrincipal,
					@RequestParam(name = "file") MultipartFile file) {
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("PUT");
		response.setEndpoint("/user/me/password");
		
		try {
			UserEntity user = userService.findUserById(userPrincipal.getUserId());
			
			if (!userService.checkVerification(user)) {
	     		response.setStatus("FAIL");
	     		response.setErrorMessage("Unverified user.");
	     		return ResponseEntity.badRequest().body(response);
	     	}
			
			if (!userService.isLocalUser(user)) {
				response.setStatus("FAIL");
	     		response.setErrorMessage("Not a local user.");
	     		return ResponseEntity.badRequest().body(response);
			}
			String profilePicUrl = fileUploadService.uploadPhoto(file);
			userService.updateUserProfilePic(user, profilePicUrl);
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
			UserEntity user = userService.findUserById(userPrincipal.getUserId());
			
			if (!userService.checkVerification(user)) {
	     		response.setStatus("FAIL");
	     		response.setErrorMessage("Unverified user.");
	     		return ResponseEntity.badRequest().body(response);
	     	}
			
			if (!userService.isLocalUser(user)) {
				response.setStatus("FAIL");
	     		response.setErrorMessage("Not a local user.");
	     		return ResponseEntity.badRequest().body(response);
			}
			
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
