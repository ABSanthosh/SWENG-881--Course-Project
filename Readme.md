# Course Project

## Authors: 
- Santhosh Anitha Boominathan
- Adam Slager

### Date: April 27, 2024

# Table of Contents

- [1. Introduction](#1-introduction)
  - [1.1 Test Project Name](#11-test-project-name)
  - [1.2 Summary of the Rest of the Test Plan](#12-summary-of-the-rest-of-the-test-plan)
- [2. Feature Description](#2-feature-description)
- [3. Assumptions](#3-assumptions)
  - [3.1 Test Case Exclusions](#31-test-case-exclusions)
  - [3.2 Test Tools, Formats, and Organizational Scheme](#32-test-tools-formats-and-organizational-scheme)
- [4. Test Approach](#4-test-approach)
  - [4.1 Addressing Past Issues](#41-addressing-past-issues)
  - [4.2 Special Testing Considerations](#42-special-testing-considerations)
  - [4.3 Test Strategy](#43-test-strategy)
  - [4.4 Test Categories](#44-test-categories)
- [5. Test Cases](#5-test-cases)
  - [5.1 Test Group Definition](#51-test-group-definition)
  - [5.2 Test Cases](#52-test-cases)
    - [5.2.1 Input Domain Modeling Test Cases](#521-input-domain-modeling-test-cases)
      - [5.2.1.1 CSVWriter IDM Test Cases](#5211-csvwriter-idm-test-cases)
      - [5.2.1.2 CSVReader IDM Test Cases](#5212-csvreader-idm-test-cases)
    - [5.2.2 Graph Based Test Cases](#522-graph-based-test-cases)
      - [5.2.2.1 CsvReader Graph Based Test Cases](#5221-csvreader-graph-based-test-cases)
      - [5.2.2.2 CsvWriter Graph Based Test Cases](#5222-csvwriter-graph-based-test-cases)
    - [5.2.3 Exploratory Test Cases](#523-exploratory-test-cases)
      - [5.2.3.1 Back Alley Tour - Part I](#5231-back-alley-tour---part-i)
      - [5.2.3.2 Back Alley Tour - Part II](#5232-back-alley-tour---part-ii)
      - [5.2.3.3 Collector's Tour Report](#5233-collectors-tour-report)
      - [5.2.3.4 FedEx Tour Report](#5234-fedex-tour-report)
      - [5.2.3.5 Intellectual Tour](#5235-intellectual-tour)
      - [5.2.3.6 Landmark Tour](#5236-landmark-tour)
  - [5.3 Traceability Matrix](#53-traceability-matrix)
- [6. Test Environment](#6-test-environment)
  - [6.1 Multiple Test Environments](#61-multiple-test-environments)
  - [6.2 Schematic Diagram](#62-schematic-diagram)
  - [6.3 Test Architecture Overview](#63-test-architecture-overview)
  - [6.4 Equipment Table](#64-equipment-table)
- [7. Testing Results](#7-testing-results)
- [8. Recommendations on Software Quality](#8-recommendations-on-software-quality)

# 1. Introduction

## 1.1 Test Project Name

FastCSV

## 1.2 Summary of the Rest of the Test Plan

[Provide a brief summary of the remaining sections of the testing report.]

The following is a brief description of the remaining portions of the testing report.
- Feature Description: This section provides a high-level overview of the primary features of FastCSV.  The purpose of this section is to educate the reader on the software and to provide a detailed description of the team’s assessment of primary functionality.  This section serves both as an introduction to the software and as a reference for the testing  described in the remainder of the document.
- Assumptions: This section serves two primary purposes. Firstly, it lays out the team’s reasoning for excluding certain features and requirements of the software from the testing plan. This section can be crucial, as it serves to memorialize the logic around the exclusions and to provide the reader with a roadmap of any additional testing that might be required to get comfort over their particular usage of the software.  Secondly, it describes the tools utilized during the testing and details pertinent organization structures utilized to describe the testing in the remainder of the document. 
- Test Approach: This primary purpose of this section is to document the team’s test strategy and the reasoning behind choosing these approaches, including any special situations that might fall outside of the general approach. This section also lays out any past issues that are pertinent to our testing, which in the case of an open-source application like FastCSV are generally well-documented.  Finally, this section outlines the categories of tests cases for the software,  including categories for certain non-functional requirements of the software. 
- Test Cases: This section serves to categorize and document the specific tests that were performed.  It also details the organization structure of the test cases and provides a traceability matrix to tie the tests into the specific software requirements.
- Test Environment: The purpose of this section is to describe the specifics around the test environment, both in terms of the hardware used in testing and the organization of the software environment.  A primary purpose of this section is to enhance the reproducibility of the tests by specifying this aforementioned information.
- Testing Results: This section serves as a running log of the results of each test performed, including their status and any observations made during the testing.  
- Recommendations on Software Quality: This section provides the team’s assessment of the quality of the software along with any recommendations for potential improvements.

# 2. Feature Description

[Describe the features of the selected open-source course application.]

Per the official FastCSV website, “FastCSV is a high-performance CSV parser and writer for Java.”  The functionality of the system can be logically bifurcated into those features belonging to each of the CsvReader and CsvWriter classes.  The website and related documentation outline the features and design goals of the software; however, based on the team’s exploration of the documentation and our own use of the software, the team believes that the following are the primary features of these classes.
-	CsvReader
    -	Ability to read standard, comma separated values from a file
    -	Ability to configure the reading of the values via the following options
        -	Field Separator  - A field separator denotes the character used to logically delineate between the fields of data in the file.  While the program defaults to utilizing commas, it provides the user with the option to select another field separator.  For example, a user may wish to read in a file that is separated by semi-colons instead. 
        - Quote Character – Using quote characters allows the parser to treat the data between the quote characters as a single field, even if it contains a field separator.  For example, the user might want to read in a CSV field, which contains commas.  In this case, the program defaults to allowing the user to surround that field with quotes, such as “ice cream, cake, and candy.”  In this case, that phrase would be read in as a single field, excluding the quotation marks.  In some instances, though, the user may wish to designate a different character to represent quotation marks, such as when the fields in a file contain lots of quotes.  The user may therefore specify another character to use, such as a percentage sign.  In this case %”ice cream, cake, and candy”% would be read in as a single field, including the quotation marks.
        -	Different Field Counts – The program allows the user to set a configuration option that either enforces or does not enforce adherence to consistent field counts.  For example, a csv file may have a first row with two columns (fields) and a second row with 3 columns.  Depending on the user’s requirements, they may wish for the program to read this with no issue or to throw an exception.  The program defaults to not enforcing this adherence, meaning that the CSV files may have varying numbers of columns per row.  
        - BOM Headers – Certain programs, such as Microsoft Excel, generate CSV files that begin with a BOM (Byte Order Mark) header.  Per the FastCSV website, the purpose of a BOM Header was originally to designate the encoding of the file, although now it is largely unnecessary, as almost all CSV files utilize UTF-8.  The program provides the user with functionality to either detect (and ignore) the BOM header, which is useful if the file has a BOM header and the user does not want this to be included in their data, or to not detect the BOM header, which is the default behavior.  
        -	Comments – Certain CSV files may contain comments, which are often designated by a character, such as ‘#.’  FastCSV allows users to customize how the program handles these comments, by selecting a custom comment designator and by specifying whether comments should be skipped by the reader or read in as a field.  For example, suppose the user processes a CSV file that has multiple comments that begin with '!' and the user does not want these comments to appear in their parsed data.  The user can specify that any lines beginning with '!' should be skipped.  They would designate the comment character and the comment behavior separately.  The program defaults to '#' as the comment character and not skipping comments as the behavior.
        -	Empty Lines – Certain CSV files may contain empty lines. By default, the program is configured to skip these empty lines (i.e. not read them in).  Alternatively, if the user wishes to read these empty lines in as blank fields, the user can configure the program to do so. 
    -	Ability to handle the following less common situations:
        -	Indexing CSV files – Provides the user with the option to read a large CSV file, while designating how many records belong on each “page” of the file.  Once the file is initially parsed, the user can quickly access any individual page of the file without having to parse the file again.  This can save substantial amounts of time if the user needs to access a certain page (for instance, paginated web data).  
        -	Field Modification – Provides the user with the ability to modify fields as they are being read.  A common use case for this is trimming or stripping leading or trailing blank characters from a field, if applicable.
        -	Reading Compressed CSV files – The program allows the user to read CSV files that were compressed using the gzip format.  The program handles the extraction process and then reads the files in as if they were not compressed.
        -	Ability to Automatically Map to Java Beans – Allows the user to configure the program to map the CSV data that is read directly into Java Beans, with minimal performance penalty.

[CSVWriter functionality to be explained here in a similar fashion to CSVReader]

# 3. Assumptions

## 3.1 Test Case Exclusions

[List any test cases or scenarios that are excluded from testing.]

## 3.2 Test Tools, Formats, and Organizational Scheme

[List the testing tools, formats, and organizational schemes used.]

# 4. Test Approach

## 4.1 Addressing Past Issues

[Explain how past issues or defects were addressed if applicable.]

## 4.2 Special Testing Considerations

[Highlight any special considerations for testing.]

## 4.3 Test Strategy

[Explain which testing techniques are used to test the different parts of the systems. Provide a rationale for the selection. Also include information on which tools, automation, and scripts are used to test each part of the system.]

There are two primary parts of FastCSV: CSVReader and CSVWriter. Both parts have deep functionality, with multiple uses and customization options (as described previously in Section 2).  The team decided to apply various testing strategies across both parts of the software, to diversify testing and maximize coverage, while strategically targeting the most common uses of the software.  

CSV files are commonly used as an application-agnostic format to transfer information between spreadsheets, from spreadsheets to other applications, or between non-spreadsheet applications. One of the authors has extensive experience in the finance industry and has seen CSV files used to transfer information between spreadsheets and general ledger, banking, and specialty subledger systems. These files are overwhelmingly formatted with a consistent number of columns between rows, with values separated by commas, and with no extraneous comment or blank rows. When designing and planning our testing approach, the team decided to focus predominantly on this most common usage, while also ensuring that edge case CSV format and program functionality was covered.

The following are the testing techniques that were utilized, along with a description of how the testing was performed:

**Input Domain Modeling**

[Description, Tools, and Rationale]

Per the Canvas module, Input Domain Modeling involves partitioning each parameter of a function into blocks of logically related input values and then testing a value from each of the blocks.  In the case of CSVReader and CSVWriter, the a selection of input variables were chosen as the target parameters that were then partioned and tested. More about the variables, the chosen coverage criteria, and the selected tests can be found in Section 5.2 below.

Input Domain Modeling was chosen to test CSVReader and CSVWriter methods for several practical reasons:
- Using Input Domain Modeling on these higher-level components provides the team with an opportunity to understand the major functionality of FastCSV early in the testing process.  This understanding will assist in designing subsequent tests.
- Since FastCSV is already in production and the primary functionality is fully operational, testing at this level is a practical option at this early stage in the testing process.  If this were an early sprint in an Agile project, this level of testing might not be available, since the functionality would not be fully developed.
- The components, and in particular the csv files themselves, are good targets for Input Domain Testing.  The input domain of the csv files is practically infinite, but those possibilities can be logically partitioned into blocks representing both common use cases and edge cases. This allowed the team to develop a manageable framework for testing a large input space.

JUnit was utilized to test both CSVReader and CSVWriter.

**Graph Based Testing**

[Description, Tools, and Rationale]

The team utilzed Control Flow Graphs (CFGs) for our graph based testing. CFGs are used to abstract the flow of a section code into a graph in order design tests based on that abstraction. CFGs provide a visual representation of often complex functionality. Once the graph is created, it allows for generating test cases that ensure coverage of a the nodes and edges of the CFG in varying combinations of traversal. The team selected edge coverage for our criteria, as, per the Canvas module, it ensures "that each edge is traversed at least once."

Two components were chosen to test using Graph Based Testing: **`writeRecord(String... values)` method** and **`skipLines(final Predicate<String> predicate, final int maxLines)` method**. These methods were chosen as candidates for Graph Based Testing, due to their numerous branching decisions and the coverage of those branches that Graph Based Testing offers.

JUnit was utilized to test both methods. [PlantUML](https://plantuml.com) was used to design the CFGs. The [Graph Coverage web application](https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage) was utilized to further abstract the CFG into a standardized number node format.

**Exploratory Testing**

[Description, Tools, and Rationale]

**Acceptance Testing**

[Description, Tools, and Rationale]

## 4.4 Test Categories

[Categorize the test cases, e.g., functional, performance, security, etc.]

# 5. Test Cases

## 5.1 Test Group Definition

[This is where you will define how the test cases will be structured and organized. Define test groups and subgroups for organizing test cases. Specify the objective for each group.]

## 5.2 Test Cases

[List all test cases. Or provide a link to the test cases. Ensure that the link you provide is accessible to the instructor.]

### 5.2.1 Input Domain Modeling Test Cases

#### 5.2.1.1 CSVWriter IDM Test Cases

**Function Under Test: `writeRecord(String...)` in `CsvWriter`**

This function takes a variable-length array of strings and writes them as a single CSV record, applying quoting and escaping rules depending on the provided configuration.

**List of Input Variables**

| Input Variable   | Description                                          | Default |
| ---------------- | ---------------------------------------------------- | ------- |
| `values`         | An array of strings to be written as one CSV record. | -       |
| `fieldSeparator` | A `char` defining the separator between fields.      | `,`     |
| `quoteCharacter` | A `char` used to enclose fields.                     | `"`     |
| `quoteStrategy`  | A `QuoteStrategy` object defining quoting behavior.  | `null`  |
| `lineDelimiter`  | A `LineDelimiter` enum value for line endings.       | `CRLF`  |

**Characteristics of Input Variables**

| Input Variable   | Type            | Constraints                                                              |
| ---------------- | --------------- | ------------------------------------------------------------------------ |
| `values`         | `String[]`      | May include `null`, empty, normal, or special character strings          |
| `fieldSeparator` | `char`          | Must not be a newline character; must differ from quote and comment char |
| `quoteCharacter` | `char`          | Must not be a newline character; must differ from field and comment char |
| `quoteStrategy`  | `QuoteStrategy` | Can be `null` or an implementation enforcing quoting rules               |
| `lineDelimiter`  | `LineDelimiter` | Must be one of the enum values: `CR`, `LF`, `CRLF`, or `PLATFORM`        |

**Partitioning Characteristics into Blocks**

| Input Variable   | Blocks                                    | Values                                              |
| ---------------- | ----------------------------------------- | --------------------------------------------------- |
| `values`         | block-a1: `null` values                   | `[null]`                                            |
|                  | block-a2: Empty strings                   | `[""]`                                              |
|                  | block-a3: Normal strings                  | `["hello", "world"]`                                |
|                  | block-a4: Strings with special characters | `["comma, separated", "\"quoted\"", "newline\r\n"]` |
| `fieldSeparator` | block-b1: Default separator               | `','`                                               |
|                  | block-b2: Alternative separator           | `';'`                                               |
| `quoteCharacter` | block-c1: Default quote                   | `'"'`                                               |
|                  | block-c2: Alternative quote               | `'\''` (single quote)                               |
| `quoteStrategy`  | block-d1: `null`                          | `null`                                              |
|                  | block-d2: Custom strategy                 | `QuoteStrategies.ALWAYS`                            |
| `lineDelimiter`  | block-e1: `CRLF`                          | `LineDelimiter.CRLF`                                |
|                  | block-e2: `LF`                            | `LineDelimiter.LF`                                  |

**Coverage Criteria**

We use **Each-Choice Coverage** to ensure every block of each input variable is represented in at least one test case.

**Test Descriptions**

Each test row (idm-w-01, idm-w-02, ...) targets specific block combinations of input variables. The values column in each test shows the concrete inputs used for that scenario.

| Test #   | Purpose Description                                                                          |
| -------- | -------------------------------------------------------------------------------------------- |
| idm-w-01 | Baseline test for normal values with all default settings.                                   |
| idm-w-02 | Verify handling of `null` values with a strategy that forces quoting.                        |
| idm-w-03 | Ensure empty strings are correctly quoted under a forced quote strategy.                     |
| idm-w-04 | Validate escaping of special characters like comma, quotes, and newlines.                    |
| idm-w-05 | Test alternate field separator (`;`) with normal string values.                              |
| idm-w-06 | Use a non-default quote character (`'`) and ensure output is consistent.                     |
| idm-w-07 | Apply a quoting strategy that forces quotes on non-empty fields.                             |
| idm-w-08 | Change line delimiter to `LF` and test output of a value with an embedded newline character. |

**Test Set Definition**

| Test #   | `values`                             | `fieldSeparator` | `quoteCharacter` | `quoteStrategy`          | `lineDelimiter` | Covered Blocks     |
| -------- | ------------------------------------ | ---------------- | ---------------- | ------------------------ | --------------- | ------------------ |
| idm-w-01 | `["hello", "world"]`                 | `','`            | `'"'`            | `null`                   | `CRLF`          | a3, b1, c1, d1, e1 |
| idm-w-02 | `[null, null]`                       | `','`            | `'"'`            | `QuoteStrategies.ALWAYS` | `CRLF`          | a1, b1, c1, d2, e1 |
| idm-w-03 | `[""]`                               | `','`            | `'"'`            | `QuoteStrategies.ALWAYS` | `CRLF`          | a2, b1, c1, d2, e1 |
| idm-w-04 | `["comma, separated", "\"quoted\""]` | `','`            | `'"'`            | `null`                   | `CRLF`          | a4, b1, c1, d1, e1 |
| idm-w-05 | `["a", "b"]`                         | `';'`            | `'"'`            | `null`                   | `CRLF`          | a3, b2, c1, d1, e1 |
| idm-w-06 | `["x", "y"]`                         | `','`            | `'\''`           | `null`                   | `CRLF`          | a3, b1, c2, d1, e1 |
| idm-w-07 | `["quoted"]`                         | `','`            | `'"'`            | `QuoteStrategies.ALWAYS` | `CRLF`          | a3, b1, c1, d2, e1 |
| idm-w-08 | `["newline\ntext"]`                  | `','`            | `'"'`            | `null`                   | `LF`            | a4, b1, c1, d1, e2 |

The code for the tests can be found in [TestWriterGraph.java](InputDomainModeling/Tests/TestWriterIDM.java)

#### 5.2.1.2 CSVReader IDM Test Cases

**Selected Functions/Features for Input Domain Modeling**  
For the `CsvReader` component, the team selected the `ofCsvRecord(Path file)` method for testing with input domain modeling. This method is a high-level entry point for reading CSV files, leveraging the `CsvReaderBuilder` and `CsvParser` to process file input into `CsvRecord` objects. It’s a suitable choice as it encapsulates the full reading process and allows testing of file-based input handling.

**List of Input Variables**

- `file`: A `Path` object representing the CSV file to read.
- `fieldSeparator`: A `char` defining the separator between fields (default: `,`).
- `quoteCharacter`: A `char` used to enclose fields (default: `"`).
- `commentStrategy`: A `CommentStrategy` enum determining comment handling (default: `NONE`).
- `commentCharacter`: A `char` denoting the beginning of a comment (default `#`).
- `ignoreDifferentFieldCount`: A `boolean` controlling wherther to ignore malformed files (default: `true`).
- `skipEmptyLines`: A `boolean` controlling whether empty lines are skipped (default: `true`).
- `detectBomHeader`: A `boolean` controlling whether to detect and skip Bom header in csv file (default: `false`)

**Characteristics of Input Variables**

| Input Variable             | Type                   | Constraints                                                                        |
| -------------------------- | ---------------------- | ---------------------------------------------------------------------------------- |
| `file`                     | `Path`                 | Must point to a readable file; content can vary                                    |
| `fieldSeparator`           | Single `char`          | Must not be a newline character.                                                   |
| `quoteCharacter`           | Single `char`          | Must not be a newline character.                                                   |
| `commentStrategy`          | `CommentStrategy` enum | Defines comment line behavior.                                                     |
| `commentCharacter`         | Single `char`          | Must not be a newline character.   
| `ignoreDifferentFieldCount`| `boolean`              | `true` or `false`.                                                                 |
| `skipEmptyLines`           | `boolean`              | `true` or `false`.                                                                 |
| `detectBomHeader`          | `boolean`              | `true` or `false`.                                                                 | 

**Partitioning Characteristics into Blocks**

| Input Variable             | Blocks                                            | Values                                           | Related Tests        |
| -------------------------- | ------------------------------------------------- | ------------------------------------------------ |----------------------|
| `file`                     | Block a1: Valid CSV file of strings and numbers   | [reader-file-a01.csv](/InputDomainModeling/CsvTestFiles/reader-file-a01.csv)                              | idm-r-01              |
|                            | Block a2: CSV of special characters               | [reader-file-a02.csv](/InputDomainModeling/CsvTestFiles/reader-file-a02.csv)                             | idm-r-02              |
|                            | Block a3: Single column CSV                       | [reader-file-a03.csv](/InputDomainModeling/CsvTestFiles/reader-file-a03.csv)                             | idm-r-03              |  
|                            | Block a4: Single row CSV                          | [reader-file-a04.csv](/InputDomainModeling/CsvTestFiles/reader-file-a04.csv)                             | idm-r-04              |
|                            | Block a5: Very large CSV (1 million rows)         | [reader-file-a05.csv](/InputDomainModeling/CsvTestFiles/reader-file-a05.csv)                             | idm-r-05              |
|                            | Block a6: CSV file with commas as data            | [reader-file-a06.csv](/InputDomainModeling/CsvTestFiles/reader-file-a06.csv)                              | idm-r-06              |
|                            | Block a7: CSV file with quotes as data            | [reader-file-a07.csv](/InputDomainModeling/CsvTestFiles/reader-file-a07.csv)                              | idm-r-07              |
|                            | Block a8: Uneven # of columns in rows             | [reader-file-a08.csv](/InputDomainModeling/CsvTestFiles/reader-file-a08.csv)                             | idm-r-08, idm-r-17     |
|                            | Block a9: Skipped Rows                            | [reader-file-a09.csv](/InputDomainModeling/CsvTestFiles/reader-file-a09.csv)                              | idm-r-09, idm-r-19     |
|                            | Block a10: Empty file                             | [reader-file-a10.csv](/InputDomainModeling/CsvTestFiles/reader-file-a10.csv)                              | idm-r-10              |
|                            | Block a11: `';'` as field separator               | [reader-file-a11.csv](/InputDomainModeling/CsvTestFiles/reader-file-a11.csv)                              | idm-r-11              |
|                            | Block a12: `'` as quotes                          | [reader-file-a12.csv](/InputDomainModeling/CsvTestFiles/reader-file-a12.csv)                              | idm-r-12              |
|                            | Block a13: `#` as comments                        | [reader-file-a13.csv](/InputDomainModeling/CsvTestFiles/reader-file-a13.csv)                              | idm-r-13, idm-r-16     |
|                            | Block a14: `@` as comments                        | [reader-file-a14.csv](/InputDomainModeling/CsvTestFiles/reader-file-a14.csv)                              | idm-r-14              |
|                            | Block a15: File with BOM header                   | [reader-file-a15.csv](/InputDomainModeling/CsvTestFiles/reader-file-a15.csv)                              | idm-r-15              |
| `fieldSeparator`           | Block b1: Default                                 | Defaults to `','`                                | all except idm-r-11   |
|                            | Block b2: Alternative separator                   | `';'`                                            | idm-r-11              |
| `quoteCharacter`           | Block c1: Default                                 | Defaults to `'"'`                                | idm-r-06, idm-r-07     |
|                            | Block c2: Alternative quote                       | `'^'`                                            | idm-r-12              |
| `commentStrategy`          | Block d1: Default                                 | Defaults to `CommentStrategy.NONE`               | idm-r-16              |
|                            | Block d2: `SKIP`                                  | `CommentStrategy.SKIP`                           | idm-r-13, idm-r-14     |
| `commentCharacter`         | Block e1: Default                                 | Defaults to `#`                                  | idm-r-13, idm-r-16     |
|                            | Block e2: Alternate                               | `@`                                              | idm-r-14              |
| `ignoreDifferentFieldCount`| Block f1: Default                                 | Defaults to `true`                               | idm-r-08              |
|                            | Block f2: `false`                                 | `false`                                          | idm-r-17              |
| `skipEmptyLines`           | Block g1: Default                                 | Defaults to `true`                               | idm-r-18              |
|                            | Block g2: `false`                                 | `false`                                          | idm-r-09              |
| `detectBomHeader`          | Block h1: Default                                 | Defaults to 'false'                              | all except idm-r-15   |
|                            | Block h2: `true`                                  | `true`                                           | idm-r-15              | 

**Coverage Criteria**  
The "Each-Choice" coverage criterion was selected to ensure each block is tested at least once, providing broad coverage of file reading scenarios (e.g., valid input, edge cases) while keeping the test set manageable.

**Test Set Definition**<br>
The below table shows each test number as columns (the "idm-r-" prefix has been removed for formatting). Each tested input variable is listed in a row. The intersections show the specific block of the input variable that is being covered by a specific test. For example, Test # 07 covers block a7 of the `file` input variable, block b1 of the `field seperator` input variable, block c1 of the `quote strategy` input variable and block h1 of the `detectBomHeader` input variable.

The code for the tests can be found in [TestReaderIDM.java](/InputDomainModeling/Tests/TestReaderIDM.java)

|test # (prefix = idm-r-)  |01| 02| 03| 04| 05| 06| 07| 08| 09| 10| 11| 12| 13| 14| 15| 16| 17| 18|
|-------------------------|--|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|file                     |a1| a2| a3| a4| a5| a6| a7| a8| a9|a10|a11|a12|a13|a14|a15|a13| a8| a9| 
|fieldSeperator           |b1| b1| b1| b1| b1| b1| b1| b1| b1| b1| b2| b1| b1| b1| b1| b1| b1| b1|
|quoteCharacter           |  |   |   |   |   | c1| c1|   |   |   |   | c2|   |   |   |   |   |   |
|commentStrategy          |  |   |   |   |   |   |   |   |   |   |   |   | d2| d2|   | d1|   |   |
|commentCharacter         |  |   |   |   |   |   |   |   |   |   |   |   | e1| e2|   | e1|   |   |
|ignoreDifferentFieldCount|  |   |   |   |   |   |   | f1|   |   |   |   |   |   |   |   | f2|   |
|skipEmptyLines           |  |   |   |   |   |   |   |   | g2|   |   |   |   |   |   |   |   | g1|
|detectBomHeader          |h1| h1| h1| h1| h1| h1| h1| h1| h1| h1| h1| h1| h1| h1| h2| h1| h1| h1|

|Test # |Test Purpose/Description                                 |Test Definition                                                                   |
|-------|---------------------------------------------------------|----------------------------------------------------------------------------------|
|idm-r-01|Test a valid csv file of strings with default settings|Path file = Paths.get("[reader-file-a01.csv](/InputDomainModeling/CsvTestFiles/reader-file-a01.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));|
|idm-r-02|Test a csv file of special characters with default settings|Path file = Paths.get("[reader-file-a02.csv](/InputDomainModeling/CsvTestFiles/reader-file-a02.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));|
|idm-r-03|Test a csv file with one column per row with default settings|Path file = Paths.get("[reader-file-a03.csv](/InputDomainModeling/CsvTestFiles/reader-file-a03.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));|
|idm-r-04|Test a csv file with one row and many columns with default <br>settings|Path file = Paths.get("[reader-file-a04.csv](/InputDomainModeling/CsvTestFiles/reader-file-a04.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));|
|idm-r-05|Test a very large csv file with one million rows with default <br>settings|Path file = Paths.get("[reader-file-a05.csv](/InputDomainModeling/CsvTestFiles/reader-file-a05.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file))|
|idm-r-06|Test a csv file with commas as data with default settings<br> including quoteCharacter|Path file = Paths.get("[reader-file-a06.csv](/InputDomainModeling/CsvTestFiles/reader-file-a06.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));|
|idm-r-07|Test a csv File that has quotes as data with default settings<br> including quoteCharacter|Path file = Paths.get("[reader-file-a07.csv](/InputDomainModeling/CsvTestFiles/reader-file-a07.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));|
|idm-r-08|Test a malformed csv file with uneven columns in rows with <br>default settings including default ignoreDifferentFieldCount value|Path file = Paths.get("[reader-file-a08.csv](/InputDomainModeling/CsvTestFiles/reader-file-a08.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));|
|idm-r-09|Test a csv File with empty rows with default settings except<br> for alternative skipEmptyLines option|Path file = Paths.get("[reader-file-a09.csv](/InputDomainModeling/CsvTestFiles/reader-file-a09.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .skipEmptyLines(false).ofCsvRecord(file));|
|idm-r-10|Test a blank csv file with default settings|Path file = Paths.get("[reader-file-a10.csv](/InputDomainModeling/CsvTestFiles/reader-file-a10.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));|
|idm-r-11|Test a csv file with ';' as separators with default settings<br> and alternative fieldSeparator ';'|Path file = Paths.get("[reader-file-a11.csv](/InputDomainModeling/CsvTestFiles/reader-file-a11.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .fieldSeparator(';') <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(file));|
|idm-r-12|Test a csv file with '^' as quote character with default <br>settings and alternative quoteCharacter '^'|Path file = Paths.get("[reader-file-a12.csv](/InputDomainModeling/CsvTestFiles/reader-file-a12.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .quoteCharacter('^') <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(file));|
|idm-r-13|Test a csv file with blank lines with default settings <br>except for CommentStrategy of 'SKIP'|Path file = Paths.get("[reader-file-a13.csv](/InputDomainModeling/CsvTestFiles/reader-file-a13.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .commentStrategy(CommentStrategy.SKIP) <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(file));|
|idm-r-14|Test a csv file with default settings except for using an<br> alternate comment character '@' and CommentStrategy of 'SKIP'|Path file = Paths.get("[reader-file-a14.csv](/InputDomainModeling/CsvTestFiles/reader-file-a14.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .commentStrategy(CommentStrategy.SKIP) <br> &nbsp; &nbsp; &nbsp; .commentCharacter('@') <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(file));|
|idm-r-15|Test a csv file that has a BOM header with default settings<br> except detectBOMHeader set to true.|Path file = Paths.get("[reader-file-a15.csv](/InputDomainModeling/CsvTestFiles/reader-file-a15.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .detectBomHeader(true) <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(file));|
|idm-r-16|Test a csv file with comments and default settings|Path file = Paths.get("[reader-file-a13.csv](/InputDomainModeling/CsvTestFiles/reader-file-a13.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));|
|idm-r-17|Test a malformed csv file with uneven columns in rows with <br>default settings including alternate<br> ignoreDifferentFieldCount value of 'false'|CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .ignoreDifferentFieldCount(false) <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(Paths.get("[reader-file-a08.csv](/InputDomainModeling/CsvTestFiles/reader-file-a08.csv)"));
|idm-r-18|Test a csv file with empty lines and default settings|Path file = Paths.get("[reader-file-a09.csv](/InputDomainModeling/CsvTestFiles/reader-file-a09.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));|

**Execution Results**  
|Test #  |Expected Results                                                                                           |Results  |
|--------|-----------------------------------------------------------------------------------------------------------|---------|
|idm-r-01|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}` |Pass     |
|idm-r-02|`{{"!@#^(&@$(*@", "*#$&(@$@", "+)_(@*&)(&"}, {"<>//;[>", "~~~~~~", "/*-*-+"}, {"(*&*#(@$)", "#$@$@#$#", "+_)(*&^%$#@!~"}, {"~!@#$%^&", "{}{}{}", "///"}}`|Pass     |
|idm-r-03|`{{"Adam"}, {"Santhosh"}, {"Bill"}, {"Ted"}, {"George"}, {"Thomas"}, {"Heather"}, {"Jane"}}`|Pass   |
|idm-r-04|`{"Adam", "Santhosh", "Bill", "Ted", "George", "Thomas", "Heather", "Jane"}`|Pass    |
|idm-r-05|Row 0: `{"Anne","Mack","(907) 789-3686")`<br> Row 191,783: `{"Sallie","Moss","(838) 455-8563"}`<br>Row 405,480 `{"Bill","Lee","(443) 584-2867"}`<br>Row 652,054 `{"Inez","Foster","(557) 675-1730"}`<br>Row 999,999 `{"Carolyn","Todd","(604) 860-4898"}`|Pass   |
|idm-r-06|`{{"Adam", "24,324"}, {"Santhosh", "56,434"}, {"Bill", "23,145"}, {"Ted", ",,,,,"}, {"George", "1,"}, {",", ","}, {"Heather", "12,111"}, {"1,", "1,1,1,1,1"}}`|Pass     |
|idm-r-07|`{{"\"Adam\"", "1"}, {"\"Santhosh\"", "\"2\""}, {"\"\"", "\"\""}}`|Pass     |
|idm-r-08|`{{"Adam", "1", "2"}, {"Santhosh", "3"}, {"Pennsylvania", "1", "3", "4"}, {"Penn State", "1", "2", "3", "4", "5", "6"}}`|Pass    |
|idm-r-09|Row 2: `""`|Pass     |
|idm-r-10|`recs.size()=0`|Pass    |
|idm-r-11|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass    |
|idm-r-12|`{{"\"Adam\"", "1"}, {"\"Santhosh\"", "\"2\""}, {"\"\"", "\"\""}}`|Pass    |
|idm-r-13|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass    |
|idm-r-14|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass    |
|idm-r-15|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass    |
|idm-r-16|`{{"apple", "banana", "cantaloupe"}, {"#This is a comment"},{"11", "22", "33"},{"#This is another comment"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass    |
|idm-r-17|Throws `CsvParseException.class`|Pass    |
|idm-r-18|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass     |

### 5.2.2 Graph Based Test Cases

#### 5.2.2.1 CsvReader Graph Based Test Cases

**Identified Component for Graph-Based Testing**  
The `skipLines(final Predicate<String> predicate, final int maxLines)` method was chosen as a suitable target for graph-based testing. Certain CSV files may have header information, such as comments or column headers, that the user may wish to skip. This function accepts a `Predicate<String> predicate` to define the header row will be the first non-skipped row of information and an integer to represent the maximum number of rows that should be searched for the predicate condition. It returns an integer representing the row in which the predicate is found.

**Control Flow Abstraction**

The pseudocode below illustrates the logic of the method `skipLines(final Predicate<String> predicate, final int maxLines)`.  Note that for ease of abstraction all potential throws have been modelled as 'if' statements :

```text

if (Objects.requireNonNull(predicate) == null):
  Throw NullPointerException
if(maxLines < 0):
  ThrowIllegalArgumentException
if (maxLines == 0):
  return 0;
for (int i = 0; i < maxLines; i++)
  if(csvParser.peekline throws IOException):
    Throw IOException
    else
      if(test(line)==true):
          return i;
      else
        if(!csvParser.skipLine(line.length()) throws IOException):
          Throw IOException
        else
          if(!csvParser.skipLine(line.length())):
            Throw CsvParseException;
          else
            i++;

Throw CsvParseException;

```
Based on the pseudo code for the `skipLines(final Predicate<String> predicate, final int maxLines)` method, I have numbered the nodes and extracted the corresponding edges in the format required by the [Graph Coverage web application](https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage).

**Node Numbering Scheme**

| Node # | Action                                                     |
| ------ | -----------------------------------------------------------|
| 1      | `if (Objects.requireNonNull(predicate) == null)`           |
| 2      | `Throw NullPointerException`                               |
| 3      | `if(maxLines < 0):`                                        |
| 4      | `Throw IllegalArgumentException`                           |
| 5      | `if (maxLines == 0):`                                      |
| 6      | `return 0`                                                 |
| 7      | `i = 0` First Part of For Loop                             |
| 8      | `i < maxLines` Condition check in For Loop                 |
| 9      | `if(csvParser.peekline throws IOException)`                |
| 10     | `Throw CSVParseException`                                  |
| 11     | `Thow IOException`                                         |
| 12     | `if(test(line)==true)`                                     |
| 13     | `return i`                                                 |
| 14     | `if(!csvParser.skipLine(line.length()) throws IOException)`|
| 15     | `Throw IOException`                                        |
| 16     | `if(!csvParser.skipLine(line.length()))`                   |
| 17     | `Throw CsvParseException`                                  |
| 18     | `i++` Loop back to 8                                       |

---

#### **Edges List (for input into web app)**

```
1 2
1 3
3 4
3 5
5 6
5 7
7 8
8 10
8 9
9 11
9 12
12 13
12 14
14 15
14 16
16 17
16 18
18 8
```

Where initial node is `1` and final nodes are `2, 4, 6, 10, 11, 13, 15, and 17`.

**Corresponding Control Flow Graph (CFG) and Condensed Node Diagram**

| Control Flow Diagram                                                 | Condensed Node Diagram                                                                        |
| -------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| <img src="GraphBasedTesting/Images/csvreader-skiplines.png" alt="Control Flow Diagram" width="500"/> | <img src="GraphBasedTesting/Images/NodeDiagramReader.png" alt="Node Diagram - Reader" width="500"/> |

[PlantUML Code](/GraphBasedTesting/ImageUMLs/CFGReader.puml) 

---

**Testing Coverage Criteria**  
Edge coverage was selected as the testing coverage criteria, in order to ensure that every edge is covered by testing at least once. Edge coverage was considered appropriate for this function, as every edge is able to be tested using nine test cases, a relatively low number of cases given the rather complex branching of the graph.  

The code for the tests can be found in [TestReaderGraph.java](/GraphBasedTesting/Tests/TestReaderGraph.java)

**Test Cases**  
|Test # |Test Purpose/Description                        |Test Definition (Refer to Above Graph)    |CSV File Used in Testing|
|----------|---------------------------------------------|------------------------------------------------|-----------------------------------------------|
|g-r-01 |The desired header is found in the first iteration of the for loop | 1→3→5→7→8→9→12→13 |[reader-file-g01.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g01.csv) |
|g-r-02 |The desired header is found in the second interation of the for loop| 1→3→5→7→8→9→12→14→16→18→8→9→12→13 |[reader-file-g02.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g02.csv) |
|g-r-03 |`Predicate<String>` is null| 1→2|[reader-file-g01.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g01.csv)|  
|g-r-04 |`maxLines` is negative | 1→3→4|[reader-file-g01.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g01.csv) |
|g-r-05 |`maxLines` equals 0 | 1→3→5→6|[reader-file-g02.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g02.csv) 
|g-r-06 |The desired header is not found within specified max lines (`maxLines`) which is less than max lines in file| 1→3→5→7→8→9→12→14→16→18→8→10 |[reader-file-g02.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g02.csv) |
|g-r-07 |The desired header is not found within specified max lines (`maxLines`) which is greater than max lines in file| 1→3→5→7→8→9→12→14→16→18→8→9→12→14→16→17 |[reader-file-g03.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g03.csv) |
|g-r-08 |`IOException` is thrown when running csvParser.peekline| 1→3→5→7→8→9→11| TODO|
|g-r-09 |`IOException` is thrown when running csvParser.skipLine| 1→3→5→7→8→9→12→14→15| TODO|

**Execution Results**  
|Test #  |Expected Results                                                                                           |Results  |
|--------|-----------------------------------------------------------------------------------------------------------|---------|
|g-r-01| Returns 0 |Pass     |
|g-r-02| Returns 1 |Pass     |
|g-r-03| Throws NullPointerException |Pass   |
|g-r-04| Throws IllegalArgumentException |Pass    |
|g-r-05| Returns 0 |Pass   |
|g-r-06| Throws CsvParseException |Pass     |
|g-r-07| Throws CsvParseException |Pass     |
|g-r-08| Throws IOException | TODO|
|g-r-09| Throws IOException | TODO|

#### 5.2.2.2 CsvWriter Graph Based Test Cases

**Identified Component for Graph-Based Testing**

The `CsvWriter` class is responsible for writing data in CSV format to various outputs (e.g., files, streams, console). The writing process involves encoding records (including string quoting, delimiter handling, and optional comment lines), managing internal buffers, and controlling the line ending strategy. The control logic in the writer can be naturally expressed using a control flow graph (CFG) due to its numerous branching decisions (e.g., when to quote, when to write separators, how to escape characters, etc.).

**Control Flow Abstraction**

The pseudocode below illustrates the logic of the method `writeRecord(String... values)`:

```text
writeRecord(values):
    validateNoOpenRecord()
    for each value in values:
        if fieldIdx > 0:
            write(fieldSeparator)
        if value is null:
            if quoteNull:
                write(empty quotes)
        else if value is empty:
            if quoteEmpty:
                write(empty quotes)
        else:
            if needsQuotes:
                write(quoteCharacter)
            if needsEscape:
                writeEscaped(value)
            else:
                write(value)
            if needsQuotes:
                write(quoteCharacter)
    endRecord()
```

Based on the pseudo code for the `writeRecord(String... values)` method, I have numbered the nodes and extracted the corresponding edges in the format required by the [Graph Coverage web application](https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage).

**Node Numbering Scheme**

| Node # | Action                                        |
| ------ | --------------------------------------------- |
| 1      | Start                                         |
| 2      | `validateNoOpenRecord()`                      |
| 3      | Begin loop: iterate over fields               |
| 4      | `fieldIdx > 0` check                          |
| 5      | `write(fieldSeparator)`                       |
| 6      | `value == null` check                         |
| 7      | `quoteNull == true` → `write("")`             |
| 8      | `value == ""` check                           |
| 9      | `quoteEmpty == true` → `write("")`            |
| 10     | `needsQuotes == true` → `write(quoteChar)`    |
| 11     | `needsEscape == true` → `writeEscaped(value)` |
| 12     | `write(value)` (when no escape needed)        |
| 13     | `needsQuotes == true` → `write(quoteChar)`    |
| 14     | End of loop → more fields?                    |
| 15     | `write(lineDelimiter)`                        |
| 16     | Stop                                          |

---

**Edges List (for input into web app)**

```
1 2
2 3
3 4
4 5
4 6
5 6
6 7
6 8
7 14
8 9
8 10
9 14
10 11
10 12
11 13
12 13
13 14
14 3
14 15
15 16
```

Where initial node is `1` and final node is `16`.

**Corresponding Control Flow Graph (CFG) and Condensed Node Diagram**

| Control Flow Diagram                                                 | Condensed Node Diagram                                                                        |
| -------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| <img src="GraphBasedTesting/Images/CFG.png" alt="Control Flow Diagram" width="500"/> | <img src="GraphBasedTesting/Images/Condensed%20Node%20Diagram.png" alt="Condensed Node Diagram" width="500"/> |

[PlantUML Code](/GraphBasedTesting/ImageUMLs/CFG.puml)                                 

---

**Graph Testing Details**

Based on the graph, the number of independent paths depends on the combinations of:

- null field
- empty string field
- quoted/escaped non-empty string
- non-quoted regular string

Here are a few representative paths:

| Path ID | Description                                  | Path Nodes Sequence (Labels in CFG)                                                      |
| ------- | -------------------------------------------- | ---------------------------------------------------------------------------------------- |
| P1      | Single quoted non-empty field needing escape | Start → validate → fieldSep → needsQuotes → writeQuote → writeEscaped → writeQuote → end |
| P2      | Null field with quote strategy               | Start → validate → quoteNull → write("") → end                                           |
| P3      | Empty field with no quoting                  | Start → validate → empty → skip write → end                                              |
| P4      | Regular field, no quotes, no escape          | Start → validate → write(value) → end                                                    |

**Testing Coverage Criteria**

Edge coverage was selected as the testing coverage criterion to ensure that every edge in the control flow graph of the `writeRecord(String... values)` method is executed at least once. This criterion is appropriate for this method because the function includes multiple conditionals and loops, but all edges can be covered using only five test cases, making the test suite efficient and maintainable while ensuring sufficient structural coverage.

**Test Cases**

| Test # | Test Purpose/Description                            | Test Definition (Graph Edge Path) |
| ------ | --------------------------------------------------- | --------------------------------- |
| g-w-01 | Tests a null value that triggers quoteNull strategy | 1→2→3→4→5→6→7→14→15→16            |
| g-w-02 | Tests empty string with quoteEmpty strategy         | 1→2→3→4→6→8→9→14→15→16            |
| g-w-03 | Tests two null values written back-to-back          | 1→2→3→4→6→7→14→3→4→6→7→14→15→16   |
| g-w-04 | Tests unescaped value that needs quoting            | 1→2→3→4→6→8→10→12→13→14→15→16     |
| g-w-05 | Tests escaped value that needs quoting and escaping | 1→2→3→4→6→8→10→11→13→14→15→16     |

**Execution Results**

| Test # | Expected Result                                  | Result |
| ------ | ------------------------------------------------ | ------ |
| g-w-01 | Output: `""` followed by line delimiter          | Pass   |
| g-w-02 | Output: `""` followed by line delimiter          | Pass   |
| g-w-03 | Output: `""`,`""` followed by line delimiter     | Pass   |
| g-w-04 | Output: `"value"` followed by line delimiter     | Pass   |
| g-w-05 | Output: `"esca\"ped"` followed by line delimiter | Pass   |

The code for the tests can be found in [TestWriterGraph.java](GraphBasedTesting/Tests/TestWriterGraph.java)

### 5.2.3 Exploratory Test Cases

#### 5.2.3.1 Back Alley Tour - Part I

**Test Description**

| Item                               | Details                                                                                                                 |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------------|
| Test Tour                          | Back Alley Tour                                                                                                         |
| Description                        | To test several of more minor features of the program, specifically those involving compressed files and indexed reading|
| Test Duration                      | 90 minutes                                                                                                              |
| Tester                             | Adam Slager                                                                                                             |
| Further Testing <br> Opportunities | Potential to continue testing additional minor features                                                                 |

**Test Protocol**

| NR  | What Done                                                                  | Status        | Comment                                                                                           | Test Artifacts                                      |
| --- | -------------------------------------------------------------------------- | ------------- | ------------------------------------------------------------------------------------------------- | ----------------------------------------------------|
| E-BA1-01   | Wrote test case to read small compressed file and test the output               | No Exceptions | Read in 4 rows and three columns without exception                                                | - [Input File](/ExploratoryTesting/CsvTestFiles/reader-file-a01.csv.gz) <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlley_1.png) |
| E-BA1-02   | Wrote test case to read very large compressed file and test the output            | No Exceptions | File contains 10M entries                        | - Input File Excluded Due to Size <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlley_1.png) |
| E-BA1-03   | Wrote test case to write compressed file and subsequently read the same compressed file | No Exceptions | Wrote and read in 4 rows and 3 columns without exception   |  - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlley_1.png) |                          |
| E-BA1-04   | Tested indexed reading of very large (100M line) file | No Exceptions | - Initial file read under 10000ms<br>- Subequent indexed page reads took 12ms or less | - [Test Script](/ExploratoryTesting/Tests/BackAlleyTourIndex.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlley_2.png) |

#### 5.2.3.2 Back Alley Tour - Part II

**Test Description**

| Item                               | Details                                                                                                                 |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------------|
| Test Tour                          | Back Alley Tour Part 2                                                                                                  |
| Description                        | Continue testing minor features, including JavaBeans and handlings white space characters                               |
| Test Duration                      | 90 minutes                                                                                                              |
| Tester                             | Adam Slager                                                                                                             |
| Further Testing <br> Opportunities | N/A                                                          |

**Test Protocol**

| NR  | What Done                                                                  | Status        | Comment                                                                                           | Test Artifacts                                      |
| --- | -------------------------------------------------------------------------- | ------------- | ------------------------------------------------------------------------------------------------- | ----------------------------------------------------|
| E-BA2-01   | Test mapping of csv file to Java beans            | No Exceptions | Read in 3 row, 5 columns of data with mixed types and stored as Java Beans | - [Input File](/ExploratoryTesting/CsvTestFiles/testJavaBeans.csv) <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour2_JB.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlleyJB_1.png) |
| E-BA2-02   | Test trimming of standard ASCII space characters preceding and following value in CSV | No Exceptions | File contained no unicode spaces.  FastCSV successfully eliminated spaces  | - [Input File](/ExploratoryTesting/CsvTestFiles/testTrimSpaces.csv) <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour2_Modify.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlleyTrim_1.png)  |
| E-BA2-03   | Tested trimming of unicode space characters preceding and following value in CSV | No Exceptions | Program correctly did not remove unicode space characters |  - [Input File](/ExploratoryTesting/CsvTestFiles/testStripSpaces.csv) <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour2_Modify.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlleyTrim_2.png)  |                          
| E-BA2-04   | Tested stripping of unicode space characters preceding and following value in CSV | No Exceptions | Program correctly removed unicode space characters |  - [Input File](/ExploratoryTesting/CsvTestFiles/testStripSpaces.csv) <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour2_Modify.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlley_Strip1.png)  |  

#### 5.2.3.3 Collector's Tour Report

**Test Description**

| Item                               | Details                                                                              |
| ---------------------------------- | ------------------------------------------------------------------------------------ |
| Test Tour                          | Collector’s Tour                                                                     |
| Description                        | Collect and count all unique values of the `amount` column to test value enumeration |
| Test Duration                      | 40 minutes                                                                           |
| Tester                             | Santhosh                                                                             |
| Further Testing <br> Opportunities | Perform uniqueness test on transformed or normalized fields                          |

**Test Protocol**

| NR  | What Done                                                                  | Status        | Comment                                                                                           | Test Artifacts                                   |
| --- | -------------------------------------------------------------------------- | ------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------ |
| E-C-01   | Read `collector-input.csv`                                                 | No Exceptions | 4 rows, 3 columns; file loaded without error                                                      | [Input File](/ExploratoryTesting/CsvTestFiles/collector-input.csv) |
| E-C-02   | Extracted and stored all unique values of `amount` in a set                | No Exceptions | Set contained 3 entries: `500`, `600`, `700`; duplicates correctly removed                        | [Test Script](/ExploratoryTesting/Tests/CollectorsTour.java)       |
| E-C-03   | Printed count of unique values to console                                  | No Exceptions | Console log showed expected value count = 3                                                       | TODO(add screenshot)                             |
| E-C-04   | Re-ran test after modifying data to include lowercase and formatted values | No Exceptions | Collector distinguished values with formatting; recommend normalization pass in future iterations | TODO(add screenshot)                             |

#### 5.2.3.4 FedEx Tour Report

**Test Description**

| Item                               | Details                                                                             |
| ---------------------------------- | ----------------------------------------------------------------------------------- |
| Test Tour                          | FedEx Tour                                                                          |
| Description                        | Track the “amount” field from input through processing, verifying data is preserved |
| Test Duration                      | 45 minutes                                                                          |
| Tester                             | Santhosh                                                                            |
| Further Testing <br> Opportunities | Track multiple columns simultaneously; use with filtered/aggregated datasets        |

**Test Protocol**

| NR                 | What Done                                                      | Status                                                           | Comment                                                                                           | Test Artifacts                                                                       |
| ------------------ | -------------------------------------------------------------- | ---------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------ |
| 1                  | Read `fedex-input.csv` and extracted columns `id` and `amount` | No Exceptions                                                    | Correct values were read from input, verified manually                                            | [Test Script](/ExploratoryTesting/Tests/FedExTour.java)<br>[Input CSV](/ExploratoryTesting/CsvTestFiles/fedex-input.csv) |
| 2                  | Wrote selected columns to `fedex-output.csv` using `CsvWriter` | No Exceptions                                                    | File format and values were correct in output. Fields aligned and properly quoted where necessary | [Output](/ExploratoryTesting/CsvTestFiles/fedex-output.csv)                                            |
| FastCSV/app/inputs | 4                                                              | Opened output in spreadsheet viewer and verified layout visually | No Exceptions                                                                                     | Output is viewable and properly delimited by common spreadsheet tools                |

#### 5.2.3.5 Intellectual Tour

**Test Description**

| Item                               | Details                                                                                                        |
| ---------------------------------- | -------------------------------------------------------------------------------------------------------------- |
| Test Tour                          | Intellectual Tour                                                                                              |
| Description                        | The goal is to see how the software will handle under stress by testing the reading and writing of large files |
| Test Duration                      | 90 minutes                                                                                                     |
| Tester                             | Adam Slager                                                                                                    |
| Further Testing <br> Opportunities | TBD                                                                                                            |

**Test Protocol**

| NR  | What Done                                                                          | Status        | Comment                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | Test Artifacts                                                                                                                             |
| --- | ---------------------------------------------------------------------------------- | ------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------ |
| E-I-01   | Tested reading of 100 million line file using CSVReader                            | No Exceptions | - Created 100 million line test file using below website with columns for 'first', 'last', and 'phone' https://www.convertcsv.com/generate-test-data.htm<br>- Wrote and executed Java program to read in file and count number of lines read.<br> - Noted that run time for processing file was 9636ms                                                                                                                                                                                                | - [Test Script](/ExploratoryTesting/Tests/IntellectualTour1.java)<br>- [Test Execution](/ExploratoryTesting/Images/E1_Test1.png)                                                   |
| E-I-02   | Tested reading of ~900 million line file using CSV Reader                          | No Exceptions | - Created ~900 million line test file using below website and manually copying and pasting data with columns for 'first', 'last', and 'phone' https://www.convertcsv.com/generate-test-data.htm<br>- Wrote and executed Java program to read in file and count number of lines read.<br> - Noted that run time for processing file was 85121ms                                                                                                                                                        | - [Test Script](/ExploratoryTesting/Tests/IntellectualTour1.java)<br>- [Test Execution](/ExploratoryTesting/Images/E1_Test2.png)                                                   |
| E-I-03   | Tested reading and writing of 100 million line file using CSVReader and CSVWriter  | No Exceptions | - Wrote Java program to read in lines from 100 million line file created above using CSVReader and immediately write those lines to a new csv file using CSVWriter<br> - Executed program and noted that run time for processing was 20486ms<br>- Used Windows fc to compare files and noted that they were exact duplicates of each other                                                                                                                                                            | - [Test Script](/ExploratoryTesting/Tests/IntellectualTour1.java)<br>- [Test Execution](/ExploratoryTesting/Images/E1_Test3a.png)<br>- [Output Verification](/ExploratoryTesting/Images/E1_Test3c.png) |
| E-I-04   | Tested reading and writing of ~900 million line file using CSVReader and CSVWriter | No Exceptions | - Wrote Java program to read in lines from ~900 million line file created above using CSVReader and immediately write those lines to a new csv file using CSVWriter<br> - Executed program and noted that run time for processing was 176041ms<br>- Attemped to use Windows FC to compare files, but file size was too large. This is a limitation of Windows FC and not the program being tested.<br>- Visually compared size of files with Windows Explorer and noted that files appear identifical | - [Test Script](/ExploratoryTesting/Tests/IntellectualTour1.java)<br>- [Test Execution](/ExploratoryTesting/Images/E1_Test4a.png)<br>- [Output Verification](/ExploratoryTesting/Images/E1_Test4b.png) |

#### 5.2.3.6 Landmark Tour

**Test Description**

| Item                               | Details                                                                                               |
| ---------------------------------- | ----------------------------------------------------------------------------------------------------- |
| Test Tour                          | Landmark Tour                                                                                         |
| Description                        | Verify correct handling of sequences by reordering data and checking structural and content integrity |
| Test Duration                      | 60 minutes                                                                                            |
| Tester                             | Santhosh                                                                                              |
| Further Testing <br> Opportunities | Reorder within subsets (e.g., per product category); reorder multiple files together                  |

**Test Protocol**

| NR  | What Done                                                   | Status        | Comment                                                           | Test Artifacts                                     |
| --- | ----------------------------------------------------------- | ------------- | ----------------------------------------------------------------- | -------------------------------------------------- |
| E-L-01   | Loaded original data from `landmark-input.csv`              | No Exceptions | Verified 4 rows and 3 columns exist                               | [Input CSV](/ExploratoryTesting/CsvTestFiles/landmark-input.csv)     |
| E-L-02   | Shuffled rows randomly and wrote to `landmark-temp.csv`     | No Exceptions | Manual comparison confirms sequence change, but data preserved    | [Temp Output](/ExploratoryTesting/CsvTestFiles/landmark-temp.csv)    |
| E-L-03   | Read shuffled file and wrote to final `landmark-output.csv` | No Exceptions | Verified that structure remained unchanged after second I/O pass  | [Final Output](/ExploratoryTesting/CsvTestFiles/landmark-output.csv) |
| E-L-04   | Compared first and second output using diff tool            | No Exceptions | Differences observed only in row order; values remained identical |                                                    |


## 5.3 Traceability Matrix

[Create a traceability matrix to map requirements to test cases]

# 6. Test Environment

## 6.1 Multiple Test Environments

[List the different test environments used if applicable.]

## 6.2 Schematic Diagram

[Provide a schematic diagram of the test environment setup if applicable.]

## 6.3 Test Architecture Overview

[Explain the overall test architecture if applicable.]

## 6.4 Equipment Table

[List the equipment and resources used in the testing environment if applicable.]

# 7. Testing Results

[Provide a summary of testing results, including passed, failed, and unresolved issues]

The following table represents a summary of the testing results. Please note that these tests and their results are fully detailed in Section 5.2 above, but have been provided here in a consolidated table format for convenience and ease of reference.

| Test ID  | Test Technique      | Test Result | Comments                                               |
|----------|---------------------|-------------|--------------------------------------------------------|
|IDM-R-01  | IDM                 | Pass        |                                                        | 
|IDM-R-02  | IDM                 | Pass        |                                                        | 
|IDM-R-03  | IDM                 | Pass        |                                                        | 
|IDM-R-04  | IDM                 | Pass        |                                                        | 
|IDM-R-05  | IDM                 | Pass        |                                                        | 
|IDM-R-06  | IDM                 | Pass        |                                                        | 
|IDM-R-07  | IDM                 | Pass        |                                                        | 
|IDM-R-08  | IDM                 | Pass        |                                                        | 
|IDM-R-09  | IDM                 | Pass        |                                                        | 
|IDM-R-10  | IDM                 | Pass        |                                                        | 
|IDM-R-11  | IDM                 | Pass        |                                                        | 
|IDM-R-12  | IDM                 | Pass        |                                                        | 
|IDM-R-13  | IDM                 | Pass        |                                                        | 
|IDM-R-14  | IDM                 | Pass        |                                                        | 
|IDM-R-15  | IDM                 | Pass        |                                                        | 
|IDM-R-16  | IDM                 | Pass        |                                                        | 
|IDM-R-17  | IDM                 | Pass        |                                                        | 
|IDM-R-18  | IDM                 | Pass        |                                                        | 
|IDM-W-01  | IDM                 | Pass        |                                                        |
|IDM-W-02  | IDM                 | Pass        |                                                        |
|IDM-W-03  | IDM                 | Pass        |                                                        |
|IDM-W-04  | IDM                 | Pass        |                                                        |
|IDM-W-05  | IDM                 | Pass        |                                                        |
|IDM-W-06  | IDM                 | Pass        |                                                        |
|IDM-W-07  | IDM                 | Pass        |                                                        |
|IDM-W-08  | IDM                 | Pass        |                                                        | 
|G-R-01    | Graph Based Testing | Pass        |                                                        |
|G-R-02    | Graph Based Testing | Pass        |                                                        |
|G-R-03    | Graph Based Testing | Pass        |                                                        |
|G-R-04    | Graph Based Testing | Pass        |                                                        |
|G-R-05    | Graph Based Testing | Pass        |                                                        |
|G-W-01    | Graph Based Testing | Pass        |                                                        |
|G-W-02    | Graph Based Testing | Pass        |                                                        |
|G-W-03    | Graph Based Testing | Pass        |                                                        |
|G-W-04    | Graph Based Testing | Pass        |                                                        |
|G-W-05    | Graph Based Testing | Pass        |                                                        |
|G-W-06    | Graph Based Testing | Pass        |                                                        |
|G-W-07    | Graph Based Testing | Pass        |                                                        |
|G-W-08    | Graph Based Testing | Open        |                                                        |
|G-W-09    | Graph Based Testing | Open        |                                                        |
|E-BA1-01  | Exploratory Testing | Pass        |                                                        |
|E-BA1-02  | Exploratory Testing | Pass        |                                                        |
|E-BA1-03  | Exploratory Testing | Pass        |                                                        |
|E-BA1-04  | Exploratory Testing | Pass        |                                                        |
|E-BA2-01  | Exploratory Testing | Pass        |                                                        |
|E-BA2-02  | Exploratory Testing | Pass        |                                                        |
|E-BA2-03  | Exploratory Testing | Pass        |                                                        |
|E-BA2-04  | Exploratory Testing | Pass        |                                                        |
|E-C-01    | Exploratory Testing | Pass        |                                                        |
|E-C-02    | Exploratory Testing | Pass        |                                                        |
|E-C-03    | Exploratory Testing | Pass        |                                                        |
|E-C-04    | Exploratory Testing | Pass        |                                                        |
|E-I-01    | Exploratory Testing | Pass        |                                                        |
|E-I-02    | Exploratory Testing | Pass        |                                                        |
|E-I-03    | Exploratory Testing | Pass        |                                                        |
|E-I-04    | Exploratory Testing | Pass        |                                                        |
|E-L-01    | Exploratory Testing | Pass        |                                                        |
|E-L-02    | Exploratory Testing | Pass        |                                                        |
|E-L-03    | Exploratory Testing | Pass        |                                                        |
|E-L-04    | Exploratory Testing | Pass        |                                                        |

[NEED TO ADD FEDEX TOUR ONCE NUMBERING IS CORRECTED]

# 8. Recommendations on Software Quality

[Offer recommendations on improving the quality of the software based on testing results]
