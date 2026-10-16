package com.spring._06_java_config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * @Component allows Spring to discover Employee through component scanning.
 */
@Component
public class Employee {

    @Value("101")
    private int id;

    @Value("Praveen")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id
                + ", name='" + name + "'}";
    }
}
