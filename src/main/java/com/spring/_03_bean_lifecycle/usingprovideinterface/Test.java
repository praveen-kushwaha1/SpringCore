package com.spring._03_bean_lifecycle.usingprovideinterface;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {

		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring/lifecycle/lifecycleinterfacebased.xml");

		context.registerShutdownHook();

		Student student = context.getBean("st", Student.class);
		System.out.println(student);

	}
}