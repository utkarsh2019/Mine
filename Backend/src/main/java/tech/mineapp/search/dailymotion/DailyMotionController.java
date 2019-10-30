package tech.mineapp.search.dailymotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

/**
 * @author utkarsh
 *
 */
@Controller
public class DailyMotionController {
	
	private RestTemplate restTemplate;
	
	@Autowired
    public DailyMotionController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
	
	public DailyMotionResponseModel dailyMotionVideoSearch(String query, int noOfSearches) {
		String dailyMotionDataUrl
		  = "https://api.dailymotion.com/videos?" +
				  "sort=relevance" +
				  "&search=" + query +
				  "&limit=" + noOfSearches;
		DailyMotionResponseModel response
		  = restTemplate.getForObject(dailyMotionDataUrl, DailyMotionResponseModel.class);
		
		return response;
	}
}
