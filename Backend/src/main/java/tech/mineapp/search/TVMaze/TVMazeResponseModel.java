package tech.mineapp.search.TVMaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import tech.mineapp.search.TVMaze.objects.Show;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TVMazeResponseModel {
    private Show show;
}
