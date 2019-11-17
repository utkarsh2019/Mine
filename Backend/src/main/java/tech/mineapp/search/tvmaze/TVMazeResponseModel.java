package tech.mineapp.search.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import tech.mineapp.search.tvmaze.objects.Show;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TVMazeResponseModel {
    private Show show;
}
