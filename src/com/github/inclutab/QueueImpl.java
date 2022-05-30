package com.github.inclutab;

public class QueueImpl implements Queue{
    private int maxSize;
    private int[] array;
    private int front;
    private int back;
    private int currentSize;

    public QueueImpl(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = 0;
        back = -1;
        currentSize = 0;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public int top() {
        return array[front];
    }
    public void enqueue(int value) {
        if (isFull())
            return;
        back = (back + 1) % maxSize;
        array[back] = value;
        currentSize++;
    }

    public void dequeue() {
        if (isEmpty())
            return;

        front = (front + 1) % maxSize;
        currentSize--;

    }
}
