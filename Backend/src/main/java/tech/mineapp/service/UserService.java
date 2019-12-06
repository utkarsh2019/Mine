package tech.mineapp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.mineapp.constants.ApiProvider;
import tech.mineapp.constants.AuthProvider;
import tech.mineapp.constants.Category;
import tech.mineapp.entity.ApiEntity;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.exception.ResourceNotFoundException;
import tech.mineapp.model.request.UserRequestModel;
import tech.mineapp.repository.ApiRepository;
import tech.mineapp.repository.UserRepository;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.util.RandomLongGeneratorUtil;

/**
 * @author utkarsh
 *
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ApiRepository apiRepository;

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
		return RandomLongGeneratorUtil.generateRandomLong();
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
    	return findUserByEmail(email).getIsVerified();
    }
    
    public boolean checkVerification(UserEntity user) {
    	return user.getIsVerified();
    }
    
    public UserEntity createLocalUser(String name, String email, String password) {
    	 UserEntity user = new UserEntity();
	     user.setUserId(generateIdForUser());
	     user.setName(name);
	     user.setEmail(email);
	     user.setPassword(password);
	     user.setProvider(AuthProvider.local);
	     user.setNoOfSearches(3);	
	     initializePreferences(user);
	     UserEntity savedUser = userRepository.save(user);
	     initializeApis(savedUser);
	     return savedUser;
    }
    
    public UserEntity createOauthUser(String name, String email, AuthProvider provider, String providerId, String profilePicUrl) {
    	UserEntity user = new UserEntity();
	    user.setUserId(generateIdForUser());
	    user.setName(name);
	    user.setEmail(email);
	    user.setProvider(provider);
	    user.setProviderId(providerId);
	    user.setProfilePicUrl(profilePicUrl);
	    user.setNoOfSearches(3);	
	    initializePreferences(user);
	    user.setIsVerified(true);	     
	    UserEntity savedUser = userRepository.save(user);
	    initializeApis(savedUser);
	    return savedUser;
    }
    
    public void updateUserPassword(UserEntity user, String password) {
    	user.setPassword(password); 
	    userRepository.save(user);
    }
    
    public void verifyUser(UserEntity user) {
    	user.setIsVerified(true);
    	userRepository.save(user);
    }
    
    public void deleteUser(UserEntity user) {
    	apiRepository.deleteByUser(user);
    	userRepository.delete(user);
    }
    
    public UserEntity updateUser(Long userId, UserRequestModel userRequest) throws Exception {
    	UserEntity user = findUserById(userId);
		String[] ignoreProperties;
		if (isLocalUser(user)) {
			ignoreProperties = new String[] {"provider", "isVerified", "categoryPreferences"};
			BeanUtils.copyProperties(userRequest, user, ignoreProperties);
		}
		else {
			ignoreProperties = new String[] {"email", "name", "profilePicUrl", "provider", "isVerified", "categoryPreferences"};
			BeanUtils.copyProperties(userRequest, user, ignoreProperties);
		}
		updatePreferences(user, userRequest.getCategoryPreferences());
		updateApis(user, userRequest.getApiList());
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
	
	public int getNoOfSearches(Long userId) {
		return findUserById(userId).getNoOfSearches();
	}
	
	public int getNoOfSearches(UserEntity user) {
		return user.getNoOfSearches();
	}
	
	public void initializePreferences(UserEntity user) {
		user.setPreference1(Category.video);
		user.setPreference2(Category.movie);
		user.setPreference3(Category.tvseries);
		user.setPreference4(Category.written);
		user.setPreference5(Category.event);
		user.setPreference6(Category.audio);
	}
	
	public void updatePreferences(UserEntity user, String categoryPreferences) throws Exception {
		String [] preferences = categoryPreferences.toLowerCase().split(",");
		int i = 1;
		for (; i <= preferences.length; i++) {
			user.getClass()
				.getMethod("setPreference"+i, Category.class)
				.invoke(user, Category.valueOf(preferences[i-1].trim()));
		}
		for(; i <= Category.values().length; i++) {
			user.getClass()
			.getMethod("setPreference"+i, Category.class)
			.invoke(user, new Object[] {null});
		}
	}
	
	public String convertToCategoryPreferences(UserEntity user) throws Exception {
		String categoryPreferences = "";
		for (int i=1; i <= Category.values().length; i++) {
			Category pref = (Category) user.getClass()
							  .getMethod("getPreference"+i)
							  .invoke(user);
			if (pref != null) {
				if (i != 1) {
					categoryPreferences += ",";
				}
				categoryPreferences += pref.toString();
			}
		}
		return categoryPreferences;
	}
	
	public void initializeApis(UserEntity user) {
		ApiEntity apiList = new ApiEntity();
		apiList.setUser(user);
		apiRepository.save(apiList);
	}
	
	public void updateApis(UserEntity user, String apiList) throws Exception {
		ApiEntity updatedApis = apiRepository.findByUser(user);
		ApiProvider[] allApiList = ApiProvider.values();
		for (int i=0; i < allApiList.length; i++) {
			String api = allApiList[i].toString();
			if (apiList.contains(api)) {
				updatedApis.getClass()
					.getMethod("set"+api, Boolean.class)
					.invoke(updatedApis, true);
			}
			else {
				updatedApis.getClass()
				.getMethod("set"+api, Boolean.class)
				.invoke(updatedApis, false);
			}
		}
		apiRepository.save(updatedApis);
	}
	
	public String convertToApiList(UserEntity user) throws Exception {
		String apiList = "";
		ApiEntity apis = apiRepository.findByUser(user);
		ApiProvider[] allApiList = ApiProvider.values();
		for (int i=0; i < allApiList.length; i++) {
			String api = allApiList[i].toString();
			if((Boolean) apis.getClass().getMethod("get"+api).invoke(apis)) {
				if (i != 0) {
					apiList += ",";
				}
				apiList += api;
			}
		}
		return apiList;
	}
	
	public ApiEntity getApiList(UserEntity user) {
		return apiRepository.findByUser(user);
	}
}