package tech.mineapp.search.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import tech.mineapp.search.google.objects.GoogleBookSearchItem;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBookResponseModel {
	private GoogleBookSearchItem[] items;
}
