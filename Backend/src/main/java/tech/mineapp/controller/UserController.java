package tech.mineapp.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.exception.ResourceNotFoundException;
import tech.mineapp.model.request.UserRequestModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.UserResponseModel;
import tech.mineapp.repository.UserRepository;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.UserService;

/**
 * The main controller for the /users endpoint
 * 
 * @author amolmoses, utkarsh
 */
@RestController
public class UserController { 
	
	@Autowired
	private UserService userService;
	
	@Autowired UserRepository userRepository;
	
	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("GET");
		response.setEndpoint("/api/users/me");
		
		try {
			UserEntity user = userRepository.findUserByUserId(userPrincipal.getUserId())
	                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getUserId()));
			
			UserResponseModel userResponse = new UserResponseModel();
			BeanUtils.copyProperties(user, userResponse);
			
			response.setStatus("SUCCESS");
			response.setResponseObject(userResponse);
			
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			
			return ResponseEntity.badRequest().body(response);
		}
		        
    }

	@PutMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateUser(@CurrentUser UserPrincipal userPrincipal,
											 @RequestBody UserRequestModel userRequest) {

		ContainerResponseModel response = new ContainerResponseModel();

		response.setVerb("PUT");
		response.setEndpoint("/api/users/me");

		try {
			UserEntity user = userRepository.findUserByUserId(userPrincipal.getUserId())
	                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getUserId()));
			String[] ignoreProperties;
			if (userService.isLocalUser(user)) {
				ignoreProperties = new String[] {"provider", "isVerified"};
				BeanUtils.copyProperties(userRequest, user, ignoreProperties);
			}
			else {
				ignoreProperties = new String[] {"email", "name", "profilePicUrl", "provider", "isVerified"};
				BeanUtils.copyProperties(userRequest, user, ignoreProperties);
			}
			UserEntity updatedUser = userRepository.save(user);
			UserResponseModel userResponse = new UserResponseModel();
			BeanUtils.copyProperties(updatedUser, userResponse);

			response.setStatus("SUCCESS");
			response.setResponseObject(userResponse);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}
	}

	@DeleteMapping("/user/me")
	public ResponseEntity<?> removeUser(@CurrentUser UserPrincipal userPrincipal) {

		ContainerResponseModel response = new ContainerResponseModel();

		response.setVerb("DELETE");
		response.setEndpoint("/api/users/me");

		try {
			userRepository.deleteByUserId(userPrincipal.getUserId());

			response.setStatus("SUCCESS");

			return ResponseEntity.ok(response);
		} catch (Exception e) {

			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}
	}

}
