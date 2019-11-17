package tech.mineapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.mineapp.search.SearchItem;
import tech.mineapp.search.google.GoogleBookResponseModel;
import tech.mineapp.search.google.GoogleController;
import tech.mineapp.search.google.objects.GoogleBookSearchItem;
import tech.mineapp.search.newsapi.NewsApiController;
import tech.mineapp.search.newsapi.NewsApiResponseModel;
import tech.mineapp.search.newsapi.objects.NewsApiSearchItem;

/**
 * @author utkarsh
 *
 */
@Service
public class WrittenSearchService {
	
	@Autowired 
	private GoogleController googleController;
	
	@Autowired 
	private NewsApiController newsApiController;
	
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
	
	public List<SearchItem> searchNewsApi(String query, int noOfSearches) {
		NewsApiResponseModel response = newsApiController.newsApiWrittenSearch(query, noOfSearches);
		List<SearchItem> searches = new ArrayList<SearchItem>();
		for (NewsApiSearchItem item : response.getArticles()) {
			searches.add(new SearchItem(
					item.getTitle(),
					item.getDescription(),
					item.getUrl(),
					item.getUrlToImage(),
					null,
					null));
		}
		return searches;
	}

}
