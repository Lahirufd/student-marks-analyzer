package com.pdsa.analyzer.controller;

import com.pdsa.analyzer.model.Student;
import com.pdsa.analyzer.datastructure.SinglyLinkedList;
import com.pdsa.analyzer.datastructure.MyStack;
import com.pdsa.analyzer.datastructure.MyQueue;

public class StudentService {

    private boolean isSorted = false;

    private SinglyLinkedList students = new SinglyLinkedList();
    private MyStack undoStack = new MyStack();
    private MyQueue analysisQueue = new MyQueue();

    /* ================= ADD & UNDO ================= */

    public void addStudent(Student student) {
        students.addNodeEnd(student);
        undoStack.push(student);
    }

    public String undoLastStudent() {

        if (undoStack.isEmpty()) {
            return "Nothing to undo.";
        }

        Student last = undoStack.pop();
        students.remove(last);

        return "Last student removed.";
    }

    /* ================= DISPLAY ================= */

    public String displayStudents() {

        if (students.isEmpty()) {
            return "No students available.";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < students.size(); i++) {
            sb.append(students.get(i)).append("\n");
        }

        return sb.toString();
    }

    /* ================= SORT ================= */

    public String sortByAverageBubble() {

        int n = students.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (students.get(j).getAverage() > students.get(j + 1).getAverage()) {

                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }

        isSorted = true;
        return "Sorted by Average (Bubble Sort)";
    }

    public String sortByTotalBubble() {

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
        return "Sorted by Total (Bubble Sort)";
    }

    public String sortByNameSelection() {

        int n = students.size();

        for (int i = 0; i < n - 1; i++) {

            int min = i;

            for (int j = i + 1; j < n; j++) {

                if (students.get(j).getName()
                        .compareToIgnoreCase(students.get(min).getName()) < 0) {

                    min = j;
                }
            }

            Student temp = students.get(min);
            students.set(min, students.get(i));
            students.set(i, temp);
        }

        isSorted = true;
        return "Sorted by Name (Selection Sort)";
    }

    /* ================= SEARCH ================= */

    public String searchByStudentId(String id) {

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);

            if (s.getStudentId().equalsIgnoreCase(id)) {
                return "Student Found:\n" + s;
            }
        }

        return "Student not found.";
    }

    public String searchByName(String name) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);

            if (s.getName().equalsIgnoreCase(name)) {
                sb.append(s).append("\n");
            }
        }

        if (sb.length() == 0) {
            return "No student found.";
        }

        return sb.toString();
    }

    public String binarySearchByStudentId(String id) {

        int low = 0;
        int high = students.size() - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int cmp = students.get(mid)
                    .getStudentId()
                    .compareToIgnoreCase(id);

            if (cmp == 0) {
                return "Student Found:\n" + students.get(mid);
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Student not found.";
    }

    /* ================= PERFORMANCE ================= */

    public String findHighestAverage() {

        if (students.isEmpty()) {
            return "No students available.";
        }

        Student best = students.getFirst();

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);

            if (s.getAverage() > best.getAverage()) {
                best = s;
            }
        }

        return "Highest Average:\n" + best;
    }

    public String findLowestAverage() {

        if (students.isEmpty()) {
            return "No students available.";
        }

        Student low = students.getFirst();

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);

            if (s.getAverage() < low.getAverage()) {
                low = s;
            }
        }

        return "Lowest Average:\n" + low;
    }

    public String findTopThreeStudents() {

        if (students.size() < 3) {
            return "Not enough students.";
        }

        // Bubble Sort Descending
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {

                if (students.get(j).getAverage() < students.get(j + 1).getAverage()) {

                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            sb.append((i + 1))
                    .append(". ")
                    .append(students.get(i))
                    .append("\n");
        }

        return sb.toString();
    }

    /* ================= QUEUE ================= */

    public String addStudentsToQueue() {

        if (students.isEmpty()) {
            return "No students to add.";
        }

        analysisQueue.clear();

        for (int i = 0; i < students.size(); i++) {
            analysisQueue.add(students.get(i));
        }

        return "Students added to queue.";
    }

    public String processNextStudent() {

        if (analysisQueue.isEmpty()) {
            return "Queue empty.";
        }

        Student s = analysisQueue.poll();
        return "Processing:\n" + s;
    }

    public String viewQueueStatus() {

        if (analysisQueue.isEmpty()) {
            return "Queue empty.";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < analysisQueue.size(); i++) {
            Student s = analysisQueue.get(i);

            sb.append(s.getStudentId())
                    .append(" - ")
                    .append(s.getName())
                    .append("\n");
        }

        return sb.toString();
    }

    public boolean isSorted() {
        return isSorted;
    }
}