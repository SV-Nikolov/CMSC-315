/**
 * Student Name: Stefan V. Nikolov
 * Project: CMSC 315 Project 3 - Complete Binary Tree Implementation
 * Date: February 9, 2026
 * Description: Main driver class that creates a complete binary tree and displays 
 *              preorder, inorder, and postorder traversals
 */

public class Main {
    public static void main(String[] args) {
        try {
            Integer[] values = { 90, 70, 50, 20, 40 };
            CompleteBinaryTree tree = new CompleteBinaryTree(values);
            
            // Display all three traversals
            tree.preorder();
            tree.inorder();
            tree.postorder();
        } catch (InvalidTreeException e) {
            System.out.println(e.getMessage());
        }
    }
}
