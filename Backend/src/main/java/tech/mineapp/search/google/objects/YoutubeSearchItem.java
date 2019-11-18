package tech.mineapp.search.google.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeSearchItem {
	private Id id;
	private Snippet snippet;
}
