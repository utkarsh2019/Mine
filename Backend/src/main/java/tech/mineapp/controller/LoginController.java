package tech.mineapp.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.mineapp.model.request.LoginRequestModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.LoginResponseModel;
import tech.mineapp.model.service.LoginDTO;
import tech.mineapp.service.LoginService;
import tech.mineapp.service.UsersService;

/**
 * @author utkarsh
 *
 */
@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/api/login")
	public ContainerResponseModel createUser(@RequestBody LoginRequestModel userRequest) {
		
		LoginDTO loginDTO = new LoginDTO();
		BeanUtils.copyProperties(userRequest,loginDTO);
		
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("POST");
		response.setEndpoint("/api/login");
		
		try {
			Boolean isLoggedIn = loginService.loginUser(loginDTO);
			
			LoginResponseModel loginResponse = new LoginResponseModel();
			loginResponse.setIsLoggedIn(isLoggedIn);
			
			response.setStatus("SUCCESS");
			response.setResponseObject(loginResponse);
			
			return response;
			
		} catch (Exception e) {
			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			
			return response;
		}
	}
}
