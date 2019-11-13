package tech.mineapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.mineapp.search.SearchItem;
import tech.mineapp.search.reddit.RedditController;
import tech.mineapp.search.reddit.RedditResponseModel;
import tech.mineapp.search.reddit.objects.RedditSearchItem;

/**
 * @author utkarsh
 *
 */
@Service
public class WrittenSearchService {
	
	@Autowired
	private RedditController redditController;
	
	public List<SearchItem> searchReddit(String query, int noOfSearches) {
		redditController.setRedditOauthToken();
		RedditResponseModel response = redditController.redditWrittenSearch(query, noOfSearches);
		List<SearchItem> searches = new ArrayList<SearchItem>();
		for (RedditSearchItem item : response.getData().getChildren()) {
			searches.add(new SearchItem(
					item.getData().getTitle(),
					null,
					"https://www.reddit.com" + item.getData().getPermalink(),
					item.getData().getThumbnail()));
		}
		return searches;
	}

}
