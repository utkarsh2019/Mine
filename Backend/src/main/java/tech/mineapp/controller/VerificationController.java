package tech.mineapp.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.entity.VerificationTokenEntity;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.repository.UserRepository;
import tech.mineapp.service.VerificationTokenService;

/**
 * @author utkarsh
 *
 */

@RestController
@RequestMapping("/verify")
public class VerificationController {
	
	@Autowired
	private VerificationTokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/confirm")
	public ResponseEntity<?> verify(@RequestParam(name = "token") String token) {
	    
		 
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("POST");
		response.setEndpoint("/verify/confirm");
		
		try {
		    VerificationTokenEntity verificationToken = tokenService.getVerificationToken(token);
		    if (verificationToken == null) {
		    	response.setStatus("FAIL");
	     		response.setErrorMessage("Null token.");
	     		return ResponseEntity.badRequest().body(response);
		    }
		     
		    UserEntity user = verificationToken.getUser();
		    Calendar cal = Calendar.getInstance();
		    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
		    	response.setStatus("FAIL");
	     		response.setErrorMessage("Expired token.");
	     		return ResponseEntity.badRequest().body(response);
		    } 
		    
		    tokenService.deleteVerificationToken(user);
		    user.setIsVerified(true); 
		    userRepository.save(user);
		    response.setStatus("SUCCESS");
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {

			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}
	}	
}
