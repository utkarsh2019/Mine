package tech.mineapp.search.reddit;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import tech.mineapp.config.ApiPropertiesConfig;

/**
 * @author utkarsh
 *
 */
@Controller
public class RedditController {
	
	private RestTemplate restTemplate;
	
	private ApiPropertiesConfig apiProperties;
	
	private String accessToken;
	
	@Autowired
    public RedditController(RestTemplateBuilder builder, ApiPropertiesConfig apiProperties) {
		this.restTemplate = builder.build();
		this.apiProperties = apiProperties;
    }
	
	public RedditResponseModel redditWrittenSearch(String query, int noOfSearches) {
		HttpHeaders headers = new HttpHeaders();
//		System.out.println(accessToken);
		headers.set("Authorization", "Bearer " + "-3jJrAo_v_kP8OBbUlzGlgdiNkQk");
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String redditDataUrl
		  = "https://oauth.reddit.com/search?" +
				  "sort=relevance" +
				  "&q=" + query +
				  "&limit=" + noOfSearches;
		ResponseEntity<RedditResponseModel> response
		  = restTemplate.exchange(redditDataUrl, HttpMethod.GET, entity, RedditResponseModel.class);
		return response.getBody();
	}
	
	public void setRedditOauthToken() {
		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String auth = apiProperties.getReddit().getClientId() + ":" + apiProperties.getReddit().getClientSecret();
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + new String( encodedAuth );
		headers.set("Authorization", authHeader);
//		headers.setBasicAuth(apiProperties.getReddit().getClientId(), apiProperties.getReddit().getClientSecret());
		
//		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//		map.add("grant_type", "client_credentials");
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String redditOauthUrl = "https://www.reddit.com/api/v1/access_token";
		ResponseEntity<RedditOauthResponseModel> response
		  = restTemplate.exchange(redditOauthUrl, HttpMethod.POST, entity, RedditOauthResponseModel.class);
		accessToken = response.getBody().getAccess_token();
	}
}
