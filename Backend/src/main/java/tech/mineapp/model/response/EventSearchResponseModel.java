package tech.mineapp.model.response;

import java.util.List;

import lombok.Data;
import tech.mineapp.search.SearchItem;

/**
 * @author utkarsh
 *
 */
@Data
public class EventSearchResponseModel implements ResponseModel {
	public List<SearchItem> seatgeek;
}
