package com.spring._02_di_xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Entry point for learning Spring XML configuration.
 *
 * Run with no arguments:
 * Loads 06-complete-student.xml.
 *
 * You can also pass another XML filename as a program argument.
 */
public class App {

    public static void main(String[] args) {

        // Default configuration used for the complete example.
        String configFile = "spring/06-complete-student.xml";

        // Optional command-line argument lets us run another example.
        if (args.length > 0) {
            configFile = args[0];
        }

        // Load XML bean definitions and initialize the Spring container.
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext(configFile)) {

            // Retrieve the bean using its XML id and expected Java type.
            Student student =
                    context.getBean("student", Student.class);

            // Display values and injected dependencies.
            student.displayStudent();
        }
    }
}
