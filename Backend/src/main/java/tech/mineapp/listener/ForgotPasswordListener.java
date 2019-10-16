package tech.mineapp.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.event.OnForgotPasswordEvent;
import tech.mineapp.service.ForgotPasswordService;

/**
 * @author utkarsh
 *
 */
@Component
public class ForgotPasswordListener implements
	ApplicationListener<OnForgotPasswordEvent> {
  
    @Autowired
    private ForgotPasswordService fpService;
  
    @Autowired
    private MessageSource messages;
  
    @Autowired
    private JavaMailSender mailSender;
 
    @Override
    public void onApplicationEvent(OnForgotPasswordEvent event) {
        this.confirmPassword(event);
    }
 
    private void confirmPassword(OnForgotPasswordEvent event) {
        UserEntity user = event.getUser();
        String token = UUID.randomUUID().toString();
        fpService.createForgotPasswordToken(user, token);
         
        String recipientAddress = user.getEmail();
        String subject = "Forgot Password";
        String confirmationUrl 
          = event.getAppUrl() + "/forgotpasswordupdate?token=" + token;
        String message = messages.getMessage("regSucc", null, event.getLocale());
         
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n\r\n" + "http://mineapp.tech" + confirmationUrl);
        mailSender.send(email);
    }
}
