package com.spring._01_basics;

/*
 * INTERFACE:
 * Defines a contract that implementing classes must follow.
 *
 * Ram and Shyam both implement Action, so both must define
 * the eat() and sleep() methods.
 */
public interface Action {

	// Interface methods are public and abstract by default.
	void eat();

	void sleep();
}