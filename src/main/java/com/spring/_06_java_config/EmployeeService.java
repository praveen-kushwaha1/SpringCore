package com.spring._06_java_config;


import org.springframework.stereotype.Service;

/*
 * @Service registers this class through component scanning.
 * Constructor injection supplies the required dependencies.
 */
@Service
public class EmployeeService {

    private final Employee employee;
    private final Address address;

    public EmployeeService(Employee employee, Address address) {
        this.employee = employee;
        this.address = address;
    }

    public void displayEmployee() {
        System.out.println("Employee Details");
        System.out.println(employee);
        System.out.println(address);
    }
}
