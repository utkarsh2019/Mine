package tech.mineapp.search.google;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import tech.mineapp.config.ApiPropertiesConfig;

/**
 * @author utkarsh
 *
 */
@Controller
public class GoogleController {
	
	private RestTemplate restTemplate;
	
	private ApiPropertiesConfig apiProperties;
	
	@Autowired
    public GoogleController(RestTemplateBuilder builder, ApiPropertiesConfig apiProperties) {
        this.restTemplate = builder.build();
        this.apiProperties = apiProperties;
    }
	
	public YoutubeResponseModel youtubeVideoSearch(String query, int noOfSearches) {
		String youtubeDataUrl
		  = "https://www.googleapis.com/youtube/v3/search?" +
				  "part=snippet" +
				  "&type=video" +
				  "&key=" + apiProperties.getGoogle().getApiKey() +
				  "&q=" + query +
				  "&maxResults=" + noOfSearches;
		YoutubeResponseModel response
		  = restTemplate.getForObject(youtubeDataUrl, YoutubeResponseModel.class);
		return response;
	}
	
	public GoogleBookResponseModel googleBookWrittenSearch(String query, int noOfSearches) {
		String googleBookDataUrl
		 = "https://www.googleapis.com/books/v1/volumes?" + 
		   "orderBy=relevance" + 
		   "&maxResults=" + noOfSearches + 
		   "&q=" + query;
		GoogleBookResponseModel response
		 = restTemplate.getForObject(googleBookDataUrl, GoogleBookResponseModel.class);
		return response;
	}
}
