package tech.mineapp.search.TMDb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import tech.mineapp.search.TMDb.objects.TMDbResponseItem;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDbResponseModel {
    private TMDbResponseItem[] results;
}
