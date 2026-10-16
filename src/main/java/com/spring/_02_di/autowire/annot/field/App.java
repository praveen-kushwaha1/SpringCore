package com.spring._02_di.autowire.annot.field;

import com.spring._02_di.autowire.annot.field.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring/di/autowire/annot/field/field-config.xml");

		Student student = context.getBean("st", Student.class);
		System.out.println(student);

	}
}