package tech.mineapp.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class VerificationTokenService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, URL_EXPIRATION_TIME);
        return new Date(cal.getTime().getTime());
    }
	
	public void createToken(UserEntity user, String token) {
    	if (!userService.userIdAlreadyExists(user.getUserId())) {
    		throw new UserDoesNotExistException();
    	}
    	
    	VerificationTokenEntity newToken = new VerificationTokenEntity();
    	newToken.setToken(token);
    	newToken.setUser(user);
    	newToken.setExpiryDate(calculateExpiryDate());
        verificationTokenRepository.save(newToken);
    }
	
	public UserEntity getUserByToken(String token) {
        return verificationTokenRepository.findByToken(token).getUser();
    }
    
    public VerificationTokenEntity getToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }
    
    public void deleteTokensByUser(UserEntity user) {
    	verificationTokenRepository.deleteByUser(user);
    }
    
    public void deleteToken(String token) {
    	verificationTokenRepository.deleteByToken(token);
    }
    
    public Boolean isExpired(String token) {
    	Calendar cal = Calendar.getInstance();
    	return (getToken(token).getExpiryDate().getTime() - cal.getTime().getTime()) <= 0;
    }
    
    public Boolean tokenExists(String token) {
    	return verificationTokenRepository.existsByToken(token);
    }
}
