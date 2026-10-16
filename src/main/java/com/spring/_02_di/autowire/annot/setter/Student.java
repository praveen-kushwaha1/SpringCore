package com.spring._02_di.autowire.annot.setter;

import com.spring._02_di.autowire.annot.setter.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Demonstrates setter-based dependency injection.
 */
public class Student {

	private Address address;

	public Student() {
		System.out.println("Student default constructor called");
	}

	// Spring selects the bean named ad2 and passes it to this setter.
	@Autowired
	public void setAddress(@Qualifier("ad2") Address address) {
		this.address = address;
		System.out.println("Setter injection");
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Student [address=" + address + "]";
	}
}