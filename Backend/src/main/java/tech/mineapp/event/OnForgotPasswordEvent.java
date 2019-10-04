/**
 * 
 */
package tech.mineapp.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import lombok.Data;
import tech.mineapp.entity.UserEntity;

/**
 * @author utkarsh
 *
 */
@Data
public class OnForgotPasswordEvent extends ApplicationEvent {
	private UserEntity user;
	private Locale locale;
	private String appUrl;
 
    public OnForgotPasswordEvent(UserEntity user, Locale locale, String appUrl) {
        super(user);
         
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
