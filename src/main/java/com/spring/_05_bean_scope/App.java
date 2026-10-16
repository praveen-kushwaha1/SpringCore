package com.spring._05_bean_scope;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Compares singleton and prototype bean behavior.
 */
public class App {

    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext(
                             "spring/beanscope/config.xml")) {

            System.out.println("=== SINGLETON SCOPE ===");

            Employee e1 = context.getBean(
                    "singletonEmployee", Employee.class);

            Employee e2 = context.getBean(
                    "singletonEmployee", Employee.class);

            System.out.println(e1);
            System.out.println(e2);
            System.out.println("Same object: " + (e1 == e2));

            e1.setName("Updated Name");

            System.out.println("Name through e2: " + e2.getName());

            System.out.println("\n=== PROTOTYPE SCOPE ===");

            PrototypeEmployee p1 = context.getBean(
                    "prototypeEmployee", PrototypeEmployee.class);

            PrototypeEmployee p2 = context.getBean(
                    "prototypeEmployee", PrototypeEmployee.class);

            System.out.println(p1);
            System.out.println(p2);
            System.out.println("Same object: " + (p1 == p2));
        }
    }
}
