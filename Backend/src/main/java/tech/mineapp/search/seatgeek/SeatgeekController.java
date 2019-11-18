package tech.mineapp.search.seatgeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import tech.mineapp.config.ApiPropertiesConfig;

/**
 * @author utkarsh
 *
 */
@Controller
public class SeatgeekController {
	
	private RestTemplate restTemplate;
	
	private ApiPropertiesConfig apiProperties;
	
	@Autowired
    public SeatgeekController(RestTemplateBuilder builder, ApiPropertiesConfig apiProperties) {
        this.restTemplate = builder.build();
        this.apiProperties = apiProperties;
    }
	
	public SeatgeekResponseModel seatgeekEventSearch(String query, int noOfSearches) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(apiProperties.getSeatgeek().getClientId(), apiProperties.getSeatgeek().getClientSecret());
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String seatgeekDataUrl
		  = "https://api.seatgeek.com/2/events?" +
				  "venue.state=IN" + 
				  "&q=" + query + 
				  "&per_page=" + noOfSearches;
		ResponseEntity<SeatgeekResponseModel> response
		  = restTemplate.exchange(seatgeekDataUrl, HttpMethod.GET, entity, SeatgeekResponseModel.class);
		return response.getBody();
	}
}
