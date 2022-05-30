package com.github.inclutab;

public class DoublyLinkedListImpl implements DoublyLinkedList{
    private Node headNode;
    private Node tailNode;
    private int size;

    public DoublyLinkedListImpl(){
        this.headNode = null;
        this.tailNode = null;
    }

    public boolean isEmpty(){
        return (headNode == null) && (tailNode == null);
    }

    @Override
    public Node getHeadNode() {
        return headNode;
    }

    @Override
    public Node getTailNode() {
        return tailNode;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        newNode.setNextNode(this.headNode);
        newNode.setPrevNode(null);
        if (headNode != null){
            headNode.setPrevNode(newNode);
        }
        else {
            this.tailNode = newNode;
        }
        this.headNode = newNode;
        size++;
    }

    @Override
    public void insertAtTail(int data) {
        if (tailNode == null){
            insertAtHead(data);
            return;
        }
        Node newNode = new Node(data);
        newNode.setPrevNode(tailNode);
        tailNode.setNextNode(newNode);
        newNode.setNextNode(null);
        this.tailNode = newNode;
        size++;
    }
}
