package tech.mineapp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@ConfigurationProperties(prefix = "app")
@Data
public class AppPropertiesConfig {
	private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();

    @Data
    public static class Auth {
        private String tokenSecret;
        private long tokenExpirationMsec;
    }
    
    @Data
    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}
