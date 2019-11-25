package tech.mineapp.model.response;

import lombok.Data;
import tech.mineapp.search.SearchSnippet;

import java.util.Arrays;
import java.util.List;

@Data
public class DummySearchResponseModel implements ResponseModel {
    private List<SearchSnippet> videoSearches = Arrays.asList(new SearchSnippet("VideoQuery01"), new SearchSnippet("VideoQuery02"));
    private List<SearchSnippet> tvSeriesSearches = Arrays.asList(new SearchSnippet("TVSeriesQuery01"), new SearchSnippet("TVSeriesQuery02"));
    private List<SearchSnippet> movieSearches = Arrays.asList(new SearchSnippet("MovieQuery01"), new SearchSnippet("MovieQuery02"));
    private List<SearchSnippet> writtenSearches = Arrays.asList(new SearchSnippet("WrittenQuery01"), new SearchSnippet("WrittenQuery02"));
    private List<SearchSnippet> eventSearches = Arrays.asList(new SearchSnippet("EventQuery01"), new SearchSnippet("EventQuery02"));
    private List<SearchSnippet> audioSearches = Arrays.asList(new SearchSnippet("AudioQuery01"), new SearchSnippet("AudioQuery02"));
}
