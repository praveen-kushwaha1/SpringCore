package com.spring._05_bean_scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
 * Demonstrates the default singleton scope explicitly.
 * Spring shares one Employee instance within this container.
 */
@Component("singletonEmployee")
@Scope("singleton")
public class Employee {

    private int id = 101;
    private String name = "Praveen";

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }
}
