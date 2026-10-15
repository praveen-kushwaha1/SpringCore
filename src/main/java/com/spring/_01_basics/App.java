package com.spring._01_basics;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Demonstrates:
 * 1. Traditional Java object creation.
 * 2. Spring IoC container.
 * 3. XML bean configuration.
 * 4. Retrieving beans using getBean().
 * 5. Using an interface to access different implementations.
 */
public class App {

    public static void main(String[] args) {

        /*
         * TRADITIONAL JAVA:
         *
         * Our code directly creates the object.
         * We are responsible for deciding when to create it.
         */
        // Action action = new Ram();
        // action.eat();
        // action.sleep();

        /*
         * SPRING IOC:
         *
         * Spring reads config.xml and creates the configured beans.
         *
         * "spring/config.xml" refers to:
         * src/main/resources/spring/config.xml
         *
         * try-with-resources automatically closes the context.
         * BeanFactory context1 =
            new ClassPathXmlApplicationContext("spring/config.xml");
         */
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("spring/config.xml")) {

            /*
             * getBean("ram", Action.class):
             *
             * "ram"       -> bean id in config.xml
             * Action.class -> expected Java type
             *
             * Spring returns the Ram object through the Action interface.
             */
            Action ram = context.getBean("ram", Action.class);

            /*
             * Spring returns the Shyam object through the same interface.
             */
            Action shyam = context.getBean("shyam", Action.class);

            // Execute methods on the Ram object.
            ram.eat();
            ram.sleep();

            // Execute methods on the Shyam object.
            shyam.eat();
            shyam.sleep();
        }
    }
}

//        BeanFactory is Spring’s basic, lazy‑loading IoC container, while ApplicationContext is its advanced, enterprise‑ready container that eagerly initializes singleton beans and adds features like annotation processing, AOP, events, internationalization, and environment support.
//        Overview
//        Spring provides two main IoC containers:
//
//        BeanFactory – minimal, core container.
//        ApplicationContext – full‑featured container built on top of BeanFactory.