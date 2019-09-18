package tech.mineapp.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.mineapp.model.request.UserRequestModel;
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
	public UserResponseModel createUser(@RequestBody UserRequestModel userRequest) {
		
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userRequest,userDTO);
		
		UserDTO createdUser = usersService.createUser(userDTO);
		
		UserResponseModel response = new UserResponseModel();
		BeanUtils.copyProperties(createdUser, response);
		
		response.setVerb("POST");
		response.setEndpoint("/users");
		response.setStatus("OK");
		response.setErrorMessage("");
		
		return response;
	}

}
