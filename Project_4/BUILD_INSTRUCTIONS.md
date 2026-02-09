# Build Instructions for Project 4: Graph Visualization

## Prerequisites

- **Java Development Kit (JDK) 8 or higher** - Required to compile and run
- **JavaFX SDK** - Required for GUI components (usually included with recent JDK versions)

## Option 1: Using Build Scripts (Recommended)

### Windows Users:
```bash
cd Project_4
build.bat
```

This will:
1. Compile all Java source files (Vertex.java, Graph.java, GraphPane.java, Main.java)
2. Create a JAR file named `GraphVisualization.jar`
3. The JAR file contains all compiled classes and is ready to run

### Linux/Mac Users:
```bash
cd Project_4
chmod +x build.sh
./build.sh
```

## Option 2: Manual Compilation

### Step 1: Compile Java Files
```bash
cd Project_4
javac Vertex.java Graph.java GraphPane.java Main.java
```

### Step 2: Create JAR File
```bash
jar cvfm GraphVisualization.jar MANIFEST.MF Vertex.class Graph.class GraphPane.class Main.class
```

## Option 3: Run Directly Without JAR

If you prefer to run the classes directly without creating a JAR:

```bash
cd Project_4
javac Vertex.java Graph.java GraphPane.java Main.java
java Main
```

## Running the Application

### Using the JAR File:
```bash
java -jar GraphVisualization.jar
```

### Direct Execution:
```bash
java Main
```

## Troubleshooting

### "javac: command not found" or "Java not found in PATH"
- **Solution**: Install Java JDK and ensure it's added to your system PATH
- **Verify Java Installation**: Run `java -version` and `javac -version`

### "JavaFX not found" Error
- **Solution**: JavaFX is included in recent JDK versions, but if you have an older version:
  - Download JavaFX SDK from https://gluonhq.com/products/javafx/
  - Compile with: `javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls Vertex.java Graph.java GraphPane.java Main.java`

### JAR File Won't Run
- **Verify the manifest**: Check that MANIFEST.MF has the correct Main-Class entry
- **Check Java version**: Ensure you're using Java 8 or higher

## File Structure After Building

```
Project_4/
├── Vertex.java              (source)
├── Graph.java               (source)
├── GraphPane.java           (source)
├── Main.java                (source)
├── MANIFEST.MF              (JAR manifest)
├── build.bat                (Windows build script)
├── build.sh                 (Linux/Mac build script)
├── Vertex.class             (compiled bytecode)
├── Graph.class              (compiled bytecode)
├── GraphPane.class          (compiled bytecode)
├── Main.class               (compiled bytecode)
├── GraphVisualization.jar   (executable JAR)
└── cleaned/                 (submission files)
    ├── Vertex.java
    ├── Graph.java
    ├── GraphPane.java
    └── Main.java
```

## Project Requirements Met

✅ All 4 required classes implemented  
✅ JavaFX GUI with interactive graph visualization  
✅ Vertex creation by clicking  
✅ Edge addition with validation  
✅ Graph analysis (connectivity, cycles)  
✅ Graph traversals (DFS, BFS)  
✅ Professional error handling  
✅ Comprehensive documentation  
✅ Executable JAR file  
✅ Build scripts for easy compilation  

---

**Author**: Stefan V. Nikolov  
**Date**: February 9, 2026  
**Course**: CMSC 315 - Data Structures and Algorithms
