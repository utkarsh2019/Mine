package tech.mineapp.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.mineapp.search.SearchItem;
import tech.mineapp.search.dailymotion.DailyMotionController;
import tech.mineapp.search.dailymotion.DailyMotionResponseModel;
import tech.mineapp.search.dailymotion.objects.DailyMotionSearchItem;
import tech.mineapp.search.google.GoogleController;
import tech.mineapp.search.google.YoutubeResponseModel;
import tech.mineapp.search.google.objects.YoutubeSearchItem;
import tech.mineapp.search.vimeo.VimeoController;
import tech.mineapp.search.vimeo.VimeoResponseModel;
import tech.mineapp.search.vimeo.objects.VimeoSearchItem;

/**
 * @author utkarsh
 *
 */
@Service
public class VideoSearchService {

	@Autowired
	private GoogleController youtubeController;
	
	@Autowired
	private VimeoController vimeoController;
	
	@Autowired
	private DailyMotionController dailyMotionController;
	
	public List<SearchItem> searchYoutube(String query, int noOfSearches) {
		YoutubeResponseModel response = youtubeController.youtubeVideoSearch(query, noOfSearches);
		List<SearchItem> searches = new ArrayList<SearchItem>();
		for (YoutubeSearchItem item : response.getItems()) {
			searches.add(new SearchItem(
					item.getSnippet().getTitle(),
					Jsoup.parse(item.getSnippet().getDescription()).text(),
					"https://www.youtube.com/watch?v=" + item.getId().getVideoId(),
					item.getSnippet().getThumbnails().getHigh().getUrl(),
					null,
					null));
		}
		return searches;
	}
	
	public List<SearchItem> searchVimeo(String query, int noOfSearches) {
		VimeoResponseModel response = vimeoController.vimeoVideoSearch(query, noOfSearches);
		List<SearchItem> searches = new ArrayList<SearchItem>();
		for (VimeoSearchItem item : response.getData()) {
			searches.add(new SearchItem(
					item.getName(),
					item.getDescription(),
					item.getLink(),
					item.getPictures().getSizes()[0].getLink(),
					null,
					null));
		}
		return searches;
	}
	
	public List<SearchItem> searchDailyMotion(String query, int noOfSearches) {
		DailyMotionResponseModel response = dailyMotionController.dailyMotionVideoSearch(query, noOfSearches);
		List<SearchItem> searches = new ArrayList<SearchItem>();
		for (DailyMotionSearchItem item : response.getList()) {
			searches.add(new SearchItem(
					item.getTitle(),
					null,
					"https://www.dailymotion.com/video/" + item.getId(),
					null,
					null,
					null));
		}
		return searches;
	}
}
