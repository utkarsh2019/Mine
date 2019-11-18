package tech.mineapp.search.newsapi;

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
public class NewsApiController {
	
	private RestTemplate restTemplate;
	
	private ApiPropertiesConfig apiProperties;
	
	@Autowired
    public NewsApiController(RestTemplateBuilder builder, ApiPropertiesConfig apiProperties) {
        this.restTemplate = builder.build();
        this.apiProperties = apiProperties;
    }
	
	public NewsApiResponseModel newsApiWrittenSearch(String query, int noOfSearches) {
		String newsApiDataUrl
		  = "https://newsapi.org/v2/everything?" + 
				  "sortBy=relevancy" + 
				  "&apiKey=" + apiProperties.getNewsapi().getApikey() + 
				  "&q=" + query + 
				  "&pageSize=" + noOfSearches;
		NewsApiResponseModel response
		  = restTemplate.getForObject(newsApiDataUrl, NewsApiResponseModel.class);
		return response;
	}

}
