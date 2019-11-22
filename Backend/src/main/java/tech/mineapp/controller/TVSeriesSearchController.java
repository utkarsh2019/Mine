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
import tech.mineapp.model.response.TVSeriesResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.TVSeriesSearchService;
import tech.mineapp.service.UserService;

/**
 * Controller for TVSearchInputs
 *
 * @author amolmoses
 */
@RestController
public class TVSeriesSearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private TVSeriesSearchService TVSearchService;

    private static final Logger logger = LoggerFactory.getLogger(VideoSearchController.class);

    @PostMapping("/search/tvseries")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> searchTVSeries(@CurrentUser UserPrincipal userPrincipal,
                                            @RequestBody SearchRequestModel searchRequestModel) {

        ContainerResponseModel response = new ContainerResponseModel();

        response.setVerb("POST");
        response.setEndpoint("/search/tvseries");

        try {
            int noOfSearches = userService.getNoOfSearches(userPrincipal.getUserId());

            TVSeriesResponseModel tvseriesResponse = new TVSeriesResponseModel();
            tvseriesResponse.setTVMaze(TVSearchService.searchTVMaze(searchRequestModel.getQuery(),
                    noOfSearches));

            response.setStatus("SUCCESS");
            response.setResponseObject(tvseriesResponse);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus("FAIL");
            response.setErrorMessage(e.getMessage());
            logger.error(e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

}
