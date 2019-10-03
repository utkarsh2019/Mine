package tech.mineapp.controller;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

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
	public String verify(@RequestParam("token") String token,
						 WebRequest request) {
		
	    Locale locale = request.getLocale();
	     
	    VerificationTokenEntity verificationToken = tokenService.getVerificationToken(token);
	    if (verificationToken == null) {
//	        String message = messages.getMessage("auth.message.invalidToken", null, locale);
//	        model.addAttribute("message", message);
	        return "Unsuccessful";//"redirect:/verificationUnsuccessful.html?lang=" + locale.getLanguage();
	    }
	     
	    UserEntity user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//	        String messageValue = messages.getMessage("auth.message.expired", null, locale)
//	        model.addAttribute("message", messageValue);
	        return "Unsuccessful";//"redirect:/verificationUnsuccessful.html?lang=" + locale.getLanguage();
	    } 
	     
	    user.setIsVerified(true); 
	    userRepository.save(user); 
	    return "Successful";//"redirect:/verificationSuccessful.html?lang=" + request.getLocale().getLanguage(); 
	}	
}
