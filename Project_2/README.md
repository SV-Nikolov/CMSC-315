# CMSC 315 Project 2 - Word Frequency & Sentiment Analysis

## Overview
A Java console program that tokenizes user-entered text, counts word frequencies (excluding stop words), sorts results, performs sentiment analysis, and reports the most frequent words.

## Files
- Main.java — handles user input/output and orchestrates processing
- NLPUtility.java — static NLP methods (tokenization, counting, sorting, sentiment, max-frequency)
- NLPUtilityTest.java — unit tests covering all five utility methods
- Project_Documentation.md — detailed design, tests, and examples

## Build & Run
```bash
javac Main.java NLPUtility.java
java Main
```

## Run Tests
```bash
javac Main.java NLPUtility.java NLPUtilityTest.java
java NLPUtilityTest
```

## Notes
- Tokenization regex: `[\s\p{P}]+` (whitespace or punctuation as delimiters)
- Word counting and sentiment are case-insensitive
- Uses TreeMap for alphabetical ordering and LinkedHashMap for value-descending order
