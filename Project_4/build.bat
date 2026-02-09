@echo off
REM Build script for CMSC 315 Project 4 - Graph Visualization
REM This script compiles all Java files and creates an executable JAR file

echo Compiling CMSC 315 Project 4...

REM Locate JavaFX SDK (required for JavaFX in JDK 11+)
if not defined JAVA_FX (
    for /d %%D in ("C:\javafx-sdk*" "C:\JavaFX\javafx-sdk*" "C:\Program Files\javafx-sdk*") do (
        set "JAVA_FX=%%~fD"
        goto :foundJavaFx
    )
)

:foundJavaFx
if not defined JAVA_FX (
    echo JavaFX SDK not found.
    echo Please download JavaFX SDK and set JAVA_FX to its install path.
    echo Example:
    echo   setx JAVA_FX "C:\javafx-sdk-21"
    echo Then re-open the terminal and rerun build.bat.
    pause
    exit /b 1
)

echo Using JavaFX SDK at: %JAVA_FX%

REM Compile all Java files with JavaFX modules
javac --module-path "%JAVA_FX%\lib" --add-modules javafx.controls Vertex.java Graph.java GraphPane.java Main.java

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
echo Creating run script (run.bat)...
(
    echo @echo off
    echo set "JAVA_FX=%JAVA_FX%"
    echo java --module-path "%%JAVA_FX%%\lib" --add-modules javafx.controls -jar GraphVisualization.jar
) > run.bat
echo.
echo You can now run the application with:
echo   run.bat
echo.
pause
