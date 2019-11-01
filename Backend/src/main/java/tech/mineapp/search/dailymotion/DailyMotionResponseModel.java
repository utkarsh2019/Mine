package tech.mineapp.search.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import tech.mineapp.search.dailymotion.objects.DailyMotionSearchItem;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyMotionResponseModel {
	private DailyMotionSearchItem[] list;
}
