package tech.mineapp.search.seatgeek.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Venue {
	String address;
	String city;
}
