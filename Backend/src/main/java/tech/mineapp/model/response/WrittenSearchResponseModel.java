package tech.mineapp.model.response;

import java.util.List;

import lombok.Data;
import tech.mineapp.search.SearchItem;

/**
 * @author utkarsh
 *
 */
@Data
public class WrittenSearchResponseModel implements ResponseModel {
	private List<SearchItem> google;
	private List<SearchItem> newsapi;
}
