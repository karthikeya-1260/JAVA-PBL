/**
 * Student.java
 * 
 * Represents an individual student entity in the Smart Student Management System.
 * Demonstrates OOP concepts: Encapsulation, Constructors, Data Abstraction.
 */
public class Student {
    // Private attributes ensuring encapsulation
    private final String rollNo; // Roll number cannot be modified once created
    private String name;
    private String department;
    private int year;
    private String email;

    /**
     * Parameterized constructor to initialize a student's record.
     *
     * @param rollNo     Unique roll number of the student (immutable)
     * @param name       Full name of the student
     * @param department Academic department/branch
     * @param year       Year of study (1 to 4)
     * @param email      Email address of the student
     */
    public Student(String rollNo, String name, String department, int year, String email) {
        this.rollNo = rollNo;
        this.name = name;
        this.department = department;
        this.year = year;
        this.email = email;
    }

    /**
     * Getter for the student's unique roll number.
     *
     * @return roll number
     */
    public String getRollNo() {
        return rollNo;
    }

    /**
     * Getter for the student's name.
     *
     * @return student name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for department.
     *
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Getter for year of study.
     *
     * @return year (1 - 4)
     */
    public int getYear() {
        return year;
    }

    /**
     * Getter for email address.
     *
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the editable details of the student.
     * Note: The roll number remains unchanged to preserve identity.
     *
     * @param name       Updated full name
     * @param department Updated department
     * @param year       Updated year of study
     * @param email      Updated email address
     */
    public void updateDetails(String name, String department, int year, String email) {
        this.name = name;
        this.department = department;
        this.year = year;
        this.email = email;
    }

    /**
     * Displays complete student information in a formatted block.
     */
    public void displayStudent() {
        System.out.println("------------------------------------------------");
        System.out.printf("  Roll Number : %s%n", rollNo);
        System.out.printf("  Name        : %s%n", name);
        System.out.printf("  Department  : %s%n", department);
        System.out.printf("  Year        : %d%n", year);
        System.out.printf("  Email       : %s%n", email);
        System.out.println("------------------------------------------------");
    }
}

