package tech.mineapp.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.entity.VerificationTokenEntity;
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
	public String verify(@RequestParam(name = "token") String token) {
	     
	    VerificationTokenEntity verificationToken = tokenService.getVerificationToken(token);
	    if (verificationToken == null) {
	        return "Verification Unsuccessful";
	    }
	     
	    UserEntity user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        return "Verification Unsuccessful";
	    } 
	    
	    tokenService.deleteVerificationToken(user);
	    user.setIsVerified(true); 
	    userRepository.save(user);
	    return "Verification Successful"; 
	}	
}
