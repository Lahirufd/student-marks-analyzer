package com.pdsa.analyzer.datastructure;

import com.pdsa.analyzer.model.Student;

public class Node {
    public Student data;
    public Node next_node;

    public Node() {
        next_node = null;
    }
}