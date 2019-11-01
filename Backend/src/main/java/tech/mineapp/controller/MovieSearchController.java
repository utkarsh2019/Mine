package tech.mineapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.mineapp.model.request.SearchRequestModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.MovieSearchResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.MovieSearchService;
import tech.mineapp.service.UserService;

/**
 * Controller for Movie Searches
 *
 * @author amolmoses
 */
@RestController
public class MovieSearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieSearchService movieSearchService;

    private static final Logger logger = LoggerFactory.getLogger(MovieSearchController.class);

    @PostMapping("/search/movie")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> searchMovies(@CurrentUser UserPrincipal userPrincipal,
                                          @RequestBody SearchRequestModel searchRequest) {

        ContainerResponseModel response = new ContainerResponseModel();

        response.setVerb("POST");
        response.setEndpoint("/search/movie");

        try {
            int noOfSearches = userService.getNoOfSearches(userPrincipal.getUserId());

            MovieSearchResponseModel moviesResponse = new MovieSearchResponseModel();
            moviesResponse.setTMDb(movieSearchService.searchTMDb(searchRequest.getQuery(),
                    noOfSearches));

            response.setStatus("SUCCESS");
            response.setResponseObject(moviesResponse);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus("FAIL");
            response.setErrorMessage(e.getMessage());
            logger.error(e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}
