package tech.mineapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author utkarsh
 *
 */
@Configuration
public class AppMessagesConfig {
	
	@Bean
    public ResourceBundleMessageSource messageSource() {

		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("messages_en");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }
}
