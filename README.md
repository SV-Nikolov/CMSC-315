# Java Delimiter Matching Program
**CMSC 315 – Project 1**

## Overview
A stack-based delimiter checker that validates matching parentheses `()`, braces `{}`, and brackets `[]` in Java source files while correctly ignoring delimiters inside comments and string/character literals.

## Features
- ✅ Validates delimiter matching with precise error reporting
- ✅ Ignores delimiters in comments (`//` and `/* */`)
- ✅ Ignores delimiters in string literals (`"..."`) and character literals (`'a'`)
- ✅ Reports exact line and character positions of errors
- ✅ Character-by-character file processing

## Project Structure

### `SourceFileReader.java`
Handles file reading and character filtering.

**Public Methods:**
- `SourceFileReader(String filename)` - Opens the file (throws `FileNotFoundException`)
- `Character getNextChar()` - Returns next valid character, skipping comments/literals
- `String getPosition()` - Returns current "Line X, Character Y" position

**State Tracking:**
- Line and character numbers
- Boolean flags: `inBlockComment`, `inLineComment`, `inString`, `inChar`

### `DelimiterChecker.java`
Main driver that validates delimiters using a stack.

**Algorithm:**
1. Prompt user for filename (retry until valid)
2. Read characters via `SourceFileReader`
3. Push left delimiters `(`, `{`, `[` onto stack
4. For right delimiters `)`, `}`, `]`:
   - If stack empty → **Error: Unmatched right delimiter**
   - If top doesn't match → **Error: Mismatched pair**
   - Otherwise → Pop and continue
5. If stack not empty at EOF → **Error: Unmatched left delimiter**

## Usage

**Compile:**
```bash
javac SourceFileReader.java DelimiterChecker.java
```

**Run:**
```bash
java DelimiterChecker
```

**Example Output:**
```
All delimiters match correctly.
```
```
Mismatched delimiters '{' and ')' at Line 14, Character 22
```
```
Unmatched delimiter ']' at Line 7, Character 9
```
```
Unmatched delimiter '(' at end of file.
```

## Requirements & Constraints
- ✅ Use Java's built-in `Stack` class
- ✅ Character-by-character processing (no regex)
- ✅ No third-party libraries
- ✅ Each class in separate `.java` file
- ✅ Header comments with student name, project, date, description
