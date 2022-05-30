package com.github.inclutab;

public interface Queue {
    boolean isFull();
    boolean isEmpty();
    int top();
    void enqueue(int value);
    void dequeue();
}
