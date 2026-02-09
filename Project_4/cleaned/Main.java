/*
 * Author: Stefan V. Nikolov
 * Project: CMSC 315 Project 4 - Graph Visualization with Analysis Tools
 * Date: February 9, 2026
 * Description: The main application class that creates the JavaFX GUI with graph visualization,
 *              vertex/edge input fields, and analysis buttons for graph operations.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class Main extends Application {
    private GraphPane graphPane;
    private TextField vertex1Field;
    private TextField vertex2Field;
    private TextField messageField;

    /**
     * Start method for JavaFX application
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        // Create the graph pane
        graphPane = new GraphPane();
        graphPane.setPrefSize(600, 500);

        // Create input fields
        vertex1Field = new TextField();
        vertex1Field.setPromptText("Vertex 1");
        vertex1Field.setPrefWidth(80);

        vertex2Field = new TextField();
        vertex2Field.setPromptText("Vertex 2");
        vertex2Field.setPrefWidth(80);

        messageField = new TextField();
        messageField.setEditable(false);
        messageField.setPrefWidth(500);

        // Create buttons
        Button addEdgeButton = new Button("Add Edge");
        Button isConnectedButton = new Button("Is Connected");
        Button hasCyclesButton = new Button("Has Cycles");
        Button dfsButton = new Button("Depth First Search");
        Button bfsButton = new Button("Breadth First Search");

        // Set button widths
        addEdgeButton.setPrefWidth(100);
        isConnectedButton.setPrefWidth(120);
        hasCyclesButton.setPrefWidth(120);
        dfsButton.setPrefWidth(150);
        bfsButton.setPrefWidth(150);

        // Add event handlers for buttons
        addEdgeButton.setOnAction(e -> handleAddEdge());
        isConnectedButton.setOnAction(e -> handleIsConnected());
        hasCyclesButton.setOnAction(e -> handleHasCycles());
        dfsButton.setOnAction(e -> handleDFS());
        bfsButton.setOnAction(e -> handleBFS());

        // Create layout panels
        HBox inputPanel = new HBox(10);
        inputPanel.setPadding(new Insets(10));
        inputPanel.getChildren().addAll(
                new Label("Vertex 1:"), vertex1Field,
                new Label("Vertex 2:"), vertex2Field,
                addEdgeButton
        );

        HBox buttonPanel = new HBox(10);
        buttonPanel.setPadding(new Insets(10));
        buttonPanel.getChildren().addAll(
                isConnectedButton,
                hasCyclesButton,
                dfsButton,
                bfsButton
        );

        HBox messagePanel = new HBox(10);
        messagePanel.setPadding(new Insets(10));
        messagePanel.getChildren().addAll(new Label("Message:"), messageField);

        VBox controlPanel = new VBox(10);
        controlPanel.getChildren().addAll(inputPanel, buttonPanel, messagePanel);

        // Create main layout
        BorderPane root = new BorderPane();
        root.setCenter(graphPane);
        root.setBottom(controlPanel);

        // Create scene and stage
        Scene scene = new Scene(root, 800, 700);
        primaryStage.setTitle("CMSC 315 Project 4 - Graph Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Handles the Add Edge button click
     */
    private void handleAddEdge() {
        String vertex1 = vertex1Field.getText().trim();
        String vertex2 = vertex2Field.getText().trim();

        if (vertex1.isEmpty() || vertex2.isEmpty()) {
            messageField.setText("Error: Please enter both vertex names.");
            return;
        }

        Graph graph = graphPane.getGraph();

        if (!graph.hasVertex(vertex1)) {
            messageField.setText("Error: Vertex " + vertex1 + " does not exist.");
            return;
        }

        if (!graph.hasVertex(vertex2)) {
            messageField.setText("Error: Vertex " + vertex2 + " does not exist.");
            return;
        }

        if (vertex1.equals(vertex2)) {
            messageField.setText("Error: Cannot create edge from vertex to itself.");
            return;
        }

        if (graph.hasEdge(vertex1, vertex2)) {
            messageField.setText("Error: Edge between " + vertex1 + " and " + vertex2 + " already exists.");
            return;
        }

        graph.addEdge(vertex1, vertex2);
        graphPane.drawEdges();
        messageField.setText("Edge added between " + vertex1 + " and " + vertex2 + ".");
        vertex1Field.clear();
        vertex2Field.clear();
    }

    /**
     * Handles the Is Connected button click
     */
    private void handleIsConnected() {
        Graph graph = graphPane.getGraph();
        if (graph.getVertices().isEmpty()) {
            messageField.setText("Error: Graph is empty.");
            return;
        }

        boolean connected = graph.isConnected();
        messageField.setText(connected ? "The graph is connected." : "The graph is not connected.");
    }

    /**
     * Handles the Has Cycles button click
     */
    private void handleHasCycles() {
        Graph graph = graphPane.getGraph();
        if (graph.getVertices().isEmpty()) {
            messageField.setText("Error: Graph is empty.");
            return;
        }

        boolean hasCycles = graph.hasCycles();
        messageField.setText(hasCycles ? "The graph has cycles." : "The graph does not have cycles.");
    }

    /**
     * Handles the Depth First Search button click
     */
    private void handleDFS() {
        Graph graph = graphPane.getGraph();
        if (graph.getVertices().isEmpty()) {
            messageField.setText("Error: Graph is empty.");
            return;
        }

        if (!graph.hasVertex("A")) {
            messageField.setText("Error: Vertex A does not exist.");
            return;
        }

        List<String> dfsResult = graph.depthFirstSearch();
        messageField.setText("DFS from A: " + dfsResult.toString());
    }

    /**
     * Handles the Breadth First Search button click
     */
    private void handleBFS() {
        Graph graph = graphPane.getGraph();
        if (graph.getVertices().isEmpty()) {
            messageField.setText("Error: Graph is empty.");
            return;
        }

        if (!graph.hasVertex("A")) {
            messageField.setText("Error: Vertex A does not exist.");
            return;
        }

        List<String> bfsResult = graph.breadthFirstSearch();
        messageField.setText("BFS from A: " + bfsResult.toString());
    }

    /**
     * Main method to launch the application
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
