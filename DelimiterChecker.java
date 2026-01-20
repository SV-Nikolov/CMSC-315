/**
 * DelimiterChecker.java
 * CMSC 315 - Project 1
 * Date: January 20, 2026
 * 
 * This is the main driver class that checks delimiter matching in Java source files.
 * It uses a stack-based approach to validate that all parentheses, braces, and
 * square brackets are properly matched.
 */

import java.io.*;
import java.util.Stack;
import java.util.Scanner;

public class DelimiterChecker {
    
    /**
     * Helper class to store delimiter information with position
     */
    private static class DelimiterInfo {
        char delimiter;
        String position;
        
        DelimiterInfo(char delimiter, String position) {
            this.delimiter = delimiter;
            this.position = position;
        }
    }
    
    /**
     * Checks if a character is a left delimiter.
     * 
     * @param ch The character to check
     * @return true if the character is '(', '{', or '['
     */
    private static boolean isLeftDelimiter(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }
    
    /**
     * Checks if a character is a right delimiter.
     * 
     * @param ch The character to check
     * @return true if the character is ')', '}', or ']'
     */
    private static boolean isRightDelimiter(char ch) {
        return ch == ')' || ch == '}' || ch == ']';
    }
    
    /**
     * Checks if two delimiters form a matching pair.
     * 
     * @param left The left delimiter
     * @param right The right delimiter
     * @return true if the delimiters match
     */
    private static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')') ||
               (left == '{' && right == '}') ||
               (left == '[' && right == ']');
    }
    
    /**
     * Main method that drives the delimiter checking process.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SourceFileReader reader = null;
        
        // Prompt for filename until a valid file is provided
        while (reader == null) {
            System.out.print("Enter Java source file name: ");
            String filename = scanner.nextLine().trim();
            
            try {
                reader = new SourceFileReader(filename);
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please try again.");
            }
        }
        
        // Stack to track delimiters
        Stack<DelimiterInfo> stack = new Stack<>();
        
        // Process characters from the file
        Character ch;
        while ((ch = reader.getNextChar()) != null) {
            if (isLeftDelimiter(ch)) {
                // Push left delimiter onto stack
                stack.push(new DelimiterInfo(ch, reader.getPosition()));
            } else if (isRightDelimiter(ch)) {
                // Check for matching left delimiter
                if (stack.isEmpty()) {
                    System.out.println("Unmatched delimiter '" + ch + "' at " + reader.getPosition());
                    scanner.close();
                    return;
                }
                
                DelimiterInfo left = stack.pop();
                if (!isMatchingPair(left.delimiter, ch)) {
                    System.out.println("Mismatched delimiters '" + left.delimiter + 
                                     "' and '" + ch + "' at " + reader.getPosition());
                    scanner.close();
                    return;
                }
            }
        }
        
        // Check for unmatched left delimiters
        if (!stack.isEmpty()) {
            DelimiterInfo unmatched = stack.pop();
            System.out.println("Unmatched delimiter '" + unmatched.delimiter + "' at end of file.");
        } else {
            System.out.println("All delimiters match correctly.");
        }
        
        scanner.close();
    }
}
