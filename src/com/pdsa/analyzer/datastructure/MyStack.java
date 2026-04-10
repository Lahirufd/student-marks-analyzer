package com.pdsa.analyzer.datastructure;

import com.pdsa.analyzer.model.Student;

public class MyStack {

    private Student[] arr;
    private int top;

    public MyStack() {
        arr = new Student[100]; // you can change size
        top = 0;
    }

    public void push(Student value) {
        if (isFull()) {
            System.out.println("Stack is Full");
        } else {
            arr[top++] = value;
        }
    }

    public Student pop() {
        if (isEmpty()) {
            return null;
        } else {
            return arr[--top];
        }
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public boolean isFull() {
        return top == arr.length;
    }
}