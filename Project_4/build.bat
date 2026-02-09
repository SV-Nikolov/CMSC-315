@echo off
REM Build script for CMSC 315 Project 4 - Graph Visualization
REM This script compiles all Java files and creates an executable JAR file

echo Compiling CMSC 315 Project 4...

REM Compile all Java files
javac Vertex.java Graph.java GraphPane.java Main.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed. Make sure Java JDK is installed and in PATH.
    pause
    exit /b 1
)

echo Compilation successful.

REM Create the JAR file with manifest
jar cvfm GraphVisualization.jar MANIFEST.MF Vertex.class Graph.class GraphPane.class Main.class

if %ERRORLEVEL% NEQ 0 (
    echo JAR creation failed.
    pause
    exit /b 1
)

echo JAR file created successfully: GraphVisualization.jar
echo.
echo You can now run the application with:
echo   java -jar GraphVisualization.jar
echo.
pause
