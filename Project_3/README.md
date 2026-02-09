# CMSC 315 Project 3 - Complete Binary Tree Implementation

## Overview
A Java program that implements a complete binary tree data structure with support for constructing trees from level-order arrays and performing three types of tree traversals: preorder, inorder, and postorder.

## Author
**Stefan V. Nikolov**  
**Date:** February 9, 2026  
**Course:** CMSC 315 - Data Structures and Analysis

## Files Included

### Source Files
| File | Description |
|------|-------------|
| `CompleteBinaryTree.java` | Main tree implementation with traversal methods |
| `InvalidTreeException.java` | Custom exception for invalid tree conditions |
| `Main.java` | Driver class that creates and tests the tree |

## Features

### Tree Construction
- **Level-Order Array Input**: Constructs a complete binary tree from an array where elements are arranged in level-order
- **Recursive Building**: Uses recursive algorithm to build tree structure
- **Null Validation**: Throws `InvalidTreeException` if null values are encountered

### Tree Traversals
The implementation provides three standard tree traversal methods:

1. **Preorder Traversal** (Root → Left → Right)
   - Visits the root node first
   - Then recursively visits left subtree
   - Finally recursively visits right subtree

2. **Inorder Traversal** (Left → Root → Right)
   - Recursively visits left subtree first
   - Then visits the root node
   - Finally recursively visits right subtree

3. **Postorder Traversal** (Left → Right → Root)
   - Recursively visits left subtree first
   - Then recursively visits right subtree
   - Finally visits the root node

## Implementation Details

### CompleteBinaryTree Class

**TreeNode Inner Class:**
```java
protected Integer value;
protected TreeNode left;
protected TreeNode right;
```

**Key Methods:**
- `CompleteBinaryTree(Integer[] values)` - Constructor that builds tree from array
- `makeNode(Integer[] values, int index)` - Recursive helper for tree construction
- `preorder()` - Public method for preorder traversal
- `inorder()` - Public method for inorder traversal
- `postorder()` - Public method for postorder traversal

**Level-Order Index Calculation:**
- For node at index `i`:
  - Left child is at index `2*i + 1`
  - Right child is at index `2*i + 2`

### Example Tree Structure

Given input array: `{90, 70, 50, 20, 40}`

```
Tree Structure:
        90
       /  \
      70   50
     / \
    20  40
```

**Expected Output:**
```
Preorder: 90 70 20 40 50
Inorder: 20 70 40 90 50
Postorder: 20 40 70 50 90
```

## Compilation & Execution

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Terminal or command prompt

### Compile
```bash
cd project3_starter/project3_starter
javac Main.java CompleteBinaryTree.java InvalidTreeException.java
```

### Run
```bash
java Main
```

## Traversal Algorithms Explained

### Preorder (Root-Left-Right)
1. Process the current node
2. Recursively traverse left subtree
3. Recursively traverse right subtree
- **Use Case:** Used to create a copy of the tree or prefix expression evaluation

### Inorder (Left-Root-Right)
1. Recursively traverse left subtree
2. Process the current node
3. Recursively traverse right subtree
- **Use Case:** For binary search trees, produces sorted output

### Postorder (Left-Right-Root)
1. Recursively traverse left subtree
2. Recursively traverse right subtree
3. Process the current node
- **Use Case:** Used to delete trees or postfix expression evaluation

## Design Decisions

### Complete Binary Tree Properties
- All levels are fully filled except possibly the last level
- Last level is filled from left to right
- Efficiently represented using an array
- Parent-child relationships follow index formulas

### Recursive Implementation
- All traversals use recursive helper methods
- Clean, readable code structure
- Follows the natural recursive definition of trees
- Base case: null node returns immediately

### Exception Handling
- Custom `InvalidTreeException` for null node errors
- Provides clear error messages
- Checked exception requires explicit handling

## Testing

### Test Case 1: Standard Tree
**Input:** `{90, 70, 50, 20, 40}`  
**Expected:**
- Preorder: 90 70 20 40 50
- Inorder: 20 70 40 90 50
- Postorder: 20 40 70 50 90

### Test Case 2: Single Node
**Input:** `{100}`  
**Expected:**
- Preorder: 100
- Inorder: 100
- Postorder: 100

### Test Case 3: Full Tree
**Input:** `{50, 25, 75, 12, 37, 62, 87}`
```
         50
       /    \
      25     75
     / \    / \
    12  37 62  87
```
**Expected:**
- Preorder: 50 25 12 37 75 62 87
- Inorder: 12 25 37 50 62 75 87
- Postorder: 12 37 25 62 87 75 50

### Test Case 4: Null Value Error
**Input:** `{10, null, 30}`  
**Expected:** Throws `InvalidTreeException` with message "Node element must not be null"

## Key Learning Outcomes

1. **Tree Data Structure**: Understanding complete binary tree properties and representation
2. **Recursion**: Implementing recursive algorithms for tree traversals
3. **Array-to-Tree Mapping**: Converting level-order array representation to linked structure
4. **Traversal Patterns**: Mastering preorder, inorder, and postorder traversal orders
5. **Exception Handling**: Using custom exceptions for validation
6. **Algorithm Analysis**: Understanding time complexity O(n) for traversals

## Time and Space Complexity

### Construction
- **Time Complexity:** O(n) - visits each array element once
- **Space Complexity:** O(n) - creates n TreeNode objects

### Traversals (All Three)
- **Time Complexity:** O(n) - visits each node exactly once
- **Space Complexity:** O(h) - recursive call stack, where h is tree height
  - For complete binary tree: O(log n)

## Summary

This project successfully implements a complete binary tree with three traversal methods. The implementation demonstrates understanding of:
- Tree data structure fundamentals
- Recursive algorithm design
- Array-to-tree conversion techniques
- Standard tree traversal patterns
- Professional Java coding practices

**Status:** Complete and ready for submission

---

## Project Structure
```
Project_3/
├── Assignment.pdf                    # Assignment specification
├── Grading.pdf                       # Grading rubric
├── README.md                         # This file
└── project3_starter/
    └── project3_starter/
        ├── CompleteBinaryTree.java   # Main implementation (complete)
        ├── InvalidTreeException.java # Exception class (complete)
        └── Main.java                 # Test driver (complete)
```

## Submission Checklist
- [x] Source code compiles without errors
- [x] All three traversals implemented (preorder, inorder, postorder)
- [x] Header comments added to all files
- [x] Exception handling works correctly
- [x] Code follows Java naming conventions
- [x] README documentation complete
- [x] Test cases verified manually

---
**Last Updated:** February 9, 2026
