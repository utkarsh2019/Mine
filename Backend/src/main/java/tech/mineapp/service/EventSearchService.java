package tech.mineapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.mineapp.search.SearchItem;
import tech.mineapp.search.seatgeek.SeatgeekController;
import tech.mineapp.search.seatgeek.SeatgeekResponseModel;
import tech.mineapp.search.seatgeek.objects.SeatgeekSearchItem;

/**
 * @author utkarsh
 *
 */
@Service
public class EventSearchService {
	
	@Autowired
	private SeatgeekController seatgeekController;
	
	public List<SearchItem> searchSeatgeek(String query, int noOfSearches) {
		SeatgeekResponseModel response = seatgeekController.seatgeekEventSearch(query, noOfSearches);
		List<SearchItem> searches = new ArrayList<SearchItem>();
		for (SeatgeekSearchItem item : response.getEvents()) {
			searches.add(new SearchItem(
					item.getTitle(),
					null,
					item.getUrl(),
					null,
					item.getDatetime_local(),
					item.getVenue().getAddress() + ", " + item.getVenue().getCity()));
		}
		return searches;
	}
}
