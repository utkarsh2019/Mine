package tech.mineapp.search.lastfm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import tech.mineapp.search.lastfm.objects.Results;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastfmResponseModel {
	private Results results;
}