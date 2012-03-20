package org.ldv.sio.getap.app.exemple.spring;

import java.util.List;

/**
 * Service to search for Hotels
 */
public interface HotelSearchService {

	/**
	 * Search for Hotels with the provided criteria
	 * 
	 * @param searchCriteria
	 *            The criteria to narrow the search
	 * @return List of Hotels
	 */
	public List<Hotel> search(HotelSearchCriteria searchCriteria);

	/**
	 * Select a specific Hotel by its id, or null if no Hotel is found
	 * 
	 * @param id
	 *            The id of the Hotel to find
	 * @return The Hotel
	 */
	public Hotel getHotel(Long id);

}
