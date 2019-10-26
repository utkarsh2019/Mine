package tech.mineapp.search.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import tech.mineapp.search.youtube.objects.YoutubeSearchItem;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeResponseModel {
	private YoutubeSearchItem[] items;
}
