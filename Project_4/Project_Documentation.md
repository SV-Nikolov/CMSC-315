# Project 4: Graph Visualization with Analysis Tools - Technical Documentation

## UML Class Diagram

```
┌─────────────────────────────┐
│         Vertex              │
├─────────────────────────────┤
│ - x: double                 │
│ - y: double                 │
│ - name: String (final)      │
├─────────────────────────────┤
│ + Vertex(double, double, String)    │
│ + getName(): String         │
│ + getX(): double            │
│ + getY(): double            │
│ + toString(): String        │
│ + equals(Object): boolean   │
│ + hashCode(): int           │
└─────────────────────────────┘

┌──────────────────────────────────────┐
│           Graph                      │
├──────────────────────────────────────┤
│ - vertices: Map<String, Vertex>      │
│ - adjacencyList: Map<String,         │
│     Set<String>>                     │
├──────────────────────────────────────┤
│ + Graph()                            │
│ + addVertex(Vertex): void            │
│ + addEdge(String, String): void      │
│ + isConnected(): boolean             │
│ + hasCycles(): boolean               │
│ + depthFirstSearch(): List<String>   │
│ + breadthFirstSearch(): List<String> │
│ + getVertex(String): Vertex          │
│ + getVertices(): Collection<Vertex>  │
│ + getNeighbors(String): Set<String>  │
│ + hasVertex(String): boolean         │
│ + hasEdge(String, String): boolean   │
│ - hasCyclesHelper(...): boolean      │
│ - dfsHelper(...): void               │
└──────────────────────────────────────┘

┌────────────────────────────────────┐
│        GraphPane                   │
│    extends Pane (JavaFX)           │
├────────────────────────────────────┤
│ - graph: Graph                     │
│ - nextVertexLabel: char            │
│ - VERTEX_RADIUS: double (static)   │
├────────────────────────────────────┤
│ + GraphPane()                      │
│ + drawEdges(): void                │
│ + clear(): void                    │
│ + getGraph(): Graph                │
│ + getNextVertexLabel(): char       │
│ - handleMouseClick(...): void      │
│ - drawVertex(Vertex): void         │
└────────────────────────────────────┘

┌──────────────────────────────────────┐
│          Main                        │
│   extends Application (JavaFX)       │
├──────────────────────────────────────┤
│ - graphPane: GraphPane               │
│ - vertex1Field: TextField            │
│ - vertex2Field: TextField            │
│ - messageField: TextField            │
├──────────────────────────────────────┤
│ + start(Stage): void                 │
│ + main(String[]): void               │
│ - handleAddEdge(): void              │
│ - handleIsConnected(): void          │
│ - handleHasCycles(): void            │
│ - handleDFS(): void                  │
│ - handleBFS(): void                  │
└──────────────────────────────────────┘
```

## Test Plan

### Test Case 1: Basic Vertex Creation
**Purpose**: Verify that vertices are created correctly with proper labeling
- **Steps**:
  1. Launch application
  2. Click on three different locations on the canvas
- **Expected Result**: Three circles appear labeled A, B, and C
- **Actual Result**: ✓ Vertices created with correct labels

### Test Case 2: Valid Edge Addition
**Purpose**: Verify that edges can be added between existing vertices
- **Steps**:
  1. Create vertices A and B (click twice)
  2. Enter "A" in Vertex 1 field, "B" in Vertex 2 field
  3. Click "Add Edge" button
- **Expected Result**: Line drawn between A and B; success message displayed
- **Actual Result**: ✓ Edge created and displayed correctly

### Test Case 3: Non-existent Vertex Error Handling
**Purpose**: Verify error handling when adding edge with non-existent vertex
- **Steps**:
  1. Create only vertex A
  2. Enter "A" in Vertex 1, "B" in Vertex 2
  3. Click "Add Edge"
- **Expected Result**: Error message "Vertex B does not exist"
- **Actual Result**: ✓ Proper error message displayed

### Test Case 4: Duplicate Edge Prevention
**Purpose**: Verify that duplicate edges are not created
- **Steps**:
  1. Create vertices A and B
  2. Add edge A-B
  3. Try to add edge A-B again
- **Expected Result**: Error message preventing duplicate edge
- **Actual Result**: ✓ Error message displayed

### Test Case 5: Self-Loop Prevention
**Purpose**: Verify that self-loops cannot be created
- **Steps**:
  1. Create vertex A
  2. Try to add edge from A to A
- **Expected Result**: Error message preventing self-loop
- **Actual Result**: ✓ Error message displayed

### Test Case 6: Connected Graph Detection
**Purpose**: Verify correct detection of connected graphs
- **Steps**:
  1. Create vertices A, B, C
  2. Add edges: A-B, B-C
  3. Click "Is Connected"
- **Expected Result**: "The graph is connected"
- **Actual Result**: ✓ Correct result

### Test Case 7: Disconnected Graph Detection
**Purpose**: Verify correct detection of disconnected graphs
- **Steps**:
  1. Create vertices A, B, C, D
  2. Add edges: A-B, C-D (no connection between components)
  3. Click "Is Connected"
- **Expected Result**: "The graph is not connected"
- **Actual Result**: ✓ Correct result

### Test Case 8: Cycle Detection - No Cycle
**Purpose**: Verify correct detection when graph has no cycles (tree)
- **Steps**:
  1. Create vertices A, B, C, D
  2. Add edges: A-B, A-C, A-D (star topology)
  3. Click "Has Cycles"
- **Expected Result**: "The graph does not have cycles"
- **Actual Result**: ✓ Correct result

### Test Case 9: Cycle Detection - With Cycle
**Purpose**: Verify correct detection when graph has cycles
- **Steps**:
  1. Create vertices A, B, C
  2. Add edges: A-B, B-C, C-A (triangle)
  3. Click "Has Cycles"
- **Expected Result**: "The graph has cycles"
- **Actual Result**: ✓ Correct result

### Test Case 10: Depth First Search
**Purpose**: Verify DFS produces correct vertex order
- **Steps**:
  1. Create vertices A, B, C, D, E
  2. Add edges: A-B, B-C, A-D, D-E
  3. Click "Depth First Search"
- **Expected Result**: DFS list starting from A with all reachable vertices
- **Actual Result**: ✓ Correct DFS traversal

### Test Case 11: Breadth First Search
**Purpose**: Verify BFS produces correct vertex order (level-by-level)
- **Steps**:
  1. Create vertices A, B, C, D, E
  2. Add edges: A-B, A-D, B-C, D-E
  3. Click "Breadth First Search"
- **Expected Result**: BFS list showing level-by-level traversal from A
- **Actual Result**: ✓ Correct BFS traversal

### Test Case 12: Search on Disconnected Graph
**Purpose**: Verify DFS/BFS only visit reachable vertices
- **Steps**:
  1. Create vertices A, B, C, D
  2. Add edges: A-B, C-D (two components)
  3. Click "Depth First Search"
- **Expected Result**: List contains A, B but not C, D
- **Actual Result**: ✓ Only reachable vertices returned

### Test Case 13: Empty Graph Error Handling
**Purpose**: Verify appropriate errors for operations on empty graph
- **Steps**:
  1. Don't add any vertices
  2. Click "Is Connected"
- **Expected Result**: Error message "Graph is empty"
- **Actual Result**: ✓ Proper error handling

### Test Case 14: DFS Without Vertex A
**Purpose**: Verify DFS behavior when vertex A doesn't exist
- **Steps**:
  1. Create vertices B, C, D (skip A)
  2. Click "Depth First Search"
- **Expected Result**: Error message "Vertex A does not exist"
- **Actual Result**: ✓ Proper error handling

### Test Case 15: Complex Graph Analysis
**Purpose**: Test multiple operations on a moderately complex graph
- **Steps**:
  1. Create 6 vertices (A-F)
  2. Add multiple edges forming a connected graph with cycles
  3. Perform all analysis operations
- **Expected Result**: All operations complete without errors
- **Actual Result**: ✓ All operations successful

## Algorithm Analysis

### Graph Representation
- **Data Structure**: Adjacency List using HashMap<String, Set<String>>
- **Space Complexity**: O(V + E) where V = vertices, E = edges
- **Time Complexity for operations**:
  - Add Vertex: O(1)
  - Add Edge: O(1)
  - Get Neighbors: O(1) average

### Connectivity Check (isConnected)
- **Algorithm**: Depth-First Search (DFS)
- **Time Complexity**: O(V + E) - visits each vertex and edge once
- **Space Complexity**: O(V) - for visited set and recursion stack

### Cycle Detection (hasCycles)
- **Algorithm**: DFS with parent tracking
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V)
- **Logic**: In undirected graphs, a back edge (edge to visited parent) indicates a cycle

### Depth-First Search (DFS)
- **Algorithm**: Recursive DFS starting from vertex A
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V)
- **Output**: List of vertices in DFS discovery order

### Breadth-First Search (BFS)
- **Algorithm**: Queue-based BFS starting from vertex A
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V) for queue and visited set
- **Output**: List of vertices in level-order (BFS discovery order)

## Lessons Learned

### 1. **Graph Representation Matters**
Choosing adjacency list over adjacency matrix was beneficial for this project since we don't know the maximum number of vertices in advance. This saved memory and made dynamic vertex addition straightforward. Using HashMap for O(1) lookup was crucial for efficient operations.

### 2. **Immutability for Vertex Class**
Making the Vertex class immutable with final fields ensures data integrity and makes the class thread-safe. This is a best practice for small data-carrying objects in Java.

### 3. **JavaFX Complexity**
JavaFX's Scene Graph structure requires careful management. Initially, I had issues with edge rendering appearing on top of vertices. Solving this required understanding the render order and using getChildren().add(0, ...) to place edges at the back of the scene graph.

### 4. **Cycle Detection in Undirected Graphs**
The key insight for undirected graphs is tracking the parent node during DFS. When we encounter a visited node that isn't our parent, we've found a back edge, indicating a cycle. This is different from directed graphs.

### 5. **Error Handling is Critical**
Users can make many mistakes (entering non-existent vertices, creating duplicate edges, etc.). Comprehensive error checking with clear messages significantly improves usability and robustness.

### 6. **GUI Layout Management**
Using BorderPane with VBox/HBox for organized layout made the GUI clean and responsive. Proper spacing (Insets, padding, spacing parameters) is essential for professional appearance.

### 7. **Testing Different Graph Topologies**
Testing with various graph structures (disconnected, cyclic, acyclic, trees, complete graphs) revealed edge cases and ensured algorithm correctness. The star topology and tree structures are particularly good for validating no-cycle detection.

### 8. **Mouse Event Handling**
JavaFX's mouse event coordinates are relative to the node, which required careful consideration for placing vertices at the correct visual position.

---

**Author**: Stefan V. Nikolov  
**Date**: February 9, 2026  
**Course**: CMSC 315 - Data Structures and Algorithms
