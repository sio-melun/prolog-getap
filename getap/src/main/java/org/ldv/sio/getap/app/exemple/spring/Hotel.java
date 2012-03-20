package org.ldv.sio.getap.app.exemple.spring;

import java.math.BigDecimal;

/**
 * A hotel where users may book stays.
 */
public class Hotel {

	private Long id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String country;
	private BigDecimal price;

	public Hotel() {
	}

	public Hotel(Long id, String name, String address, String city, String state,
	    String zip, String country, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Hotel(" + name + "," + address + "," + city + "," + zip + ")";
	}
}
