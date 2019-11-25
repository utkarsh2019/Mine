package tech.mineapp.model.response;

import lombok.Data;
import tech.mineapp.search.SearchSnippet;

import java.util.List;

/**
 * Represent previous searches by the user,
 * divided into categories
 *
 * @author amolmoses
 */
@Data
public class PreviousSearchResponseModel implements ResponseModel {
    private List<SearchSnippet> videoSearches;
    private List<SearchSnippet> tvSeriesSearches;
    private List<SearchSnippet> movieSearches;
    private List<SearchSnippet> writtenSearches;
    private List<SearchSnippet> eventSearches;
    private List<SearchSnippet> audioSearches;
}
