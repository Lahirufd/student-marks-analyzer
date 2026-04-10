package com.pdsa.analyzer.datastructure;

import com.pdsa.analyzer.model.Student;

public class MyQueue {

    private Student[] arr;
    private int front;
    private int rear;

    public MyQueue() {
        arr = new Student[100];
        front = 0;
        rear = 0;
    }

    public void add(Student value) {
        if (isFull()) {
            System.out.println("Queue is Full");
        } else {
            arr[rear++] = value;
        }
    }

    public Student poll() {
        if (isEmpty()) {
            return null;
        } else {
            return arr[front++];
        }
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return rear == arr.length;
    }

    public void clear() {
        front = 0;
        rear = 0;
    }

    public int size() {
        return rear - front;
    }

    public Student get(int index) {
        return arr[front + index];
    }
}