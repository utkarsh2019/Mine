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
import tech.mineapp.model.response.ContainerResponseModel;
import tech.mineapp.model.response.EventSearchResponseModel;
import tech.mineapp.security.CurrentUser;
import tech.mineapp.security.UserPrincipal;
import tech.mineapp.service.EventSearchService;
import tech.mineapp.service.SearchPersistenceService;
import tech.mineapp.service.UserService;

/**
 * @author utkarsh
 *
 */
@RestController
public class EventSearchController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EventSearchService eventSearchService;
	
	@Autowired	
	private SearchPersistenceService searchPersistenceService;

	private static final Logger logger = LoggerFactory.getLogger(VideoSearchController.class);
	
	@PostMapping("/search/event")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> searchWritten(@CurrentUser UserPrincipal userPrincipal,
												@RequestBody SearchRequestModel searchRequest) {
		ContainerResponseModel response = new ContainerResponseModel();
		
		response.setVerb("POST");
		response.setEndpoint("/search/event");
		
		try {
			int noOfSearches = userService.getNoOfSearches(userPrincipal.getUserId());
			
			EventSearchResponseModel eventSearchResponse = new EventSearchResponseModel();
			eventSearchResponse.setSeatgeek(eventSearchService.searchSeatgeek(searchRequest.getQuery(), noOfSearches));
			searchPersistenceService	
			.persistSearchDetails(userPrincipal.getUserId(), Category.event, searchRequest.getQuery());
			
			response.setStatus("SUCCESS");
			response.setResponseObject(eventSearchResponse);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {			
			response.setStatus("FAIL");
			response.setErrorMessage(e.getMessage());
			logger.error(e.getMessage());
			
			return ResponseEntity.badRequest().body(response);
		}
	}
}
