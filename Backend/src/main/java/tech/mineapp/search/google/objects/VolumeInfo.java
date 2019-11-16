package tech.mineapp.search.google.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {
	private String title;
	private String description;
	private ImageLinks imageLinks;
	private String previewLink;
}
