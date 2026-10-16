package com.spring._05_bean_scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
 * Demonstrates prototype scope.
 * Each getBean() call creates a separate object.
 */
@Component("prototypeEmployee")
@Scope("prototype")
public class PrototypeEmployee {

    private int id = 201;
    private String name = "New Employee";

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
        return "PrototypeEmployee{id=" + id
                + ", name='" + name + "'}";
    }
}
