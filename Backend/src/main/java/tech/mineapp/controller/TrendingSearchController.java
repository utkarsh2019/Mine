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
import tech.mineapp.model.response.SearchStatisticsResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.SearchStatisticsService;
import tech.mineapp.service.UserService;

/**
 * Controller for accessing user's previous searches
 *
 * @author amolmoses
 */
@RestController
public class TrendingSearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private SearchStatisticsService searchStatisticsService;

    private static final Logger logger = LoggerFactory.getLogger(MovieSearchController.class);

    @GetMapping("/search/trending")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getTrendingSearches(@CurrentUser UserPrincipal userPrincipal) {
        ContainerResponseModel response = new ContainerResponseModel();

        response.setVerb("GET");
        response.setEndpoint("/search/trending");

        try {
            int noOfSearches = userService.getNoOfSearches(userPrincipal.getUserId());

            SearchStatisticsResponseModel searchStatisticsResponseModel = new SearchStatisticsResponseModel();

            searchStatisticsResponseModel.setVideoSearches(searchStatisticsService
                    .getTrendingSearchesForCategory(Category.video, noOfSearches));
            searchStatisticsResponseModel.setTvSeriesSearches(searchStatisticsService
                    .getTrendingSearchesForCategory(Category.tvseries, noOfSearches));
            searchStatisticsResponseModel.setMovieSearches(searchStatisticsService
                    .getTrendingSearchesForCategory(Category.movie, noOfSearches));
            searchStatisticsResponseModel.setWrittenSearches(searchStatisticsService
                    .getTrendingSearchesForCategory(Category.written, noOfSearches));
            searchStatisticsResponseModel.setEventSearches(searchStatisticsService
                    .getTrendingSearchesForCategory(Category.event, noOfSearches));
            searchStatisticsResponseModel.setAudioSearches(searchStatisticsService
                    .getTrendingSearchesForCategory(Category.audio, noOfSearches));

            response.setStatus("SUCCESS");
            response.setResponseObject(searchStatisticsResponseModel);

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            response.setStatus("FAIL");
            response.setErrorMessage(e.getMessage());

            logger.error(e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

}
