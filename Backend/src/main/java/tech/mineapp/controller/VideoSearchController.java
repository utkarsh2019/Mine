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
import tech.mineapp.entity.ApiEntity;
import tech.mineapp.entity.UserEntity;
import tech.mineapp.model.request.SearchRequestModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.VideoSearchResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.SearchPersistenceService;
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

	@Autowired
	private SearchPersistenceService searchPersistenceService;
	
	private static final Logger logger = LoggerFactory.getLogger(VideoSearchController.class);

	@PostMapping("/search/video")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> searchVideos(@CurrentUser UserPrincipal userPrincipal,
												@RequestBody SearchRequestModel searchRequest) {
		
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("POST");
		response.setEndpoint("/search/video");
		
		try {
			UserEntity user = userService.findUserById(userPrincipal.getUserId());
			int noOfSearches = userService.getNoOfSearches(user);
			ApiEntity apiList = userService.getApiList(user);
			
			VideoSearchResponseModel videoSearchResponse = new VideoSearchResponseModel();
			if (apiList.getYoutube()) {
				videoSearchResponse.setYoutube(videoSearchService.searchYoutube(searchRequest.getQuery(), noOfSearches));
			}
			if (apiList.getVimeo()) {
				videoSearchResponse.setVimeo(videoSearchService.searchVimeo(searchRequest.getQuery(), noOfSearches));
			}
			if (apiList.getDailymotion()) {
				videoSearchResponse.setDailymotion(videoSearchService.searchDailyMotion(searchRequest.getQuery(), noOfSearches));
			}

			searchPersistenceService
					.persistSearchDetails(user, Category.video, searchRequest.getQuery());
			
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
