# Project 4: Graph Visualization with Analysis Tools

## Overview

This project implements a JavaFX GUI application that allows users to create and visualize an undirected graph with analysis capabilities. Users can interactively add vertices by clicking on the canvas and create edges between vertices. The application provides four analysis functions to examine graph properties and traverse the graph.

## Features

- **Interactive Vertex Addition**: Click anywhere on the graph pane to create new vertices (labeled A, B, C, etc.)
- **Edge Creation**: Add edges between vertices using the provided text fields
- **Graph Analysis**:
  - **Is Connected**: Determines if all vertices are connected (every vertex is reachable from every other vertex)
  - **Has Cycles**: Detects whether the graph contains any cycles
  - **Depth First Search**: Performs DFS traversal starting from vertex A
  - **Breadth First Search**: Performs BFS traversal starting from vertex A

## Classes

### 1. Vertex
An immutable class representing a single vertex in the graph.
- **Properties**: x-coordinate, y-coordinate, name (label)
- **Methods**: getName(), getX(), getY()

### 2. Graph
The core graph data structure using adjacency list representation.
- **Methods**:
  - `addVertex(Vertex)`: Adds a vertex to the graph
  - `addEdge(String, String)`: Creates an undirected edge between two vertices
  - `isConnected()`: Checks if graph is connected using DFS
  - `hasCycles()`: Detects cycles in the undirected graph
  - `depthFirstSearch()`: Returns vertices in DFS order from vertex A
  - `breadthFirstSearch()`: Returns vertices in BFS order from vertex A

### 3. GraphPane
Extends JavaFX Pane to provide visual graph representation.
- **Features**:
  - Mouse click event handler for adding vertices
  - Visual rendering of vertices (circles) and edges (lines)
  - Methods to draw/update the graph display

### 4. Main
The main application class containing the JavaFX GUI.
- **Components**: Graph canvas, input fields, analysis buttons, status message field
- **Event Handlers**: Add edge, Is Connected, Has Cycles, DFS, BFS

## Usage

1. **Run the Application**: Execute `Main.java` to launch the GUI
2. **Add Vertices**: Click on the gray canvas area to create vertices (auto-labeled A, B, C, etc.)
3. **Add Edges**: Enter two vertex names in the text fields and click "Add Edge"
4. **Analyze Graph**: Click the appropriate button to check connectivity, cycles, or perform searches

## Technical Details

- **Graph Representation**: Adjacency list (Map of Sets)
- **Cycle Detection**: DFS with parent tracking for undirected graphs
- **Connectivity**: DFS-based connectivity check
- **Traversals**: Standard recursive DFS and queue-based BFS implementations

## Requirements

- Java 8 or higher
- JavaFX library

## Compilation and Execution

```bash
javac Vertex.java Graph.java GraphPane.java Main.java
java Main
```

---
**Author**: Stefan V. Nikolov  
**Date**: February 9, 2026  
**Course**: CMSC 315 - Data Structures and Algorithms
