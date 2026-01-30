package com.pdsa.analyzer;

import java.util.Scanner;

public class Menu {

    private StudentService service = new StudentService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=== Student Marks Analyzer ===");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Undo Last Entry");
            System.out.println("4. Sort by Average Marks");
            System.out.println("5. Sort by Total Marks");
            System.out.println("6. Sort by Name");
            System.out.println("7. Search by Student ID");
            System.out.println("8. Search by Name");
            System.out.println("9. Find Highest Average");
            System.out.println("10. Find Lowest Average");
            System.out.println("11. Find Top 3 Students");

            if (service.isSorted()) {
                System.out.println("12. Binary Search by Student ID");
                System.out.println("13. Exit");
            } else {
                System.out.println("12. Exit");
            }

            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            if (service.isSorted()) {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> service.displayStudents();
                    case 3 -> service.undoLastStudent();
                    case 4 -> service.sortByAverageBubble();
                    case 5 -> service.sortByTotalBubble();
                    case 6 -> service.sortByNameSelection();
                    case 7 -> searchById();
                    case 8 -> searchByName();
                    case 9 -> findHighestAverage();
                    case 10 -> findLowestAverage();
                    case 11 -> findTopThree();
                    case 12 -> binarySearchById();
                    case 13 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option!");
                }
            } else {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> service.displayStudents();
                    case 3 -> service.undoLastStudent();
                    case 4 -> service.sortByAverageBubble();
                    case 5 -> service.sortByTotalBubble();
                    case 6 -> service.sortByNameSelection();
                    case 7 -> searchById();
                    case 8 -> searchByName();
                    case 9 -> findHighestAverage();
                    case 10 -> findLowestAverage();
                    case 11 -> findTopThree();
                    case 12 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option!");
                }
            }
        }
    }

    private void addStudent() {
        System.out.print("Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        int[] marks = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter mark " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }
        scanner.nextLine();

        Student student = new Student(id, name, marks);
        service.addStudent(student);
    }

    private void searchById() {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine();
        service.searchByStudentId(id);
    }

    private void searchByName() {
        System.out.print("Enter Student Name to search: ");
        String name = scanner.nextLine();
        service.searchByName(name);
    }

    private void binarySearchById() {
        System.out.print("Enter Student ID for binary search: ");
        String id = scanner.nextLine();
        service.binarySearchByStudentId(id);
    }

    private void findHighestAverage() {
        service.findHighestAverage();
    }

    private void findLowestAverage() {
        service.findLowestAverage();
    }

    private void findTopThree() {
        service.findTopThreeStudents();
    }

}
