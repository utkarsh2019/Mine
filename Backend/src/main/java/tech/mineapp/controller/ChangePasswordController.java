package tech.mineapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.model.request.PasswordUpdateRequestModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.UserService;

/**
 * @author utkarsh
 *
 */
@RestController
public class ChangePasswordController {

	@Autowired
	private UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PutMapping("/user/me/password")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updatePassword(@CurrentUser UserPrincipal userPrincipal,
											@RequestBody PasswordUpdateRequestModel passwordUpdateRequest){

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
			userService.updateUserPassword(
					user,
					passwordEncoder.encode(passwordUpdateRequest.getPassword()));

			response.setStatus("SUCCESS");

			return ResponseEntity.ok(response);
		} catch (Exception e) {

			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}
	}

}
