package tech.mineapp.search.tvmaze.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Image {
    private String medium;
    private String original;
}
