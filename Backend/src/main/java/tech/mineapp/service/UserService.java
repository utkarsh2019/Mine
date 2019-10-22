package tech.mineapp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.mineapp.constants.AuthProvider;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.exception.ResourceNotFoundException;
import tech.mineapp.model.request.UserRequestModel;
import tech.mineapp.repository.UserRepository;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.util.RandomLongGenerator;

/**
 * @author utkarsh
 *
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return UserPrincipal.create(findUserByEmail(email));
    }
    
    public UserEntity findUserByEmail(String email) {
    	return userRepository.findUserByEmail(email)
        		.orElseThrow(() ->
    				new ResourceNotFoundException("User", "email", email));
    }
    
    public UserDetails loadUserById(String userId) {
        return UserPrincipal.create(findUserById(Long.parseLong(userId)));
    }
    
    public UserEntity findUserById(Long userId) {
    	return userRepository.findUserByUserId(userId)
        		.orElseThrow(() -> 
    				new ResourceNotFoundException("User", "id", userId));
    }
    
    public Long generateIdForUser() {
//		String potentialUserId;

//		do {
//			potentialUserId = RandomLongGenerator.generateRandomLong();
//		} while(userIdAlreadyExists(Long.parseLong(potentialUserId)));

		return RandomLongGenerator.generateRandomLong();
	}
    
    public boolean userIdAlreadyExists(Long userId) {
		return userRepository.existsByUserId(userId);
	}
    
    public boolean userEmailAlreadyExists(String email) {
    	return userRepository.existsByEmail(email);
    }
    
    public boolean isLocalUser(UserEntity user) {
    	return user.getProvider() == AuthProvider.local;
    }
    
    public boolean checkVerificationByEmail(String email) {
    	UserEntity user = userRepository.findUserByEmail(email)
        		.orElseThrow(() ->
        			new ResourceNotFoundException("User", "email", email));
    	return user.getIsVerified();
    }
    
    public boolean checkVerificationByUserId(Long userId) {
    	UserEntity user = userRepository.findUserByUserId(userId)
        		.orElseThrow(() ->
        			new ResourceNotFoundException("User", "id", userId));
    	return user.getIsVerified();
    }
    
    public UserEntity createLocalUser(String name, String email, String password) {
    	 UserEntity user = new UserEntity();
	     user.setUserId(generateIdForUser());
	     user.setName(name);
	     user.setEmail(email);
	     user.setPassword(password);
	     user.setProvider(AuthProvider.local);
	     user.setNoOfPreviousSearches(3);	
	     user.setCategoryPreferences("movies,music,social,text,audio");
	     
	     return userRepository.save(user);
    }
    
    public UserEntity createOauthUser(String name, String email, AuthProvider provider, String providerId, String profilePicUrl) {
    	UserEntity user = new UserEntity();
	    user.setUserId(generateIdForUser());
	    user.setName(name);
	    user.setEmail(email);
	    user.setProvider(provider);
	    user.setProviderId(providerId);
	    user.setProfilePicUrl(profilePicUrl);
	    user.setNoOfPreviousSearches(3);	
	    user.setCategoryPreferences("movies,music,social,text,audio");
	    user.setIsVerified(true);
	     
	    return userRepository.save(user);
    }
    
    public void updateUserPassword(UserEntity user, String password) {
    	user.setPassword(password); 
	    userRepository.save(user);
    }
    
    public void verifyUser(UserEntity user) {
    	user.setIsVerified(true);
    	userRepository.save(user);
    }
    
    public void deleteUser(Long userId) {
    	userRepository.deleteByUserId(userId);
    }
    
    public UserEntity updateUser(Long userId, UserRequestModel userRequest) {
    	UserEntity user = findUserById(userId);
		String[] ignoreProperties;
		if (isLocalUser(user)) {
			ignoreProperties = new String[] {"provider", "isVerified"};
			BeanUtils.copyProperties(userRequest, user, ignoreProperties);
		}
		else {
			ignoreProperties = new String[] {"email", "name", "profilePicUrl", "provider", "isVerified"};
			BeanUtils.copyProperties(userRequest, user, ignoreProperties);
		}
		return userRepository.save(user);
    }
    
    public UserEntity updateOauthUser(UserEntity user, String name, String profilePicUrl) {
    	user.setName(name);
        user.setProfilePicUrl(profilePicUrl);
        return userRepository.save(user);
    }

	public void updateUserProfilePic(UserEntity user, String profilePicUrl) {
		user.setProfilePicUrl(profilePicUrl);
		userRepository.save(user);
	}
}