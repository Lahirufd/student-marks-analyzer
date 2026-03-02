package com.pdsa.analyzer;

import javax.swing.*;
import java.awt.*;

public class StudentUI extends JFrame {

    private StudentService service = new StudentService();
    private JTextArea outputArea = new JTextArea();

    public StudentUI() {

        setTitle("Student Marks Analyzer Tool");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        /* ---------- TITLE ---------- */
        JLabel title = new JLabel("Student Marks Analyzer", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        /* ---------- OUTPUT AREA ---------- */
        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);
        add(scroll, BorderLayout.CENTER);

        /* ---------- BUTTON PANEL ---------- */
        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panel, BorderLayout.SOUTH);

        /* ================= BUTTONS ================= */
        JButton addBtn = new JButton("Add Student");
        JButton displayBtn = new JButton("Display Students");
        JButton undoBtn = new JButton("Undo Last");
        JButton searchIdBtn = new JButton("Search by ID");
        JButton searchNameBtn = new JButton("Search by Name");
        JButton sortAvgBtn = new JButton("Sort Avg");
        JButton sortTotalBtn = new JButton("Sort Total");
        JButton sortNameBtn = new JButton("Sort Name");
        JButton highBtn = new JButton("Highest Avg");
        JButton lowBtn = new JButton("Lowest Avg");
        JButton top3Btn = new JButton("Top 3");
        JButton queueAddBtn = new JButton("Add To Queue");
        JButton queueProcessBtn = new JButton("Process Next");
        JButton queueViewBtn = new JButton("View Queue");
        JButton binaryBtn = new JButton("Binary Search ID");


        /* ================= ADD STUDENT ================= */
        addBtn.addActionListener(e -> {

            String id = JOptionPane.showInputDialog("Enter Student ID:");
            String name = JOptionPane.showInputDialog("Enter Student Name:");

            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(
                        JOptionPane.showInputDialog("Enter mark " + (i + 1))
                );
            }

            service.addStudent(new Student(id, name, marks));
            outputArea.setText("Student Added Successfully!");

        });
        panel.add(addBtn);


        /* ================= DISPLAY ================= */
        displayBtn.addActionListener(e ->
                outputArea.setText(service.displayStudents()));
        panel.add(displayBtn);


        /* ================= UNDO ================= */
        undoBtn.addActionListener(e ->
                outputArea.setText(service.undoLastStudent()));
        panel.add(undoBtn);


        /* ================= SEARCH ================= */
        searchIdBtn.addActionListener(e -> {

            String id = JOptionPane.showInputDialog("Enter Student ID:");
            outputArea.setText(service.searchByStudentId(id));

        });
        panel.add(searchIdBtn);

        searchNameBtn.addActionListener(e -> {

            String name = JOptionPane.showInputDialog("Enter Name:");
            outputArea.setText(service.searchByName(name));

        });
        panel.add(searchNameBtn);


        /* ================= SORTING ================= */
        sortAvgBtn.addActionListener(e -> {

            outputArea.setText(service.sortByAverageBubble());

            if(service.isSorted()){
                binaryBtn.setVisible(true);
            }
        });
        panel.add(sortAvgBtn);

        sortTotalBtn.addActionListener(e -> {

            outputArea.setText(service.sortByTotalBubble());

            if(service.isSorted()){
                binaryBtn.setVisible(true);
            }
        });
        panel.add(sortTotalBtn);

        sortNameBtn.addActionListener(e -> {

            outputArea.setText(service.sortByNameSelection());

            if(service.isSorted()){
                binaryBtn.setVisible(true);
            }
        });
        panel.add(sortNameBtn);


        /* ================= PERFORMANCE ================= */
        highBtn.addActionListener(e ->
                outputArea.setText(service.findHighestAverage()));
        panel.add(highBtn);

        lowBtn.addActionListener(e ->
                outputArea.setText(service.findLowestAverage()));
        panel.add(lowBtn);

        top3Btn.addActionListener(e ->
                outputArea.setText(service.findTopThreeStudents()));
        panel.add(top3Btn);


        /* ================= QUEUE ================= */
        queueAddBtn.addActionListener(e ->
                outputArea.setText(service.addStudentsToQueue()));
        panel.add(queueAddBtn);

        queueProcessBtn.addActionListener(e ->
                outputArea.setText(service.processNextStudent()));
        panel.add(queueProcessBtn);

        queueViewBtn.addActionListener(e ->
                outputArea.setText(service.viewQueueStatus()));
        panel.add(queueViewBtn);


        /* ================= BINARY SEARCH ================= */
        binaryBtn.setVisible(false);

        binaryBtn.addActionListener(e -> {

            if (!service.isSorted()) {
                outputArea.setText("Please sort students first!");
                return;
            }

            String id = JOptionPane.showInputDialog("Enter Student ID:");
            outputArea.setText(service.binarySearchByStudentId(id));

        });
        panel.add(binaryBtn);


        setVisible(true);
    }
}