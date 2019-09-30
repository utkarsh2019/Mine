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
}
