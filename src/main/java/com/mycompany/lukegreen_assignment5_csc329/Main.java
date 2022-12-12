package com.mycompany.lukegreen_assignment5_csc329;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Reads in 2 graphs from JSON files, and show MST, and shortest path for each respectively
 * 
 * 
 * @author Luke Green
 */
public class Main {
    
    public static void main(String[] args) {
        //create gSON object
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        //MST is shown here (read in from graph basic)
        try {
            FileReader fr = new FileReader("graphbasic.json");
            
            MyGraph graph1 = gson.fromJson(fr, MyGraph.class);
            
            //show original graph read in
            System.out.println("Original Graph (SimpleGraph)");
            graph1.showGraph();
            MyGraph simpleMST = graph1.mst();
            
            //spacing
            System.out.println();
            
            //show calculated MST graph
            System.out.println("MST for (SimpleGraph)");
            simpleMST.showGraph();

        } catch (FileNotFoundException ex) {

        }
        
        //spacing
        System.out.println();
        
        //shortest path shown here (read in from shortest path graph)
        try {
            FileReader fr = new FileReader("graphshortestpath.json");
            
            MyGraph graph1 = gson.fromJson(fr, MyGraph.class);
            
            //will show the calculated shortest distances from vertex 0
            System.out.println("Shortest Path to Each Vertex from 0 (GraphShortestPath)");
            int[] answer = graph1.shortestPath(0);
            for (int i = 0; i < answer.length; i++) {
                System.out.println("Vertex: " + i + " - " + "Distance " + answer[i]);
            }

        } catch (FileNotFoundException ex) {

        }
        
        //i had to comment out the below because i kept getting a heap spcae error:
        //java.lang.OutOfMemoryError: Java heap space error
        //any idea how to prevent that?
        
        //--------------Below is my personl testing-----------------------------
        //System.out.println("--------------Below is my personl testing-----------------------------");
        
        //MyGraph graph = new MyGraph();
        
        //for (int i = 0; i < 4; i++) {
        //    graph.addVertex(i);
        //}
        //graph.addEdge(0, 1, 2);
        //graph.addEdge(1, 2, 2);
        //graph.addEdge(2, 3, 2);
        
        //graph.addEdge(1, 3, 5);
        //graph.addEdge(0, 2, 5);
        //graph.addEdge(0, 3, 7);
        
        //graph.showGraph();
        
        //MyGraph mst = graph.mst();
        //mst.showGraph();
        
        //System.out.println("-------------------------------------------------");
        
        //int[] shortPath = graph.shortestPath(0);
        //for (int i = 0; i < shortPath.length; i++) {
        //    System.out.println("Vertex: " + i + " - " + "Distance " + shortPath[i]);
        //}
    }
    
}
