package tech.mineapp.search.lastfm.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	private String text;
	
	@JsonProperty("#text")
    public String getText() {
      return text;
    }
	
	private String size;
}
