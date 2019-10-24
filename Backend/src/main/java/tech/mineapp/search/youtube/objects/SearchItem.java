package tech.mineapp.search.youtube.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchItem {
	private Id id;
	private Snippet snippet;
}
