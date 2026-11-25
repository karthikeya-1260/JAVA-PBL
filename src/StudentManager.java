import java.util.ArrayList;

/**
 * StudentManager.java
 * 
 * Manages the collection of student records using an ArrayList<Student>.
 * Belongs to the Application Logic & Data Management Layers.
 * Implements CRUD operations: Add, View, Search, Update, Delete.
 */
public class StudentManager {
    // In-memory collection storing Student objects
    private final ArrayList<Student> students;

    /**
     * Constructor initializing the student list.
     */
    public StudentManager() {
        this.students = new ArrayList<>();
    }

    /**
     * Adds a new student record to the collection after checking for duplicates.
     *
     * @param student Student object to add
     * @return true if student was added successfully, false if duplicate roll number exists
     */
    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }
        // Duplicate check (case-insensitive)
        if (searchStudent(student.getRollNo()) != null) {
            return false;
        }
        students.add(student);
        return true;
    }

    /**
     * Searches for a student by roll number using case-insensitive comparison.
     *
     * @param rollNo Roll number to search for
     * @return Student object if found, or null if not found
     */
    public Student searchStudent(String rollNo) {
        if (rollNo == null || rollNo.trim().isEmpty()) {
            return null;
        }
        for (Student student : students) {
            if (student.getRollNo().equalsIgnoreCase(rollNo.trim())) {
                return student;
            }
        }
        return null;
    }

    /**
     * Updates an existing student's editable information.
     *
     * @param rollNo        Roll number of the student to update
     * @param newName       New name
     * @param newDepartment New department
     * @param newYear       New study year (1-4)
     * @param newEmail      New email address
     * @return true if updated successfully, false if student not found
     */
    public boolean updateStudent(String rollNo, String newName, String newDepartment, int newYear, String newEmail) {
        Student student = searchStudent(rollNo);
        if (student == null) {
            return false;
        }
        student.updateDetails(newName, newDepartment, newYear, newEmail);
        return true;
    }

    /**
     * Deletes a student from the collection using roll number.
     *
     * @param rollNo Roll number of the student to delete
     * @return true if deleted successfully, false if student not found
     */
    public boolean deleteStudent(String rollNo) {
        Student student = searchStudent(rollNo);
        if (student == null) {
            return false;
        }
        return students.remove(student);
    }

    /**
     * Displays all stored student records in an organized tabular format.
     */
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("\n[!] No student records found. The system is currently empty.");
            return;
        }

        System.out.println("\n=========================================================================================");
        System.out.println("                                ALL REGISTERED STUDENTS                                  ");
        System.out.println("=========================================================================================");
        System.out.printf("%-12s | %-22s | %-12s | %-6s | %-26s%n",
                "ROLL NO", "STUDENT NAME", "DEPARTMENT", "YEAR", "EMAIL ADDRESS");
        System.out.println("-------------+------------------------+--------------+--------+--------------------------");

        for (Student student : students) {
            System.out.printf("%-12s | %-22s | %-12s | %-6d | %-26s%n",
                    student.getRollNo(),
                    student.getName(),
                    student.getDepartment(),
                    student.getYear(),
                    student.getEmail());
        }

        System.out.println("=========================================================================================");
        System.out.printf("Total Student Record(s): %d%n%n", students.size());
    }

    /**
     * Returns total number of students currently maintained.
     *
     * @return count of students
     */
    public int getStudentCount() {
        return students.size();
    }
}

