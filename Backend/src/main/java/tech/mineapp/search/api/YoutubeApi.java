package tech.mineapp.search.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author utkarsh
 *
 */
@Service
public class YoutubeApi {
	
	private RestTemplate restTemplate;
	
	@Autowired
    public YoutubeApi(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
	
	public ResponseEntity<String> youtubeVideoSearch(String query, int noOfSearch) {
		String fooResourceUrl
		  = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyBTVgmDUTKrGoRLS9IFF1ZvS6o7N3FVTrM&part=snippet&type=video&q="+query+"&maxResults="+noOfSearch;
		ResponseEntity<String> response
		  = restTemplate.getForEntity(fooResourceUrl, String.class);
		return response;
//		try {
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode root = mapper.readTree(response.getBody());
//		log.info("", root);
//		JsonNode name = root.path("name");
//		assertThat(name.asText(), notNullValue());
//		} catch (IOException e) {
			// TODO: Error handling
//		}
	}
}
