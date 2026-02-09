#!/bin/bash
# Build script for CMSC 315 Project 4 - Graph Visualization
# This script compiles all Java files and creates an executable JAR file

echo "Compiling CMSC 315 Project 4..."

# Compile all Java files
javac Vertex.java Graph.java GraphPane.java Main.java

if [ $? -ne 0 ]; then
    echo "Compilation failed. Make sure Java JDK is installed."
    exit 1
fi

echo "Compilation successful."

# Create the JAR file with manifest
jar cvfm GraphVisualization.jar MANIFEST.MF Vertex.class Graph.class GraphPane.class Main.class

if [ $? -ne 0 ]; then
    echo "JAR creation failed."
    exit 1
fi

echo "JAR file created successfully: GraphVisualization.jar"
echo ""
echo "You can now run the application with:"
echo "  java -jar GraphVisualization.jar"
echo ""
