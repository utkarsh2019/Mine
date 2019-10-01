package tech.mineapp.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.mineapp.model.request.UserRequestModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.UserResponseModel;
import tech.mineapp.model.service.UserDTO;
import tech.mineapp.service.UsersService;

/**
 * The main controller for the /users endpoint
 * 
 * @author amolmoses
 */
@RestController
public class UsersController { 
	
	@Autowired
	private UsersService usersService;
		
	@PostMapping("/users")
	public ContainerResponseModel createUser(@RequestBody UserRequestModel userRequest) {
		
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userRequest,userDTO);
		
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("POST");
		response.setEndpoint("/api/users/");
		
		try {
			UserDTO createdUser = usersService.createUser(userDTO);
			
			UserResponseModel userResponse = new UserResponseModel();
			BeanUtils.copyProperties(createdUser, userResponse);
			
			response.setStatus("SUCCESS");
			response.setResponseObject(userResponse);
			
			return response;
			
		} catch (Exception e) {
			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			
			return response;
		}
	}
	
	@GetMapping("/users/{userId}")
	public ContainerResponseModel getUser(@PathVariable("userId") String userId) {
		
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("GET");
		response.setEndpoint("/api/users/" + userId);
		
		try {
			UserDTO user = usersService.getUser(userId);
			
			UserResponseModel userResponse = new UserResponseModel();
			BeanUtils.copyProperties(user, userResponse);
			
			response.setStatus("SUCCESS");
			response.setResponseObject(userResponse);
			
			return response;
			
		} catch (Exception e) {
			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			
			return response;
		}
	}

}
