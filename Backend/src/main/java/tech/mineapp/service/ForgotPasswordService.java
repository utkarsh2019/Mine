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
	private ForgotPasswordRepository fpRepository;
	
	private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, URL_EXPIRATION_TIME);
        return new Date(cal.getTime().getTime());
    }
	
	public void createForgotPasswordToken(UserEntity user, String token) {
    	if (!userService.userIdAlreadyExists(user.getUserId())) {
    		throw new UserDoesNotExistException();
    	}
    	
    	ForgotPasswordEntity myToken = new ForgotPasswordEntity();
    	myToken.setToken(token);
    	myToken.setUser(user);
    	myToken.setExpiryDate(calculateExpiryDate());
        fpRepository.save(myToken);
    }
	
	public UserEntity getUser(String fpToken) {
        UserEntity user = fpRepository.findByToken(fpToken).getUser();
        return user;
    }
    
    public ForgotPasswordEntity getForgotPasswordToken(String fpToken) {
        return fpRepository.findByToken(fpToken);
    }
    
    public void deleteForgotPasswordToken(UserEntity user) {
    	fpRepository.deleteByUser(user);
    }
}