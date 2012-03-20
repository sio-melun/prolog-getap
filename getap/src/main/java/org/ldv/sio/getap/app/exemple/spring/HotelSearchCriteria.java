package org.ldv.sio.getap.app.exemple.spring;

/**
 * Form backing bean to hold search details
 */
public class HotelSearchCriteria {

	/**
	 * The free form search query
	 */
	private String query;

	/**
	 * Construct an empty criteria
	 */
	public HotelSearchCriteria() {
		this("");
	}

	/**
	 * Construct criteria with the provided query
	 * 
	 * @param query
	 */
	public HotelSearchCriteria(String query) {
		this.query = query;
	}

	/**
	 * Get the query
	 * 
	 * @return query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Set the query
	 * 
	 * @param query
	 */
	public void setQuery(String query) {
		this.query = query;
	}
}