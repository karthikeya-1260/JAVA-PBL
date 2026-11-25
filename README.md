# Smart Student Management System
**Course:** Object Oriented Programming through Java (25CS21002)  
**Project Type:** Project Based Learning (PBL) Project  

---

## 1. Project Overview
The **Smart Student Management System** is a modular, console-based Java application designed to allow an operator or academic administrator to manage student records efficiently during runtime. The system provides core student information management operations including adding, viewing, searching, updating, and deleting student records using an intuitive menu-driven console interface.

Records are kept strictly in-memory using the Java Collections Framework (`ArrayList<Student>`), maintaining security, clean separation of concerns, and encapsulation throughout execution.

---

## 2. Project Objectives
- Demonstrate fundamental **Object-Oriented Programming (OOP)** principles in Java.
- Implement **Encapsulation**, **Data Abstraction**, and proper object relationships.
- Utilize the **Java Collections Framework** (`ArrayList`) for dynamic in-memory data management.
- Provide a robust, user-friendly, and defensive **Command-Line Interface (CLI)** that handles invalid inputs without crashing.
- Maintain a clean three-tier architecture: **Presentation Layer**, **Application Logic Layer**, and **Data Management Layer**.

---

## 3. Technologies Used
- **Programming Language:** Java (Standard Edition / OpenJDK 21 LTS)
- **Paradigm:** Object-Oriented Programming (OOP)
- **Collections Framework:** `java.util.ArrayList`
- **Input/Output:** `java.util.Scanner`, Standard CLI (Command Line Interface)
- **Build & Runtime:** Standard Java Compiler (`javac`) and Java Virtual Machine (`java` / JVM)
- **Dependencies:** 100% Core Java — No external frameworks, databases, or third-party libraries.

---

## 4. System Architecture
The application follows a clean 3-tier architectural separation:

```
┌────────────────────────────────────────────────────────┐
│                   Presentation Layer                   │
│          (SmartStudentManagementSystem.java)           │
│   - Console UI, Main Menu Loop (do-while & switch)     │
│   - User Input Reception via Scanner                   │
│   - Formatting Output & User Validation Feedback       │
└───────────────────────────┬────────────────────────────┘
                            │
┌───────────────────────────▼────────────────────────────┐
│                Application Logic Layer                 │
│                 (StudentManager.java)                  │
│   - Duplicate Roll Number Checking (Case-Insensitive)  │
│   - Business Logic & Input Constraints Validation      │
│   - Orchestrating CRUD Operations                      │
└───────────────────────────┬────────────────────────────┘
                            │
┌───────────────────────────▼────────────────────────────┐
│                 Data Management Layer                  │
│           (ArrayList<Student> in Memory)               │
│   - In-memory collection of Student entity objects     │
│   - Encapsulated entity (Student.java)                 │
└────────────────────────────────────────────────────────┘
```

---

## 5. Directory Structure
```
SmartStudentManagementSystem/
│
├── src/
│   ├── Student.java                     # Entity class representing a single student
│   ├── StudentManager.java              # Collection manager handling CRUD logic
│   └── SmartStudentManagementSystem.java# Entry point & interactive CLI presentation
│
├── out/                                 # Compiled Java bytecode (.class files)
│   ├── Student.class
│   ├── StudentManager.class
│   └── SmartStudentManagementSystem.class
│
└── README.md                            # Complete documentation and usage guide
```

---

## 6. Java Classes and Responsibilities

### 1. `Student.java` (Entity / Model)
- **Encapsulation:** Holds private fields:
  - `rollNo` (`String`, `final` - immutable identifier)
  - `name` (`String`)
  - `department` (`String`)
  - `year` (`int`, 1 to 4)
  - `email` (`String`)
- **Constructor:** Initializes all fields upon object creation.
- **Getters:** Provides read access to student details.
- **`updateDetails(name, department, year, email)`:** Safely updates mutable fields while preserving the original `rollNo`.
- **`displayStudent()`:** Formats and prints individual student cards.

### 2. `StudentManager.java` (Logic & Data Storage)
- **Collection Storage:** Maintains `ArrayList<Student> students`.
- **Methods:**
  - `addStudent(Student student)`: Checks for duplicates (case-insensitive) and appends valid records.
  - `searchStudent(String rollNo)`: Finds and returns matching student using case-insensitive comparison (`equalsIgnoreCase`).
  - `updateStudent(rollNo, newName, newDepartment, newYear, newEmail)`: Finds student and invokes `updateDetails()`.
  - `deleteStudent(String rollNo)`: Finds and removes student from `ArrayList`.
  - `viewStudents()`: Formats and displays all records in a structured tabular view.
  - `getStudentCount()`: Returns total number of active records.

### 3. `SmartStudentManagementSystem.java` (Presentation & Main Flow)
- Contains `main(String[] args)`.
- Presents numbered interactive menu:
  1. Add Student
  2. View All Students
  3. Search Student
  4. Update Student
  5. Delete Student
  6. Exit
- Coordinates menu navigation using a `do-while` loop and `switch-case` statement.
- Reads input defensively using `nextLine()` and parses integers inside `try-catch (NumberFormatException)` blocks to prevent standard `Scanner` input buffering issues.

---

## 7. Object-Oriented Programming (OOP) Concepts Demonstrated

1. **Classes and Objects:**
   - Real-world entity modeling using `Student` class and instantiating dynamic student objects at runtime.
2. **Encapsulation:**
   - Private attributes (`rollNo`, `name`, `department`, `year`, `email`) accessible only via controlled public getters and update methods.
   - Preserves integrity by disallowing direct external mutation of the student's roll number.
3. **Constructors:**
   - Parameterized constructor initializes the student object state properly.
4. **Data Abstraction & Separation of Concerns:**
   - Presentation layer does not directly manipulate the underlying list; it interacts solely through the well-defined API methods of `StudentManager`.
5. **Java Collections Framework (`ArrayList`):**
   - Dynamic memory management without fixed array length limitations.
6. **Robust Control Flow:**
   - `do-while` menu execution, `switch-case` multi-branch dispatch, and enhanced `for-each` iterations.

---

## 8. Compilation and Execution Instructions

### Prerequisites
Make sure a Java Development Kit (JDK 8 or higher, e.g. OpenJDK 21) is installed.

### Step 1: Open Terminal in the Project Directory
Navigate to the root directory `SmartStudentManagementSystem`:
```bash
cd SmartStudentManagementSystem
```

### Step 2: Compile the Java Files
Compile all source files into the `out` directory:
```bash
javac -d out src/Student.java src/StudentManager.java src/SmartStudentManagementSystem.java
```

### Step 3: Run the Application
Execute the compiled main class:
```bash
java -cp out SmartStudentManagementSystem
```

*(Note: On Windows PowerShell, the exact same commands work out of the box).*

---

## 9. Verification & Test Suite Results

The application was tested across all required test scenarios:

| Test ID | Scenario Description | Input Data | Expected Result | Result |
|---|---|---|---|---|
| **TEST 1** | Add new student successfully | Roll: `STU101`, Name: `Arav Kumar`, Dept: `CSE`, Year: `2`, Email: `arav@example.com` | Success message displayed, record stored | **PASSED** |
| **TEST 2** | Duplicate roll number rejection | Roll: `STU101` | Error displayed: Duplicate roll numbers are not allowed | **PASSED** |
| **TEST 3** | Case-insensitive duplicate check | Roll: `stu101` | Error displayed: Duplicate detected regardless of casing | **PASSED** |
| **TEST 4** | View all student records | Option 2 selected | Tabular display showing all records and total count | **PASSED** |
| **TEST 5** | Search existing student | Roll: `stu101` (case-insensitive) | Full student details displayed | **PASSED** |
| **TEST 6** | Search nonexistent student | Roll: `STU999` | "Student Not Found" message displayed | **PASSED** |
| **TEST 7** | Update existing student | Roll: `STU101` -> Name: `Arav K.`, Dept: `CSE-AI`, Year: `3`, Email: `arav.new@example.com` | Update successful, details refreshed | **PASSED** |
| **TEST 8** | Verify roll number immutability | Inspect updated record | `STU101` remains strictly unchanged | **PASSED** |
| **TEST 9** | Delete existing student | Roll: `stu101` | Deletion success message, record removed from list | **PASSED** |
| **TEST 10** | Delete nonexistent student | Roll: `STU999` | "Student Not Found" message displayed | **PASSED** |
| **TEST 11** | Invalid study year (range check) | Year: `5` or `0` | Error: Year must be between 1 and 4. Cancelled gracefully | **PASSED** |
| **TEST 12** | Non-numeric study year | Year: `second_year` | Error: Must be numeric integer. No crash | **PASSED** |
| **TEST 13** | Blank student details rejection | Name: `""` (empty) | Error: Field cannot be blank. Operation cancelled | **PASSED** |
| **TEST 14** | Invalid menu choice | Option: `99` | Error: Invalid choice (1-6) message displayed | **PASSED** |
| **TEST 15** | Exit application | Option: `6` | Application prints exit greeting and terminates | **PASSED** |

---

## 10. Project Limitations
- **In-Memory Storage:** Student records are maintained dynamically in `ArrayList<Student>` in RAM. All records reset once the application terminates (as specified by the PBL requirements).
- **Single-User Console Application:** Designed strictly as a command-line interface for educational demonstration without concurrent networking or external persistent databases.

