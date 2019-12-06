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
public class UserSearchStatisticsController {

    @Autowired
    private UserService userService;

    @Autowired
    private SearchStatisticsService searchStatisticsService;

    private static final Logger logger = LoggerFactory.getLogger(MovieSearchController.class);

    @GetMapping("/user/me/search/previous")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getPreviousSearchesForUser(@CurrentUser UserPrincipal userPrincipal) {
        ContainerResponseModel response = new ContainerResponseModel();

        response.setVerb("GET");
        response.setEndpoint("/user/me/search/previous");

        try {
            int noOfSearches = userService.getNoOfSearches(userPrincipal.getUserId());

            SearchStatisticsResponseModel searchStatisticsResponseModel = new SearchStatisticsResponseModel();
            
            String userCategoryPreferences = userService.convertToCategoryPreferences(userService.findUserById(userPrincipal.getUserId())); 
            String categoryPreferences = userCategoryPreferences.toLowerCase();
            
            if (categoryPreferences.contains("video")) {
            	searchStatisticsResponseModel.setVideoSearches(searchStatisticsService.
                        getPreviousSearchesForUserAndCategory(userPrincipal.getUserId(), Category.video, noOfSearches));
            }
            
            if (categoryPreferences.contains("tvseries")) {
            	searchStatisticsResponseModel.setTvSeriesSearches(searchStatisticsService.
                        getPreviousSearchesForUserAndCategory(userPrincipal.getUserId(), Category.tvseries, noOfSearches));
            }
            
            if (categoryPreferences.contains("movie")) {
            	searchStatisticsResponseModel.setMovieSearches(searchStatisticsService
                        .getPreviousSearchesForUserAndCategory(userPrincipal.getUserId(), Category.movie, noOfSearches));
            }
            
            
            if (categoryPreferences.contains("written")) {
            	searchStatisticsResponseModel.setWrittenSearches(searchStatisticsService.
                        getPreviousSearchesForUserAndCategory(userPrincipal.getUserId(), Category.written, noOfSearches));
            }
            
            if (categoryPreferences.contains("event")) {
            	searchStatisticsResponseModel.setEventSearches(searchStatisticsService.
                        getPreviousSearchesForUserAndCategory(userPrincipal.getUserId(), Category.event, noOfSearches));
            }
            
            if (categoryPreferences.contains("audio")) {
            	searchStatisticsResponseModel.setAudioSearches(searchStatisticsService.
                        getPreviousSearchesForUserAndCategory(userPrincipal.getUserId(), Category.audio, noOfSearches));
            }  

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

    @GetMapping("/user/me/search/frequent")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getFrequentSearchesForUser(@CurrentUser UserPrincipal userPrincipal) {
        ContainerResponseModel response = new ContainerResponseModel();

        response.setVerb("GET");
        response.setEndpoint("/user/me/search/frequent");

        try {
            int noOfSearches = userService.getNoOfSearches(userPrincipal.getUserId());
            
            String userCategoryPreferences = userService.convertToCategoryPreferences(userService.findUserById(userPrincipal.getUserId())); 
            String categoryPreferences = userCategoryPreferences.toLowerCase();

            SearchStatisticsResponseModel searchStatisticsResponseModel = new SearchStatisticsResponseModel();

            if (categoryPreferences.contains("video")) {
            	searchStatisticsResponseModel.setVideoSearches(searchStatisticsService.
            			getFrequentSearchesForUserAndCategory(userPrincipal.getUserId(), Category.video, noOfSearches));
            }
            
            if (categoryPreferences.contains("tvseries")) {
            	searchStatisticsResponseModel.setTvSeriesSearches(searchStatisticsService.
                        getFrequentSearchesForUserAndCategory(userPrincipal.getUserId(), Category.tvseries, noOfSearches));
            }
            
            if (categoryPreferences.contains("movies")) {
            	searchStatisticsResponseModel.setMovieSearches(searchStatisticsService
                        .getFrequentSearchesForUserAndCategory(userPrincipal.getUserId(), Category.movie, noOfSearches));
            }
            
            if (categoryPreferences.contains("written")) {
            	 searchStatisticsResponseModel.setWrittenSearches(searchStatisticsService
                         .getFrequentSearchesForUserAndCategory(userPrincipal.getUserId(), Category.written, noOfSearches));
            }

            if (categoryPreferences.contains("event")) {
            	searchStatisticsResponseModel.setEventSearches(searchStatisticsService
                        .getFrequentSearchesForUserAndCategory(userPrincipal.getUserId(), Category.event, noOfSearches));
            }
           
            
            if (categoryPreferences.contains("audio")) {
            	searchStatisticsResponseModel.setAudioSearches(searchStatisticsService
                        .getFrequentSearchesForUserAndCategory(userPrincipal.getUserId(), Category.audio, noOfSearches));
            }

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
