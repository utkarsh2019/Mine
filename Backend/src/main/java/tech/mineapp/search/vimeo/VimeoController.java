package tech.mineapp.search.vimeo;

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
public class VimeoController {
	
	private RestTemplate restTemplate;
	
	private ApiPropertiesConfig apiProperties;
	
	@Autowired
    public VimeoController(RestTemplateBuilder builder, ApiPropertiesConfig apiProperties) {
        this.restTemplate = builder.build();
        this.apiProperties = apiProperties;
    }
	
	public VimeoResponseModel vimeoVideoSearch(String query, int noOfSearches) {
		String vimeoDataUrl
		  = "https://api.vimeo.com/videos?" +
				  "access_token=" + apiProperties.getVimeo().getAccessToken() +
				  "&query=" + query +
				  "&per_page=" + noOfSearches;
		VimeoResponseModel response
		  = restTemplate.getForObject(vimeoDataUrl, VimeoResponseModel.class);
		return response;
	}
}
