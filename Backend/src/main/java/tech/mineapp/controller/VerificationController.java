package tech.mineapp.controller;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

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
	
	@Autowired
	private MessageSource messages;
	
	@GetMapping("/confirm")
	public String verify(@RequestParam(name = "token") String token,
						 WebRequest request,
						 Model model) {
		
	    Locale locale = request.getLocale();
	     
	    VerificationTokenEntity verificationToken = tokenService.getVerificationToken(token);
	    if (verificationToken == null) {
	        String message = messages.getMessage("regSucc", null, locale);
	        model.addAttribute("message", message);
	        return "VerificationUnsuccessful";//"redirect:/verificationUnsuccessful.html?lang=" + locale.getLanguage();
	    }
	     
	    UserEntity user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//	        String messageValue = messages.getMessage("auth.message.expired", null, locale)
//	        model.addAttribute("message", messageValue);
	        return "VerificationUnsuccessful";//"redirect:/verificationUnsuccessful.html?lang=" + locale.getLanguage();
	    } 
	    
	    tokenService.deleteVerificationToken(user);
	    user.setIsVerified(true); 
	    userRepository.save(user);
	    return "VerificationSuccessful"; 
	}	
}
