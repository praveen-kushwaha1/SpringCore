
package com.spring._04_stereotype_annot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Loads the XML configuration and retrieves prototype-scoped beans.
 */
public class App {

	public static void main(String[] args) {

		ApplicationContext context =
				new ClassPathXmlApplicationContext(
						"spring/stereotype/config.xml");

		Emp em = context.getBean("employee", Emp.class);
		Emp em2 = context.getBean("employee", Emp.class);

		System.out.println(em);
		System.out.println("First object hashCode: " + em.hashCode());

		System.out.println(em2);
		System.out.println("Second object hashCode: " + em2.hashCode());

		System.out.println("Same object? " + (em == em2));

		((ClassPathXmlApplicationContext) context).close();
	}
}
