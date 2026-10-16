package com.spring._02_di.autowire.xml.cons;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Loads Spring XML configuration and retrieves the Student bean.
 */
public class App {

	public static void main(String[] args) {

		ApplicationContext context =
				new ClassPathXmlApplicationContext(
						"spring/di/autowire/xml/cons/config-cons.xml");

		Student student = context.getBean("st", Student.class);

		System.out.println(student);

		((ClassPathXmlApplicationContext) context).close();
	}
}