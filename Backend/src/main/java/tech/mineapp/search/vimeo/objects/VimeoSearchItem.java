package tech.mineapp.search.vimeo.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoSearchItem {
	private String name;
	private String description;
	private String link;
	private Pictures pictures;
}
