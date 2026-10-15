package com.spring._02_di_xml;

/**
 * Address is a normal Java class.
 *
 * Spring can manage its objects as beans when they are
 * declared in an XML configuration file.
 */
public class Address {

    private String houseNumber;
    private String city;
    private String state;
    private int pinCode;

    // No-argument constructor for setter-based configuration
    public Address() {
        System.out.println("Address object created");
    }

    // Constructor for constructor injection examples
    public Address(String houseNumber, String city,
                   String state, int pinCode) {
        this.houseNumber = houseNumber;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    // Setter methods: Spring calls these for XML properties
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public void displayAddress() {
        System.out.println("House Number : " + houseNumber);
        System.out.println("City         : " + city);
        System.out.println("State        : " + state);
        System.out.println("PIN Code     : " + pinCode);
    }
}
