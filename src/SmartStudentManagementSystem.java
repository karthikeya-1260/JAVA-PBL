import java.util.Scanner;

/**
 * SmartStudentManagementSystem.java
 * 
 * Main entry point and presentation layer of the Smart Student Management System.
 * Object Oriented Programming through Java (25CS21002) - PBL Project.
 * 
 * Demonstrates:
 * - Menu-driven CLI interface
 * - Defensive console input handling with Scanner
 * - Input validation & error recovery
 * - Coordination of application layers
 */
public class SmartStudentManagementSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        int choice = -1;

        System.out.println("=================================================");
        System.out.println("    WELCOME TO SMART STUDENT MANAGEMENT SYSTEM   ");
        System.out.println("       Course: OOP through Java (25CS21002)     ");
        System.out.println("=================================================");

        // Do-while loop for menu navigation
        do {
            displayMenu();
            choice = readChoice();

            switch (choice) {
                case 1:
                    handleAddStudent();
                    break;
                case 2:
                    handleViewAllStudents();
                    break;
                case 3:
                    handleSearchStudent();
                    break;
                case 4:
                    handleUpdateStudent();
                    break;
                case 5:
                    handleDeleteStudent();
                    break;
                case 6:
                    handleExit();
                    break;
                default:
                    System.out.println("\n[ERROR] Invalid choice! Please select a valid option (1-6).");
            }

        } while (choice != 6);
    }

    /**
     * Displays the main menu according to documentation requirements.
     */
    private static void displayMenu() {
        System.out.println("\n================================================");
        System.out.println("           MAIN MENU - SELECT OPERATION         ");
        System.out.println("================================================");
        System.out.println("  1. Add Student");
        System.out.println("  2. View All Students");
        System.out.println("  3. Search Student");
        System.out.println("  4. Update Student");
        System.out.println("  5. Delete Student");
        System.out.println("  6. Exit");
        System.out.println("================================================");
        System.out.print("Enter Your Choice: ");
    }

    /**
     * Reads and parses user menu choice safely.
     *
     * @return integer choice, or -1 if non-numeric
     */
    private static int readChoice() {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Handles adding a new student with strict validation.
     */
    private static void handleAddStudent() {
        System.out.println("\n--- [1] ADD NEW STUDENT ---");

        // 1. Roll Number
        System.out.print("Enter Roll Number: ");
        String rollNo = scanner.nextLine().trim();
        if (rollNo.isEmpty()) {
            System.out.println("[ERROR] Roll number cannot be blank. Operation cancelled.");
            return;
        }

        // Check duplicate roll number (case-insensitive)
        if (manager.searchStudent(rollNo) != null) {
            System.out.printf("[ERROR] Student with Roll Number '%s' already exists! Duplicate roll numbers are not allowed.%n", rollNo);
            return;
        }

        // 2. Student Name
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("[ERROR] Student name cannot be blank. Operation cancelled.");
            return;
        }

        // 3. Department
        System.out.print("Enter Department: ");
        String department = scanner.nextLine().trim();
        if (department.isEmpty()) {
            System.out.println("[ERROR] Department cannot be blank. Operation cancelled.");
            return;
        }

        // 4. Year of Study
        System.out.print("Enter Year of Study (1-4): ");
        String yearStr = scanner.nextLine().trim();
        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid year! Year of study must be a numeric integer. Operation cancelled.");
            return;
        }

        if (year < 1 || year > 4) {
            System.out.println("[ERROR] Invalid year! Year of study must be between 1 and 4. Operation cancelled.");
            return;
        }

        // 5. Email Address
        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine().trim();
        if (email.isEmpty()) {
            System.out.println("[ERROR] Email address cannot be blank. Operation cancelled.");
            return;
        }

        // Create Student object and add to manager
        Student newStudent = new Student(rollNo, name, department, year, email);
        boolean success = manager.addStudent(newStudent);

        if (success) {
            System.out.println("\n[SUCCESS] Student record added successfully!");
        } else {
            System.out.println("\n[ERROR] Failed to add student. A record with this roll number already exists.");
        }
    }

    /**
     * Handles displaying all student records in tabular format.
     */
    private static void handleViewAllStudents() {
        System.out.println("\n--- [2] VIEW ALL STUDENTS ---");
        manager.viewStudents();
    }

    /**
     * Handles searching for a student by roll number.
     */
    private static void handleSearchStudent() {
        System.out.println("\n--- [3] SEARCH STUDENT ---");
        System.out.print("Enter Roll Number to Search: ");
        String rollNo = scanner.nextLine().trim();

        if (rollNo.isEmpty()) {
            System.out.println("[ERROR] Roll number cannot be blank.");
            return;
        }

        Student student = manager.searchStudent(rollNo);
        if (student != null) {
            System.out.println("\n[SUCCESS] Student Record Found:");
            student.displayStudent();
        } else {
            System.out.printf("\n[RESULT] Student Not Found with Roll Number: %s%n", rollNo);
        }
    }

    /**
     * Handles updating an existing student's details.
     */
    private static void handleUpdateStudent() {
        System.out.println("\n--- [4] UPDATE STUDENT ---");
        System.out.print("Enter Roll Number of Student to Update: ");
        String rollNo = scanner.nextLine().trim();

        if (rollNo.isEmpty()) {
            System.out.println("[ERROR] Roll number cannot be blank.");
            return;
        }

        Student existingStudent = manager.searchStudent(rollNo);
        if (existingStudent == null) {
            System.out.printf("\n[RESULT] Student Not Found with Roll Number: %s%n", rollNo);
            return;
        }

        System.out.println("\nCurrent Details:");
        existingStudent.displayStudent();
        System.out.println("Enter New Information (Roll Number cannot be modified):");

        // 1. New Name
        System.out.print("Enter New Name: ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty()) {
            System.out.println("[ERROR] Name cannot be blank. Update cancelled.");
            return;
        }

        // 2. New Department
        System.out.print("Enter New Department: ");
        String newDepartment = scanner.nextLine().trim();
        if (newDepartment.isEmpty()) {
            System.out.println("[ERROR] Department cannot be blank. Update cancelled.");
            return;
        }

        // 3. New Year
        System.out.print("Enter New Year of Study (1-4): ");
        String yearStr = scanner.nextLine().trim();
        int newYear;
        try {
            newYear = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid year! Must be a numeric integer. Update cancelled.");
            return;
        }

        if (newYear < 1 || newYear > 4) {
            System.out.println("[ERROR] Invalid year! Year must be between 1 and 4. Update cancelled.");
            return;
        }

        // 4. New Email
        System.out.print("Enter New Email Address: ");
        String newEmail = scanner.nextLine().trim();
        if (newEmail.isEmpty()) {
            System.out.println("[ERROR] Email cannot be blank. Update cancelled.");
            return;
        }

        boolean updated = manager.updateStudent(rollNo, newName, newDepartment, newYear, newEmail);
        if (updated) {
            System.out.println("\n[SUCCESS] Student details updated successfully!");
            System.out.println("Updated Details:");
            existingStudent.displayStudent();
        } else {
            System.out.println("\n[ERROR] Failed to update student details.");
        }
    }

    /**
     * Handles deleting a student record by roll number.
     */
    private static void handleDeleteStudent() {
        System.out.println("\n--- [5] DELETE STUDENT ---");
        System.out.print("Enter Roll Number to Delete: ");
        String rollNo = scanner.nextLine().trim();

        if (rollNo.isEmpty()) {
            System.out.println("[ERROR] Roll number cannot be blank.");
            return;
        }

        boolean deleted = manager.deleteStudent(rollNo);
        if (deleted) {
            System.out.printf("\n[SUCCESS] Student with Roll Number '%s' deleted successfully!%n", rollNo);
        } else {
            System.out.printf("\n[RESULT] Student Not Found with Roll Number: %s%n", rollNo);
        }
    }

    /**
     * Handles proper application exit.
     */
    private static void handleExit() {
        System.out.println("\n=================================================");
        System.out.println("  Thank you for using Smart Student Management   ");
        System.out.println("             Application Terminated.             ");
        System.out.println("=================================================");
    }
}

