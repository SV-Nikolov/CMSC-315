# UML Class Diagram - CMSC 315 Project 2

## Class Diagram (Text Representation)

```
┌─────────────────────────────────────────────────────────────────────┐
│                            Main                                      │
├─────────────────────────────────────────────────────────────────────┤
│ - scanner: Scanner                                                  │
│ - stopWords: Set<String>                                            │
│ - positiveWords: Set<String>                                        │
│ - negativeWords: Set<String>                                        │
├─────────────────────────────────────────────────────────────────────┤
│ + main(args: String[]): void                                        │
│                                                                     │
│ Responsibilities:                                                   │
│ • Handles user input/output                                         │
│ • Calls NLPUtility methods                                          │
│ • Displays results to console                                       │
│ • Manages word sets (stopWords, positiveWords, negativeWords)      │
└─────────────────────────────────────────────────────────────────────┘
                              ▲
                              │ uses
                              │
┌─────────────────────────────────────────────────────────────────────┐
│                        NLPUtility                                    │
├─────────────────────────────────────────────────────────────────────┤
│ Static Methods:                                                     │
├─────────────────────────────────────────────────────────────────────┤
│ + splitTextIntoTokens(text: String): String[]                      │
│   └─ Splits text on whitespace and punctuation using regex         │
│      Pattern: [\s\p{P}]+                                           │
│                                                                     │
│ + countFilteredWords(words: String[],                              │
│                      stopWords: Set<String>):                       │
│                      TreeMap<String, Integer>                       │
│   └─ Counts word frequencies (case-insensitive)                    │
│   └─ Excludes stop words                                            │
│   └─ Returns TreeMap sorted alphabetically                          │
│                                                                     │
│ + sortByValueDescending(map: Map<String, Integer>):                │
│                         LinkedHashMap<String, Integer>              │
│   └─ Sorts entries by frequency in descending order                 │
│   └─ Preserves order for equal frequencies                          │
│   └─ Returns LinkedHashMap to maintain sort order                   │
│                                                                     │
│ + getSentiment(wordMap: Map<String, Integer>,                      │
│                positiveWords: Set<String>,                          │
│                negativeWords: Set<String>): String                  │
│   └─ Sums frequencies of positive words                             │
│   └─ Sums frequencies of negative words                             │
│   └─ Returns formatted string: "Positive: X, Negative: Y"          │
│                                                                     │
│ + getWordsWithMaxFrequency(wordMap: Map<String, Integer>):         │
│                             Map<String, Object>                     │
│   └─ Finds maximum frequency value                                  │
│   └─ Collects all words with max frequency                          │
│   └─ Sorts words alphabetically                                     │
│   └─ Returns Map with "words" and "frequency" keys                  │
├─────────────────────────────────────────────────────────────────────┤
│ Responsibilities:                                                   │
│ • Text tokenization and processing                                  │
│ • Word frequency analysis                                           │
│ • Collection sorting and manipulation                               │
│ • Sentiment analysis                                                │
│ • Data processing and transformation                                │
└─────────────────────────────────────────────────────────────────────┘
```

## Detailed Method Signatures

### Class: Main
```java
public class Main {
    public static void main(String[] args)
}
```

### Class: NLPUtility
```java
public class NLPUtility {
    
    // Task 1: Tokenization
    public static String[] splitTextIntoTokens(String text)
    
    // Task 2: Frequency Counting
    public static TreeMap<String, Integer> countFilteredWords(
        String[] words, 
        Set<String> stopWords
    )
    
    // Task 3: Sorting by Value
    public static LinkedHashMap<String, Integer> sortByValueDescending(
        Map<String, Integer> map
    )
    
    // Task 4: Sentiment Analysis
    public static String getSentiment(
        Map<String, Integer> wordMap,
        Set<String> positiveWords,
        Set<String> negativeWords
    )
    
    // Task 5: Max Frequency Finder
    public static Map<String, Object> getWordsWithMaxFrequency(
        Map<String, Integer> wordMap
    )
}
```

## Class Relationships

### Association: Main → NLPUtility
- **Type:** Dependency (uses)
- **Multiplicity:** Main uses NLPUtility static methods
- **Description:** Main class calls all five static methods from NLPUtility to process user input

### Data Flow Diagram

```
User Input
    ↓
Main.main()
    ↓
splitTextIntoTokens(text)
    ├─→ returns String[] tokens
    ↓
countFilteredWords(tokens, stopWords)
    ├─→ returns TreeMap<String, Integer>
    ↓
    ├─ Display word map sorted by key
    ↓
sortByValueDescending(wordMap)
    ├─→ returns LinkedHashMap<String, Integer>
    ↓
    ├─ Display word map sorted by value
    ↓
getSentiment(wordMap, positiveWords, negativeWords)
    ├─→ returns String "Positive: X, Negative: Y"
    ↓
    ├─ Display sentiment
    ↓
getWordsWithMaxFrequency(wordMap)
    ├─→ returns Map<String, Object> {words=[...], frequency=N}
    ↓
    └─ Display most frequent words
```

## Collections Used

| Method | Input Collection | Output Collection | Purpose |
|--------|------------------|-------------------|---------|
| splitTextIntoTokens | String | String[] | Tokenization |
| countFilteredWords | String[] + Set | TreeMap | Counting & alphabetical sort |
| sortByValueDescending | Map | LinkedHashMap | Frequency-based sort |
| getSentiment | Map + Set + Set | String | Analysis & formatting |
| getWordsWithMaxFrequency | Map | Map<String, Object> | Finding max frequency |

## Design Patterns Used

1. **Utility Class Pattern**: NLPUtility contains only static methods, serving as a utility/helper class
2. **Data Transformation Pattern**: Each method transforms data from one form to another
3. **Filtering Pattern**: countFilteredWords filters input based on stop words
4. **Sorting Pattern**: sortByValueDescending and getWordsWithMaxFrequency sort results

## Key Interfaces & Collections

- `Set<String>`: Used for stop words, positive words, negative words (unordered, no duplicates)
- `TreeMap<String, Integer>`: Used for alphabetical sorting of word counts
- `LinkedHashMap<String, Integer>`: Used to maintain insertion order after sorting by value
- `Map<String, Object>`: Used for heterogeneous return type in getWordsWithMaxFrequency

---
**Created:** January 26, 2026  
**Student:** Stefan V. Nikolov  
**Course:** CMSC 315 - Data Structures and Analysis
