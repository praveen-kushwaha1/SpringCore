package com.spring._01_basics;

/*
 * Shyam provides different behavior for the same Action contract.
 */
public class Shyam implements Action {

	public Shyam() {
		System.out.println("Shyam object is created");
	}

	@Override
	public void eat() {
		System.out.println("Shyam is eating");
	}

	@Override
	public void sleep() {
		System.out.println("Shyam is sleeping");
	}
}