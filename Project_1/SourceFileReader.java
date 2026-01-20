/**
 * SourceFileReader.java
 * CMSC 315 - Project 1
 * Date: January 20, 2026
 * 
 * This class encapsulates all logic related to reading and filtering characters
 * from a Java source file. It reads characters sequentially and skips characters
 * inside comments, string literals, and character literals while tracking line
 * and character positions.
 */

import java.io.*;

public class SourceFileReader {
    private BufferedReader reader;
    private int lineNumber;
    private int charNumber;
    private String currentLine;
    private int lineIndex;
    private boolean inBlockComment;
    private boolean inLineComment;
    private boolean inString;
    private boolean inChar;
    private boolean endOfFile;
    
    /**
     * Constructor that opens the specified file for reading.
     * 
     * @param filename The name of the Java source file to read
     * @throws FileNotFoundException if the file does not exist
     */
    public SourceFileReader(String filename) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filename));
        lineNumber = 1;
        charNumber = 0;
        currentLine = "";
        lineIndex = 0;
        inBlockComment = false;
        inLineComment = false;
        inString = false;
        inChar = false;
        endOfFile = false;
    }
    
    /**
     * Returns the next valid character from the file, skipping characters
     * inside comments, string literals, and character literals.
     * 
     * @return The next valid character, or null when end of file is reached
     */
    public Character getNextChar() {
        try {
            while (!endOfFile) {
                // Read a new line if needed
                if (lineIndex >= currentLine.length()) {
                    currentLine = reader.readLine();
                    if (currentLine == null) {
                        endOfFile = true;
                        reader.close();
                        return null;
                    }
                    lineIndex = 0;
                    lineNumber++;
                    charNumber = 0;
                    inLineComment = false; // Line comments end at newline
                }
                
                if (lineIndex < currentLine.length()) {
                    char ch = currentLine.charAt(lineIndex);
                    charNumber++;
                    lineIndex++;
                    
                    // Check for comment and literal state changes
                    if (!inString && !inChar && !inBlockComment) {
                        // Check for line comment start
                        if (ch == '/' && lineIndex < currentLine.length() && 
                            currentLine.charAt(lineIndex) == '/') {
                            inLineComment = true;
                            charNumber++;
                            lineIndex++;
                            continue;
                        }
                        // Check for block comment start
                        if (ch == '/' && lineIndex < currentLine.length() && 
                            currentLine.charAt(lineIndex) == '*') {
                            inBlockComment = true;
                            charNumber++;
                            lineIndex++;
                            continue;
                        }
                    }
                    
                    // Check for block comment end
                    if (inBlockComment && ch == '*' && lineIndex < currentLine.length() && 
                        currentLine.charAt(lineIndex) == '/') {
                        inBlockComment = false;
                        charNumber++;
                        lineIndex++;
                        continue;
                    }
                    
                    // Skip if inside any comment
                    if (inLineComment || inBlockComment) {
                        continue;
                    }
                    
                    // Handle string literals
                    if (ch == '"' && !inChar) {
                        if (!inString) {
                            inString = true;
                        } else {
                            // Check if it's escaped
                            int backslashCount = 0;
                            int idx = lineIndex - 2;
                            while (idx >= 0 && currentLine.charAt(idx) == '\\') {
                                backslashCount++;
                                idx--;
                            }
                            // If even number of backslashes, the quote is not escaped
                            if (backslashCount % 2 == 0) {
                                inString = false;
                            }
                        }
                        continue;
                    }
                    
                    // Handle character literals
                    if (ch == '\'' && !inString) {
                        if (!inChar) {
                            inChar = true;
                        } else {
                            // Check if it's escaped
                            int backslashCount = 0;
                            int idx = lineIndex - 2;
                            while (idx >= 0 && currentLine.charAt(idx) == '\\') {
                                backslashCount++;
                                idx--;
                            }
                            // If even number of backslashes, the quote is not escaped
                            if (backslashCount % 2 == 0) {
                                inChar = false;
                            }
                        }
                        continue;
                    }
                    
                    // Skip if inside string or char literal
                    if (inString || inChar) {
                        continue;
                    }
                    
                    // Return valid character
                    return ch;
                }
            }
            return null;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Returns the current position in the file as a formatted string.
     * 
     * @return A string containing the current line and character number
     */
    public String getPosition() {
        return "Line " + lineNumber + ", Character " + charNumber;
    }
}
