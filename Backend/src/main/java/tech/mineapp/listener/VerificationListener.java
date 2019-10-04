package tech.mineapp.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import tech.mineapp.entity.UserEntity;
import tech.mineapp.event.OnVerificationCompleteEvent;
import tech.mineapp.service.VerificationTokenService;

/**
 * @author utkarsh
 *
 */
@Component
public class VerificationListener implements
	ApplicationListener<OnVerificationCompleteEvent> {
  
    @Autowired
    private VerificationTokenService tokenService;
  
    @Autowired
    private MessageSource messages;
  
    @Autowired
    private JavaMailSender mailSender;
 
    @Override
    public void onApplicationEvent(OnVerificationCompleteEvent event) {
        this.confirmRegistration(event);
    }
 
    private void confirmRegistration(OnVerificationCompleteEvent event) {
        UserEntity user = event.getUser();
        String token = UUID.randomUUID().toString();
        tokenService.createVerificationToken(user, token);
         
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = event.getAppUrl() + "/verify/confirm?token=" + token;
        String message = messages.getMessage("regSucc", null, event.getLocale());
         
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
