package tech.mineapp.search.vimeo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import tech.mineapp.search.vimeo.objects.VimeoSearchItem;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoResponseModel {
	private VimeoSearchItem[] data;
}
