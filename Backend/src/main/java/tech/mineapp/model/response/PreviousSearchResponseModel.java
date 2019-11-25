package tech.mineapp.model.response;

import lombok.Data;

import java.util.List;

/**
 * Represent previous searches by the user,
 * divided into categories
 *
 * @author amolmoses
 */
@Data
public class PreviousSearchResponseModel implements ResponseModel {
    private List<String> videoSearches;
    private List<String> tvSeriesSearches;
    private List<String> movieSearches;
    private List<String> writtenSearches;
    private List<String> eventSearches;
    private List<String> audioSearches;
}
