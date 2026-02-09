/*
 * Author: Stefan V. Nikolov
 * Project: CMSC 315 Project 4 - Graph Visualization with Analysis Tools
 * Date: February 9, 2026
 * Description: An immutable class that represents a vertex in a graph with coordinates and a name label.
 */

public class Vertex {
    private final double x;
    private final double y;
    private final String name;

    /**
     * Constructor for Vertex
     *
     * @param x    the x-coordinate of the vertex
     * @param y    the y-coordinate of the vertex
     * @param name the name/label of the vertex
     */
    public Vertex(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    /**
     * Gets the name of the vertex
     *
     * @return the vertex name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the x-coordinate of the vertex
     *
     * @return the x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the vertex
     *
     * @return the y-coordinate
     */
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex vertex = (Vertex) obj;
        return name.equals(vertex.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
