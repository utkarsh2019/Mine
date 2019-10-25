package tech.mineapp.model.response;

import java.util.List;

import lombok.Data;
import tech.mineapp.search.SearchItem;

/**
 * @author utkarsh
 *
 */
@Data
public class VideoSearchResponseModel implements ResponseModel {
	private List<SearchItem> youtube;
	private List<SearchItem> vimeo;
}
