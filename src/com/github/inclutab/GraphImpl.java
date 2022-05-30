package com.github.inclutab;

public class GraphImpl implements Graph{
    private int vertices;
    private double[][] lookUpTable;
    private DoublyLinkedListImpl[] adjacencyList;
    boolean[] isVisited;
    QueueImpl queue;


    public GraphImpl(int vertices){
        this.vertices = vertices;
        this.lookUpTable = new double[vertices][vertices];
        this.adjacencyList = new DoublyLinkedListImpl[vertices];
        this.isVisited = new boolean[vertices];
        this.queue = new QueueImpl(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new DoublyLinkedListImpl();
        }
    }

    @Override
    public void addToLookUpTable(int source, int destination, double probability) {
        lookUpTable[source][destination] = probability;
        lookUpTable[destination][source] = probability;
    }

    public double[][] getLookUpTable() {
        return lookUpTable;
    }

    @Override
    public void addEdge(int source, int destination, double probability) {
        source = source - 1;
        destination = destination - 1;
        if (source < vertices && destination < vertices){
            adjacencyList[source].insertAtTail(destination);
            adjacencyList[destination].insertAtTail(source);
        }
        addToLookUpTable(source, destination, probability);
    }

    public DoublyLinkedListImpl[] getAdjacencyList() {
        return adjacencyList;
    }

    public double traverseGraphAndReturnProbability(int source, int destination) throws InvalidIndexException {
        if(source > vertices || destination > vertices || source < 0 || destination < 0){
            throw new InvalidIndexException("Node index supplied is invalid");
        }
        if (source == destination){
            return 1;
        }
        if (lookUpTable[source][destination] != 0) {
            return lookUpTable[source][destination];
        }
        else{
            isVisited[source] = true;
            Node temp = adjacencyList[source].getHeadNode();
            if (!isVisited[temp.getData()]){
                queue.enqueue(temp.getData());
            }
            while (temp.getNextNode() != null){
                temp = temp.getNextNode();
                int intermediateNode = temp.getData();
                if (!isVisited[intermediateNode]){
                    queue.enqueue(intermediateNode);
                }
            }
            int node = queue.top();
            queue.dequeue();

            if (source == 0){
                boolean isAdjacentToDst = false;
                while (!isAdjacentToDst) {
                    Node tempNode = adjacencyList[node].getHeadNode();
                    for (int i = 0; i < adjacencyList[node].getSize(); i++) {
                        if (tempNode.getData() == destination) {
                            isAdjacentToDst = true;
                        }
                        tempNode = tempNode.getNextNode();
                    }
                    if (!isAdjacentToDst){
                        node = queue.top();
                        queue.dequeue();
                    }
                }
            }
            return lookUpTable[source][node] * traverseGraphAndReturnProbability(node, destination);
        }
    }
}
