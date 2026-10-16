package com.spring._02_di.autowire.xml.byname;

/**
 * Demonstrates setter and constructor dependency injection.
 * Student depends on the Address class.
 */
public class Student {

	private Address address;

	// Used when Spring creates the bean without constructor arguments.
	public Student() {
		System.out.println("Default constructor called");
	}

	// Used for constructor-based dependency injection.
	public Student(Address address) {
		this.address = address;
		System.out.println("Constructor injection");
	}

	public Address getAddress() {
		return address;
	}

	// Used for setter injection and XML byName autowiring.
	public void setAddress(Address address) {
		System.out.println("Setter injection");
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [address=" + address + "]";
	}
}