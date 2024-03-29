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
	private final TmdbApi tmdb = new TmdbApi();
	private final RedditApi reddit = new RedditApi();
	private final SeatgeekApi seatgeek = new SeatgeekApi();
	private final NewsApi newsapi = new NewsApi();
	private final LastfmApi lastfm = new LastfmApi();
	
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

	@Data
	public static class TmdbApi {
		private String apiKey;
		private String imageUrl;
	}
	
	@Data
	public static class RedditApi {
		private String clientId;
		private String clientSecret;
	}
	
	@Data
	public static class SeatgeekApi {
		private String clientId;
		private String clientSecret;
	}
	
	@Data
	public static class NewsApi {
		private String apikey;
	}
	
	@Data
	public static class LastfmApi {
		private String apiKey;
	}
}
