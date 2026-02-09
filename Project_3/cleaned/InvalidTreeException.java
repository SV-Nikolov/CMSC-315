/**
 * Student Name: Stefan V. Nikolov
 * Project: CMSC 315 Project 3 - Complete Binary Tree Implementation
 * Date: February 9, 2026
 * Description: Custom exception class for handling invalid tree conditions, 
 *              such as null nodes in positions where values are expected
 */

public class InvalidTreeException extends Exception {
    public InvalidTreeException(String message) {
        super(message);
    }
}
