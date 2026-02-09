# CMSC 315 Project 4 - Quick Start Guide

## One-Click Setup (Windows)

1. **Install Java JDK** (if not already installed)
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Choose JDK 17 or higher
   - During installation, make sure to add Java to PATH

2. **Build the Application**
   - Open Command Prompt or PowerShell
   - Navigate to `Project_4` folder
   - Run: `build.bat`
   - Wait for compilation to complete

3. **Run the Application**
   - Option A: Double-click `GraphVisualization.jar`
   - Option B: Run in Command Prompt: `java -jar GraphVisualization.jar`

## One-Click Setup (Linux/Mac)

1. **Install Java JDK**
   ```bash
   # Ubuntu/Debian
   sudo apt-get install default-jdk
   
   # macOS with Homebrew
   brew install openjdk
   ```

2. **Build the Application**
   ```bash
   cd Project_4
   chmod +x build.sh
   ./build.sh
   ```

3. **Run the Application**
   ```bash
   java -jar GraphVisualization.jar
   ```

## Application Features

Once running, you can:

### 1. Add Vertices
- **Click anywhere** on the gray canvas to create vertices
- Vertices are automatically labeled A, B, C, etc.
- Each vertex appears as a blue circle

### 2. Add Edges
- Enter two vertex names in the text fields (e.g., "A" and "B")
- Click **"Add Edge"** button
- An edge (line) connects the two vertices

### 3. Analyze the Graph

| Button | Function |
|--------|----------|
| **Is Connected** | Checks if all vertices are reachable from each other |
| **Has Cycles** | Detects if the graph contains any cycles |
| **Depth First Search** | Shows DFS traversal starting from vertex A |
| **Breadth First Search** | Shows BFS traversal starting from vertex A |

### 4. Error Handling
The application will display helpful error messages for:
- Non-existent vertices
- Duplicate edges
- Self-loops (edges from a vertex to itself)
- Empty graphs

## Example Walkthrough

### Creating a Triangle (Has Cycle)

1. Click three locations on canvas → Creates vertices A, B, C
2. Vertex 1: `A`, Vertex 2: `B`, Click "Add Edge" → A-B connected
3. Vertex 1: `B`, Vertex 2: `C`, Click "Add Edge" → B-C connected
4. Vertex 1: `C`, Vertex 2: `A`, Click "Add Edge" → C-A connected
5. Click **"Has Cycles"** → Message: "The graph has cycles."

### Creating a Tree (No Cycle)

1. Click four locations → Vertices A, B, C, D
2. Add edges: A-B, A-C, A-D (star pattern)
3. Click **"Has Cycles"** → Message: "The graph does not have cycles."

## Project Files

### Source Code (in `cleaned/` folder for submission)
- `Vertex.java` - Vertex data class
- `Graph.java` - Graph implementation with analysis algorithms
- `GraphPane.java` - Visual graph rendering
- `Main.java` - JavaFX GUI application

### Build Files
- `MANIFEST.MF` - JAR manifest configuration
- `build.bat` - Automated Windows build script
- `build.sh` - Automated Linux/Mac build script
- `BUILD_INSTRUCTIONS.md` - Detailed compilation guide

### Documentation
- `README.md` - Project overview
- `PROJECT_DOCUMENTATION.md` - Technical details, UML, tests
- `QUICK_START_GUIDE.md` - This file

## Troubleshooting

### Issue: "java: command not found"
- **Solution**: Java is not installed or not in PATH
- Install JDK and verify with: `java -version`

### Issue: JAR won't open
- **Solution**: Ensure Java is properly installed
- Try running from command line instead: `java -jar GraphVisualization.jar`

### Issue: Build script fails
- **Solution**: Check Java installation and PATH
- Run build manually: `javac Vertex.java Graph.java GraphPane.java Main.java`

### Issue: GUI doesn't appear
- **Solution**: Ensure JavaFX is included with your JDK (Java 8+)
- Update Java to a newer version if needed

## Technical Specifications

- **Language**: Java 8+
- **GUI Framework**: JavaFX
- **Graph Type**: Undirected, weighted with adjacency list
- **Algorithms**: DFS, BFS, connectivity check, cycle detection
- **Executable**: GraphVisualization.jar (~50KB)

## Project Submission

For submission, use the `cleaned/` folder which contains:
- All 4 Java source files
- No compiled classes
- No JAR files
- Ready for your instructor to compile and test

---

**Need Help?** See `BUILD_INSTRUCTIONS.md` for detailed compilation instructions  
**Want Details?** See `PROJECT_DOCUMENTATION.md` for UML diagrams and test cases

---

**Author**: Stefan V. Nikolov  
**Date**: February 9, 2026  
**Course**: CMSC 315 - Data Structures and Algorithms
