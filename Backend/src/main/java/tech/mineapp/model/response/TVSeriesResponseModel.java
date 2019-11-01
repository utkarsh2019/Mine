package tech.mineapp.model.response;

import lombok.Data;
import tech.mineapp.search.SearchItem;

import java.util.List;

@Data
public class TVSeriesResponseModel implements ResponseModel {
    private List<SearchItem> TVMazeSearchResponses;
}
