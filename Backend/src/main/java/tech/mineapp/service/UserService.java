package tech.mineapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static tech.mineapp.constants.Constants.ApplicationConstants.*;

import tech.mineapp.constants.AuthProvider;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.entity.VerificationTokenEntity;
import tech.mineapp.exception.ResourceNotFoundException;
import tech.mineapp.exception.UserDoesNotExistException;
import tech.mineapp.repository.UserRepository;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.util.RandomAlphanumericStringGenerator;

/**
 * @author utkarsh
 *
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByEmail(email)
        		.orElseThrow(() ->
        			new UsernameNotFoundException("User not found with email : " + email));

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(String userId) {
        UserEntity user = userRepository.findUserByUserId(Long.parseLong(userId))
        		.orElseThrow(() -> 
        			new ResourceNotFoundException("User", "id", userId));

        return UserPrincipal.create(user);
    }
    
    public Long generateIdForUser() {
		String potentialUserId;

//		do {
			potentialUserId = RandomAlphanumericStringGenerator.generateAlphanumericString(userIdLength);
//		} while(userIdAlreadyExists(Long.parseLong(potentialUserId)));

		return Long.parseLong(potentialUserId);
	}
    
    public boolean userIdAlreadyExists(Long userId) {
		return userRepository.findUserByUserId(userId) != null;
	}
    
    public boolean isLocalUser(UserEntity user) {
    	return user.getProvider() == AuthProvider.local;
    }
}