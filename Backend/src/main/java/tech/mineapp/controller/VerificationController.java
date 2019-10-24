package tech.mineapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.service.UserService;
import tech.mineapp.service.VerificationTokenService;

/**
 * @author utkarsh
 *
 */

@RestController
public class VerificationController {
	
	@Autowired
	private VerificationTokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/verify/account")
	public ResponseEntity<?> verify(@RequestParam(name = "token") String token) {
	    
		 
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("POST");
		response.setEndpoint("/verify/confirm");
		
		try {
			if (!tokenService.tokenExists(token)) {
		    	response.setStatus("FAIL");
	     		response.setErrorMessage("Bad token.");
	     		return ResponseEntity.badRequest().body(response);
		    }
		    
		    if (tokenService.isExpired(token)) {
		    	response.setStatus("FAIL");
	     		response.setErrorMessage("Expired token.");
	     		return ResponseEntity.badRequest().body(response);
		    } 
		    
		    userService.verifyUser(tokenService.getUserByToken(token)); 
		    tokenService.deleteToken(token);
		    
		    response.setStatus("SUCCESS");
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {

			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(response);
		}
	}	
}
