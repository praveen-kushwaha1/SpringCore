package com.spring._06_java_config;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * Starts the Spring container using Java configuration instead of XML.
 */
public class App {

    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            EmployeeService service =
                    context.getBean(EmployeeService.class);

            service.displayEmployee();
        }
    }
}
