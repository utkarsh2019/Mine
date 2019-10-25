package tech.mineapp.search.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

/**
 * @author utkarsh
 *
 */
@Controller
public class YoutubeController {
	
	private RestTemplate restTemplate;
	
	@Autowired
    public YoutubeController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
	
	public YoutubeResponseModel youtubeVideoSearch(String query, int noOfSearches) {
		String youtubeDataUrl
		  = "https://www.googleapis.com/youtube/v3/search?" +
				  "part=snippet" +
				  "&type=video" +
				  "&key=AIzaSyBTVgmDUTKrGoRLS9IFF1ZvS6o7N3FVTrM" +
				  "&q=" + query +
				  "&maxResults=" + noOfSearches;
		YoutubeResponseModel response
		  = restTemplate.getForObject(youtubeDataUrl, YoutubeResponseModel.class);
		return response;
	}
}
