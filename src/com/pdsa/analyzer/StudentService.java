package com.pdsa.analyzer;

import java.util.LinkedList;
import java.util.Stack;

public class StudentService {

    private boolean isSorted = false;
    private LinkedList<Student> students = new LinkedList<>();
    private Stack<Student> undoStack = new Stack<>();

    public void addStudent(Student student) {
        students.add(student);
        undoStack.push(student);
        System.out.println("Student added successfully!");
    }

    public void undoLastStudent() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        Student last = undoStack.pop();
        students.remove(last);
        System.out.println("Last student entry undone.");
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void sortByAverageBubble() {
        int n = students.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (students.get(j).getAverage() > students.get(j + 1).getAverage()) {
                    // swap
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }

        isSorted = true;
        System.out.println("Students sorted by average marks (Bubble Sort).");
    }

    public void sortByTotalBubble() {
        int n = students.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (students.get(j).getTotal() > students.get(j + 1).getTotal()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }

        isSorted = true;
        System.out.println("Students sorted by total marks (Bubble Sort).");
    }

    public void sortByNameSelection() {
        int n = students.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (students.get(j).getName()
                        .compareToIgnoreCase(students.get(minIndex).getName()) < 0) {
                    minIndex = j;
                }
            }

            Student temp = students.get(minIndex);
            students.set(minIndex, students.get(i));
            students.set(i, temp);
        }

        isSorted = true;
        System.out.println("Students sorted by name (Selection Sort).");
    }

    public void searchByStudentId(String studentId) {
        boolean found = false;

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                System.out.println("Student found:");
                System.out.println(student);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    public void searchByName(String name) {
        boolean found = false;

        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println("Student found:");
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No student found with name " + name);
        }
    }

    public void binarySearchByStudentId(String studentId) {
        int low = 0;
        int high = students.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int compare = students.get(mid).getStudentId().compareToIgnoreCase(studentId);

            if (compare == 0) {
                System.out.println("Student found:");
                System.out.println(students.get(mid));
                return;
            } else if (compare < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Student not found (Binary Search).");
    }

    public void findHighestAverage() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        Student topStudent = students.getFirst();

        for (Student student : students) {
            if (student.getAverage() > topStudent.getAverage()) {
                topStudent = student;
            }
        }

        System.out.println("Student with Highest Average:");
        System.out.println(topStudent);
    }

    public void findLowestAverage() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        Student lowStudent = students.getFirst();

        for (Student student : students) {
            if (student.getAverage() < lowStudent.getAverage()) {
                lowStudent = student;
            }
        }

        System.out.println("Student with Lowest Average:");
        System.out.println(lowStudent);
    }

    public void findTopThreeStudents() {
        if (students.size() < 3) {
            System.out.println("Not enough students to determine top 3.");
            return;
        }

        // Create a temporary copy
        LinkedList<Student> tempList = new LinkedList<>(students);

        // Bubble sort temp list by average (descending)
        int n = tempList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tempList.get(j).getAverage() < tempList.get(j + 1).getAverage()) {
                    Student temp = tempList.get(j);
                    tempList.set(j, tempList.get(j + 1));
                    tempList.set(j + 1, temp);
                }
            }
        }

        System.out.println("Top 3 Students by Average:");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + tempList.get(i));
        }
    }

    public boolean isSorted() {
        return isSorted;
    }

}
