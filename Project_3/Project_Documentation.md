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
├─────────────────────────────────────────────────────────────┤
│ <<nested class>>                                            │
│ TreeNode                                                    │
│   # value: Integer                                          │
│   # left: TreeNode                                          │
│   # right: TreeNode                                         │
│   + TreeNode(value: Integer)                                │
├─────────────────────────────────────────────────────────────┤
│ + CompleteBinaryTree(values: Integer[])                     │
│ # makeNode(values: Integer[], index: int): TreeNode        │
│ + preorder(): void                                          │
│ - preorder(root: TreeNode): void                           │
│ + inorder(): void                                           │
│ - inorder(root: TreeNode): void                            │
│ + postorder(): void                                         │
│ - postorder(root: TreeNode): void                          │
└─────────────────────────────────────────────────────────────┘
                            △
                            │ throws
                            │
┌─────────────────────────────────────────────────────────────┐
│                InvalidTreeException                         │
├─────────────────────────────────────────────────────────────┤
│ + InvalidTreeException(message: String)                     │
└─────────────────────────────────────────────────────────────┘
                            △
                            │ uses
                            │
┌─────────────────────────────────────────────────────────────┐
│                           Main                              │
├─────────────────────────────────────────────────────────────┤
│ + main(args: String[]): void                                │
│ Responsibilities:                                           │
│ • Creates tree from array                                   │
│ • Calls all traversal methods                              │
│ • Handles exceptions                                        │
└─────────────────────────────────────────────────────────────┘
```

### Class Relationships
- **Main** uses **CompleteBinaryTree** to create and test tree operations
- **CompleteBinaryTree** contains nested class **TreeNode** representing tree nodes
- **CompleteBinaryTree** throws **InvalidTreeException** on invalid input
- **CompleteBinaryTree** implements three traversal algorithms as public methods

---

## 2. Method Implementations

### Constructor
```java
public CompleteBinaryTree(Integer[] values) throws InvalidTreeException
```
**Purpose:** Constructs a complete binary tree from a level-order array  
**Algorithm:** Calls recursive `makeNode()` starting from index 0  
**Throws:** `InvalidTreeException` if array contains null values

### makeNode (Recursive Helper)
```java
protected TreeNode makeNode(Integer[] values, int index) throws InvalidTreeException
```
**Purpose:** Recursively builds tree from array  
**Algorithm:**
- Base case: if index ≥ array length, return null
- Validation: throw exception if value is null
- Create node with current value
- Recursively build left child at index `2*i + 1`
- Recursively build right child at index `2*i + 2`
- Return constructed node

### Preorder Traversal
```java
public void preorder()
private void preorder(TreeNode root)
```
**Order:** Root → Left → Right  
**Algorithm:**
1. Visit root node (print value)
2. Recursively traverse left subtree
3. Recursively traverse right subtree

### Inorder Traversal
```java
public void inorder()
private void inorder(TreeNode root)
```
**Order:** Left → Root → Right  
**Algorithm:**
1. Recursively traverse left subtree
2. Visit root node (print value)
3. Recursively traverse right subtree

### Postorder Traversal
```java
public void postorder()
private void postorder(TreeNode root)
```
**Order:** Left → Right → Root  
**Algorithm:**
1. Recursively traverse left subtree
2. Recursively traverse right subtree
3. Visit root node (print value)

---

## 3. Tree Construction Example

### Input Array
```java
Integer[] values = {90, 70, 50, 20, 40};
```

### Index Mapping
```
Index:  0   1   2   3   4
Value: 90  70  50  20  40

Parent-Child Relationships:
- Node at index 0 (90): left=1, right=2
- Node at index 1 (70): left=3, right=4
- Node at index 2 (50): left=5, right=6 (both out of bounds = null)
- Node at index 3 (20): left=7, right=8 (both out of bounds = null)
- Node at index 4 (40): left=9, right=10 (both out of bounds = null)
```

### Resulting Tree Structure
```
        90 (index 0)
       /  \
      /    \
     70    50 (indices 1, 2)
    / \
   /   \
  20   40 (indices 3, 4)
```

### Traversal Results
- **Preorder:**  90 70 20 40 50
- **Inorder:**   20 70 40 90 50
- **Postorder:** 20 40 70 50 90

---

## 4. Algorithm Analysis

### Time Complexity

| Operation | Complexity | Explanation |
|-----------|------------|-------------|
| Constructor | O(n) | Visits each array element once |
| makeNode | O(n) | Called once per array element |
| Preorder | O(n) | Visits each node exactly once |
| Inorder | O(n) | Visits each node exactly once |
| Postorder | O(n) | Visits each node exactly once |

### Space Complexity

| Operation | Complexity | Explanation |
|-----------|------------|-------------|
| Tree Storage | O(n) | n TreeNode objects |
| Traversal Stack | O(h) | Recursive call stack height h |
| Complete Binary Tree | O(log n) | Height h = ⌊log₂(n)⌋ for complete tree |

---

## 5. Test Plan & Results

### Test Case 1: Standard Complete Tree
**Input:** `{90, 70, 50, 20, 40}`  
**Tree:**
```
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
**Status:** ✅ Pass

---

### Test Case 2: Single Node Tree
**Input:** `{100}`  
**Tree:**
```
100
```
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
**Tree:**
```
         50
       /    \
      25     75
     / \    / \
    12  37 62  87
```
**Expected Output:**
```
Preorder: 50 25 12 37 75 62 87
Inorder: 12 25 37 50 62 75 87
Postorder: 12 37 25 62 87 75 50
```
**Status:** ✅ Pass

---

### Test Case 4: Left-Heavy Tree
**Input:** `{10, 5, 15, 2, 7}`  
**Tree:**
```
       10
      /  \
     5    15
    / \
   2   7
```
**Expected Output:**
```
Preorder: 10 5 2 7 15
Inorder: 2 5 7 10 15
Postorder: 2 7 5 15 10
```
**Status:** ✅ Pass

---

### Test Case 5: Invalid Tree (Null Value)
**Input:** `{10, null, 30}`  
**Expected:** Throws `InvalidTreeException` with message "Node element must not be null"  
**Status:** ✅ Pass

---

### Test Case 6: Empty Array
**Input:** `{}`  
**Expected:** Creates empty tree (root = null), no output  
**Status:** ✅ Pass

---

### Test Case 7: Large Tree (15 nodes)
**Input:** `{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}`  
**Tree:**
```
              1
         /         \
       2             3
     /   \         /   \
    4     5       6     7
   / \   / \     / \   / \
  8   9 10 11   12 13 14 15
```
**Expected Output:**
```
Preorder: 1 2 4 8 9 5 10 11 3 6 12 13 7 14 15
Inorder: 8 4 9 2 10 5 11 1 12 6 13 3 14 7 15
Postorder: 8 9 4 10 11 5 2 12 13 6 14 15 7 3 1
```
**Status:** ✅ Pass

---

## 6. Traversal Comparison

### Visual Example with Tree {90, 70, 50, 20, 40}

```
        90
       /  \
      70   50
     / \
    20  40
```

#### Preorder (Root-Left-Right)
```
Visit: 90 → 70 → 20 → 40 → 50
Order: Parent before children
```

#### Inorder (Left-Root-Right)
```
Visit: 20 → 70 → 40 → 90 → 50
Order: Left subtree, then parent, then right subtree
Note: For BST, produces sorted order
```

#### Postorder (Left-Right-Root)
```
Visit: 20 → 40 → 70 → 50 → 90
Order: Children before parent
Note: Useful for deleting trees
```

---

## 7. Design Decisions & Rationale

### 1. Recursive Implementation
**Decision:** Use recursion for all traversals  
**Rationale:**
- Natural fit for tree structure
- Clean, readable code
- Follows mathematical definition of tree operations
- Easier to verify correctness

### 2. Level-Order Array Representation
**Decision:** Build tree from array using index formulas  
**Rationale:**
- Standard representation for complete binary trees
- Efficient space usage
- Simple parent-child relationship: left = 2i+1, right = 2i+2
- No null placeholders needed for complete trees

### 3. Separate Public and Private Methods
**Decision:** Public methods call private recursive helpers  
**Rationale:**
- Encapsulation: hide implementation details
- Cleaner API for users
- Private methods can accept TreeNode parameters
- Public methods handle formatting (print statements)

### 4. Protected Access Modifiers
**Decision:** Use `protected` for root and TreeNode fields  
**Rationale:**
- Allow potential subclassing
- Restrict external access
- Balance between private and public
- Common pattern in tree implementations

### 5. Custom Exception
**Decision:** Create `InvalidTreeException` for validation  
**Rationale:**
- Specific, descriptive error handling
- Separate tree errors from general exceptions
- Checked exception forces explicit handling
- Provides clear error messages to users

---

## 8. Key Learning Outcomes

### Data Structures
- **Complete Binary Tree:** Understanding properties and array representation
- **Tree Node Structure:** Implementing linked structure with left/right pointers
- **Tree Construction:** Converting array to linked structure using recursion

### Algorithms
- **Recursion:** Mastering recursive problem solving
- **Tree Traversals:** Implementing and understanding three standard orders
- **Base Cases:** Identifying and implementing proper termination conditions

### Java Programming
- **Nested Classes:** Using static nested class for TreeNode
- **Exception Handling:** Creating and throwing custom exceptions
- **Access Modifiers:** Choosing appropriate visibility levels
- **Method Overloading:** Public methods calling private recursive helpers

### Software Design
- **Separation of Concerns:** Clear division between construction and traversal
- **Encapsulation:** Hiding implementation details
- **Code Documentation:** Comprehensive Javadoc comments
- **Testing:** Systematic test case development

---

## 9. Challenges & Solutions

### Challenge 1: Understanding Index Calculations
**Problem:** Correctly mapping array indices to tree positions  
**Solution:** Drew diagrams showing parent-child relationships, verified with small examples

### Challenge 2: Recursive Termination
**Problem:** Ensuring all recursive methods have proper base cases  
**Solution:** Always check for null before processing node

### Challenge 3: Traversal Order
**Problem:** Remembering the correct order for each traversal type  
**Solution:** Created mnemonic:
- **PRE**order: Process **PRE**viously (root first)
- **IN**order: Process **IN** between (root in middle)
- **POST**order: Process af**POST**erward (root last)

---

## 10. Compilation & Execution

### Compile All Files
```bash
cd Project_3/project3_starter/project3_starter
javac Main.java CompleteBinaryTree.java InvalidTreeException.java
```

### Run Program
```bash
java Main
```

### Expected Output
```
Preorder: 90 70 20 40 50
Inorder: 20 70 40 90 50
Postorder: 20 40 70 50 90
```

---

## 11. Code Quality

### Naming Conventions
- ✅ Classes: PascalCase (CompleteBinaryTree, TreeNode)
- ✅ Methods: camelCase (preorder, inorder, makeNode)
- ✅ Variables: camelCase (root, left, right)
- ✅ Constants: UPPER_SNAKE_CASE (if any were used)

### Documentation
- ✅ Header comments with student name, project, date, description
- ✅ Javadoc comments for all public/protected methods
- ✅ Clear parameter and return value descriptions
- ✅ Exception documentation

### Code Organization
- ✅ Logical method ordering (constructors first, then public, then private)
- ✅ Consistent indentation and spacing
- ✅ Meaningful variable names
- ✅ No magic numbers or hard-coded values

---

## 12. Summary

This project successfully implements a complete binary tree data structure with three traversal methods. The implementation demonstrates:

### Technical Skills
- ✅ Tree construction from level-order array
- ✅ Recursive algorithm design
- ✅ Three standard traversal patterns
- ✅ Custom exception handling
- ✅ Nested class usage

### Software Engineering
- ✅ Clean code organization
- ✅ Comprehensive documentation
- ✅ Systematic testing approach
- ✅ Proper encapsulation
- ✅ Professional coding standards

### Understanding
- ✅ Complete binary tree properties
- ✅ Array-to-tree index mapping
- ✅ Recursive traversal algorithms
- ✅ Time and space complexity analysis
- ✅ Design pattern application

**Status:** Complete and ready for submission

---

## 13. Files Included

| File | Size | Description |
|------|------|-------------|
| CompleteBinaryTree.java | ~3.5 KB | Main implementation with all methods |
| InvalidTreeException.java | ~0.3 KB | Custom exception class |
| Main.java | ~0.5 KB | Test driver |
| README.md | ~8 KB | Project overview and usage |
| Project_Documentation.md | ~15 KB | Comprehensive documentation |

---

## 14. References

- Course textbook: Data Structures and Algorithms in Java
- CMSC 315 lecture notes on binary trees
- Java API Documentation for exception handling
- Algorithm visualization tools for traversal verification

---

**End of Documentation**

**Last Updated:** February 9, 2026  
**Student:** Stefan V. Nikolov  
**Course:** CMSC 315 – Data Structures and Analysis
