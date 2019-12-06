package tech.mineapp.search.lastfm.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
	private String name;
	private String artist;
	private String url;
	private Image[] image;
}
