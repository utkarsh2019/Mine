package tech.mineapp.search.vimeo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

/**
 * @author utkarsh
 *
 */
@Controller
public class VimeoController {
	
private RestTemplate restTemplate;
	
	@Autowired
    public VimeoController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
	
	public VimeoResponseModel vimeoVideoSearch(String query, int noOfSearch) {
		String vimeoDataUrl
		  = "https://api.vimeo.com/videos?" +
				  "access_token=822488738e9c2b7fc073f29121966e75" +
				  "&query=" + query +
				  "&per_page=" + noOfSearch;
		VimeoResponseModel response
		  = restTemplate.getForObject(vimeoDataUrl, VimeoResponseModel.class);
		return response;
	}
}
