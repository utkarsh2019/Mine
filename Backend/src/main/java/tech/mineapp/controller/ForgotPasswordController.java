package tech.mineapp.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import tech.mineapp.entity.ForgotPasswordEntity;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.event.OnForgotPasswordEvent;
import tech.mineapp.event.OnVerificationCompleteEvent;
import tech.mineapp.exception.ResourceNotFoundException;
import tech.mineapp.model.request.ForgotPasswordRequestModel;
import tech.mineapp.model.request.SignupRequestModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.repository.UserRepository;
import tech.mineapp.service.ForgotPasswordService;
import tech.mineapp.service.UserService;

/**
 * @author utkarsh
 *
 */
@RestController
public class ForgotPasswordController {
	
	@Autowired
	private ForgotPasswordService fpService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
    @Autowired
    ApplicationEventPublisher eventPublisher;
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequestModel forgotPasswordRequest,
											WebRequest request) {
		
		ContainerResponseModel response = new ContainerResponseModel();

		response.setVerb("POST");
		response.setEndpoint("/forgotPassword");

		try {
			
			if (!userService.checkVerificationByEmail(forgotPasswordRequest.getEmail())) {
	     		response.setStatus("FAIL");
	     		response.setErrorMessage("Unverified user.");
	     		return ResponseEntity.badRequest().body(response);
	     	}
			UserEntity user = userRepository.findUserByEmail(forgotPasswordRequest.getEmail())
	                .orElseThrow(() -> new ResourceNotFoundException("User", "email", forgotPasswordRequest.getEmail()));
			eventPublisher.publishEvent(new OnForgotPasswordEvent(user, request.getLocale(), request.getContextPath()));
			response.setStatus("SUCCESS");

			return ResponseEntity.ok(response);
		} catch (Exception e) {

			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping("/verify/password")
	public String verify(@RequestParam(name = "token") String token) {
	     
	    ForgotPasswordEntity fpToken = fpService.getForgotPasswordToken(token);
	    if (fpToken == null) {
	        return "Password Update Unsuccessful";
	    }
	     
	    UserEntity user = fpToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((fpToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        return "Password Update Unsuccessful";
	    } 
	    
	    fpService.deleteForgotPasswordToken(user);
	    user.setIsVerified(true); 
	    userRepository.save(user);
	    return "Password Update Successful"; 
	}	
}