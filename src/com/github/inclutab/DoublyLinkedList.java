package com.github.inclutab;

public interface DoublyLinkedList {
    boolean isEmpty();

    Node getHeadNode();

    Node getTailNode();

    int getSize();

    void insertAtHead(int data);

    void insertAtTail(int data);
}
