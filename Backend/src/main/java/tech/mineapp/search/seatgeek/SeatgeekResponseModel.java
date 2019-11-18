package tech.mineapp.search.seatgeek;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import tech.mineapp.search.seatgeek.objects.SeatgeekSearchItem;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeatgeekResponseModel {
	private SeatgeekSearchItem[] events;
}
