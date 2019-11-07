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
import tech.mineapp.model.response.WrittenSearchResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.UserService;
import tech.mineapp.service.WrittenSearchService;

/**
 * @author utkarsh
 *
 */
@RestController
public class WrittenSearchController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WrittenSearchService writtenSearchService;

	private static final Logger logger = LoggerFactory.getLogger(VideoSearchController.class);
	
	@PostMapping("/search/written")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> searchWritten(@CurrentUser UserPrincipal userPrincipal,
												@RequestBody SearchRequestModel searchRequest) {
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("POST");
		response.setEndpoint("/search/written");
		
		try {
			int noOfSearches = userService.getNoOfSearches(userPrincipal.getUserId());
			
			WrittenSearchResponseModel writtenSearchResponse = new WrittenSearchResponseModel();
			writtenSearchResponse.setReddit(writtenSearchService.searchReddit(searchRequest.getQuery(), noOfSearches));
			
			response.setStatus("SUCCESS");
			response.setResponseObject(writtenSearchResponse);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			logger.error(e.getMessage());
			
			return ResponseEntity.badRequest().body(response);
		}
	}
}
