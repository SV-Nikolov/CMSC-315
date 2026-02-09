# CMSC 315 – Project 3 Documentation
## Complete Binary Tree Implementation

**Student:** Stefan V. Nikolov  
**Date:** February 9, 2026  
**Course:** CMSC 315 – Data Structures and Analysis

---

## 1. UML Class Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                    CompleteBinaryTree                       │
├─────────────────────────────────────────────────────────────┤
│ # root: TreeNode                                            │
│                                                             │
│ <<nested class>>                                            │
│ TreeNode                                                    │
│   # value: Integer                                          │
│   # left: TreeNode                                          │
│   # right: TreeNode                                         │
├─────────────────────────────────────────────────────────────┤
│ + CompleteBinaryTree(values: Integer[])                     │
│ # makeNode(Integer[], int): TreeNode                        │
│ + preorder(): void                                          │
│ + inorder(): void                                           │
│ + postorder(): void                                         │
└─────────────────────────────────────────────────────────────┘
                            △
                            │ throws
                            │
┌─────────────────────────────────────────────────────────────┐
│                InvalidTreeException                         │
├─────────────────────────────────────────────────────────────┤
│ + InvalidTreeException(message: String)                     │
└─────────────────────────────────────────────────────────────┘
```

### Class Relationships
- **CompleteBinaryTree** contains nested class **TreeNode** for tree nodes
- **CompleteBinaryTree** throws **InvalidTreeException** if null values are found
- **Main** uses **CompleteBinaryTree** for testing

---

## 2. Method Implementations

### Constructor
```java
public CompleteBinaryTree(Integer[] values) throws InvalidTreeException
```
**Purpose:** Constructs a complete binary tree from a level-order array  
**Implementation:** Calls recursive `makeNode()` starting at index 0

### Tree Construction
```java
protected TreeNode makeNode(Integer[] values, int index) throws InvalidTreeException
```
**Purpose:** Recursively builds tree from array  
**Logic:**
- Base case: return null if index ≥ array length
- Throw exception if value is null
- Create node and recursively build children at indices `2*i+1` (left) and `2*i+2` (right)

### Preorder Traversal
```java
public void preorder()
```
**Order:** Root → Left → Right  
**Example:** Array `{90, 70, 50, 20, 40}` outputs `90 70 20 40 50`

### Inorder Traversal
```java
public void inorder()
```
**Order:** Left → Root → Right  
**Example:** Array `{90, 70, 50, 20, 40}` outputs `20 70 40 90 50`

### Postorder Traversal
```java
public void postorder()
```
**Order:** Left → Right → Root  
**Example:** Array `{90, 70, 50, 20, 40}` outputs `20 40 70 50 90`

---

## 3. Test Plan

### Test Case 1: Standard Complete Tree
**Input:** `{90, 70, 50, 20, 40}`  
**Expected Output:**
```
Preorder: 90 70 20 40 50
Inorder: 20 70 40 90 50
Postorder: 20 40 70 50 90
```
**Status:** ✅ Pass

---

### Test Case 2: Single Node
**Input:** `{100}`  
**Expected Output:**
```
Preorder: 100
Inorder: 100
Postorder: 100
```
**Status:** ✅ Pass

---

### Test Case 3: Full Binary Tree (7 nodes)
**Input:** `{50, 25, 75, 12, 37, 62, 87}`  
**Expected Output:**
```
Preorder: 50 25 12 37 75 62 87
Inorder: 12 25 37 50 62 75 87
Postorder: 12 37 25 62 87 75 50
```
**Status:** ✅ Pass

---

### Test Case 4: Invalid Tree (Null Value)
**Input:** `{10, null, 30}`  
**Expected:** Throws `InvalidTreeException`  
**Status:** ✅ Pass

---

### Test Case 5: Empty Array
**Input:** `{}`  
**Expected:** Creates empty tree (no output)  
**Status:** ✅ Pass

---

## 4. Lessons Learned

This project reinforced understanding of fundamental tree algorithms:

1. **Recursive Tree Traversals:** Implementing preorder, inorder, and postorder demonstrates the recursive nature of tree operations
2. **Array-to-Tree Mapping:** Using the formula (left child at `2i+1`, right child at `2i+2`) converts level-order arrays into binary tree structures
3. **Algorithm Design:** Three different traversal patterns produce different orderings from the same tree structure
4. **Exception Handling:** Custom exceptions provide clear error messages for invalid input conditions
5. **Separation of Concerns:** Public methods handle formatting while private recursive helpers perform the actual algorithm

---

## 5. Conclusion

The Complete Binary Tree project successfully implements:
- ✅ Tree construction from level-order array
- ✅ Three standard tree traversals (preorder, inorder, postorder)
- ✅ Custom exception handling for validation
- ✅ Professional code organization and documentation

**Status:** Complete and ready for submission

---

**Last Updated:** February 9, 2026
