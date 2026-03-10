package com.pdsa.analyzer.model;

public class Student {

    private String studentId;
    private String name;
    private int[] marks;   // Array for subjects
    private int total;
    private double average;
    private String grade;

    public Student(String studentId, String name, int[] marks) {
        this.studentId = studentId;
        this.name = name;
        this.marks = marks;
        calculateResults();
    }

    private void calculateResults() {
        total = 0;
        for (int mark : marks) {
            total += mark;
        }
        average = (double) total / marks.length;

        if (average >= 75) grade = "Distinction";
        else if (average >= 60) grade = "Credit";
        else if (average >= 40) grade = "Pass";
        else grade = "Fail";
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getTotal() { return total; }
    public double getAverage() { return average; }
    public String getGrade() { return grade; }

    @Override
    public String toString() {
        return studentId + " | " + name +
                " | Total: " + total +
                " | Avg: " + average +
                " | Grade: " + grade;
    }
}
