package tech.mineapp.controller;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.entity.VerificationTokenEntity;
import tech.mineapp.repository.UserRepository;
import tech.mineapp.service.UserService;
import tech.mineapp.service.VerificationTokenService;

/**
 * @author utkarsh
 *
 */

@RequestMapping("/verify")
public class RegistrationController {
	
	@Autowired
	private VerificationTokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value = "/confirm")
	public String confirmRegistration(@RequestParam("token") String token,
									  WebRequest request) {
	  
	    Locale locale = request.getLocale();
	     
	    VerificationTokenEntity verificationToken = tokenService.getVerificationToken(token);
	    if (verificationToken == null) {
	        
	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
	    }
	     
	    UserEntity user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        
	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
	    } 
	     
	    user.setIsVerified(true); 
	    userRepository.save(user); 
	    return "redirect:/login.html?lang=" + locale.getLanguage(); 
	}
}
