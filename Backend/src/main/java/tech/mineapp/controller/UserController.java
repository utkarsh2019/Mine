package tech.mineapp.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.model.request.UserRequestModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.UserResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.ForgotPasswordService;
import tech.mineapp.service.UserService;
import tech.mineapp.service.VerificationTokenService;

/**
 * The main controller for the /users endpoint
 * 
 * @author amolmoses, utkarsh
 */
@RestController
public class UserController { 
	
	@Autowired
	private UserService userService;

	@Autowired
	VerificationTokenService verificationTokenService;

	@Autowired
	ForgotPasswordService forgotPasswordService;
	
	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("GET");
		response.setEndpoint("/user/me");
		
		try {
			UserResponseModel userResponse = new UserResponseModel();
			BeanUtils.copyProperties(
					userService.findUserById(userPrincipal.getUserId()),
					userResponse);
			
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
		response.setEndpoint("/user/me");

		try {
			UserResponseModel userResponse = new UserResponseModel();
			BeanUtils.copyProperties(
					userService.updateUser(userPrincipal.getUserId(), userRequest),
					userResponse);

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
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> removeUser(@CurrentUser UserPrincipal userPrincipal) {

		ContainerResponseModel response = new ContainerResponseModel();

		response.setVerb("DELETE");
		response.setEndpoint("/user/me");
		
		if (!userService.checkVerificationByUserId(userPrincipal.getUserId())) {
     		response.setStatus("FAIL");
     		response.setErrorMessage("Unverified user.");
     		return ResponseEntity.badRequest().body(response);
     	}

		try {
			UserEntity user = userService.findUserById(userPrincipal.getUserId());

			verificationTokenService.deleteTokensByUser(user);
			forgotPasswordService.deleteTokensByUser(user);
			userService.deleteUser(userPrincipal.getUserId());

			response.setStatus("SUCCESS");

			return ResponseEntity.ok(response);
		} catch (Exception e) {

			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}
	}
}
