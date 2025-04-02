# Progress Report: Testing Open-Source Application

Report Date: 4/4/2025

#### Students Name:

- Santhosh Anitha Boominathan
- Adam Slager

#### Project/Application: FastCSV

FastCSV is a high-performance CSV parser and writer for Java licensed under the [MIT open source license](https://opensource.org/license/mit). It is designed to not only be fast and lightweight but also to be compliant with the CSV specification [RFC 4180](https://datatracker.ietf.org/doc/html/rfc4180).

### Contents

- [Section 1. Input Domain Modeling](#section-1-progress-from-input-domain-modeling)
- [Section 2. Graph-Based Testing](#section-2-progress-from-graph-based-testing)
- [Section 3. Progress Summary](#section-3-progress-summary)

## Input Domain Modeling:

### Introduction

The CSVWriter and CSVReader components were selected for Input Domain Modeling for several practical reasons:
- Using Input Domain Modeling on these higher-level components provides the team with an opportunity to understand the major functionality of FastCSV early in the testing process.  This understanding will assist in designing subsequent tests.
- Since FastCSV is already in production and the primary functionality is fully operational, testing at this level is a practical option at this early stage in the testing process.  If this were an early sprint in an Agile project, this level of testing might not be available, since the functionality would not be fully developed.
- The components, and in particular the csv files themselves, are good targets for Input Domain Testing.  The input domain of the csv files is practically infinite, but those possibilities can be logically partitioned into blocks representing both common use cases and edge cases. This allowed the team to develop a manageable framework for testing a large input space.

### CsvWriter

**Selected Functions/Features for Input Domain Modeling**  
For the `CsvWriter` component, we selected the `writeRecord(String... values)` method as the primary function for testing with input domain modeling. This method is central to the CSV writing process, handling the writing of complete records with multiple fields and applying quoting logic based on the configured `QuoteStrategy`. It’s a critical feature because it directly interacts with user-provided input and encapsulates the core functionality of writing CSV data.

**List of Input Variables**

- `values`: An array of `String` objects representing the fields to write in a single record.
- `fieldSeparator`: A `char` defining the separator between fields (default: `,`).
- `quoteCharacter`: A `char` used to enclose fields (default: `"`).
- `quoteStrategy`: A `QuoteStrategy` object determining when fields are quoted (default: `null`, meaning only required quoting).
- `lineDelimiter`: A `LineDelimiter` enum specifying the end-of-line sequence (default: `CRLF`).

| Input Variable   | Type                             | Constraints                      | Range                                                            |
| ---------------- | -------------------------------- | -------------------------------- | ---------------------------------------------------------------- |
| `values`         | Array of `String`                | Can be `null` or empty           | From 0 to arbitrary length                                       |
| `fieldSeparator` | Single `char`                    | Must not be a newline character  | Any character except newline                                     |
| `quoteCharacter` | Single `char`                    | Must not be a newline character  | Any character except newline                                     |
| `quoteStrategy`  | `QuoteStrategy` object or `null` | Defines quoting behavior         | `null` for minimal quoting, custom strategies for forced quoting |
| `lineDelimiter`  | `LineDelimiter` enum             | Limited to `CR`, `LF`, or `CRLF` | `CR`, `LF`, `CRLF`                                               |

**Partitioning Characteristics into Blocks**

| Input Variable   | Blocks                                   | Values                                                 |
| ---------------- | ---------------------------------------- | ------------------------------------------------------ |
| `values`         | Block 1: `null` values                   | `[null]`                                               |
|                  | Block 2: Empty strings                   | `[""]`                                                 |
|                  | Block 3: Normal strings                  | `["hello", "world"]`                                   |
|                  | Block 4: Strings with special characters | `["comma, separated", "\"quoted\"", "newline\r\n"]`    |
| `fieldSeparator` | Block 1: Default separator               | `','`                                                  |
|                  | Block 2: Alternative separator           | `';'`                                                  |
| `quoteCharacter` | Block 1: Default quote                   | `'"'`                                                  |
|                  | Block 2: Alternative quote               | `'''`                                                  |
| `quoteStrategy`  | Block 1: `null`                          | `null`                                                 |
|                  | Block 2: Custom strategy                 | Custom `QuoteStrategy` forcing all fields to be quoted |
| `lineDelimiter`  | Block 1: `CRLF`                          | `LineDelimiter.CRLF`                                   |
|                  | Block 2: `LF`                            | `LineDelimiter.LF`                                     |

**Coverage Criteria**  
I chose the "Each-Choice" coverage criterion, which ensures that each block of every input variable is tested at least once. This provides a balanced approach, covering key scenarios (e.g., normal usage, edge cases with special characters) without the combinatorial explosion of "All-Combinations" coverage, given the number of variables.

**Test Set Definition**

- Test 1: `writeRecord("hello", "world")` with default settings (`fieldSeparator=','`, `quoteCharacter='"'`, `quoteStrategy=null`, `lineDelimiter=CRLF`).
- Test 2: `writeRecord(null, "")` with `fieldSeparator=';'`, `quoteCharacter='''`, `quoteStrategy=null`, `lineDelimiter=LF`.
- Test 3: `writeRecord(",", "\"quoted\"")` with default settings.
- Test 4: `writeRecord("\r\n")` with custom `QuoteStrategy` forcing quotes, `fieldSeparator=','`, `quoteCharacter='"'`, `lineDelimiter=CRLF`.

**Execution Results**  
TODO

---

#### CsvReader

**Selected Functions/Features for Input Domain Modeling**  
For the `CsvReader` component, my teammate selected the `ofCsvRecord(Path file)` method for testing with input domain modeling. This method is a high-level entry point for reading CSV files, leveraging the `CsvReaderBuilder` and `CsvParser` to process file input into `CsvRecord` objects. It’s a suitable choice as it encapsulates the full reading process and allows testing of file-based input handling.

**List of Input Variables**

- `file`: A `Path` object representing the CSV file to read.
- `fieldSeparator`: A `char` defining the separator between fields (default: `,`).
- `quoteCharacter`: A `char` used to enclose fields (default: `"`).
- `commentStrategy`: A `CommentStrategy` enum determining comment handling (default: `NONE`).
- `skipEmptyLines`: A `boolean` controlling whether empty lines are skipped (default: `true`).

**Characteristics of Input Variables**

| Input Variable    | Type                   | Constraints                                                                        |
| ----------------- | ---------------------- | ---------------------------------------------------------------------------------- |
| `file`            | `Path`                 | Must point to a readable file; content can vary                                    |
| `fieldSeparator`  | Single `char`          | Must not be a newline character.                                                   |
| `quoteCharacter`  | Single `char`          | Must not be a newline character.                                                   |
| `commentStrategy` | `CommentStrategy` enum | Defines comment line behavior.                                                     |
| `skipEmptyLines`  | `boolean`              | `true` or `false`.                                                                 |

**Partitioning Characteristics into Blocks**

| Input Variable             | Blocks                                            | Values                                           | Related Tests        |
| -------------------------- | ------------------------------------------------- | ------------------------------------------------ |----------------------|
| `file`                     | Block a1: Valid CSV file of strings and numbers   | reader-file-a01.csv                              | idm-r01              |
|                            | Block a2: CSV of special characters               | reader-file-a02.csv                              | idm-r02              |
|                            | Block a3: Single column CSV                       | reader-file-a03.csv                              | idm-r03              |  
|                            | Block a4: Single row CSV                          | reader-file-a04.csv                              | idm-r04              |
|                            | Block a5: Very large CSV (1 million rows)         | reader-file-a05.csv                              | idm-r05              |
|                            | Block a6: CSV file with commas as data            | reader-file-a06.csv                              | idm-r06              |
|                            | Block a7: CSV file with quotes as data            | reader-file-a07.csv                              | idm-r07              |
|                            | Block a8: Uneven # of columns in rows             | reader-file-a08.csv                              | idm-r08, idm-r17     |
|                            | Block a9: Skipped Rows                            | reader-file-a09.csv                              | idm-r09, idm-r19     |
|                            | Block a10: Empty file                             | reader-file-a10.csv                              | idm-r10              |
|                            | Block a11: `';'` as field separator               | reader-file-a11.csv                              | idm-r11              |
|                            | Block a12: `'` as quotes                          | reader-file-a12.csv                              | idm-r12              |
|                            | Block a13: `#` as comments                        | reader-file-a13.csv                              | idm-r13, idm-r16     |
|                            | Block a14: `@` as comments                        | reader-file-a14.csv                              | idm-r14              |
|                            | Block a15: File with BOM header                   | reader-file-a15.csv                              | idm-r15              |
| `fieldSeparator`           | Block b1: Default                                 | Defaults to `','`                                | all except idm-r11   |
|                            | Block b2: Alternative separator                   | `';'`                                            | idm-r11              |
| `quoteCharacter`           | Block c1: Default                                 | Defaults to `'"'`                                | idm-r06, idm-r07     |
|                            | Block c2: Alternative quote                       | `'^'`                                            | idm-r12              |
| `commentStrategy`          | Block d1: Default                                 | Defaults to `CommentStrategy.NONE`               | idm-r16              |
|                            | Block d2: `SKIP`                                  | `CommentStrategy.SKIP`                           | idm-r13, idm-r14     |
| `commentCharacter`         | Block e1: Default                                 | Defaults to `#`                                  | idm-r13, idm-r16     |
|                            | Block e2: Alternate                               | `@`                                              | idm-r14              |
| `ignoreDifferentFieldCount`| Block f1: Default                                 | Defaults to `true`                               | idm-r08              |
|                            | Block f2: `false`                                 | `false`                                          | idm-r17              |
| `skipEmptyLines`           | Block g1: Default                                 | Defaults to `true`                               | idm-r18              |
|                            | Block g2: `false`                                 | `false`                                          | idm-r09              |
| `detectBomHeader`          | Block h1: Default                                 | Defaults to 'false'                              | all except idm-r15   |
|                            | Block h2: `true`                                  | `true`                                           | idm-r15              | 


**Coverage Criteria**  
The "Each-Choice" coverage criterion was selected to ensure each block is tested at least once, providing broad coverage of file reading scenarios (e.g., valid input, edge cases) while keeping the test set manageable.

**Test Set Definition**
|test # (prefix = idm-r)  | 01 | 02 | 03 | 04 | 05 | 06 | 07 | 08 | 09 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 |
|-------------------------|----|----|----|----|----|----|----|----|----|----|----|----|----|----|----|----|----|----|
|file                     | a1 | a2 | a3 | a4 | a5 | a6 | a7 | a8 | a9 | a10| a16| a12| a13| a14| a15| a13| a8 | a9 |
|fieldSeperator           | b1 | b1 | b1 | b1 | b1 | b1 | b1 | b1 | b1 | b1 | b2 | b1 | b1 | b1 | b1 | b1 | b1 | b1 |
|quoteCharacter           |    |    |    |    |    | c1 | c1 |    |    |    |    | c2 |    |    |    |    |    |    |
|commentStrategy          |    |    |    |    |    |    |    |    |    |    |    |    | d2 | d2 |    | d1 |    |    |
|commentCharacter         |    |    |    |    |    |    |    |    |    |    |    |    | e1 | e2 |    | e1 |    |    |
|ignoreDifferentFieldCount|    |    |    |    |    |    |    | f1 |    |    |    |    |    |    |    |    | f2 |    |
|skipEmptyLines           |    |    |    |    |    |    |    |    | g2 |    |    |    |    |    |    |    |    | g1 |
|detectBomHeader          | h1 | h1 | h1 | h1 | h1 | h1 | h1 | h1 | h1 | h1 | h1 | h1 | h1 | h1 | h2 | h1 | h1 | h1 |

- Test 1: `ofCsvRecord(Path to file with "a,b\nc,d")` with default settings (`fieldSeparator=','`, `quoteCharacter='"'`, `commentStrategy=NONE`, `skipEmptyLines=true`).
- Test 2: `ofCsvRecord(Path to empty file)` with `fieldSeparator=';'`, `quoteCharacter='''`, `commentStrategy=SKIP`, `skipEmptyLines=false`.
- Test 3: `ofCsvRecord(Path to file with "a,b\nc")` with default settings.

**Execution Results**  
TODO

## Section 2. Graph-Based Testing

### CsvWriter

**Identified Component for Graph-Based Testing**  
For the `CsvWriter` component, we selected the `LineDelimiter.of(String str)` method for graph-based testing. This method is a static utility within the `LineDelimiter` enum, responsible for converting a string representation of a line delimiter (e.g., `"\r\n"`, `"\n"`, `"\r"`) into its corresponding enum value (`CRLF`, `LF`, `CR`). It’s a suitable choice because it involves clear decision points based on input string matching, making it ideal for modeling as a control flow graph. This method is indirectly used by `CsvWriter` to configure line endings, affecting how records are terminated.

**Graph Model**  
The control flow of `LineDelimiter.of(String str)` can be represented as a decision tree with distinct branches for each possible input string.

![CsvWriterGraphModel](./Image/CsvWriterGraphModel.png)

- **Nodes**:
  - Start: Entry point with input `str`.
  - Decision: Switch statement evaluating `str`.
  - Outcomes: Four terminal nodes (`CRLF`, `LF`, `CR`, or exception).
- **Edges**: Represent transitions based on string matching.
- **Dependencies**: The method relies solely on the input `str` and has no external state dependencies.

**Testing Coverage Criteria**  
I selected **Edge Coverage** as the testing coverage criterion. This ensures that every edge in the control flow graph (i.e., each possible transition from the switch statement to an outcome) is exercised at least once. Edge coverage is appropriate here because it guarantees testing of all defined paths (valid delimiters and the error case) while remaining feasible given the simplicity of the method.

**Test Cases**  
Based on edge coverage, the following test cases were generated:

- Test 1: `LineDelimiter.of("\r\n")`
  - Expected Outcome: Returns `LineDelimiter.CRLF`.
- Test 2: `LineDelimiter.of("\n")`
  - Expected Outcome: Returns `LineDelimiter.LF`.
- Test 3: `LineDelimiter.of("\r")`
  - Expected Outcome: Returns `LineDelimiter.CR`.
- Test 4: `LineDelimiter.of(" ")`
  - Expected Outcome: Throws `IllegalArgumentException`.

**Execution Results**  
The test cases were executed in a local Java environment:

- Test 1: Successfully returned `CRLF`, confirming correct handling of Windows-style line endings.
- Test 2: Returned `LF`, validating UNIX-style line endings.
- Test 3: Returned `CR`, correctly processing Mac classic-style endings.
- Test 4: Threw `IllegalArgumentException` with the message "Unknown line delimiter: ", as expected for an invalid input.  

---

### CsvReader

**Identified Component for Graph-Based Testing**  
TODO

**Graph Model**  
TODO

**Testing Coverage Criteria**  
TODO

**Test Cases**  
TODO

**Execution Results**  
TODO
