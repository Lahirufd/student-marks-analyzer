package com.pdsa.analyzer.datastructure;

import com.pdsa.analyzer.model.Student;

public class SinglyLinkedList {

    public Node head;

    public SinglyLinkedList() {
        head = null;
    }

    public void addNodeEnd(Student value) {

        Node new_node = new Node();
        new_node.data = value;

        if (head == null) {
            head = new_node;
        } else {
            Node i = head;
            while (i.next_node != null) {
                i = i.next_node;
            }
            i.next_node = new_node;
        }
    }

    public Student get(int index) {
        Node i = head;
        int count = 0;

        while (i != null) {
            if (count == index) {
                return i.data;
            }
            i = i.next_node;
            count++;
        }
        return null;
    }

    public void set(int index, Student value) {
        Node i = head;
        int count = 0;

        while (i != null) {
            if (count == index) {
                i.data = value;
                return;
            }
            i = i.next_node;
            count++;
        }
    }

    public int size() {
        Node i = head;
        int count = 0;

        while (i != null) {
            count++;
            i = i.next_node;
        }
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void remove(Student value) {

        if (head == null) return;

        if (head.data.equals(value)) {
            head = head.next_node;
            return;
        }

        Node i = head;

        while (i.next_node != null) {
            if (i.next_node.data.equals(value)) {
                i.next_node = i.next_node.next_node;
                return;
            }
            i = i.next_node;
        }
    }

    public Student getFirst() {
        if (head != null) {
            return head.data;
        }
        return null;
    }
}