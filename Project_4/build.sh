#!/bin/bash
# Build script for CMSC 315 Project 4 - Graph Visualization
# This script compiles all Java files and creates an executable JAR file

echo "Compiling CMSC 315 Project 4..."

# Locate JavaFX SDK (required for JavaFX in JDK 11+)
if [ -z "$JAVA_FX" ]; then
    for d in /opt/javafx-sdk* /opt/JavaFX/javafx-sdk* /usr/local/javafx-sdk* "$HOME/javafx-sdk"*; do
        if [ -d "$d" ]; then
            JAVA_FX="$d"
            break
        fi
    done
fi

if [ -z "$JAVA_FX" ]; then
    echo "JavaFX SDK not found."
    echo "Please download JavaFX SDK and set JAVA_FX to its install path."
    echo "Example: export JAVA_FX=\"/opt/javafx-sdk-21\""
    exit 1
fi

echo "Using JavaFX SDK at: $JAVA_FX"

# Compile all Java files with JavaFX modules
javac --module-path "$JAVA_FX/lib" --add-modules javafx.controls Vertex.java Graph.java GraphPane.java Main.java

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
echo "Creating run script (run.sh)..."
cat > run.sh <<'EOF'
#!/bin/bash
if [ -z "$JAVA_FX" ]; then
    echo "JAVA_FX is not set. Example: export JAVA_FX=\"/opt/javafx-sdk-21\""
    exit 1
fi
java --module-path "$JAVA_FX/lib" --add-modules javafx.controls -jar GraphVisualization.jar
EOF
chmod +x run.sh
echo ""
echo "You can now run the application with:"
echo "  ./run.sh"
echo ""
