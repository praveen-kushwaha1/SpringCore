package com.spring._02_di_xml;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Student demonstrates XML-based dependency injection.
 *
 * Constructor injection:
 * Address is supplied to the constructor.
 *
 * Setter injection:
 * Department and other properties are supplied through setters.
 *
 * Simple values:
 * studentId, studentName, fees.
 *
 * Collections:
 * skills, certifications and marks.
 */
public class Student {

    private int studentId;
    private String studentName;
    private double fees;

    private Address address;
    private Department department;

    private List<String> skills;
    private Set<String> certifications;
    private Map<String, Integer> marks;

    // Constructor injection: Address is required at construction time.
    public Student(Address address) {
        this.address = address;
        System.out.println("Student object created");
    }

    // Setter injection for simple values
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    // Setter injection for another Spring bean
    public void setDepartment(Department department) {
        this.department = department;
    }

    // Setter injection for collections
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setCertifications(Set<String> certifications) {
        this.certifications = certifications;
    }

    public void setMarks(Map<String, Integer> marks) {
        this.marks = marks;
    }

    public void displayStudent() {
        System.out.println("\n========== STUDENT DETAILS ==========");
        System.out.println("ID             : " + studentId);
        System.out.println("Name           : " + studentName);
        System.out.println("Fees           : " + fees);

        System.out.println("\n========== ADDRESS DETAILS ==========");
        address.displayAddress();

        System.out.println("\n========== DEPARTMENT DETAILS ========");
        department.displayDepartment();

        System.out.println("\n========== COLLECTION DETAILS ========");
        System.out.println("Skills         : " + skills);
        System.out.println("Certifications : " + certifications);
        System.out.println("Marks          : " + marks);
    }
}
