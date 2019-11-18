package tech.mineapp.search.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import tech.mineapp.search.tmdb.objects.TMDbResponseItem;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDbResponseModel {
    private TMDbResponseItem[] results;
}
