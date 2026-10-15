package com.spring._02_di_xml;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Department demonstrates simple values and collections.
 *
 * List: maintains order and permits duplicates.
 * Set: stores unique elements.
 * Map: stores key-value pairs.
 */
public class Department {

    private String departmentName;
    private String hodName;

    private List<String> subjects;
    private Set<String> facilities;
    private Map<String, String> faculty;

    public Department() {
        System.out.println("Department object created");
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public void setFacilities(Set<String> facilities) {
        this.facilities = facilities;
    }

    public void setFaculty(Map<String, String> faculty) {
        this.faculty = faculty;
    }

    public void displayDepartment() {
        System.out.println("Department : " + departmentName);
        System.out.println("HOD        : " + hodName);
        System.out.println("Subjects   : " + subjects);
        System.out.println("Facilities : " + facilities);
        System.out.println("Faculty    : " + faculty);
    }
}
