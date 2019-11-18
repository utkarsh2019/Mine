package tech.mineapp.search.reddit.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChildrenData {
	private String thumbnail;
	private String permalink;
	private String title;
}
