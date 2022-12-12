package com.mycompany.lukegreen_assignment5_csc329;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This will make up the Graph class containing mst and shortest path
 * 
 * @author Luke Green
 */
public class MyGraph {
    
    ArrayList<Integer> vertices;
    
    HashMap<Integer, ArrayList<Edge>> edges;
    
    /**
     * default constructor sets members to null
     */
    public MyGraph() {
        vertices = new ArrayList<>();
        edges = new HashMap<>();
    }
    
    /**
     * add parameter int as a vertex
     * 
     * @param v 
     */
    void addVertex(int v) {
        vertices.add(v);
        edges.put(v, new ArrayList<>());
    }
    
    /**
     * add parameter as edge in graph
     * 
     * @param source
     * @param destination
     * @param weight 
     */
    void addEdge(int source, int destination, int weight) {
        Edge e = new Edge(source, destination, weight);
        
        ArrayList<Edge> sourceList = edges.get(source);
        sourceList.add(e);
        
        ArrayList<Edge> destinationList = edges.get(destination);
        destinationList.add(e);
    }
    
    /**
     * display the current vertices and edges of the graph
     */
    void showGraph(){
       showVertices();
       ShowEdges();
   }
   
    /**
     * show all vertices in the graph
     */
   private void showVertices(){
       System.out.printf("Verticies: ");
       for (int i : vertices){
           System.out.printf("%d  ", i);
       }
       System.out.println();
   }
   
   /**
    * show all edges in the graph
    */
   private void ShowEdges(){
       for(HashMap.Entry<Integer, ArrayList<Edge>> entry : edges.entrySet()) {
           System.out.println("Vertex: " + entry.getKey() + ", " + entry.getValue());
       }
   }   
    
   /**
    * will calculate a minimum spanning tree based off the current instance
    * 
    * @return mst version of current instance
    */
    MyGraph mst() {
        //Create a visited array of Boolean and set all elements to false.
        Boolean[] visited = new Boolean[vertices.size()];
        //Add all vertices to the MST
        MyGraph mst = new MyGraph();
        for (int i = 0; i < vertices.size(); i++) {
            visited[i] = false;
            mst.addVertex(vertices.get(i));
        }
        //Pick any vertex to be the starting vertex of the MST
        int startVertex = vertices.get(0);
        //Set visited[startVertex] to true
        visited[startVertex] = true;
        //While(all vertices not visited)
        for (int i = 0; i < visited.length; i++) {
            while(!visited[i]) {
                Edge minEdge = getMinDistNeightborEdge(visited);
                visited[minEdge.source] = true;
                visited[minEdge.dest] = true;
                mst.addEdge(minEdge.source, minEdge.dest, minEdge.weight);
            }
        }   
        return mst;
    }
    
    /**
     * helper for mst
     * will return the smallest edge from the visited area to non visited area greedily
     * 
     * @param visited boolean array to tall method which vertices have been visited
     * @return minimum weight edge chosen greedily
     */
    Edge getMinDistNeightborEdge(Boolean[] visited) {
        Edge minEdge = new Edge(0, 0, Integer.MAX_VALUE);
        //For all vertices in graph (call current vertex v)
        for(int v = 0; v < vertices.size(); v++) {
        if (visited[v]) {
            ArrayList<Edge> adjList = edges.get(v);
            //For all edges e in adjList
            for(Edge e: adjList) {
            if (((visited[e.source] && !visited[e.dest]) || (!visited[e.source] && visited[e.dest]) )&& (e.weight < minEdge.weight))
                minEdge = e;
            }
        }
        }
        return minEdge;
    }
    
    /**
     * will calculate the shortest path to every vertex in the graph from a specified vertex
     * distance from a vertex to itself is 0
     * 
     * @param startVertex connect this vertex to every other vertex
     * @return int array that shows the distance from parameter to every other vertex
     */
    int[] shortestPath(int startVertex) {
        int[] dist = new int[vertices.size()];
        int[] previous = new int[vertices.size()];
        Boolean[] visited = new Boolean[vertices.size()];
        ArrayList<Integer> unvisitedList = new ArrayList<Integer>();
        for (int i = 0; i < vertices.size(); i++) {
            unvisitedList.add(vertices.get(i));
            visited[i] = false;
            previous[i] = -1;
            dist[i] = Integer.MAX_VALUE;
        }
        //Pick a starting vertex and set its distance to 0
        dist[0] = 0;
        
        //while unvisitedList not empty
            while(!unvisitedList.isEmpty()) {
                int currV = GetMinDistVertex(unvisitedList, dist);
                //Remove currV from unvisitedList and set visited[currV] to true
                unvisitedList.remove(Integer.valueOf(currV));
                visited[currV] = true;
                //For all unvisited neighbors of currV (call them n)
                ArrayList<Edge> adjList = edges.get(currV);
                ArrayList<Integer> unvisitedNeightbors = new ArrayList<Integer>();
                for(int i = 0; i < adjList.size(); i++) {
                    if(visited[adjList.get(i).dest] == false && !unvisitedNeightbors.contains(adjList.get(i).dest)) {
                        unvisitedNeightbors.add(adjList.get(i).dest);
                    }
                }
                for(int n: unvisitedNeightbors) {
                    
                //INT possibleDist = (distance[currV] + weight of edge to n)
                int possibleDist = (dist[currV] + getWeightBetween(currV, n));
                    if(possibleDist < dist[n]) {
                        dist[n] = possibleDist;
                        previous[n] = currV;
                    }
            }
            }
            return dist;
        }
    
    /**
     * helper to shortest path
     * will return the weight between source and destination vertices
     * (assumes only 1 edge per vertex pair)
     * will return -1 if source/destination vertices not found
     * 
     * @param source vertex coming from
     * @param dest vertex going to
     * @return weight of edge between source and dest vertices
     */
    int getWeightBetween(int source, int dest) {
        ArrayList<Edge> adjList = edges.get(source);
        for(Edge e: adjList) {
            if(e.source == source && e.dest == dest) {
                return e.weight;
            }
        }
        return -1;
    }
        
    /**
     * helper to shortest path
     * will return the min distance unvisited vertex from unvisitedList
     * 
     * @param unvisitedList ArrayList to show method which vertices are unvisited
     * @param dist 
     * @return the min distance unvisited vertex from unvisitedList
     */
    int GetMinDistVertex(ArrayList<Integer> unvisitedList, int[] dist) {
       //minNeighborVertex = unvisitedList first element
       int minNeighborVertex = unvisitedList.get(0);
        int minNeighborDist = dist[0];
        //For all vertex v in unvisitedList
        for(int v: unvisitedList) {
            if (dist[v] < minNeighborDist) {
            minNeighborDist = dist[v];
            minNeighborVertex = v;
            }
        }
        return minNeighborVertex;
        }
    
}
