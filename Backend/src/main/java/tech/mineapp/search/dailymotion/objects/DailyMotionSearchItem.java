package tech.mineapp.search.dailymotion.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyMotionSearchItem {
	private String id;
	private String title;
}
