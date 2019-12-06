package tech.mineapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.mineapp.constants.Category;
import tech.mineapp.model.request.SearchRequestModel;
import tech.mineapp.model.response.AudioSearchResponseModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.AudioSearchService;
import tech.mineapp.service.SearchPersistenceService;
import tech.mineapp.service.UserService;

/**
 * Controller for Audio Searches
 *
 * @author amolmoses
 */
@RestController
public class AudioSearchController {
	
	 @Autowired
	    private UserService userService;

	    @Autowired
	    private AudioSearchService audioSearchService;

	    @Autowired
	    private SearchPersistenceService searchPersistenceService;
	    
	    private static final Logger logger = LoggerFactory.getLogger(AudioSearchController.class);
	    
	    @PostMapping("/search/audio")
	    @PreAuthorize("hasRole('USER')")
	    public ResponseEntity<?> searchAudio(@CurrentUser UserPrincipal userPrincipal, @RequestBody SearchRequestModel searchRequest) {
	    			ContainerResponseModel response = new ContainerResponseModel();

	    	        response.setVerb("POST");
	    	        response.setEndpoint("/search/audio");

	    	        try {
	    	            int noOfSearches = userService.getNoOfSearches(userPrincipal.getUserId());
	    	            
	    	            AudioSearchResponseModel audioResponse = new AudioSearchResponseModel();
	    	            
	    	            audioResponse.setLastfm(audioSearchService.searchLastfm(searchRequest.getQuery(), noOfSearches));
	    	            response.setStatus("SUCCESS");
	    	            response.setResponseObject(audioResponse);
	    	            searchPersistenceService
	                    .persistSearchDetails(userPrincipal.getUserId(), Category.audio, searchRequest.getQuery());

	    	            return ResponseEntity.ok(response);
	    	        } catch (Exception e) {
	    	            response.setStatus("FAIL");
	    	            response.setErrorMessage(e.getMessage());
	    	            logger.error(e.getMessage());

	    	            return ResponseEntity.badRequest().body(response);
	    	        }
	    	    }
}
