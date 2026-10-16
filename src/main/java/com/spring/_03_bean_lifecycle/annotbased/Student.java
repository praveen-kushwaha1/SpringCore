package com.spring._03_bean_lifecycle.annotbased;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Demonstrates Spring bean lifecycle callbacks using annotations.
 *
 * @PostConstruct runs after dependency injection and initialization
 * processing reaches the annotation callback stage.
 * @PreDestroy runs before Spring destroys the bean.
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
	 * Called during bean initialization after properties are populated.
	 */
	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct: Student initialized");
	}

	/**
	 * Called before Spring destroys this bean.
	 */
	@PreDestroy
	public void destroy() {
		System.out.println("@PreDestroy: Student destroyed");
	}
}