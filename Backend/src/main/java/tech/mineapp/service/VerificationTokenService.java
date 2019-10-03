package tech.mineapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.entity.VerificationTokenEntity;
import tech.mineapp.exception.UserDoesNotExistException;
import tech.mineapp.repository.VerificationTokenRepository;

/**
 * @author utkarsh
 *
 */
@Service
public class VerificationTokenService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VerificationTokenRepository tokenRepository;
	
	public void createVerificationToken(UserEntity user, String token) {
    	if (!userService.userIdAlreadyExists(user.getUserId())) {
    		throw new UserDoesNotExistException();
    	}
    	
    	VerificationTokenEntity myToken = new VerificationTokenEntity();
    	myToken.setToken(token);
    	myToken.setUser(user);
        tokenRepository.save(myToken);
    }
	
	public UserEntity getUser(String verificationToken) {
        UserEntity user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }
    
    public VerificationTokenEntity getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }
}
