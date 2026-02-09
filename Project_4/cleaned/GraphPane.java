/*
 * Author: Stefan V. Nikolov
 * Project: CMSC 315 Project 4 - Graph Visualization with Analysis Tools
 * Date: February 9, 2026
 * Description: A JavaFX Pane subclass that visually displays the graph with vertices as circles
 *              and edges as lines. Includes mouse click event handler for adding vertices.
 */

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.geometry.Bounds;

public class GraphPane extends Pane {
    private static final double VERTEX_RADIUS = 20;
    private Graph graph;
    private char nextVertexLabel;

    /**
     * Constructor for GraphPane
     */
    public GraphPane() {
        this.graph = new Graph();
        this.nextVertexLabel = 'A';
        this.setStyle("-fx-border-color: black;");
        this.setOnMouseClicked(this::handleMouseClick);
    }

    /**
     * Handles mouse click events to add vertices to the graph
     *
     * @param event the mouse event
     */
    private void handleMouseClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        Vertex newVertex = new Vertex(x, y, String.valueOf(nextVertexLabel));
        graph.addVertex(newVertex);
        drawVertex(newVertex);

        nextVertexLabel++;
    }

    /**
     * Draws a single vertex on the pane
     *
     * @param vertex the vertex to draw
     */
    private void drawVertex(Vertex vertex) {
        Circle circle = new Circle(vertex.getX(), vertex.getY(), VERTEX_RADIUS);
        circle.setFill(Color.LIGHTBLUE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);

        Text label = new Text(vertex.getX() - 8, vertex.getY() + 6, vertex.getName());
        label.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        this.getChildren().addAll(circle, label);
    }

    /**
     * Draws edges between vertices on the pane
     */
    public void drawEdges() {
        // Remove existing edges (lines) from the pane, keeping only circles and labels
        this.getChildren().removeIf(node -> node instanceof Line);

        // Redraw all edges from the graph
        for (Vertex vertex1 : graph.getVertices()) {
            for (String neighbor : graph.getNeighbors(vertex1.getName())) {
                Vertex vertex2 = graph.getVertex(neighbor);
                if (vertex2 != null && vertex1.getName().compareTo(vertex2.getName()) < 0) {
                    // Only draw each edge once (compare names to avoid duplicates)
                    Line edge = new Line(vertex1.getX(), vertex1.getY(),
                                         vertex2.getX(), vertex2.getY());
                    edge.setStroke(Color.BLACK);
                    edge.setStrokeWidth(2);
                    this.getChildren().add(0, edge); // Add to back so vertices appear on top
                }
            }
        }
    }

    /**
     * Clears all vertices and edges from the pane
     */
    public void clear() {
        this.getChildren().clear();
        this.graph = new Graph();
        this.nextVertexLabel = 'A';
    }

    /**
     * Gets the graph
     *
     * @return the Graph object
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Gets the next vertex label that will be used
     *
     * @return the next vertex label character
     */
    public char getNextVertexLabel() {
        return nextVertexLabel;
    }
}
