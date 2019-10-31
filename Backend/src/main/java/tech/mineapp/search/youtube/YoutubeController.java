package tech.mineapp.search.youtube;

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
public class YoutubeController {
	
	private RestTemplate restTemplate;
	
	private ApiPropertiesConfig apiProperties;
	
	@Autowired
    public YoutubeController(RestTemplateBuilder builder, ApiPropertiesConfig apiProperties) {
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
}
