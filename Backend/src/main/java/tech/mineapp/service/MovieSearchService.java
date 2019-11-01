package tech.mineapp.service;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mineapp.config.ApiPropertiesConfig;
import tech.mineapp.search.SearchItem;
import tech.mineapp.search.TMDb.TMDbController;
import tech.mineapp.util.SearchQueryParser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSearchService {

    @Autowired
    private TMDbController TMDbController;

    @Autowired
    private ApiPropertiesConfig apiPropertiesConfig;

    @Autowired
    private SearchQueryParser searchQueryParser;

    public List<SearchItem> searchTMDb(String query, int numOfSearches) {
        return Arrays.stream(TMDbController.searchTMDb(query).getResults())
                .limit(numOfSearches)
                .map(response -> new SearchItem(
                        response.getOriginal_title(),
                        Jsoup.parse(response.getOverview()).text(),
                        "https://www.google.com/search?q=" +
                                searchQueryParser.parseSpaceDeLimitedQueries(query),
                        apiPropertiesConfig.getTmdb().getImageUrl()
                                + response.getPoster_path()
                )).collect(Collectors.toList());
    }
}
