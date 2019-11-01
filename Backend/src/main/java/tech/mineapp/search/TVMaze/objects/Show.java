package tech.mineapp.search.TVMaze.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Show {
    private String name;
    private String officialSite;
    private String summary;
    private Image image;
}
