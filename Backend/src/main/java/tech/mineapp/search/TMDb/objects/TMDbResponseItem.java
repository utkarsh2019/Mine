package tech.mineapp.search.TMDb.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDbResponseItem {
    private String original_title;
    private String overview;
    private String poster_path;
}
