package tech.mineapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.event.OnForgotPasswordEvent;
import tech.mineapp.model.request.ForgotPasswordRequestModel;
import tech.mineapp.model.request.PasswordUpdateRequestModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.service.ForgotPasswordService;
import tech.mineapp.service.UserService;

/**
 * @author utkarsh
 *
 */
@RestController
public class ForgotPasswordController {
	
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @Autowired
    ApplicationEventPublisher eventPublisher;
    
	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequestModel forgotPasswordRequest,
											WebRequest request) {
		
		ContainerResponseModel response = new ContainerResponseModel();

		response.setVerb("POST");
		response.setEndpoint("/forgotPassword");

		try {
			UserEntity user = userService.findUserByEmail(forgotPasswordRequest.getEmail());
			
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
			
			eventPublisher.publishEvent(new OnForgotPasswordEvent(user, request.getLocale(), request.getContextPath()));
			response.setStatus("SUCCESS");

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			logger.error(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping("/verify/password")
	public ResponseEntity<?> verify(@RequestParam(name = "token") String token,
						 @RequestBody PasswordUpdateRequestModel passwordUpdateRequest) {
	    
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("POST");
		response.setEndpoint("/verify/password");
		
		try {
		    
		    if (!forgotPasswordService.tokenExists(token)) {
		    	response.setStatus("FAIL");
	     		response.setErrorMessage("Bad token.");
	     		return ResponseEntity.badRequest().body(response);
		    }
		    
		    if (forgotPasswordService.isExpired(token)) {
		    	response.setStatus("FAIL");
	     		response.setErrorMessage("Expired token.");
	     		return ResponseEntity.badRequest().body(response);
		    } 
		    
		    userService.updateUserPassword(
		    		forgotPasswordService.getUserByToken(token), 
		    		passwordEncoder.encode(passwordUpdateRequest.getPassword()));
		    forgotPasswordService.deleteToken(token);
		    
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