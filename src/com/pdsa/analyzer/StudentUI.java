package com.pdsa.analyzer;

import javax.swing.*;
import java.awt.*;

public class StudentUI extends JFrame {

    private StudentService service = new StudentService();
    private JTextArea outputArea = new JTextArea();

    public StudentUI() {

        // --- Apply Modern Look and Feel ---
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // ignore
        }

        setTitle("Student Marks Analyzer Tool");
        setSize(950, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Dark Theme Colors ---
        Color bgColor = new Color(30, 30, 46);
        Color panelColor = new Color(36, 39, 58);
        Color textColor = new Color(202, 211, 245);
        Color titleColor = new Color(138, 173, 244);
        Color btnColor = new Color(54, 58, 79);
        Color btnText = new Color(202, 211, 245);
        Color primaryBtnColor = new Color(166, 218, 149);
        Color primaryBtnText = new Color(24, 24, 37);

        getContentPane().setBackground(bgColor);
        setLayout(new BorderLayout(15, 15));

        /* ---------- TITLE ---------- */
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(bgColor);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

        JLabel title = new JLabel("Student Marks Analyzer", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(titleColor);
        titlePanel.add(title);

        add(titlePanel, BorderLayout.NORTH);

        /* ---------- OUTPUT AREA ---------- */
        outputArea.setEditable(false);
        outputArea.setBackground(panelColor);
        outputArea.setForeground(textColor);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        outputArea.setMargin(new Insets(15, 15, 15, 15));
        outputArea.setCaretColor(textColor);

        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 20),
                BorderFactory.createLineBorder(new Color(69, 71, 90), 1)));
        scroll.getViewport().setBackground(panelColor);
        add(scroll, BorderLayout.CENTER);

        /* ---------- CATEGORIZED BUTTON PANELS ---------- */
        JPanel mainButtonPanel = new JPanel(new GridLayout(2, 1, 0, 15));
        mainButtonPanel.setBackground(bgColor);
        mainButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(mainButtonPanel, BorderLayout.SOUTH);

        JPanel mgmtPanel = createCategoryPanel("Management", panelColor, titleColor);
        JPanel sortPanel = createCategoryPanel("Sorting", panelColor, titleColor);
        JPanel perfPanel = createCategoryPanel("Performance", panelColor, titleColor);
        JPanel queuePanel = createCategoryPanel("Queue", panelColor, titleColor);
        JPanel searchPanel = createCategoryPanel("Search", panelColor, titleColor);

        JPanel row1 = new JPanel(new GridLayout(1, 3, 15, 0));
        row1.setBackground(bgColor);
        row1.add(mgmtPanel);
        row1.add(sortPanel);
        row1.add(perfPanel);

        // Uses FlowLayout to dynamically center identically-sized panels
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        row2.setBackground(bgColor);
        row2.add(queuePanel);
        row2.add(searchPanel);

        // Link the sizing of row2 panels to perfectly match row1 panels dynamically
        row1.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                Dimension size = mgmtPanel.getSize();
                if (size.width > 0 && !size.equals(queuePanel.getPreferredSize())) {
                    queuePanel.setPreferredSize(size);
                    searchPanel.setPreferredSize(size);
                    row2.revalidate();
                }
            }
        });

        mainButtonPanel.add(row1);
        mainButtonPanel.add(row2);

        /* ================= BUTTONS ================= */
        JButton addBtn = createStyledButton("Add Student", primaryBtnColor, primaryBtnText, true);
        JButton displayBtn = createStyledButton("Display Students", primaryBtnColor, primaryBtnText, true);
        JButton undoBtn = createStyledButton("Undo Last", primaryBtnColor, primaryBtnText, true);
        JButton searchIdBtn = createStyledButton("Search by ID", primaryBtnColor, primaryBtnText, true);
        JButton searchNameBtn = createStyledButton("Search by Name", primaryBtnColor, primaryBtnText, true);
        JButton sortAvgBtn = createStyledButton("Sort Avg", primaryBtnColor, primaryBtnText, true);
        JButton sortTotalBtn = createStyledButton("Sort Total", primaryBtnColor, primaryBtnText, true);
        JButton sortNameBtn = createStyledButton("Sort Name", primaryBtnColor, primaryBtnText, true);
        JButton highBtn = createStyledButton("Highest Avg", primaryBtnColor, primaryBtnText, true);
        JButton lowBtn = createStyledButton("Lowest Avg", primaryBtnColor, primaryBtnText, true);
        JButton top3Btn = createStyledButton("Top 3", primaryBtnColor, primaryBtnText, true);
        JButton queueAddBtn = createStyledButton("Add To Queue", primaryBtnColor, primaryBtnText, true);
        JButton queueProcessBtn = createStyledButton("Process Next", primaryBtnColor, primaryBtnText, true);
        JButton queueViewBtn = createStyledButton("View Queue", primaryBtnColor, primaryBtnText, true);
        JButton binaryBtn = createStyledButton("Binary Search ID", new Color(138, 173, 244), new Color(24, 24, 37),
                true);

        /* ================= ADD STUDENT ================= */
        addBtn.addActionListener(e -> {

            String id = JOptionPane.showInputDialog(this, "Enter Student ID:");
            if (id == null)
                return;

            String name = JOptionPane.showInputDialog(this, "Enter Student Name:");
            if (name == null)
                return;

            int[] marks = new int[3];

            try {
                for (int i = 0; i < 3; i++) {
                    String markStr = JOptionPane.showInputDialog(this, "Enter mark " + (i + 1));
                    if (markStr == null)
                        return;
                    marks[i] = Integer.parseInt(markStr);
                }
                service.addStudent(new Student(id, name, marks));
                outputArea.setText("Student Added Successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid mark. Please enter numbers only.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
        mgmtPanel.add(addBtn);

        /* ================= DISPLAY ================= */
        displayBtn.addActionListener(e -> outputArea.setText(service.displayStudents()));
        mgmtPanel.add(displayBtn);

        /* ================= UNDO ================= */
        undoBtn.addActionListener(e -> outputArea.setText(service.undoLastStudent()));
        mgmtPanel.add(undoBtn);

        /* ================= SEARCH ================= */
        searchIdBtn.addActionListener(e -> {

            String id = JOptionPane.showInputDialog(this, "Enter Student ID:");
            if (id != null) {
                outputArea.setText(service.searchByStudentId(id));
            }

        });
        searchPanel.add(searchIdBtn);

        searchNameBtn.addActionListener(e -> {

            String name = JOptionPane.showInputDialog(this, "Enter Name:");
            if (name != null) {
                outputArea.setText(service.searchByName(name));
            }

        });
        searchPanel.add(searchNameBtn);

        /* ================= SORTING ================= */
        sortAvgBtn.addActionListener(e -> {

            outputArea.setText(service.sortByAverageBubble());

            if (service.isSorted()) {
                binaryBtn.setVisible(true);
            }
        });
        sortPanel.add(sortAvgBtn);

        sortTotalBtn.addActionListener(e -> {

            outputArea.setText(service.sortByTotalBubble());

            if (service.isSorted()) {
                binaryBtn.setVisible(true);
            }
        });
        sortPanel.add(sortTotalBtn);

        sortNameBtn.addActionListener(e -> {

            outputArea.setText(service.sortByNameSelection());

            if (service.isSorted()) {
                binaryBtn.setVisible(true);
            }
        });
        sortPanel.add(sortNameBtn);

        /* ================= PERFORMANCE ================= */
        highBtn.addActionListener(e -> outputArea.setText(service.findHighestAverage()));
        perfPanel.add(highBtn);

        lowBtn.addActionListener(e -> outputArea.setText(service.findLowestAverage()));
        perfPanel.add(lowBtn);

        top3Btn.addActionListener(e -> outputArea.setText(service.findTopThreeStudents()));
        perfPanel.add(top3Btn);

        /* ================= QUEUE ================= */
        queueAddBtn.addActionListener(e -> outputArea.setText(service.addStudentsToQueue()));
        queuePanel.add(queueAddBtn);

        queueProcessBtn.addActionListener(e -> outputArea.setText(service.processNextStudent()));
        queuePanel.add(queueProcessBtn);

        queueViewBtn.addActionListener(e -> outputArea.setText(service.viewQueueStatus()));
        queuePanel.add(queueViewBtn);

        /* ================= BINARY SEARCH ================= */
        binaryBtn.setVisible(false);

        binaryBtn.addActionListener(e -> {

            if (!service.isSorted()) {
                outputArea.setText("Please sort students first!");
                return;
            }

            String id = JOptionPane.showInputDialog(this, "Enter Student ID:");
            if (id != null) {
                outputArea.setText(service.binarySearchByStudentId(id));
            }

        });
        searchPanel.add(binaryBtn);

        setVisible(true);
    }

    private JButton createStyledButton(String text, Color bg, Color fg, boolean isBold) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", isBold ? Font.BOLD : Font.PLAIN, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bg.darker(), 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(
                        Math.min(255, bg.getRed() + 20),
                        Math.min(255, bg.getGreen() + 20),
                        Math.min(255, bg.getBlue() + 20)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg);
            }
        });

        return btn;
    }

    private JPanel createCategoryPanel(String title, Color bgColor, Color titleColor) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBackground(bgColor);
        javax.swing.border.TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(69, 71, 90), 1),
                title);
        border.setTitleColor(titleColor);
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.setBorder(BorderFactory.createCompoundBorder(
                border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        return panel;
    }
}