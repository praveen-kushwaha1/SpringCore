package com.spring._02_di.autowire.annot.cons;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Demonstrates constructor-based dependency injection.
 */
public class Student {

	private final Address address;

	// Spring selects ad2 and passes it to the constructor.
	@Autowired
	public Student(@Qualifier("ad2") Address address) {
		this.address = address;
		System.out.println("Constructor injection");
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Student [address=" + address + "]";
	}
}