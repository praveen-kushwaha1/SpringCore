package com.spring._06_java_config;

/*
 * Represents an employee address.
 * AppConfig will register this class as a Spring bean using @Bean.
 */
public class Address {

    private final String city;
    private final String state;

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Address{city='" + city
                + "', state='" + state + "'}";
    }
}
