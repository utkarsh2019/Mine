package tech.mineapp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.exception.UserAlreadyExistsException;
import tech.mineapp.model.service.UserDTO;
import tech.mineapp.repository.UserRepository;

/**
 * Service layer implementation for dealing with all actions
 * related to Users
 * 
 * @author amolmoses
 */
@Service
public class UsersService {

	@Autowired
	private UserRepository userRepository;
	
	public UserDTO createUser(UserDTO newUserDTO) {

		validateUserInputForCreation(newUserDTO);

		UserEntity newUserEntity = new UserEntity();
		BeanUtils.copyProperties(newUserDTO, newUserEntity);

		UserEntity savedEntity = userRepository.save(newUserEntity);

		UserDTO createdUserDTO = new UserDTO();
		BeanUtils.copyProperties(savedEntity, createdUserDTO);

		return createdUserDTO;
	}

	private void validateUserInputForCreation(UserDTO newUserDTO) {

		if (userAlreadyExists(newUserDTO.getEmailId())) {
			throw new UserAlreadyExistsException();
		}
	}

	public boolean userAlreadyExists(String email) {
		return userRepository.findUserByEmailId(email) != null;
	}
	
	public UserDTO getUser(long userId) {
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		
		return user;
	}
}
