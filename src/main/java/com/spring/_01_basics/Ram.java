package com.spring._01_basics;

/*
 * Ram implements the Action interface.
 * Spring will create and manage this object through config.xml.
 */
public class Ram implements Action {

	// No-argument constructor used when Spring creates the bean.
	public Ram() {
		System.out.println("Ram object is created");
	}

	// Implementation of the Action interface method.
	@Override
	public void eat() {
		System.out.println("Ram is eating");
	}

	// Implementation of the Action interface method.
	@Override
	public void sleep() {
		System.out.println("Ram is sleeping");
	}
}