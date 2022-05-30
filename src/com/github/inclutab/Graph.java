package com.github.inclutab;

public interface Graph {
    void addEdge(int source, int destination, double probability);
    void addToLookUpTable(int source, int destination, double probability);
}
