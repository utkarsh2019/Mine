package tech.mineapp.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static tech.mineapp.constants.Constants.VerificationConstants.URL_EXPIRATION_TIME;
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
	
	private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, URL_EXPIRATION_TIME);
        return new Date(cal.getTime().getTime());
    }
	
	public void createVerificationToken(UserEntity user, String token) {
    	if (!userService.userIdAlreadyExists(user.getUserId())) {
    		throw new UserDoesNotExistException();
    	}
    	
    	VerificationTokenEntity myToken = new VerificationTokenEntity();
    	myToken.setToken(token);
    	myToken.setUser(user);
    	myToken.setExpiryDate(calculateExpiryDate());
        tokenRepository.save(myToken);
    }
	
	public UserEntity getUser(String verificationToken) {
        UserEntity user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }
    
    public VerificationTokenEntity getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }
    
    public void deleteVerificationToken(UserEntity user) {
    	tokenRepository.deleteByUser(user);
    }
}
