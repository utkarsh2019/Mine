package tech.mineapp.search.reddit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import tech.mineapp.search.reddit.objects.SearchData;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditResponseModel {
	private SearchData data;
}
