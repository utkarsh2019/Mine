package tech.mineapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.event.OnVerificationCompleteEvent;
import tech.mineapp.model.request.AuthRequestModel;
import tech.mineapp.model.request.SignupRequestModel;
import tech.mineapp.model.response.AuthResponseModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.security.TokenProvider;
import tech.mineapp.service.UserService;

/**
 * @author utkarsh
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;
    
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequestModel loginRequest) {
    	
    	ContainerResponseModel response = new ContainerResponseModel();
    	
    	response.setVerb("POST");
    	response.setEndpoint("/auth/login");
    	
 		if (!userService.checkVerificationByEmail(loginRequest.getEmail()) ) {
     		response.setStatus("FAIL");
     		response.setErrorMessage("Unverified user.");
     		return ResponseEntity.badRequest().body(response);
     	}
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = tokenProvider.createToken(authentication);
        
        response.setStatus("SUCCESS");
        response.setResponseObject(new AuthResponseModel(token));
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestModel signupRequest, 
    										   WebRequest request ) {
        ContainerResponseModel response = new ContainerResponseModel();
        
        response.setVerb("POST");
		response.setEndpoint("/auth/signup");
		
		try {
			if(userService.userEmailAlreadyExists(signupRequest.getEmail())) {
				response.setStatus("FAIL");
				response.setErrorMessage("Email address already in use.");
				
				return ResponseEntity.badRequest().body(response);
	        }

	        UserEntity savedUser = userService.createLocalUser(
	        		signupRequest.getName(),
	        		signupRequest.getEmail(),
	        		passwordEncoder.encode(signupRequest.getPassword()));
			
	        eventPublisher.publishEvent(new OnVerificationCompleteEvent(savedUser, request.getLocale(), request.getContextPath()));
	        
			response.setStatus("SUCCESS");
			
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			
			return ResponseEntity.badRequest().body(response);
		}
    }

}
