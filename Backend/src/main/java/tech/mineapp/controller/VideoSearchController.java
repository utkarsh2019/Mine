package tech.mineapp.controller;

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

	@PostMapping("/search")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal userPrincipal,
												@RequestBody SearchRequestModel searchRequest) {
		
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("POST");
		response.setEndpoint("/search/video");
		
		try {
			VideoSearchResponseModel videoSearchResponse = new VideoSearchResponseModel();
			videoSearchResponse.setYoutube(videoSearchService.searchYoutube(searchRequest.getQuery(), 3));
			videoSearchResponse.setVimeo(videoSearchService.searchVimeo(searchRequest.getQuery(), 3));
			
			response.setStatus("SUCCESS");
			response.setResponseObject(videoSearchResponse);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			
			return ResponseEntity.badRequest().body(response);
		}
		        
    }
}
