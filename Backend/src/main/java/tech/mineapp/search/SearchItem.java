package tech.mineapp.search;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
public class SearchItem {
	private String name;
	private String description;
	private String url;
	private String imageUrl;
	private String date;
	private String venue;
	
	public SearchItem(String name, String description, String url, String imageUrl, String date, String venue) {
		this.name = name;
		this.description = description;
		this.url = url;
		this.imageUrl = imageUrl;
		this.date = date;
		this.venue = venue;
	}
}
