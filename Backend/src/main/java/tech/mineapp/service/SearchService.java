package tech.mineapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.mineapp.search.api.YoutubeApi;

/**
 * @author utkarsh
 *
 */
@Service
public class SearchService {

	@Autowired
	private YoutubeApi youtubeApi;
	
	public ResponseEntity<?> videoSearchYoutube(String query, int n) {
		return youtubeApi.youtubeVideoSearch(query, n);
	}
}
