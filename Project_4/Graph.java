/*
 * Author: Stefan V. Nikolov
 * Project: CMSC 315 Project 4 - Graph Visualization with Analysis Tools
 * Date: February 9, 2026
 * Description: A class that represents an undirected graph using adjacency list representation
 *              with methods for adding vertices/edges and analyzing graph properties.
 */

import java.util.*;

public class Graph {
    private Map<String, Vertex> vertices;
    private Map<String, Set<String>> adjacencyList;

    /**
     * Constructor for Graph
     */
    public Graph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph
     *
     * @param vertex the vertex to add
     */
    public void addVertex(Vertex vertex) {
        if (!vertices.containsKey(vertex.getName())) {
            vertices.put(vertex.getName(), vertex);
            adjacencyList.put(vertex.getName(), new HashSet<>());
        }
    }

    /**
     * Adds an undirected edge between two vertices
     *
     * @param vertex1Name the name of the first vertex
     * @param vertex2Name the name of the second vertex
     */
    public void addEdge(String vertex1Name, String vertex2Name) {
        if (vertices.containsKey(vertex1Name) && vertices.containsKey(vertex2Name)) {
            adjacencyList.get(vertex1Name).add(vertex2Name);
            adjacencyList.get(vertex2Name).add(vertex1Name);
        }
    }

    /**
     * Checks whether the graph is connected
     *
     * @return true if the graph is connected, false otherwise
     */
    public boolean isConnected() {
        if (vertices.isEmpty()) {
            return true;
        }

        Set<String> visited = new HashSet<>();
        String startVertex = vertices.keySet().iterator().next();
        dfsHelper(startVertex, visited);

        return visited.size() == vertices.size();
    }

    /**
     * Checks whether the graph has cycles
     *
     * @return true if the graph has cycles, false otherwise
     */
    public boolean hasCycles() {
        Set<String> visited = new HashSet<>();

        for (String vertex : vertices.keySet()) {
            if (!visited.contains(vertex)) {
                if (hasCyclesHelper(vertex, visited, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helper method to detect cycles using DFS
     *
     * @param vertex        the current vertex
     * @param visited       set of visited vertices
     * @param parentVertex  the parent vertex (for undirected graph cycle detection)
     * @return true if a cycle is detected, false otherwise
     */
    private boolean hasCyclesHelper(String vertex, Set<String> visited, String parentVertex) {
        visited.add(vertex);

        for (String neighbor : adjacencyList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                if (hasCyclesHelper(neighbor, visited, vertex)) {
                    return true;
                }
            } else if (!neighbor.equals(parentVertex)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Performs a depth-first search starting from vertex A
     *
     * @return a list of vertices in DFS order
     */
    public List<String> depthFirstSearch() {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        if (vertices.containsKey("A")) {
            dfsHelper("A", visited);
            for (String vertex : vertices.keySet()) {
                if (visited.contains(vertex)) {
                    result.add(vertex);
                }
            }
        }
        return result;
    }

    /**
     * Helper method for depth-first search
     *
     * @param vertex  the current vertex
     * @param visited set of visited vertices
     */
    private void dfsHelper(String vertex, Set<String> visited) {
        visited.add(vertex);
        for (String neighbor : adjacencyList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    /**
     * Performs a breadth-first search starting from vertex A
     *
     * @return a list of vertices in BFS order
     */
    public List<String> breadthFirstSearch() {
        List<String> result = new ArrayList<>();

        if (!vertices.containsKey("A")) {
            return result;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add("A");
        visited.add("A");

        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            result.add(vertex);

            for (String neighbor : adjacencyList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }

    /**
     * Gets a vertex by its name
     *
     * @param name the name of the vertex
     * @return the Vertex object, or null if not found
     */
    public Vertex getVertex(String name) {
        return vertices.get(name);
    }

    /**
     * Gets all vertices in the graph
     *
     * @return a collection of all vertices
     */
    public Collection<Vertex> getVertices() {
        return vertices.values();
    }

    /**
     * Gets the adjacency list for a vertex
     *
     * @param vertexName the name of the vertex
     * @return a set of neighbor vertex names
     */
    public Set<String> getNeighbors(String vertexName) {
        return adjacencyList.getOrDefault(vertexName, new HashSet<>());
    }

    /**
     * Checks if a vertex exists in the graph
     *
     * @param vertexName the name of the vertex
     * @return true if the vertex exists, false otherwise
     */
    public boolean hasVertex(String vertexName) {
        return vertices.containsKey(vertexName);
    }

    /**
     * Checks if an edge exists between two vertices
     *
     * @param vertex1Name the name of the first vertex
     * @param vertex2Name the name of the second vertex
     * @return true if an edge exists, false otherwise
     */
    public boolean hasEdge(String vertex1Name, String vertex2Name) {
        return adjacencyList.containsKey(vertex1Name) &&
               adjacencyList.get(vertex1Name).contains(vertex2Name);
    }
}
