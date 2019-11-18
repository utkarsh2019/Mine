package tech.mineapp.search.seatgeek.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeatgeekSearchItem {
	String title;
	String datetime_local;
	String url;
	Venue venue;
}
