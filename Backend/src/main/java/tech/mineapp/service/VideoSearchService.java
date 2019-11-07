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
import tech.mineapp.search.vimeo.VimeoController;
import tech.mineapp.search.vimeo.VimeoResponseModel;
import tech.mineapp.search.vimeo.objects.VimeoSearchItem;
import tech.mineapp.search.youtube.YoutubeController;
import tech.mineapp.search.youtube.YoutubeResponseModel;
import tech.mineapp.search.youtube.objects.YoutubeSearchItem;

/**
 * @author utkarsh
 *
 */
@Service
public class VideoSearchService {

	@Autowired
	private YoutubeController youtubeController;
	
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
					item.getSnippet().getThumbnails().getHigh().getUrl()));
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
					item.getPictures().getSizes()[0].getLink()));
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
					null));
		}
		return searches;
	}
}
