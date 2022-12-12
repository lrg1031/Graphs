package com.mycompany.lukegreen_assignment5_csc329;

/**
 * This will make up the edge class for My Graph implementation
 * 
 * @author Luke Green
 */
public class Edge {
    
    int source;
    int dest;
    int weight;
    
    /**
     * constructor to take in parameters
     * 
     * @param source
     * @param destination
     * @param weight 
     */
    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.dest = destination;
        this.weight = weight;
    }
    
    /**
     * constructor that sets all member vars to 0
     */
    public Edge() {
        source = 0;
        dest = 0;
        weight = 0;
    }
    
    /**
     * 
     * @return Edge displayed in "Edge{Source: x, Destination: y, Weigth: z}]" format
     */
    @Override
    public String toString() {
        return "Edge{" + "Source: " + source + ", Destination: " + dest + ", Weigth: " + weight + '}';
    }
}

