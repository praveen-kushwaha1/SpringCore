package com.spring._02_di.autowire.xml.cons;

/**
 * Represents a student's address.
 * Spring creates and configures this object as a bean.
 */
public class Address {

	private String city;
	private String state;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + "]";
	}
}