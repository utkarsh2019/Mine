package tech.mineapp.constants;

public class Constants {

	public class ApplicationConstants {
		public static final int userIdLength = 15;
	}
	
	public class SecurityConstants {
	    public static final String SECRET = "SecretKeyToGenJWTs";
	    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	    public static final String TOKEN_PREFIX = "Bearer ";
	    public static final String HEADER_STRING = "Authorization";
	}
	
	public class HttpCookieOAuth2AuthConstants {
		public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
	    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
	}
	
	public class VerificationConstants {
		public static final int URL_EXPIRATION_TIME = 60 * 24;
	}
}
