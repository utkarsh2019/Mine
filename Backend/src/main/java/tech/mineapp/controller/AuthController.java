package tech.mineapp.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.mineapp.constants.AuthProvider;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.event.OnRegistrationCompleteEvent;
import tech.mineapp.exception.BadRequestException;
import tech.mineapp.exception.ResourceNotFoundException;
import tech.mineapp.model.request.AuthRequestModel;
import tech.mineapp.model.request.SignupRequestModel;
import tech.mineapp.model.response.ApiResponse;
import tech.mineapp.model.response.AuthResponseModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.UserResponseModel;
import tech.mineapp.repository.UserRepository;
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
    private UserRepository userRepository;
    
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

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponseModel(token));
    }
    
    @PostMapping("/signup")
    public ContainerResponseModel registerUser(@Valid @RequestBody SignupRequestModel signupRequest, 
    										   WebRequest request ) {
        ContainerResponseModel response = new ContainerResponseModel();
        
        response.setVerb("POST");
		response.setEndpoint("/auth/signup");
		
		try {
			if(userRepository.existsByEmail(signupRequest.getEmail())) {
	            throw new BadRequestException("Email address already in use.");
	        }

	        UserEntity user = new UserEntity();
	        user.setUserId(userService.generateIdForUser());
	        user.setName(signupRequest.getName());
	        user.setEmail(signupRequest.getEmail());
	        user.setPassword(signupRequest.getPassword());
	        user.setProvider(AuthProvider.local);

	        user.setPassword(passwordEncoder.encode(user.getPassword()));

	        UserEntity savedUser = userRepository.save(user);
			
	        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(savedUser, request.getLocale(), request.getContextPath()));
	        
			response.setStatus("SUCCESS");
			
			return response;
			
		} catch (Exception e) {
			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			
			return response;
		}
    	
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/user/me")
//                .buildAndExpand(result.getUserId()).toUri();
//
//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "User registered successfully@"));
    }

}