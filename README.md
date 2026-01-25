# CMSC 315: Data Structures and Analysis

## Overview
This repository contains coursework for CMSC 315 (Data Structures and Analysis) at UMGC. It includes multiple programming projects implementing fundamental data structures and algorithms in Java.

## Author
**Stefan V. Nikolov**

## Repository Structure
```
CMSC-315/
├── Project_1/                    # First programming assignment
│   ├── DelimiterChecker.java
│   ├── SourceFileReader.java
│   ├── README.md
│   └── Project_Documentation.md
│
├── Project_2/                    # Natural Language Processing Utilities
│   ├── Main.java
│   ├── NLPUtility.java
│   ├── Ready.zip                 # Submission package
│   ├── README.md
│   ├── Project2.pdf              # Project specification
│   └── Project_Documentation.md  # Additional documentation
│
└── README.md                      # This file
```

## Projects Overview

### Project 1: Delimiter Checker
A program that validates balanced delimiters (brackets, parentheses, braces) in source code files.

**Key Components:**
- `SourceFileReader.java` - Reads and processes source code files
- `DelimiterChecker.java` - Validates delimiter balance and nesting

**Features:**
- Handles multiple delimiter types
- Provides detailed error reporting
- Supports various file formats

### Project 2: Word Frequency & Sentiment Analysis
A Natural Language Processing utility that analyzes text for word frequency and sentiment.

**Key Components:**
- `Main.java` - User interface and I/O handling
- `NLPUtility.java` - Core NLP processing methods

**Features:**
- Text tokenization with whitespace and punctuation handling
- Word frequency analysis with stop word filtering
- Case-insensitive word processing
- Sentiment analysis (positive/negative word counting)
- Results sorting by frequency and alphabetical order
- Identification of most frequent words

**Methods Implemented:**
1. `splitTextIntoTokens()` - Tokenizes text into words
2. `countFilteredWords()` - Counts word frequencies excluding stop words
3. `sortByValueDescending()` - Sorts results by frequency (descending)
4. `getSentiment()` - Calculates sentiment scores
5. `getWordsWithMaxFrequency()` - Finds most frequent words

For detailed information, see [Project_2 README](Project_2/README.md)

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A terminal or command prompt

### Compilation
Navigate to the project folder and compile:

```bash
# For Project 1
cd Project_1
javac DelimiterChecker.java SourceFileReader.java

# For Project 2
cd Project_2
javac Main.java NLPUtility.java
```

### Execution

**Project 1:**
```bash
java DelimiterChecker <source_file>
```

**Project 2:**
```bash
java Main
# Then enter a paragraph of text when prompted
```

## Course Information
- **Course:** CMSC 315 - Data Structures and Analysis
- **University:** University of Maryland Global Campus (UMGC)
- **Semester:** Spring 2026
- **Instructor:** [Course Instructor]

## Technologies Used
- **Language:** Java
- **IDE:** Visual Studio Code
- **Version Control:** Git
- **Build Tool:** Standard Java Compiler (javac)

## Key Learning Outcomes
- Implementing and analyzing data structures
- Algorithm design and complexity analysis
- String processing and tokenization
- Collection framework usage (TreeMap, LinkedHashMap, HashSet)
- File I/O and processing
- Natural Language Processing basics
- Software design principles

## Notes for Future Projects
- Maintain clear project separation in individual folders
- Include comprehensive documentation for each project
- Package submissions as required (.zip files with source code only)
- Add header comments to all Java files with student name, project name, date, and description
- Test edge cases thoroughly before submission
- Keep the repository organized and clean

## Submission Checklist
- [ ] Source code compiles without errors
- [ ] Program runs without exceptions
- [ ] All methods implemented and tested
- [ ] Header comments added to all files
- [ ] README documentation complete
- [ ] Submission package (.zip) created with source files only
- [ ] Code follows Java naming conventions
- [ ] No external libraries used (unless specified)

## Contact & Support
For questions or issues related to these projects, please refer to the individual project README files and documentation.

---
**Last Updated:** January 26, 2026
