package com.spring._02_di.autowire.annot.cons;

import com.spring._02_di.autowire.annot.cons.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring/di/autowire/annot/cons/cons-config.xml");

		Student student = context.getBean("st", Student.class);
		System.out.println(student);

	}
}