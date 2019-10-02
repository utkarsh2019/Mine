package tech.mineapp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.exception.UserAlreadyExistsException;
import tech.mineapp.exception.UserDoesNotExistException;
import tech.mineapp.model.service.UserDTO;
import tech.mineapp.repository.UserRepository;
import tech.mineapp.util.RandomAlphanumericStringGenerator;

import java.util.Arrays;

import static java.util.Collections.emptyList;
import static tech.mineapp.constants.Constants.ApplicationConstants.userIdLength;

/**
 * Service layer implementation for dealing with all actions
 * related to Users
 * 
 * @author amolmoses
 */
@Service
public class UsersServiceImplementation implements UsersService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDTO createUser(UserDTO newUserDTO) {

		validateUserInputForCreation(newUserDTO);

		UserEntity newUserEntity = new UserEntity();
		BeanUtils.copyProperties(newUserDTO, newUserEntity);

		setEntityProperties(newUserEntity);

		UserEntity savedEntity = userRepository.save(newUserEntity);

		UserDTO createdUserDTO = new UserDTO();
		BeanUtils.copyProperties(savedEntity, createdUserDTO);

		return createdUserDTO;
	}

	public void setEntityProperties(UserEntity newUserEntity) {
		newUserEntity.setUserId(generateIdForUser());
		newUserEntity.setPassword(passwordEncoder.encode(newUserEntity.getPassword()));
	}

	public String generateIdForUser() {
		String potentialUserId;

		do {
			potentialUserId = RandomAlphanumericStringGenerator.generateAlphanumericString(userIdLength);
		}while(userIdAlreadyExists(potentialUserId));

		return potentialUserId;
	}

	public void validateUserInputForCreation(UserDTO newUserDTO) {
		if (userAlreadyExists(newUserDTO.getEmailId())) {
			throw new UserAlreadyExistsException();
		}
	}

	public boolean userAlreadyExists(String email) {
		return userRepository.findUserByEmailId(email) != null;
	}

	public boolean userIdAlreadyExists(String userId) {
		return userRepository.findUserByUserId(userId) != null;
	}
	
	public UserDTO getUser(String userId) {
		UserDTO userDTO = new UserDTO();
		UserEntity userEntity = userRepository.findUserByUserId(userId);
		if (userEntity == null) {
			throw new UserDoesNotExistException();
		}
		BeanUtils.copyProperties(userEntity, userDTO);
		return userDTO;
	}

	@Override
	public void removeUser(String userId) {
		if (!userIdAlreadyExists(userId)) {
			throw new UserDoesNotExistException();
		}
		userRepository.deleteByUserId(userId);
	}

	@Override
	public UserDTO updateUser(String userId, UserDTO updatedUserDTO) {
		if (!userIdAlreadyExists(userId)) {
			throw new UserDoesNotExistException();
		}

		UserEntity existingUserEntity = userRepository.findUserByUserId(userId);
		BeanUtils.copyProperties(
				updatedUserDTO,
				existingUserEntity,
				"userId");
		// Ignoring the userId property from the DTO because it will most likely be null
		userRepository.save(existingUserEntity);

		return updatedUserDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findUserByEmailId(email);

		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		} else {
			return new User(userEntity.getEmailId(), userEntity.getPassword(), emptyList());
		}
	}
}
