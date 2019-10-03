package tech.mineapp.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import tech.mineapp.model.request.UserRequestModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.UserResponseModel;
import tech.mineapp.model.service.UserDTO;
import tech.mineapp.service.UserService;

/**
 * The main controller for the /users endpoint
 * 
 * @author amolmoses
 */
@RestController
public class UserController { 
	
	@Autowired
	private UserService userService;
		
//	@PostMapping("/users")
//	public ContainerResponseModel createUser(@RequestBody UserRequestModel userRequest) {
//		
//		ContainerResponseModel response = new ContainerResponseModel();
//		
//		response.setVerb("POST");
//		response.setEndpoint("/api/users/");
//		
//		try {
//			UserDTO userDTO = new UserDTO();
//			BeanUtils.copyProperties(userRequest,userDTO);
//
//			UserDTO createdUser = usersService.createUser(userDTO);
//			
//			UserResponseModel userResponse = new UserResponseModel();
//			BeanUtils.copyProperties(createdUser, userResponse);
//			
//			response.setStatus("SUCCESS");
//			response.setResponseObject(userResponse);
//			
//			return response;
//			
//		} catch (Exception e) {
//			
//			response.setStatus("FAIL");
//			response.setErrorMessage(e.getMessage());
//			
//			return response;
//		}
//	}
	
	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public ContainerResponseModel getUser(@PathVariable("userId") String userId) {
		
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("GET");
		response.setEndpoint("/api/users/" + userId);
		
		try {
			UserDetails user = userService.loadUserById(userId);
			
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

//	@PutMapping("/users/{userId}")
//	public ContainerResponseModel updateUser(@RequestBody UserRequestModel userRequest,
//											 @PathVariable("userId") String userId) {
//
//		ContainerResponseModel response = new ContainerResponseModel();
//
//		response.setVerb("PUT");
//		response.setEndpoint("/api/users" + userId);
//
//		try {
//			UserDTO userDTO = new UserDTO();
//			BeanUtils.copyProperties(userRequest,userDTO);
//
//			UserDTO updatedUser = usersService.updateUser(userId, userDTO);
//
//			UserResponseModel userResponse = new UserResponseModel();
//			BeanUtils.copyProperties(updatedUser, userResponse);
//
//			response.setStatus("SUCCESS");
//			response.setResponseObject(userResponse);
//
//			return response;
//		} catch (Exception e) {
//
//			response.setStatus("FAIL");
//			response.setErrorMessage(e.getMessage());
//
//			return response;
//		}
//	}
//
//	@DeleteMapping("/users/{userId}")
//	public ContainerResponseModel removeUser(@PathVariable("userId") String userId) {
//
//		ContainerResponseModel response = new ContainerResponseModel();
//
//		response.setVerb("DELETE");
//		response.setEndpoint("/api/users/" + userId);
//
//		try {
//			usersService.removeUser(userId);
//
//			response.setStatus("SUCCESS");
//
//			return response;
//		} catch (Exception e) {
//
//			response.setStatus("FAIL");
//			response.setErrorMessage(e.getMessage());
//
//			return response;
//		}
//	}

}
