package tech.mineapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.mineapp.search.youtube.YoutubeController;
import tech.mineapp.search.youtube.YoutubeResponseModel;

/**
 * @author utkarsh
 *
 */
@Service
public class SearchService {

	@Autowired
	private YoutubeController youtubeApi;
	
	public YoutubeResponseModel videoSearchYoutube(String query, int n) {
		return youtubeApi.youtubeVideoSearch(query, n);
	}
}
