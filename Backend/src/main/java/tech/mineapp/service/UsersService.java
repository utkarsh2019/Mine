package tech.mineapp.service;

import org.springframework.stereotype.Service;

import tech.mineapp.model.service.UserDTO;

/**
 * Service layer implementation'
 * for dealing with all actions
 * related to Users
 * 
 * @author amolmoses
 */
@Service
public class UsersService {
	
	public UserDTO createUser(UserDTO newUser) {
		return newUser;
	}
}
