package com.spring._03_bean_lifecycle.usingprovideinterface;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Demonstrates bean lifecycle callbacks using Spring interfaces.
 *
 * InitializingBean provides afterPropertiesSet().
 * DisposableBean provides destroy().
 */
public class Student implements InitializingBean, DisposableBean {

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
	 * Called after Spring has populated the bean's properties.
	 */
	@Override
	public void afterPropertiesSet() {
		System.out.println("InitializingBean: Student initialized");
	}

	/**
	 * Called when Spring destroys the bean.
	 */
	@Override
	public void destroy() {
		System.out.println("DisposableBean: Student destroyed");
	}
}