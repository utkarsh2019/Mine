package tech.mineapp.service;

import org.springframework.stereotype.Service;

import tech.mineapp.model.service.LoginDTO;

/**
 * @author utkarsh
 *
 */
@Service
public class LoginService {
	
	public Boolean loginUser(LoginDTO userCred) {
		return true;
	}

}
