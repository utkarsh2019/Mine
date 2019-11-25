package tech.mineapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.mineapp.constants.Category;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.DummySearchResponseModel;
import tech.mineapp.model.response.PreviousSearchResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.PreviousSearchService;
import tech.mineapp.service.UserService;

/**
 * Controller for accessing user's previous searches
 *
 * @author amolmoses
 */
@RestController
public class UserSearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private PreviousSearchService previousSearchService;

    private static final Logger logger = LoggerFactory.getLogger(MovieSearchController.class);

    @GetMapping("/user/me/search/previous")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getPreviousSearchesForUser(@CurrentUser UserPrincipal userPrincipal) {
        ContainerResponseModel response = new ContainerResponseModel();

        response.setVerb("GET");
        response.setEndpoint("/user/me/search/previous");

        try {
            int noOfSearches = userService.getNoOfSearches(userPrincipal.getUserId());

            PreviousSearchResponseModel previousSearchResponseModel = new PreviousSearchResponseModel();

            previousSearchResponseModel.setMovieSearches(previousSearchService
                    .getPreviousSearchesForUserAndCategory(userPrincipal.getUserId(), Category.movie, noOfSearches));

            response.setStatus("SUCCESS");
            response.setResponseObject(previousSearchResponseModel);

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            response.setStatus("FAIL");
            response.setErrorMessage(e.getMessage());

            logger.error(e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/user/me/search/frequent")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getFrequentSearchesForUser(@CurrentUser UserPrincipal userPrincipal) {
        ContainerResponseModel response = new ContainerResponseModel();

        response.setVerb("GET");
        response.setEndpoint("/user/me/search/frequent");

        response.setStatus("SUCCESS");
        response.setResponseObject(new DummySearchResponseModel());

        return ResponseEntity.ok(response);
    }
}
