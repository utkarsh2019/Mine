package tech.mineapp.search.lastfm.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trackmatches {
	private Track[] track;
}
