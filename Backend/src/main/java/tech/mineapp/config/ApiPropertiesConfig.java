package tech.mineapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@ConfigurationProperties(prefix = "api")
@Data
public class ApiPropertiesConfig {
	private final GoogleApi google = new GoogleApi();
	private final VimeoApi vimeo = new VimeoApi();
	private final CloudinaryApi cloudinary = new CloudinaryApi();
	
	@Data
	public static class GoogleApi {
		private String apiKey;
	}
	
	@Data
	public static class VimeoApi {
		private String accessToken;
	}
	
	@Data
	public static class CloudinaryApi {
		private String apiKey;
		private String apiSecret;
		private String cloudName;
	}
}
