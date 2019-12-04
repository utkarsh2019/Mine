package tech.mineapp.search.lastfm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import tech.mineapp.config.ApiPropertiesConfig;
import tech.mineapp.util.SearchQueryParser;

@Controller
public class LastfmController {
	
	private RestTemplate restTemplate;

    private ApiPropertiesConfig apiPropertiesConfig;
    
    @Autowired
    private SearchQueryParser searchQueryParser;
    
    public LastfmController() {
    	
    }

    @Autowired
    public LastfmController(RestTemplateBuilder restTemplateBuilder, ApiPropertiesConfig apiPropertiesConfig){
        this.restTemplate = restTemplateBuilder.build();
        this.apiPropertiesConfig = apiPropertiesConfig;
    }
    
    public LastfmResponseModel searchLastfm(String query) {
    	String LastfmSearchUrl = "http://ws.audioscrobbler.com/2.0/?method=track.search"
    			+ "&track=" + searchQueryParser.parseSpaceDeLimitedQueries(query)
    			+ "&api_key=" + apiPropertiesConfig.getLastfm().getApiKey()
    			+ "&format=json";
    	
    	LastfmResponseModel responses = 
    			restTemplate.getForObject(LastfmSearchUrl, LastfmResponseModel.class);
    	
    	
    	return responses;
    }
    

}
