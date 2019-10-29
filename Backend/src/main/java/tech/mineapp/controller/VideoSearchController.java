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
import tech.mineapp.model.response.VideoSearchResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.UserService;
import tech.mineapp.service.VideoSearchService;

/**
 * @author utkarsh
 *
 */
@RestController
public class VideoSearchController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VideoSearchService videoSearchService;
	
	private static final Logger logger = LoggerFactory.getLogger(VideoSearchController.class);

	@PostMapping("/search/video")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> searchVideos(@CurrentUser UserPrincipal userPrincipal,
												@RequestBody SearchRequestModel searchRequest) {
		
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("POST");
		response.setEndpoint("/search/video");
		
		try {
			int noOfSearches = userService.getNoOfSearches(userPrincipal.getUserId());
			
			VideoSearchResponseModel videoSearchResponse = new VideoSearchResponseModel();
			videoSearchResponse.setYoutube(videoSearchService.searchYoutube(searchRequest.getQuery(), noOfSearches));
			videoSearchResponse.setVimeo(videoSearchService.searchVimeo(searchRequest.getQuery(), noOfSearches));
			videoSearchResponse.setDailymotion(videoSearchService.searchDailyMotion(searchRequest.getQuery(), noOfSearches));
			
			response.setStatus("SUCCESS");
			response.setResponseObject(videoSearchResponse);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			logger.error(e.getMessage());
			
			return ResponseEntity.badRequest().body(response);
		}
		        
    }
}
