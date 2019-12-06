package tech.mineapp.service;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.mineapp.search.lastfm.LastfmController;
import tech.mineapp.util.SearchQueryParser;
import tech.mineapp.search.SearchItem;

@Service
public class AudioSearchService {
	
	@Autowired
	private LastfmController lastfmController;

    @Autowired
    private SearchQueryParser searchQueryParser;
    
    public List<SearchItem> searchLastfm(String query, int numOfSearches) {
    	return Arrays.stream(lastfmController.searchLastfm(
    			searchQueryParser.parseSpaceDeLimitedQueries(query))
    			.getResults()
    			.getTrackmatches()
    			.getTrack())
    			.limit(numOfSearches)
    			.map(response -> new SearchItem(
    					response.getName(),
    					response.getArtist(),
    					response.getUrl(),
    					null,
    					null,
    					null
    					))
    			.collect(Collectors.toList());
    }
	
}
