package tech.mineapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.mineapp.model.request.SearchRequestModel;
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.SearchVideoResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.SearchService;
import tech.mineapp.service.UserService;

/**
 * @author utkarsh
 *
 */
@RestController
public class SearchController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SearchService searchService;

	@PostMapping("/search")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal userPrincipal,
												@RequestBody SearchRequestModel searchRequest) {
		
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("POST");
		response.setEndpoint("/search/video");
		
		try {
			SearchVideoResponseModel searchVideoResponse = new SearchVideoResponseModel();
			searchVideoResponse.setYoutube(searchService.videoSearchYoutube(searchRequest.getQuery(), 3));
			
			response.setStatus("SUCCESS");
			
			response.setResponseObject(searchVideoResponse);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			
			return ResponseEntity.badRequest().body(response);
		}
		        
    }
}
