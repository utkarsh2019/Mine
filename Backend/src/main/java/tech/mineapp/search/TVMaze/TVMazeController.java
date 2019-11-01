package tech.mineapp.search.TVMaze;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class TVMazeController {

    private RestTemplate restTemplate;

    @Autowired
    public TVMazeController(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public TVMazeResponseModel[] TVMazeSearch(String query, int numOfSearches) {
        String TVMazeSearchUrl
                = "http://api.tvmaze.com/search/"
                + "shows?q=" + query;

        TVMazeResponseModel[] response =
                restTemplate.getForObject(TVMazeSearchUrl, TVMazeResponseModel[].class);

        return Arrays.stream(response).limit(numOfSearches).collect(Collectors.toList()).toArray(new TVMazeResponseModel[numOfSearches]);
    }
}
