# CMSC 315 Project 2: Word Frequency & Sentiment Analysis

## Overview
This project implements a Natural Language Processing (NLP) utility in Java that analyzes text for word frequency, filters common words, and performs sentiment analysis. The program tokenizes input text, counts word frequencies while excluding stop words, sorts results, and evaluates the sentiment based on positive and negative word lists.

## Author
**Stefan V. Nikolov**

## Date
January 26, 2026

## Project Requirements
- Analyze paragraph of text entered by user
- Tokenize text into individual words
- Count word frequencies (case-insensitive)
- Filter out common stop words
- Sort results by frequency and alphabetically
- Perform sentiment analysis on text
- Identify most frequently occurring words

## Files Included
- `Main.java` - Driver class that handles user input/output (fully implemented, do not modify)
- `NLPUtility.java` - Utility class containing all NLP processing methods

## Compilation and Execution

### Compile:
```bash
javac Main.java NLPUtility.java
```

### Run:
```bash
java Main
```

## Methods Implemented

### 1. `splitTextIntoTokens(String text)`
Splits input text into word tokens using whitespace and punctuation as delimiters.

**Regex Pattern:** `[\s\p{P}]+`

**Example:**
```java
String[] tokens = NLPUtility.splitTextIntoTokens("WOW!?! That .?# is amazing!");
// Result: [WOW, That, is, amazing]
```

### 2. `countFilteredWords(String[] words, Set<String> stopWords)`
Counts word frequencies while excluding stop words (case-insensitive).
Returns a TreeMap sorted alphabetically by word.

**Example:**
```java
String[] words = {"i", "love", "a", "good", "BOOK", "and", "LOVE"};
Set<String> stopWords = new HashSet<>(Arrays.asList("the", "is", "a", "and"));
Map<String, Integer> result = NLPUtility.countFilteredWords(words, stopWords);
// Result: {book=1, good=1, i=1, love=2}
```

### 3. `sortByValueDescending(Map<String, Integer> map)`
Sorts word-frequency pairs by frequency in descending order.
Returns a LinkedHashMap to preserve insertion order.

**Example:**
```java
Map<String, Integer> sorted = NLPUtility.sortByValueDescending(wordMap);
// Input:  {book=3, good=1, love=2}
// Output: {book=3, love=2, good=1}
```

### 4. `getSentiment(Map<String, Integer> wordMap, Set<String> positiveWords, Set<String> negativeWords)`
Calculates sentiment scores by summing frequencies of positive and negative words.
Returns formatted string: "Positive: X, Negative: Y"

**Example:**
```java
String sentiment = NLPUtility.getSentiment(wordMap, positiveWords, negativeWords);
// Result: "Positive: 3, Negative: 1"
```

### 5. `getWordsWithMaxFrequency(Map<String, Integer> wordMap)`
Identifies all words with the highest frequency and sorts them alphabetically.
Returns a Map<String, Object> with two entries:
- "words": List<String> of most frequent words
- "frequency": Integer maximum frequency

**Example:**
```java
Map<String, Object> maxFreq = NLPUtility.getWordsWithMaxFrequency(wordMap);
// Result: {words=[book, love], frequency=3}
```

## Sample Program Flow

### Input:
```
I really love a good book, and You REALLY love a sad movie. We both ReAllY LOVE going for a walk!
```

### Output:
```
Tokenized:
[I, really, love, a, good, book, and, You, REALLY, love, a, sad, movie, We, both, ReAllY, LOVE, going, for, a, walk]

Word map sorted by key ascending:
book:1
both:1
for:1
going:1
good:1
i:1
love:3
movie:1
really:3
sad:1
walk:1
we:1
you:1

Word map sorted by value descending:
love:3
really:3
book:1
both:1
for:1
going:1
good:1
i:1
movie:1
sad:1
walk:1
we:1
you:1

Sentiment: Positive: 4, Negative: 1

Most frequent word(s): [love, really] (used 3 times)
```

### Empty Input Example:
```
Input: SO is.! It????

Tokenized:
[SO, is, It]

No valid words found.
```

## Predefined Word Sets
The following sets are defined in `Main.java` and used for analysis:

**Stop Words:** the, is, in, at, of, and, a, to, it, or, was, so

**Positive Words:** good, great, happy, love, like

**Negative Words:** bad, terrible, horrible, sad, hate

## Key Design Decisions
- Case-insensitive word matching while preserving original casing in tokenization
- TreeMap used for alphabetical sorting
- LinkedHashMap used to maintain frequency sort order
- Handles empty results gracefully without throwing exceptions
- No external libraries used - only Java standard library

## Edge Cases Handled
- Empty or whitespace-only input
- Input with only stop words
- Multiple words with same maximum frequency
- Mixed case words (converted to lowercase for comparison)
- Punctuation and special characters (used as delimiters)

## Submission
This project has been packaged in `Ready.zip` containing only the source code files:
- Main.java
- NLPUtility.java
