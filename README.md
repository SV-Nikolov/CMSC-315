CMSC 315 – Project 1
Java Delimiter Matching Program
Plain Text README

PROJECT OVERVIEW

This project implements a Java source code delimiter checker.
The program reads a Java source file and determines whether all delimiters are properly matched.

The delimiters that must be checked are:

Parentheses: ( )

Braces: { }

Square brackets: [ ]

The program must ignore any delimiters that appear inside:

Single-line comments (//)

Block comments (/* ... */)

String literals ("...")

Character literals ('a')

The project demonstrates the use of stacks, file encapsulation, and character-by-character parsing as required by CMSC 315 – Data Structures and Analysis.

FUNCTIONAL REQUIREMENTS

Prompt the user for a Java source file name.

If the file does not exist, continue prompting until a valid file is entered.

Read the file one character at a time.

Exclude all characters inside comments, string literals, and character literals.

Detect delimiter errors:

Unmatched right delimiter

Mismatched delimiter pair

Unmatched left delimiter remaining at end of file

Report the delimiter involved and the exact line and character position.

Confirm success if all delimiters match correctly.

PROGRAM STRUCTURE

The program consists of exactly two classes.

CLASS 1: SourceFileReader.java

Purpose:
Encapsulates all logic related to reading and filtering characters from a Java source file.

Responsibilities:

Open and validate the source file

Read characters sequentially

Track current line number and character number

Skip characters inside:

Line comments

Block comments

String literals

Character literals

Required Public Methods:

SourceFileReader(String filename) throws FileNotFoundException

Constructor that opens the file

Throws FileNotFoundException if the file does not exist

Character getNextChar()

Returns the next valid character from the file

Skips excluded characters

Returns null when the end of the file is reached

String getPosition()

Returns a formatted string indicating:

Current line number

Current character number

Internal State Tracking:

Line number

Character number

Current line buffer

Index within the current line

Boolean flags:

inBlockComment

inLineComment

inString

inChar

CLASS 2: DelimiterChecker.java

Purpose:
Main driver class that checks delimiter matching using a stack.

Responsibilities:

Prompt the user for input

Create a SourceFileReader object

Process characters until end of file or error

Use Java’s predefined Stack class

Algorithm:

Read characters one at a time from SourceFileReader

If a left delimiter is encountered:

Push it onto the stack

If a right delimiter is encountered:

If the stack is empty, report an unmatched delimiter error

Otherwise, pop the stack and verify matching delimiters

After reaching the end of the file:

If the stack is not empty, report an unmatched left delimiter

Otherwise, report successful matching

DELIMITER MATCHING RULES

Valid delimiter pairs:
( matches )
{ matches }
[ matches ]

Errors occur when:

A right delimiter appears without a corresponding left delimiter

A closing delimiter does not match the most recent opening delimiter

A left delimiter remains unmatched at the end of the file

PROJECT FILE STRUCTURE

project-root
|
|-- SourceFileReader.java
|-- DelimiterChecker.java
|-- README.txt

Notes:

No package declaration is required

Each public class must be in its own .java file

SAMPLE OUTPUT

Successful file:
All delimiters match correctly.

Mismatched delimiter:
Mismatched delimiters '{' and ')' at Line 14, Character 22

Unmatched right delimiter:
Unmatched delimiter ']' at Line 7, Character 9

Unmatched left delimiter at end of file:
Unmatched delimiter '(' at end of file.

COMPILATION AND EXECUTION

Compile:
javac SourceFileReader.java DelimiterChecker.java

Run:
java DelimiterChecker

CODING CONSTRAINTS

Use only Java’s predefined Stack class

Do not use regular expressions for parsing

Do not load the entire file into memory

Read input incrementally

No third-party libraries allowed

HEADER COMMENT REQUIREMENT

Each .java file must include a comment block at the top containing:

Student name

Project name

Date

Short description of the class

DESIGN PHILOSOPHY

Clear separation of responsibilities

Defensive and state-driven parsing

Stack-based delimiter validation

Readable and maintainable logic

PROJECT COMPLETION CRITERIA

The project is complete when:

All delimiters are correctly validated

Comments and literals are properly ignored

Errors are reported with accurate positions

The program follows all CMSC 315 requirements exactly

END OF README
