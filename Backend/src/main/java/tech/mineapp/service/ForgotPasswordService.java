/**
 * 
 */
package tech.mineapp.service;

import static tech.mineapp.constants.Constants.VerificationConstants.URL_EXPIRATION_TIME;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.mineapp.entity.ForgotPasswordEntity;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.exception.UserDoesNotExistException;
import tech.mineapp.repository.ForgotPasswordRepository;

/**
 * @author utkarsh
 *
 */
@Service
@Transactional
public class ForgotPasswordService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ForgotPasswordRepository forgotPasswordRepository;
	
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
    	
    	ForgotPasswordEntity newToken = new ForgotPasswordEntity();
    	newToken.setToken(token);
    	newToken.setUser(user);
    	newToken.setExpiryDate(calculateExpiryDate());
        forgotPasswordRepository.save(newToken);
    }
	
	public UserEntity getUserByToken(String token) {
        return forgotPasswordRepository.findByToken(token).getUser();
    }
    
    public ForgotPasswordEntity getToken(String token) {
        return forgotPasswordRepository.findByToken(token);
    }
    
    public void deleteTokensByUser(UserEntity user) {
    	forgotPasswordRepository.deleteByUser(user);
    }
    
    public void deleteToken(String token) {
    	forgotPasswordRepository.deleteByToken(token);
    }
    
    public Boolean isExpired(String token) {
    	Calendar cal = Calendar.getInstance();
    	return (getToken(token).getExpiryDate().getTime() - cal.getTime().getTime()) <= 0;
    }
    
    public Boolean tokenExists(String token) {
    	return forgotPasswordRepository.existsByToken(token);
    }
}