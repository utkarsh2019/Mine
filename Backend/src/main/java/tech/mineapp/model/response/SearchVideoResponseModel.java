package tech.mineapp.model.response;

import lombok.Data;
import tech.mineapp.search.youtube.YoutubeResponseModel;

/**
 * @author utkarsh
 *
 */
@Data
public class SearchVideoResponseModel implements ResponseModel {
	private YoutubeResponseModel youtube;
}
