package org.ldv.sio.getap.app.exemple.spring.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ldv.sio.getap.app.exemple.spring.Hotel;
import org.ldv.sio.getap.app.exemple.spring.HotelSearchCriteria;
import org.ldv.sio.getap.app.exemple.spring.HotelSearchService;
import org.springframework.stereotype.Service;

/**
 * Stub implementation of the HotelSearchService using a predefined set of
 * Hotels
 */
@Service("monService3")
public class Stub3HotelSearchService implements HotelSearchService {

	/**
	 * Searches stubbed Hotels using an exact match on the hotels name from the
	 * search query
	 * 
	 * @param searchCriteria
	 *          The criteria to search with
	 * @return List of Hotels matching the search
	 */
	public List<Hotel> search(HotelSearchCriteria searchCriteria) {
		List<Hotel> hotels = this.getStubHotels();
		List<Hotel> foundHotels = new ArrayList<Hotel>();
		Hotel hotel = null;
		Iterator<Hotel> hotelIt = hotels.iterator();

		while (hotelIt.hasNext()) {
			hotel = hotelIt.next();
			if (hotel.getName().contains(searchCriteria.getQuery())) {
				foundHotels.add(hotel);
			}
		}

		return foundHotels;
	}

	/**
	 * Gets the specific Hotel using the id from stubbed Hotels
	 * 
	 * @param id
	 *          The id of the Hotel to find
	 * @return The Hotel
	 */
	public Hotel getHotel(Long id) {
		List<Hotel> hotels = this.getStubHotels();
		Hotel hotel = null;
		Iterator<Hotel> hotelIt = hotels.iterator();

		while (hotel == null && hotelIt.hasNext()) {
			hotel = hotelIt.next();
			if (!id.equals(hotel.getId())) {
				hotel = null;
			}
		}

		return hotel;
	}

	/**
	 * Set of Hotels predefined for this stub service
	 * 
	 * @return List of Hotels
	 */
	protected List<Hotel> getStubHotels() {
		List<Hotel> hotels;

		hotels = new ArrayList<Hotel>();
		hotels.add(new Hotel(100l, "SIO Stub 3", "rue E.B", "Melun", "IdF",
		    "77000", "France", new BigDecimal(1)));
		hotels.add(new Hotel(1l, "Westin Diplomat", "3555 S. Ocean Drive",
		    "Hollywood", "FL", "33019", "USA", new BigDecimal(199)));
		hotels.add(new Hotel(2l, "Marriott Courtyard", "Tower Place, Buckhead",
		    "Atlanta", "GA", "30305", "USA", new BigDecimal(120)));
		hotels.add(new Hotel(3l, "Doubletree", "Tower Place, Buckhead", "Atlanta",
		    "GA", "30305", "USA", new BigDecimal(180)));
		hotels.add(new Hotel(4l, "W Hotel", "Union Square, Manhattan", "NY", "NY",
		    "10011", "USA", new BigDecimal(450)));
		hotels.add(new Hotel(5l, "W Hotel", "Lexington Ave, Manhattan", "NY", "NY",
		    "10011", "USA", new BigDecimal(450)));
		hotels.add(new Hotel(6l, "Hotel Rouge", "1315 16th Street NW",
		    "Washington", "DC", "20036", "USA", new BigDecimal(250)));
		hotels.add(new Hotel(7l, "70 Park Avenue Hotel", "70 Park Avenue", "NY",
		    "NY", "10011", "USA", new BigDecimal(300)));
		hotels.add(new Hotel(8l, "Conrad Miami", "1395 Brickell Ave", "Miami",
		    "FL", "33131", "USA", new BigDecimal(300)));
		hotels.add(new Hotel(9l, "Sea Horse Inn", "2106 N Clairemont Ave",
		    "Eau Claire", "WI", "54703", "USA", new BigDecimal(80)));
		hotels.add(new Hotel(10l, "Super 8 Eau Claire Campus Area",
		    "1151 W Macarthur Ave", "Eau Claire", "WI", "54701", "USA",
		    new BigDecimal(90)));
		hotels.add(new Hotel(11l, "Marriot Downtown", "55 Fourth Street",
		    "San Francisco", "CA", "94103", "USA", new BigDecimal(160)));
		hotels.add(new Hotel(12l, "Hilton Diagonal Mar",
		    "Passeig del Taulat 262-264", "Barcelona", "Catalunya", "08019",
		    "Spain", new BigDecimal(200)));
		hotels.add(new Hotel(13l, "Hilton Tel Aviv", "Independence Park",
		    "Tel Aviv", "", "63405", "Israel", new BigDecimal(210)));
		hotels.add(new Hotel(14l, "InterContinental Tokyo Bay", "Takeshiba Pier",
		    "Tokyo", "", "105", "Japan", new BigDecimal(240)));
		hotels.add(new Hotel(15l, "Hotel Beaulac", " Esplanade L�opold-Robert 2",
		    "Neuchatel", "", "2000", "Switzerland", new BigDecimal(130)));
		hotels.add(new Hotel(16l, "Conrad Treasury Place",
		    "William & George Streets", "Brisbane", "QLD", "4001", "Australia",
		    new BigDecimal(140)));
		hotels.add(new Hotel(17l, "Ritz Carlton", "1228 Sherbrooke St",
		    "West Montreal", "Quebec", "H3G1H6", "Canada", new BigDecimal(230)));
		hotels.add(new Hotel(18l, "Ritz Carlton", "Peachtree Rd, Buckhead",
		    "Atlanta", "GA", "30326", "USA", new BigDecimal(460)));
		hotels.add(new Hotel(19l, "Swissotel", "68 Market Street", "Sydney", "NSW",
		    "2000", "Australia", new BigDecimal(220)));
		hotels.add(new Hotel(20l, "Meli� White House", "Albany Street",
		    "Regents Park London", "", "NW13UP", "Great Britain", new BigDecimal(
		        250)));
		hotels.add(new Hotel(21l, "Hotel Allegro", "171 West Randolph Street",
		    "Chicago", "IL", "60601", "USA", new BigDecimal(210)));

		return hotels;
	}

}
