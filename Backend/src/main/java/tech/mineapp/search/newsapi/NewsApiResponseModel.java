package tech.mineapp.search.newsapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import tech.mineapp.search.newsapi.objects.NewsApiSearchItem;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsApiResponseModel {
	private NewsApiSearchItem[] articles;
}
