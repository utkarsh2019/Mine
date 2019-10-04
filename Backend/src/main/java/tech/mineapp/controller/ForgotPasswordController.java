package tech.mineapp.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.mineapp.entity.ForgotPasswordEntity;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.repository.UserRepository;
import tech.mineapp.service.ForgotPasswordService;

/**
 * @author utkarsh
 *
 */
@RestController
@RequestMapping("/verify")
public class ForgotPasswordController {
	
	@Autowired
	private ForgotPasswordService fpService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/forgotPassword")
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