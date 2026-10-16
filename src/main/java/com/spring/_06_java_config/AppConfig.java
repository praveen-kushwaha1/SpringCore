package com.spring._06_java_config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * @Configuration defines Java-based Spring configuration.
 * @ComponentScan discovers Employee and EmployeeService.
 */
@Configuration
@ComponentScan(basePackages = "com.spring._06_java_config")
public class AppConfig {

    /*
     * @Bean registers the returned Address object in Spring's container.
     * The default bean name is the method name: address.
     */
    @Bean
    public Address address() {
        return new Address("Noida", "Uttar Pradesh");
    }
}
