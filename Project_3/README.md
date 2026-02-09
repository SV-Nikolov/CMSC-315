# CMSC 315 Project 3 - Complete Binary Tree Implementation

## Overview
A Java program that implements a complete binary tree data structure with support for constructing trees from level-order arrays and performing three tree traversals: preorder, inorder, and postorder.

## Features
- ✅ Creates complete binary tree from level-order array
- ✅ Validates input (throws exception on null values)
- ✅ Three tree traversal methods: preorder, inorder, postorder
- ✅ Recursive algorithm implementation

## Project Structure

### `CompleteBinaryTree.java`
Main class implementing the binary tree.

**Public Methods:**
- `CompleteBinaryTree(Integer[] values)` - Constructor (throws `InvalidTreeException`)
- `void preorder()` - Root-Left-Right traversal
- `void inorder()` - Left-Root-Right traversal
- `void postorder()` - Left-Right-Root traversal

**Nested Class:**
- `TreeNode` - Represents individual tree nodes

### `InvalidTreeException.java`
Custom exception for invalid tree conditions.

### `Main.java`
Test driver that creates a tree and displays all three traversals.

## Traversal Orders

| Traversal | Order | Example |
|-----------|-------|---------|
| Preorder | Root → Left → Right | `90 70 20 40 50` |
| Inorder | Left → Root → Right | `20 70 40 90 50` |
| Postorder | Left → Right → Root | `20 40 70 50 90` |

## Usage

**Compile:**
```bash
javac CompleteBinaryTree.java InvalidTreeException.java Main.java
```

**Run:**
```bash
java Main
```

**Expected Output:**
```
Preorder: 90 70 20 40 50
Inorder: 20 70 40 90 50
Postorder: 20 40 70 50 90
```

## Test Case

Input array: `{90, 70, 50, 20, 40}`

Tree structure:
```
        90
       /  \
      70   50
     / \
    20  40
```

## Requirements
- ✅ Uses recursive algorithms for tree operations
- ✅ Implements three standard traversal methods
- ✅ Custom exception handling
- ✅ Each class in separate .java file
- ✅ Header comments with student name, project, date, description
