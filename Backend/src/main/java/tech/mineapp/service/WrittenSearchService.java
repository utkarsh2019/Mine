package tech.mineapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.mineapp.search.SearchItem;
import tech.mineapp.search.google.GoogleBookResponseModel;
import tech.mineapp.search.google.GoogleController;
import tech.mineapp.search.google.objects.GoogleBookSearchItem;

/**
 * @author utkarsh
 *
 */
@Service
public class WrittenSearchService {
	
	@Autowired 
	private GoogleController googleController;
	
	public List<SearchItem> searchGoogle(String query, int noOfSearches) {
		GoogleBookResponseModel response = googleController.googleBookWrittenSearch(query, noOfSearches);
		List<SearchItem> searches = new ArrayList<SearchItem>();
		for (GoogleBookSearchItem item : response.getItems()) {
			searches.add(new SearchItem(
					item.getVolumeInfo().getTitle(),
					item.getVolumeInfo().getDescription(),
					item.getVolumeInfo().getPreviewLink(),
					item.getVolumeInfo().getImageLinks().getThumbnail(),
					null,
					null));
		}
		return searches;
	}

}
