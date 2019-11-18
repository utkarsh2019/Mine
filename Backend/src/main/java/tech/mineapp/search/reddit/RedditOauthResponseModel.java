package tech.mineapp.search.reddit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;	

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditOauthResponseModel {
	private String access_token;
	private String token_type;
}
