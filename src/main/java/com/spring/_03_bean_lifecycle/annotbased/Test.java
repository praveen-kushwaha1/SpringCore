package com.spring._03_bean_lifecycle.annotbased;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Runs the annotation-based bean lifecycle example.
 */
public class Test {

	public static void main(String[] args) {
		AbstractApplicationContext context =
				new ClassPathXmlApplicationContext(
						"spring/lifecycle/lifecycle-annotation.xml");

		// Registers a JVM shutdown hook to close the Spring context.
		context.registerShutdownHook();

		Student student = context.getBean("annot", Student.class);
		System.out.println(student);
	}
}