package tech.mineapp.model.response;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class DummySearchResponseModel implements ResponseModel {
    private List<String> videoSearches = Arrays.asList("VideoQuery01", "VideoQuery02");
    private List<String> tvSeriesSearches = Arrays.asList("TVSeriesQuery01", "TVSeriesQuery02");
    private List<String> movieSearches = Arrays.asList("MovieQuery01", "MovieQuery02");
    private List<String> writtenSearches = Arrays.asList("WrittenQuery01", "WrittenQuery02");
    private List<String> eventSearches = Arrays.asList("EventQuery01", "EventQuery02");
    private List<String> audioSearches = Arrays.asList("AudioQuery01", "AudioQuery02");
}
