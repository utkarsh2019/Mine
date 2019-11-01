package tech.mineapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tech.mineapp.search.SearchItem;
import tech.mineapp.search.TVMaze.TVMazeController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TVSearchService {

    @Autowired
    private TVMazeController tvMazeController;

    public List<SearchItem> searchTVMaze(String query, int numOfSearches) {
        return Arrays.stream(tvMazeController.TVMazeSearch(query, numOfSearches))
                .map(response -> new SearchItem(
                        response.getShow().getName(),
                        response.getShow().getSummary(),
                        response.getShow().getOfficialSite(),
                        response.getShow().getImage().getMedium()
                )).collect(Collectors.toList());
    }
}
