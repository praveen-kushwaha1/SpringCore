package com.spring._03_bean_lifecycle.xml;

/**
 * Demonstrates lifecycle callbacks configured through XML.
 *
 * The init and destroy methods are ordinary Java methods.
 * Spring invokes them because they are declared in the bean definition.
 */
public class Student {

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

	/**
	 * Configured as init-method in the XML bean definition.
	 */
	public void init() {
		System.out.println("XML init-method: Student initialized");
	}

	/**
	 * Configured as destroy-method in the XML bean definition.
	 */
	public void destroy() {
		System.out.println("XML destroy-method: Student destroyed");
	}
}