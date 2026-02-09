# Quick Reference - Project 3

## Tree Traversals Quick Guide

### Input Array
```java
Integer[] values = {90, 70, 50, 20, 40};
```

### Tree Structure
```
        90
       /  \
      70   50
     / \
    20  40
```

### Traversal Outputs

| Traversal | Order | Result |
|-----------|-------|--------|
| Preorder | Root → Left → Right | `90 70 20 40 50` |
| Inorder | Left → Root → Right | `20 70 40 90 50` |
| Postorder | Left → Right → Root | `20 40 70 50 90` |

---

## Method Signatures

```java
// CompleteBinaryTree.java
public CompleteBinaryTree(Integer[] values) throws InvalidTreeException
public void preorder()
public void inorder()
public void postorder()
protected TreeNode makeNode(Integer[] values, int index) throws InvalidTreeException
```

---

## Compilation Commands

```bash
# Navigate to directory
cd Project_3/project3_starter/project3_starter

# Compile
javac Main.java CompleteBinaryTree.java InvalidTreeException.java

# Run
java Main
```

---

## Index Formula for Complete Binary Tree

For node at index `i`:
- **Left child:** `2i + 1`
- **Right child:** `2i + 2`
- **Parent:** `⌊(i-1)/2⌋`

---

## Time Complexity

| Operation | Complexity |
|-----------|------------|
| Construction | O(n) |
| Preorder | O(n) |
| Inorder | O(n) |
| Postorder | O(n) |

---

## Space Complexity

| Aspect | Complexity |
|--------|------------|
| Tree Storage | O(n) |
| Recursion Stack | O(h) = O(log n) |

---

## When to Use Each Traversal

- **Preorder**: Create copy, prefix notation, evaluate expressions
- **Inorder**: BST sorted output, infix notation
- **Postorder**: Delete tree, postfix notation, evaluate expressions

---

## Project Files

✅ CompleteBinaryTree.java (154 lines)  
✅ InvalidTreeException.java (13 lines)  
✅ Main.java (23 lines)  
✅ README.md  
✅ Project_Documentation.md  
✅ SUMMARY.md

---

**Student:** Stefan V. Nikolov  
**Date:** February 9, 2026
