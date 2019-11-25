package tech.mineapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.DummySearchResponseModel;

/**
 * Controller for accessing user's previous searches
 *
 * @author amolmoses
 */
@RestController
public class TrendingSearchController {

    @GetMapping("/search/trending")
    public ResponseEntity<?> getTrendingSearches() {
        ContainerResponseModel response = new ContainerResponseModel();

        response.setVerb("GET");
        response.setEndpoint("/user/me/search/previous");

        response.setStatus("SUCCESS");
        response.setResponseObject(new DummySearchResponseModel());

        return ResponseEntity.ok(response);
    }

}
