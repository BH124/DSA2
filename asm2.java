package asm1demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class representing a student with ID, name, and score.
 */
class Student {
    private String studentId;
    private String studentName;
    private double studentScore;

    public Student(String studentId, String studentName, double studentScore) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentScore = studentScore;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getStudentScore() {
        return studentScore;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentScore(double studentScore) {
        this.studentScore = studentScore;
    }

    public String getRank() {
        if (studentScore < 5.0) return "Fail";
        else if (studentScore < 6.5) return "Medium";
        else if (studentScore < 7.5) return "Good";
        else if (studentScore < 9.0) return "Very Good";
        return "Excellent";
    }

    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + studentName + ", Score: " + studentScore + ", Rank: " + getRank();
    }
}

/**
 * Class to manage a list of students with functionality like adding, editing, deleting, and sorting.
 */
class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();

    /**
     * Adds a new student to the list.
     * @param id    Student ID.
     * @param name  Student name.
     * @param score Student score.
     */
    public void addStudent(String id, String name, double score) {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                System.out.println("Error: Student with this ID already exists.");
                return;
            }
        }
        students.add(new Student(id, name, score));
        System.out.println("Student added successfully.");
    }

    /**
     * Edits an existing student's details.
     * @param id      Student ID to edit.
     * @param newName New name for the student.
     * @param newScore New score for the student.
     */
    public void editStudent(String id, String newName, double newScore) {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                student.setStudentName(newName);
                student.setStudentScore(newScore);
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Error: Student not found.");
    }

    /**
     * Deletes a student by ID.
     * @param id Student ID to delete.
     */
    public void deleteStudent(String id) {
        if (students.removeIf(student -> student.getStudentId().equals(id))) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Error: Student not found.");
        }
    }

    /**
     * Sorts students by score (descending) and name (ascending if scores are equal).
     */
    public void sortStudentsByMergeSort() {
        students = mergeSort(students);
        System.out.println("Students sorted using MergeSort.");
    }

    private List<Student> mergeSort(List<Student> list) {
        if (list.size() <= 1) return list;

        int mid = list.size() / 2;
        List<Student> left = mergeSort(new ArrayList<>(list.subList(0, mid)));
        List<Student> right = mergeSort(new ArrayList<>(list.subList(mid, list.size())));

        return merge(left, right);
    }
    private List<Student> merge(List<Student> left, List<Student> right) {
        List<Student> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            Student leftStudent = left.get(i);
            Student rightStudent = right.get(j);

            if (leftStudent.getStudentScore() > rightStudent.getStudentScore() ||
                (leftStudent.getStudentScore() == rightStudent.getStudentScore() &&
                 leftStudent.getStudentName().compareTo(rightStudent.getStudentName()) < 0)) {
                merged.add(leftStudent);
                i++;
            } else {
                merged.add(rightStudent);
                j++;
            }
        }

        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));

        return merged;
    }

    /**
     * Searches for a student by ID.
     * @param id Student ID to search for.
     */
    public void searchStudentById(String id) {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    /**
     * Displays all students.
     */
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}

/**
 * Main class to run the student management system.
 */
public class asm2 {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Display Students");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.next();
                    System.out.print("Enter Student Name: ");
                    scanner.nextLine(); // Clear buffer
                    String name = scanner.nextLine();
                    double score = validateScoreInput(scanner);
                    sms.addStudent(id, name, score);
                    break;
                case 2:
                    System.out.print("Enter Student ID to Edit: ");
                    id = scanner.next();
                    System.out.print("Enter New Student Name: ");
                    scanner.nextLine(); // Clear buffer
                    name = scanner.nextLine();
                    score = validateScoreInput(scanner);
                    sms.editStudent(id, name, score);
                    break;
                case 3:
                    System.out.print("Enter Student ID to Delete: ");
                    id = scanner.next();
                    sms.deleteStudent(id);
                    break;
                case 4:
                    sms.sortStudentsByMergeSort();
                     System.out.println("Students sorted in descending order by score:");
                    sms.displayStudents(); // Display the sorted list
                    break;

                case 5:
                    System.out.print("Enter Student ID to Search: ");
                    id = scanner.next();
                    sms.searchStudentById(id);
                    break;
                case 6:
                    sms.displayStudents();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 7);

        scanner.close();
    }

    /**
     * Validates and retrieves a valid score input from the user.
     */
        private static String validateIdInput(Scanner scanner) {
        String id;
        while (true) {
            System.out.print("Enter Student ID: ");
            id = scanner.nextLine().trim();
            if (!id.isEmpty() && id.matches("[a-zA-Z0-9]+")) {
                break;
            } else {
                System.out.println("Invalid ID. Please enter a non-empty alphanumeric ID.");
            }
        }
        return id;
    }
        private static String validateNameInput(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("Enter Student Name: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty() && name.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("Invalid name. Please enter alphabetic characters only.");
            }
        }
        return name;
    }
    private static double validateScoreInput(Scanner scanner) {
        double score;
        while (true) {
            System.out.print("Enter Student Score (0.0 to 10.0): ");
            if (scanner.hasNextDouble()) {
                score = scanner.nextDouble();
                if (score >= 0.0 && score <= 10.0) {
                    return score;
                } else {
                    System.out.println("Error: Score must be between 0.0 and 10.0.");
                }
            } else {
                System.out.println("Error: Invalid input. Please enter a numeric value.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}
