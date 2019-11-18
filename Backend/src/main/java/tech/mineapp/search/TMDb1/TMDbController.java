package tech.mineapp.search.TMDb1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import tech.mineapp.config.ApiPropertiesConfig;
import tech.mineapp.util.SearchQueryParser;

@Controller
public class TMDbController {

    private RestTemplate restTemplate;

    private ApiPropertiesConfig apiPropertiesConfig;

    @Autowired
    private SearchQueryParser searchQueryParser;

    @Autowired
    public TMDbController(RestTemplateBuilder restTemplateBuilder, ApiPropertiesConfig apiPropertiesConfig){
        this.restTemplate = restTemplateBuilder.build();
        this.apiPropertiesConfig = apiPropertiesConfig;
    }

    public TMDbResponseModel searchTMDb(String query) {
        String TMDbSearchQuery
                = "https://api.themoviedb.org/3/search/movie"
                + "?api_key=" + apiPropertiesConfig.getTmdb().getApiKey()
                + "&query=" + searchQueryParser.parseSpaceDeLimitedQueries(query);

        TMDbResponseModel responses =
                restTemplate.getForObject(TMDbSearchQuery, TMDbResponseModel.class);

        return responses;
    }


}
