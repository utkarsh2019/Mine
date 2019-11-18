package tech.mineapp.search.newsapi.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApiSearchItem {
	private String title;
	private String description;
	private String url;
	private String urlToImage;
}
