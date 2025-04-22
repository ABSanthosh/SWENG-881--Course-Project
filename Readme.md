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

- Feature Description: This section provides a high-level overview of the primary features of FastCSV. The purpose of this section is to educate the reader on the software and to provide a detailed description of the team’s assessment of primary functionality. This section serves both as an introduction to the software and as a reference for the testing described in the remainder of the document.
- Assumptions: This section serves two primary purposes. Firstly, it lays out the team’s reasoning for excluding certain features and requirements of the software from the testing plan. This section can be crucial, as it serves to memorialize the logic around the exclusions and to provide the reader with a roadmap of any additional testing that might be required to get comfort over their particular usage of the software. Secondly, it describes the tools utilized during the testing and details pertinent organization structures utilized to describe the testing in the remainder of the document.
- Test Approach: This primary purpose of this section is to document the team’s test strategy and the reasoning behind choosing these approaches, including any special situations that might fall outside of the general approach. This section also lays out any past issues that are pertinent to our testing, which in the case of an open-source application like FastCSV are generally well-documented. Finally, this section outlines the categories of tests cases for the software, including categories for certain non-functional requirements of the software.
- Test Cases: This section serves to categorize and document the specific tests that were performed. It also details the organization structure of the test cases and provides a traceability matrix to tie the tests into the specific software requirements.
- Test Environment: The purpose of this section is to describe the specifics around the test environment, both in terms of the hardware used in testing and the organization of the software environment. A primary purpose of this section is to enhance the reproducibility of the tests by specifying this aforementioned information.
- Testing Results: This section serves as a running log of the results of each test performed, including their status and any observations made during the testing.
- Recommendations on Software Quality: This section provides the team’s assessment of the quality of the software along with any recommendations for potential improvements.

# 2. Feature Description

[Describe the features of the selected open-source course application.]

Per the official FastCSV website, “FastCSV is a high-performance CSV parser and writer for Java.” The functionality of the system can be logically bifurcated into those features belonging to each of the CsvReader and CsvWriter classes. The website and related documentation outline the features and design goals of the software; however, based on the team’s exploration of the documentation and our own use of the software, the team believes that the following are the primary features of these classes.

- CsvReader

  - Ability to read standard, comma separated values from a file
  - Ability to configure the reading of the values via the following options
    - Field Separator - A field separator denotes the character used to logically delineate between the fields of data in the file. While the program defaults to utilizing commas, it provides the user with the option to select another field separator. For example, a user may wish to read in a file that is separated by semi-colons instead.
    - Quote Character – Using quote characters allows the parser to treat the data between the quote characters as a single field, even if it contains a field separator. For example, the user might want to read in a CSV field, which contains commas. In this case, the program defaults to allowing the user to surround that field with quotes, such as “ice cream, cake, and candy.” In this case, that phrase would be read in as a single field, excluding the quotation marks. In some instances, though, the user may wish to designate a different character to represent quotation marks, such as when the fields in a file contain lots of quotes. The user may therefore specify another character to use, such as a percentage sign. In this case %”ice cream, cake, and candy”% would be read in as a single field, including the quotation marks.
    - Different Field Counts – The program allows the user to set a configuration option that either enforces or does not enforce adherence to consistent field counts. For example, a csv file may have a first row with two columns (fields) and a second row with 3 columns. Depending on the user’s requirements, they may wish for the program to read this with no issue or to throw an exception. The program defaults to not enforcing this adherence, meaning that the CSV files may have varying numbers of columns per row.
    - BOM Headers – Certain programs, such as Microsoft Excel, generate CSV files that begin with a BOM (Byte Order Mark) header. Per the FastCSV website, the purpose of a BOM Header was originally to designate the encoding of the file, although now it is largely unnecessary, as almost all CSV files utilize UTF-8. The program provides the user with functionality to either detect (and ignore) the BOM header, which is useful if the file has a BOM header and the user does not want this to be included in their data, or to not detect the BOM header, which is the default behavior.
    - Comments – Certain CSV files may contain comments, which are often designated by a character, such as ‘#.’ FastCSV allows users to customize how the program handles these comments, by selecting a custom comment designator and by specifying whether comments should be skipped by the reader or read in as a field. For example, suppose the user processes a CSV file that has multiple comments that begin with '!' and the user does not want these comments to appear in their parsed data. The user can specify that any lines beginning with '!' should be skipped. They would designate the comment character and the comment behavior separately. The program defaults to '#' as the comment character and not skipping comments as the behavior.
    - Empty Lines – Certain CSV files may contain empty lines. By default, the program is configured to skip these empty lines (i.e. not read them in). Alternatively, if the user wishes to read these empty lines in as blank fields, the user can configure the program to do so.
  - Ability to handle the following less common situations:
    - Indexing CSV files – Provides the user with the option to read a large CSV file, while designating how many records belong on each “page” of the file. Once the file is initially parsed, the user can quickly access any individual page of the file without having to parse the file again. This can save substantial amounts of time if the user needs to access a certain page (for instance, paginated web data).
    - Field Modification – Provides the user with the ability to modify fields as they are being read. A common use case for this is trimming or stripping leading or trailing blank characters from a field, if applicable.
    - Reading Compressed CSV files – The program allows the user to read CSV files that were compressed using the gzip format. The program handles the extraction process and then reads the files in as if they were not compressed.
    - Ability to Automatically Map to Java Beans – Allows the user to configure the program to map the CSV data that is read directly into Java Beans, with minimal performance penalty.

- CsvWriter

  - Core Functionalities

    - Write CSV Records: Supports writing CSV records from arrays of strings or other data types. Fields are automatically separated using a configurable separator character and optionally quoted.
    - Line Delimiter Configuration: Allows setting the line-ending strategy (e.g., CR, LF, CRLF, or platform-specific), supporting cross-platform compatibility.
    - Field Quoting and Escaping: Provides a flexible mechanism for quoting fields that contain special characters. Escaping strategies can be applied to prevent misinterpretation of field separators, quotes, or newline characters.

  - Customization Options

    - Field Separator: The default separator is a comma (`,`), but this can be replaced with other characters such as semicolons (`;`), tabs (`\t`), or pipes (`|`) based on user needs.
    - Quote Character: Users can configure which character is used to quote fields. The default is the double-quote (`"`), but alternative characters such as the single-quote (`'`) can be specified.
    - Quote Strategy: FastCSV supports multiple quote strategies through the `QuoteStrategy` interface. Examples include:
      - Always quote all fields.
      - Quote only when necessary (e.g., if the field contains the separator or quote character).
      - Never quote (if fields are guaranteed to be safe).
    - Escape Mechanism: When quoting is enabled and a field contains the quote character itself, the character is duplicated to escape it. For example, the value `5" screw` becomes `"5"" screw"` in the CSV output.

  - Advanced Capabilities

    - Comment Writing: Supports writing comments into the CSV using a specified comment character (e.g., `#`). Comments appear as standalone lines in the output.
    - Null and Empty Value Handling: Allows configuration on whether `null` or empty strings should be written as blank fields, quoted empty fields (`""`), or omitted entirely.
    - Buffer Management: Internally buffered writing supports large-scale output without performance bottlenecks.
    - Output Stream and Writer Support: Users can direct output to various destinations, including files, network sockets, or in-memory buffers through Java’s `OutputStream` or `Writer` interfaces.
    - Auto-Flushing: Configurable behavior to control whether data should be flushed to the output stream automatically after each record.

  - Edge Case Handling
    - Handling Embedded Newlines: If a field includes a newline character (`\n` or `\r\n`), quoting ensures the record remains syntactically correct.
    - Control Character Restrictions: FastCSV enforces that the field separator, quote character, and comment character must all be distinct and must not be newline characters.
    - Exception Safety: Provides safeguards through exceptions for illegal states, such as unclosed records or invalid configurations (e.g., duplicate control characters).

# 3. Assumptions

## 3.1 Test Case Exclusions

FastCSV is a mature and feature-heavy library for reading and writing CSVs.  Since its initial release in 2015, a significant amount of additional functionality has been added.  The team began the project by thoroughly reviewing the FastCSV’s documentation, including the detailed instructions and tutorials outlined on their website.   It quickly became clear that, due to time constraints, the team would have to adopt a testing approach that thoroughly tested the primary uses of the software (based on the team’s independent research and discussions), while deemphasizing or even excluding certain scenarios from our testing.  

One of the team members has nearly two decades of experience utilizing CSV files, as they are very heavily used in business software applications, including general ledger, banking, and analytical systems.  Based on this knowledge, independent research, and the description in the FastCSV documentation, the team decided that their testing would exclude the following functionality:
-	CsvReader Customization settings:
    - acceptingCharsAfterQuotes
    -	maxBufferSize
-	Custom Callbank Handlers
-	Writing CSV with Comments Santhosh is this correct?
-	Reading from classpath

## 3.2 Test Tools, Formats, and Organizational Scheme

[List the testing tools, formats, and organizational schemes used.]

**Testing Tools**

The full list of test tools utilized are listed in [Section 4.3](#43-test-strategy), as related to testing strategy, and [Section 6](#6-test-environment), as related to the overall test environment.  The following, however, serves as an introduction to the tools used along with the reasoning behind their usage.
  -	IntelliJ IDEA Community Edition – a robustly featured IDE developed and owned by JetBrains.  While the Community Edition has slightly fewer features than the full edition, it is still very robust and more than capable for the scope of this project.  Since FastCSV is written in Java, the team decided to utilize IntelliJ as the IDE for testing. This was selected for several reasons:
    -	The team has experience using IntelliJ, so there would be little to no learning curve
    -	The other testing tools utilized (namely, JUnit and Cucumber) are known to work well with IntelliJ IDEA and the team has experience using these tools in conjunction with IntelliJ IDEA.
    -	IntelliJ IDEA is one of the most popular and supported Java IDEs on the market. 
  -	JUnit – an open-source testing framework that is widely used in industry and academia.  It uses a combination of assertions and annotations to organize and automate testing.  This was selected due to its popularity and the team’s experience in using JUnit for testing. 
  -	Cucumber –an open-source framework that allows for acceptance test criteria to be written in a natural language that both the technical and non-technical stakeholders can understand.  Cucumber utilizes .feature files to write these plain language tests, which are then directly linked to the Java code and JUnit tests that actually test the code.  The team selected Cucumber due to their experience using it and the simplicity of implementation.

**Testing Formats**

The testing formats utilized generally parallel those that were covered in the course.  These are explained in more detail, including the rational for choosing them, in [Section 4.2](#42-special-testing-considerations).  The individual test formats are also explained in great detail in [Section 5.2](#52-test-cases), alongside the test cases.

**Organizational Scheme**

Several organizational conventions were utilized when developing this testing document:
-	Unique Test Identification: In order to facilitate traceability, all test cases were assigned a unique identifier in the following format: 111-2-33 where:
  -	111 represents a three-letter code designating the type of testing that was performed
    -	IDM: input domain modeling
    -	GBT: graph based testing
    -	EXP: exploratory testing
    -	ACC: acceptance testing.
  -	2 represents a one or two character abbreviation for a subcategory of test
    -	W: a test that targets the CSVWriter functionality
    -	R: a test that targets the CSVReader functionality
    -	B: an exploratory test that is part of the Back Alley Tour
    -	C: an exploratory test that is part of the Collector’s Tour
    -	F: an exploratory test that is part of the FedEx Tour
    -	I: an exploratory test that is part of the Intellectual Tour
    -	L: an exploratory test that is part of the Landmark Tour
  -	33 represents the unique test number that is part of the preceding categorizations. 
-	Unique Requirement Identifiers
    -	Requirements targeting the CSVWriter functionality are formatted with REQ-W-XX where XX represents the requirement number
    -	Requirements targeting the CSVReader functionality are formatted with REQ-W-XX where XX represents the requirement number
-	Unique Block Identifiers - For Input Domain Testing, each input variable is partitioned into blocks.  Those blocks are uniquely numbered with a character representing the input variable and a number representing the partition number.

Additionally, GitHub is being used to organize all files related to testing.  The GitHub folder structure is as follows:

Exploratory Testing&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<-- Files related to Exploratory Testing<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- CsvTestFiles&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<-- Input and/or output files in CSV Format<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Images&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<-- Images supporting the exploratory testing<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Tests&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<-- .java test files<br>
GraphBasedTesting &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <-- Files related to Graph Based Testing<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- CsvTestFiles&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<-- Input files in CSV Format<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- ImageUMLs&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<-- PlantUML files for graphs<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Images&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<-- Image files of graphs<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Tests&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<-- .java test files<br>
InputDomainModeling  <-- Files related to Input Domain Modeling<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- CsvTestFiles&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<-- Input and/or output files in CSV Format<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Tests&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<-- .java test files<br>

# 4. Test Approach

## 4.1 Addressing Past Issues

[Explain how past issues or defects were addressed if applicable.]

As described in [Section 3.1](#31-test-case-exclusions), FastCSV is mature software, that was first released in 2015.  Since then, the application has been widely used and issues/defects have been reports and tracked in the Issues section of the project’s GitHub repository. The team reviewed the Issues section of the repository and it appears to be actively monitored and curated by the developer, with any issues being addressed and closed out, as appropriate.  In some cases, the issue was remedied via a new version of the software being released and in other cases, the issue was deemed out of scope for the software.  Additionally, the Issues section has been used to make feature suggestions or to request help using the software.  As of the date of this document, all open issues have been marked as cleared.  Due to the above, the team has decided that past issues or defects will not have a notable impact on our testing strategy.  

## 4.2 Special Testing Considerations

[Highlight any special considerations for testing.]

The team has noted the following special considerations when developing our testing plan:
-	As previously described, FastCSV is a Java library and not a standalone program.  Therefore, instead of focusing on interactions via a user interface, the team will be able to narrow the testing focus and perform deeper tests on the many configuration options of the library.  
-	FastCSV has extensive documentation, and the domain of CSV file reading and writing is well documented and unambiguous. This removes much of the uncertainty about the intended performance of the program and therefore allows the team to write tests with confidence around the intended outcomes.
-	As stated previously, the maturity level of the software and the active involvement of the developer decreases the likelihood of the team finding any major errors when performing the tests.  While this will not alter our testing plan significantly, it is important to note while interpreting the results. 
-	The code around the software has been refactored and enhanced for over a decade and the developer has utilized advanced object-oriented concepts and Java features to streamline performance.  The team recognizes that object-oriented complexity and testability are often inversely related and will consider this when developing tests.


## 4.3 Test Strategy

[Explain which testing techniques are used to test the different parts of the systems. Provide a rationale for the selection. Also include information on which tools, automation, and scripts are used to test each part of the system.]

There are two primary parts of FastCSV: CSVReader and CSVWriter. Both parts have deep functionality, with multiple uses and customization options (as described previously in [Section 2](#2-feature-description)). The team decided to apply various testing strategies across both parts of the software, to diversify testing and maximize coverage, while strategically targeting the most common uses of the software.

CSV files are commonly used as an application-agnostic format to transfer information between spreadsheets, from spreadsheets to other applications, or between non-spreadsheet applications. One of the authors has extensive experience in the finance industry and has seen CSV files used to transfer information between spreadsheets and general ledger, banking, and specialty subledger systems. These files are overwhelmingly formatted with a consistent number of columns between rows, with values separated by commas, and with no extraneous comment or blank rows. When designing and planning our testing approach, the team decided to focus predominantly on this most common usage, while also ensuring that edge case CSV format and program functionality was covered.

The following are the testing techniques that were utilized, along with a description of how the testing was performed:

**Input Domain Modeling**

[Description, Tools, and Rationale]

Per the Canvas module, Input Domain Modeling involves partitioning each parameter of a function into blocks of logically related input values and then testing a value from each of the blocks. In the case of CSVReader and CSVWriter, the a selection of input variables were chosen as the target parameters that were then partioned and tested. More about the variables, the chosen coverage criteria, and the selected tests can be found in [Section 5.2](#52-test-cases) below.

Input Domain Modeling was chosen to test CSVReader and CSVWriter methods for several practical reasons:

- Using Input Domain Modeling on these higher-level components provides the team with an opportunity to understand the major functionality of FastCSV early in the testing process. This understanding will assist in designing subsequent tests.
- Since FastCSV is already in production and the primary functionality is fully operational, testing at this level is a practical option at this early stage in the testing process. If this were an early sprint in an Agile project, this level of testing might not be available, since the functionality would not be fully developed.
- The components, and in particular the csv files themselves, are good targets for Input Domain Testing. The input domain of the csv files is practically infinite, but those possibilities can be logically partitioned into blocks representing both common use cases and edge cases. This allowed the team to develop a manageable framework for testing a large input space.

JUnit was utilized to test both CSVReader and CSVWriter.

**Graph Based Testing**

[Description, Tools, and Rationale]

The team utilzed Control Flow Graphs (CFGs) for our graph based testing. CFGs are used to abstract the flow of a section code into a graph in order design tests based on that abstraction. CFGs provide a visual representation of often complex functionality. Once the graph is created, it allows for generating test cases that ensure coverage of a the nodes and edges of the CFG in varying combinations of traversal. The team selected edge coverage for our criteria, as, per the Canvas module, it ensures "that each edge is traversed at least once."

Two components were chosen to test using Graph Based Testing: **`writeRecord(String... values)` method** and **`skipLines(final Predicate<String> predicate, final int maxLines)` method**. These methods were chosen as candidates for Graph Based Testing, due to their numerous branching decisions and the coverage of those branches that Graph Based Testing offers.

JUnit was utilized to test both methods. [PlantUML](https://plantuml.com) was used to design the CFGs. The [Graph Coverage web application](https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage) was utilized to further abstract the CFG into a standardized number node format.

**Exploratory Testing**

Exploratory testing is a less rigid approach to testing that can be utilized to augment traditional testing approaches.   The team’s exploratory tests began with defining “tours,” which, per the class slides, are “recommended testing charters.”  By reviewing the listing of predefined tours, the team was able to brainstorm appropriate tests that were not fully covered by the previous testing (input domain and graph-based).  Once a tour and a feature of the program was chosen, the team began to plan out the tests associated with the tour and determine a time limit of the testing session that was appropriate, given the nature of the tests.  Throughout the process, the team captured appropriate information (screen shots, test files, test scripts, etc.) to ensure that the process was repeatable. 

As mentioned above, the team generally used exploratory testing as a chance to supplement the tests conducted in the previous sections, including lesser used features and other types of testing, such as stress tests and testing on data ordering, flow, and enumeration.  The team utilized exploratory testing to achieve additional comfort over the quality of the system, given the time constraints surrounding the project. The guidance of the tour definitions also steered our testing in directions that we may not have considered without utilizing exploratory testing.

Generally, exploratory testing was performed in the IDE, using no additional tools. For several of the tests, however, Junit was utilized, as well.

[Description, Tools, and Rationale]

**Acceptance Testing**

[Description, Tools, and Rationale]

## 4.4 Test Categories

[Categorize the test cases, e.g., functional, performance, security, etc.]

All of tests that the team performed for FastCSV were functional.  The functionality for the software is deep and highly configurable, so the team planned to focus the testing on this functionality. 

Several of our tests also covered non-functional categories, though, as described in Section 4.2 of the Canvas Module:

-	Robustness Tests – Many of our tests also met the definition of Boundary Value tests.
    - System Defaults - Per the Canvas module, boundary tests can target default values.  FastCSV is configured with many default values that represent the most common use case of the system.  The majority of the tests conducted by the team include at least one or more configuration options that remain at their default values.  While this category is noted, the team has decided to not utilize System Defaults as an additional classification for the tests, since all tests would be labeled as such providing little additional value.
    - Special Values – Many of the team’s tests include testing edge cases or special values to ensure that they are handled properly by the system.  For example, some tests in the Input Domain Modeling section test how the system handles special characters, commas used as data (these are also used as field separators), etc. 
    -	Invalid Data – Several of the tests involve providing invalid data to the System and ensuring that the System handles the error gracefully.
-	Performance Tests – As part of our exploratory testing, the team tested the performance of the System while reading exceptionally large csv files.  
-	Stress Tests – The same tests that were used to test performance were also targeted at trying to cause a failure by reading exceptionally large csv files.

Given the above, in addition to Functional Tests, the team will classify tests using the following categories: Robustness-Special Values, Robustness-Invalid Data, Performance, and Stress.

Refer to [Section 7](#7-testing-results) below for a complete list of Tests, Categories, and Results.


# 5. Test Cases

## 5.1 Test Group Definition

[This is where you will define how the test cases will be structured and organized. Define test groups and subgroups for organizing test cases. Specify the objective for each group.]

The team's organization of test cases into groups and subgroups mirrors the organization of the test cases numbering pattern, as described in [Section 3.2](#32-test-tools-formats-and-organizational-scheme).  The tests are divided into four distinct testing groups, one for each type of testing performed:

|Group                 | Objective                                                                           |
|----------------------|-------------------------------------------------------------------------------------|
| Input Domain Modeling|                                                                                     |                                   
| Graph Based Testing  |                                                                                     |
| Exploratory Testing  |                                                                                     |
| Acceptance Testing   |                                                                                     |

Each of the main groups is further broken into subgroups:

The subgroups for Input Domain Modeling, Graph Based Testing, and Acceptance Testing are: CSVWriter and CSVReader, which are the two primary functionalites of the software that were being tested.

The subgroups for Exploratory Testing are Back Alley, Collector’s, FedEx, Intellectual, and Landmark, which correspond with the type of exploratory tour used.

By examining each test case number, the read can clearly determine both the category and subcategory of the test case.  For example, test IDM-W-01 is category Input Domain Modeling and subcategory Writer. Test case EXP-C-02 is category Exploratory Tour and subcategory Collector's Tour.  The test case identifier schema is defined in [Section 3.2](#32-test-tools-formats-and-organizational-scheme).

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

Each test row (IDM-W-01, IDM-W-02, ...) targets specific block combinations of input variables. The values column in each test shows the concrete inputs used for that scenario.

| Test #   | Purpose Description                                                                          |
| -------- | -------------------------------------------------------------------------------------------- |
| IDM-W-01 | Baseline test for normal values with all default settings.                                   |
| IDM-W-02 | Verify handling of `null` values with a strategy that forces quoting.                        |
| IDM-W-03 | Ensure empty strings are correctly quoted under a forced quote strategy.                     |
| IDM-W-04 | Validate escaping of special characters like comma, quotes, and newlines.                    |
| IDM-W-05 | Test alternate field separator (`;`) with normal string values.                              |
| IDM-W-06 | Use a non-default quote character (`'`) and ensure output is consistent.                     |
| IDM-W-07 | Apply a quoting strategy that forces quotes on non-empty fields.                             |
| IDM-W-08 | Change line delimiter to `LF` and test output of a value with an embedded newline character. |

**Test Set Definition**

| Test #   | `values`                             | `fieldSeparator` | `quoteCharacter` | `quoteStrategy`          | `lineDelimiter` | Covered Blocks     |
| -------- | ------------------------------------ | ---------------- | ---------------- | ------------------------ | --------------- | ------------------ |
| IDM-W-01 | `["hello", "world"]`                 | `','`            | `'"'`            | `null`                   | `CRLF`          | a3, b1, c1, d1, e1 |
| IDM-W-02 | `[null, null]`                       | `','`            | `'"'`            | `QuoteStrategies.ALWAYS` | `CRLF`          | a1, b1, c1, d2, e1 |
| IDM-W-03 | `[""]`                               | `','`            | `'"'`            | `QuoteStrategies.ALWAYS` | `CRLF`          | a2, b1, c1, d2, e1 |
| IDM-W-04 | `["comma, separated", "\"quoted\""]` | `','`            | `'"'`            | `null`                   | `CRLF`          | a4, b1, c1, d1, e1 |
| IDM-W-05 | `["a", "b"]`                         | `';'`            | `'"'`            | `null`                   | `CRLF`          | a3, b2, c1, d1, e1 |
| IDM-W-06 | `["x", "y"]`                         | `','`            | `'\''`           | `null`                   | `CRLF`          | a3, b1, c2, d1, e1 |
| IDM-W-07 | `["quoted"]`                         | `','`            | `'"'`            | `QuoteStrategies.ALWAYS` | `CRLF`          | a3, b1, c1, d2, e1 |
| IDM-W-08 | `["newline\ntext"]`                  | `','`            | `'"'`            | `null`                   | `LF`            | a4, b1, c1, d1, e2 |

The below table shows an alternate view of the above.  Each test number as columns (the "IDM-R-" prefix has been removed for formatting). Each tested input variable is listed in a row. The intersections show the specific block of the input variable that is being covered by a specific test. 

| test # (prefix = IDM-W-)  | 01  | 02  | 03  | 04  | 05  | 06  | 07  | 08  | 
| ------------------------- | --- | --- | --- | --- | --- | --- | --- | --- | 
| values                    | a3  | a1  | a2  | a4  | a3  | a3  | a3  | a4  | 
| fieldSeperator            | b1  | b1  | b1  | b1  | b2  | b1  | b1  | b1  | 
| quoteCharacter            | c1  | c1  | c1  | c1  | c1  | c2  | c1  | c1  | 
| quoteStrategy             | d1  | d2  | d2  | d1  | d1  | d1  | d2  | d1  | 
| lineDelimiter             | e1  | e1  | e1  | e1  | e1  | e1  | e1  | e2  | 

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

| Input Variable              | Type                   | Constraints                                     |
| --------------------------- | ---------------------- | ----------------------------------------------- |
| `file`                      | `Path`                 | Must point to a readable file; content can vary |
| `fieldSeparator`            | Single `char`          | Must not be a newline character.                |
| `quoteCharacter`            | Single `char`          | Must not be a newline character.                |
| `commentStrategy`           | `CommentStrategy` enum | Defines comment line behavior.                  |
| `commentCharacter`          | Single `char`          | Must not be a newline character.                |
| `ignoreDifferentFieldCount` | `boolean`              | `true` or `false`.                              |
| `skipEmptyLines`            | `boolean`              | `true` or `false`.                              |
| `detectBomHeader`           | `boolean`              | `true` or `false`.                              |

**Partitioning Characteristics into Blocks**

| Input Variable              | Blocks                                          | Values                                                                       | Related Tests       |
| --------------------------- | ----------------------------------------------- | ---------------------------------------------------------------------------- | ------------------- |
| `file`                      | Block a1: Valid CSV file of strings and numbers | [reader-file-a01.csv](/InputDomainModeling/CsvTestFiles/reader-file-a01.csv) | IDM-R-01            |
|                             | Block a2: CSV of special characters             | [reader-file-a02.csv](/InputDomainModeling/CsvTestFiles/reader-file-a02.csv) | IDM-R-02            |
|                             | Block a3: Single column CSV                     | [reader-file-a03.csv](/InputDomainModeling/CsvTestFiles/reader-file-a03.csv) | IDM-R-03            |
|                             | Block a4: Single row CSV                        | [reader-file-a04.csv](/InputDomainModeling/CsvTestFiles/reader-file-a04.csv) | IDM-R-04            |
|                             | Block a5: Very large CSV (1 million rows)       | [reader-file-a05.csv](/InputDomainModeling/CsvTestFiles/reader-file-a05.csv) | IDM-R-05            |
|                             | Block a6: CSV file with commas as data          | [reader-file-a06.csv](/InputDomainModeling/CsvTestFiles/reader-file-a06.csv) | IDM-R-06            |
|                             | Block a7: CSV file with quotes as data          | [reader-file-a07.csv](/InputDomainModeling/CsvTestFiles/reader-file-a07.csv) | IDM-R-07            |
|                             | Block a8: Uneven # of columns in rows           | [reader-file-a08.csv](/InputDomainModeling/CsvTestFiles/reader-file-a08.csv) | IDM-R-08, IDM-R-17  |
|                             | Block a9: Skipped Rows                          | [reader-file-a09.csv](/InputDomainModeling/CsvTestFiles/reader-file-a09.csv) | IDM-R-09, IDM-R-19  |
|                             | Block a10: Empty file                           | [reader-file-a10.csv](/InputDomainModeling/CsvTestFiles/reader-file-a10.csv) | IDM-R-10            |
|                             | Block a11: `';'` as field separator             | [reader-file-a11.csv](/InputDomainModeling/CsvTestFiles/reader-file-a11.csv) | IDM-R-11            |
|                             | Block a12: `'` as quotes                        | [reader-file-a12.csv](/InputDomainModeling/CsvTestFiles/reader-file-a12.csv) | IDM-R-12            |
|                             | Block a13: `#` as comments                      | [reader-file-a13.csv](/InputDomainModeling/CsvTestFiles/reader-file-a13.csv) | IDM-R-13, IDM-R-16  |
|                             | Block a14: `@` as comments                      | [reader-file-a14.csv](/InputDomainModeling/CsvTestFiles/reader-file-a14.csv) | IDM-R-14            |
|                             | Block a15: File with BOM header                 | [reader-file-a15.csv](/InputDomainModeling/CsvTestFiles/reader-file-a15.csv) | IDM-R-15            |
| `fieldSeparator`            | Block b1: Default                               | Defaults to `','`                                                            | all except IDM-R-11 |
|                             | Block b2: Alternative separator                 | `';'`                                                                        | IDM-R-11            |
| `quoteCharacter`            | Block c1: Default                               | Defaults to `'"'`                                                            | IDM-R-06, IDM-R-07  |
|                             | Block c2: Alternative quote                     | `'^'`                                                                        | IDM-R-12            |
| `commentStrategy`           | Block d1: Default                               | Defaults to `CommentStrategy.NONE`                                           | IDM-R-16            |
|                             | Block d2: `SKIP`                                | `CommentStrategy.SKIP`                                                       | IDM-R-13, IDM-R-14  |
| `commentCharacter`          | Block e1: Default                               | Defaults to `#`                                                              | IDM-R-13, IDM-R-16  |
|                             | Block e2: Alternate                             | `@`                                                                          | IDM-R-14            |
| `ignoreDifferentFieldCount` | Block f1: Default                               | Defaults to `true`                                                           | IDM-R-08            |
|                             | Block f2: `false`                               | `false`                                                                      | IDM-R-17            |
| `skipEmptyLines`            | Block g1: Default                               | Defaults to `true`                                                           | IDM-R-18            |
|                             | Block g2: `false`                               | `false`                                                                      | IDM-R-09            |
| `detectBomHeader`           | Block h1: Default                               | Defaults to 'false'                                                          | all except IDM-R-15 |
|                             | Block h2: `true`                                | `true`                                                                       | IDM-R-15            |

**Coverage Criteria**  
The "Each-Choice" coverage criterion was selected to ensure each block is tested at least once, providing broad coverage of file reading scenarios (e.g., valid input, edge cases) while keeping the test set manageable.

**Test Set Definition**<br>
The below table shows each test number as columns (the "IDM-R-" prefix has been removed for formatting). Each tested input variable is listed in a row. The intersections show the specific block of the input variable that is being covered by a specific test. For example, Test # 07 covers block a7 of the `file` input variable, block b1 of the `field seperator` input variable, block c1 of the `quote strategy` input variable and block h1 of the `detectBomHeader` input variable.

The code for the tests can be found in [TestReaderIDM.java](/InputDomainModeling/Tests/TestReaderIDM.java)

| test # (prefix = IDM-R-)  | 01  | 02  | 03  | 04  | 05  | 06  | 07  | 08  | 09  | 10  | 11  | 12  | 13  | 14  | 15  | 16  | 17  | 18  |
| ------------------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| file                      | a1  | a2  | a3  | a4  | a5  | a6  | a7  | a8  | a9  | a10 | a11 | a12 | a13 | a14 | a15 | a13 | a8  | a9  |
| fieldSeperator            | b1  | b1  | b1  | b1  | b1  | b1  | b1  | b1  | b1  | b1  | b2  | b1  | b1  | b1  | b1  | b1  | b1  | b1  |
| quoteCharacter            |     |     |     |     |     | c1  | c1  |     |     |     |     | c2  |     |     |     |     |     |     |
| commentStrategy           |     |     |     |     |     |     |     |     |     |     |     |     | d2  | d2  |     | d1  |     |     |
| commentCharacter          |     |     |     |     |     |     |     |     |     |     |     |     | e1  | e2  |     | e1  |     |     |
| ignoreDifferentFieldCount |     |     |     |     |     |     |     | f1  |     |     |     |     |     |     |     |     | f2  |     |
| skipEmptyLines            |     |     |     |     |     |     |     |     | g2  |     |     |     |     |     |     |     |     | g1  |
| detectBomHeader           | h1  | h1  | h1  | h1  | h1  | h1  | h1  | h1  | h1  | h1  | h1  | h1  | h1  | h1  | h2  | h1  | h1  | h1  |

| Test #   | Test Purpose/Description                                                                                                                           | Test Definition                                                                                                                                                                                                                                                                                                           |
| -------- | -------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| IDM-R-01 | Test a valid csv file of strings with default settings                                                                                             | Path file = Paths.get("[reader-file-a01.csv](/InputDomainModeling/CsvTestFiles/reader-file-a01.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));                                                                                                                                              |
| IDM-R-02 | Test a csv file of special characters with default settings                                                                                        | Path file = Paths.get("[reader-file-a02.csv](/InputDomainModeling/CsvTestFiles/reader-file-a02.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));                                                                                                                                              |
| IDM-R-03 | Test a csv file with one column per row with default settings                                                                                      | Path file = Paths.get("[reader-file-a03.csv](/InputDomainModeling/CsvTestFiles/reader-file-a03.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));                                                                                                                                              |
| IDM-R-04 | Test a csv file with one row and many columns with default <br>settings                                                                            | Path file = Paths.get("[reader-file-a04.csv](/InputDomainModeling/CsvTestFiles/reader-file-a04.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));                                                                                                                                              |
| IDM-R-05 | Test a very large csv file with one million rows with default <br>settings                                                                         | Path file = Paths.get("[reader-file-a05.csv](/InputDomainModeling/CsvTestFiles/reader-file-a05.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file))                                                                                                                                               |
| IDM-R-06 | Test a csv file with commas as data with default settings<br> including quoteCharacter                                                             | Path file = Paths.get("[reader-file-a06.csv](/InputDomainModeling/CsvTestFiles/reader-file-a06.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));                                                                                                                                              |
| IDM-R-07 | Test a csv File that has quotes as data with default settings<br> including quoteCharacter                                                         | Path file = Paths.get("[reader-file-a07.csv](/InputDomainModeling/CsvTestFiles/reader-file-a07.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));                                                                                                                                              |
| IDM-R-08 | Test a malformed csv file with uneven columns in rows with <br>default settings including default ignoreDifferentFieldCount value                  | Path file = Paths.get("[reader-file-a08.csv](/InputDomainModeling/CsvTestFiles/reader-file-a08.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));                                                                                                                                              |
| IDM-R-09 | Test a csv File with empty rows with default settings except<br> for alternative skipEmptyLines option                                             | Path file = Paths.get("[reader-file-a09.csv](/InputDomainModeling/CsvTestFiles/reader-file-a09.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .skipEmptyLines(false).ofCsvRecord(file));                                                                                             |
| IDM-R-10 | Test a blank csv file with default settings                                                                                                        | Path file = Paths.get("[reader-file-a10.csv](/InputDomainModeling/CsvTestFiles/reader-file-a10.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));                                                                                                                                              |
| IDM-R-11 | Test a csv file with ';' as separators with default settings<br> and alternative fieldSeparator ';'                                                | Path file = Paths.get("[reader-file-a11.csv](/InputDomainModeling/CsvTestFiles/reader-file-a11.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .fieldSeparator(';') <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(file));                                                                    |
| IDM-R-12 | Test a csv file with '^' as quote character with default <br>settings and alternative quoteCharacter '^'                                           | Path file = Paths.get("[reader-file-a12.csv](/InputDomainModeling/CsvTestFiles/reader-file-a12.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .quoteCharacter('^') <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(file));                                                                    |
| IDM-R-13 | Test a csv file with blank lines with default settings <br>except for CommentStrategy of 'SKIP'                                                    | Path file = Paths.get("[reader-file-a13.csv](/InputDomainModeling/CsvTestFiles/reader-file-a13.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .commentStrategy(CommentStrategy.SKIP) <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(file));                                                  |
| IDM-R-14 | Test a csv file with default settings except for using an<br> alternate comment character '@' and CommentStrategy of 'SKIP'                        | Path file = Paths.get("[reader-file-a14.csv](/InputDomainModeling/CsvTestFiles/reader-file-a14.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .commentStrategy(CommentStrategy.SKIP) <br> &nbsp; &nbsp; &nbsp; .commentCharacter('@') <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(file)); |
| IDM-R-15 | Test a csv file that has a BOM header with default settings<br> except detectBOMHeader set to true.                                                | Path file = Paths.get("[reader-file-a15.csv](/InputDomainModeling/CsvTestFiles/reader-file-a15.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .detectBomHeader(true) <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(file));                                                                  |
| IDM-R-16 | Test a csv file with comments and default settings                                                                                                 | Path file = Paths.get("[reader-file-a13.csv](/InputDomainModeling/CsvTestFiles/reader-file-a13.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));                                                                                                                                              |
| IDM-R-17 | Test a malformed csv file with uneven columns in rows with <br>default settings including alternate<br> ignoreDifferentFieldCount value of 'false' | CsvReader<CsvRecord> csv = CsvReader.builder() <br> &nbsp; &nbsp; &nbsp; .ignoreDifferentFieldCount(false) <br> &nbsp; &nbsp; &nbsp; .ofCsvRecord(Paths.get("[reader-file-a08.csv](/InputDomainModeling/CsvTestFiles/reader-file-a08.csv)"));                                                                             |
| IDM-R-18 | Test a csv file with empty lines and default settings                                                                                              | Path file = Paths.get("[reader-file-a09.csv](/InputDomainModeling/CsvTestFiles/reader-file-a09.csv)");<br>CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file));                                                                                                                                              |

**Execution Results**  
|Test # |Expected Results |Results |
|--------|-----------------------------------------------------------------------------------------------------------|---------|
|IDM-R-01|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}` |Pass |
|IDM-R-02|`{{"!@#^(&@$(*@", "*#$&(@$@", "+)_(@*&)(&"}, {"<>//;[>", "~~~~~~", "/*-*-+"}, {"(*&*#(@$)", "#$@$@#$#", "+_)(*&^%$#@!~"}, {"~!@#$%^&", "{}{}{}", "///"}}`|Pass |
|IDM-R-03|`{{"Adam"}, {"Santhosh"}, {"Bill"}, {"Ted"}, {"George"}, {"Thomas"}, {"Heather"}, {"Jane"}}`|Pass |
|IDM-R-04|`{"Adam", "Santhosh", "Bill", "Ted", "George", "Thomas", "Heather", "Jane"}`|Pass |
|IDM-R-05|Row 0: `{"Anne","Mack","(907) 789-3686")`<br> Row 191,783: `{"Sallie","Moss","(838) 455-8563"}`<br>Row 405,480 `{"Bill","Lee","(443) 584-2867"}`<br>Row 652,054 `{"Inez","Foster","(557) 675-1730"}`<br>Row 999,999 `{"Carolyn","Todd","(604) 860-4898"}`|Pass |
|IDM-R-06|`{{"Adam", "24,324"}, {"Santhosh", "56,434"}, {"Bill", "23,145"}, {"Ted", ",,,,,"}, {"George", "1,"}, {",", ","}, {"Heather", "12,111"}, {"1,", "1,1,1,1,1"}}`|Pass |
|IDM-R-07|`{{"\"Adam\"", "1"}, {"\"Santhosh\"", "\"2\""}, {"\"\"", "\"\""}}`|Pass |
|IDM-R-08|`{{"Adam", "1", "2"}, {"Santhosh", "3"}, {"Pennsylvania", "1", "3", "4"}, {"Penn State", "1", "2", "3", "4", "5", "6"}}`|Pass |
|IDM-R-09|Row 2: `""`|Pass |
|IDM-R-10|`recs.size()=0`|Pass |
|IDM-R-11|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass |
|IDM-R-12|`{{"\"Adam\"", "1"}, {"\"Santhosh\"", "\"2\""}, {"\"\"", "\"\""}}`|Pass |
|IDM-R-13|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass |
|IDM-R-14|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass |
|IDM-R-15|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass |
|IDM-R-16|`{{"apple", "banana", "cantaloupe"}, {"#This is a comment"},{"11", "22", "33"},{"#This is another comment"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass |
|IDM-R-17|Throws `CsvParseException.class`|Pass |
|IDM-R-18|`{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}}`|Pass |

### 5.2.2 Graph Based Test Cases

#### 5.2.2.1 CsvReader Graph Based Test Cases

**Identified Component for Graph-Based Testing**  
The `skipLines(final Predicate<String> predicate, final int maxLines)` method was chosen as a suitable target for graph-based testing. Certain CSV files may have header information, such as comments or column headers, that the user may wish to skip. This function accepts a `Predicate<String> predicate` to define the header row will be the first non-skipped row of information and an integer to represent the maximum number of rows that should be searched for the predicate condition. It returns an integer representing the row in which the predicate is found.

**Control Flow Abstraction**

The pseudocode below illustrates the logic of the method `skipLines(final Predicate<String> predicate, final int maxLines)`. Note that for ease of abstraction all potential throws have been modelled as 'if' statements :

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

| Node # | Action                                                      |
| ------ | ----------------------------------------------------------- |
| 1      | `if (Objects.requireNonNull(predicate) == null)`            |
| 2      | `Throw NullPointerException`                                |
| 3      | `if(maxLines < 0):`                                         |
| 4      | `Throw IllegalArgumentException`                            |
| 5      | `if (maxLines == 0):`                                       |
| 6      | `return 0`                                                  |
| 7      | `i = 0` First Part of For Loop                              |
| 8      | `i < maxLines` Condition check in For Loop                  |
| 9      | `if(csvParser.peekline throws IOException)`                 |
| 10     | `Throw CSVParseException`                                   |
| 11     | `Thow IOException`                                          |
| 12     | `if(test(line)==true)`                                      |
| 13     | `return i`                                                  |
| 14     | `if(!csvParser.skipLine(line.length()) throws IOException)` |
| 15     | `Throw IOException`                                         |
| 16     | `if(!csvParser.skipLine(line.length()))`                    |
| 17     | `Throw CsvParseException`                                   |
| 18     | `i++` Loop back to 8                                        |

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

| Control Flow Diagram                                                                                 | Condensed Node Diagram                                                                              |
| ---------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| <img src="GraphBasedTesting/Images/csvreader-skiplines.png" alt="Control Flow Diagram" width="500"/> | <img src="GraphBasedTesting/Images/NodeDiagramReader.png" alt="Node Diagram - Reader" width="500"/> |

[PlantUML Code](/GraphBasedTesting/ImageUMLs/CFGReader.puml)

---

**Testing Coverage Criteria**  
Edge coverage was selected as the testing coverage criteria, in order to ensure that every edge is covered by testing at least once. Edge coverage was considered appropriate for this function, as every edge is able to be tested using nine test cases, a relatively low number of cases given the rather complex branching of the graph.

The code for the tests can be found in [TestReaderGraph.java](/GraphBasedTesting/Tests/TestReaderGraph.java)

**Test Cases**  
|Test # |Test Purpose/Description |Test Definition (Refer to Above Graph) |CSV File Used in Testing|
|----------|---------------------------------------------|------------------------------------------------|-----------------------------------------------|
|GBT-R-01 |The desired header is found in the first iteration of the for loop | 1→3→5→7→8→9→12→13 |[reader-file-g01.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g01.csv) |
|GBT-R-02 |The desired header is found in the second interation of the for loop| 1→3→5→7→8→9→12→14→16→18→8→9→12→13 |[reader-file-g02.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g02.csv) |
|GBT-R-03 |`Predicate<String>` is null| 1→2|[reader-file-g01.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g01.csv)|  
|GBT-R-04 |`maxLines` is negative | 1→3→4|[reader-file-g01.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g01.csv) |
|GBT-R-05 |`maxLines` equals 0 | 1→3→5→6|[reader-file-g02.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g02.csv)
|GBT-R-06 |The desired header is not found within specified max lines (`maxLines`) which is less than max lines in file| 1→3→5→7→8→9→12→14→16→18→8→10 |[reader-file-g02.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g02.csv) |
|GBT-R-07 |The desired header is not found within specified max lines (`maxLines`) which is greater than max lines in file| 1→3→5→7→8→9→12→14→16→18→8→9→12→14→16→17 |[reader-file-g03.csv](/GraphBasedTesting/CsvTestFiles/reader-file-g03.csv) |
|GBT-R-08 |`IOException` is thrown when running csvParser.peekline| 1→3→5→7→8→9→11| TODO|
|GBT-R-09 |`IOException` is thrown when running csvParser.skipLine| 1→3→5→7→8→9→12→14→15| TODO|

**Execution Results**  
|Test # |Expected Results |Results |
|--------|-----------------------------------------------------------------------------------------------------------|---------|
|GBT-R-01| Returns 0 |Pass |
|GBT-R-02| Returns 1 |Pass |
|GBT-R-03| Throws NullPointerException |Pass |
|GBT-R-04| Throws IllegalArgumentException |Pass |
|GBT-R-05| Returns 0 |Pass |
|GBT-R-06| Throws CsvParseException |Pass |
|GBT-R-07| Throws CsvParseException |Pass |
|GBT-R-08| Throws IOException | TODO|
|GBT-R-09| Throws IOException | TODO|

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

| Control Flow Diagram                                                                 | Condensed Node Diagram                                                                                        |
| ------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------- |
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

| Test # | Test Purpose/Description                              | Test Definition (Graph Edge Path) |
| ------ | ----------------------------------------------------- | --------------------------------- |
| GBT-W-01 | Tests a null value that triggers quoteNull strategy | 1→2→3→4→5→6→7→14→15→16            |
| GBT-W-02 | Tests empty string with quoteEmpty strategy         | 1→2→3→4→6→8→9→14→15→16            |
| GBT-W-03 | Tests two null values written back-to-back          | 1→2→3→4→6→7→14→3→4→6→7→14→15→16   |
| GBT-W-04 | Tests unescaped value that needs quoting            | 1→2→3→4→6→8→10→12→13→14→15→16     |
| GBT-W-05 | Tests escaped value that needs quoting and escaping | 1→2→3→4→6→8→10→11→13→14→15→16     |

**Execution Results**

| Test # | Expected Result                                    | Result |
| ------ | -------------------------------------------------- | ------ |
| GBT-W-01 | Output: `""` followed by line delimiter          | Pass   |
| GBT-W-02 | Output: `""` followed by line delimiter          | Pass   |
| GBT-W-03 | Output: `""`,`""` followed by line delimiter     | Pass   |
| GBT-W-04 | Output: `"value"` followed by line delimiter     | Pass   |
| GBT-W-05 | Output: `"esca\"ped"` followed by line delimiter | Pass   |

The code for the tests can be found in [TestWriterGraph.java](GraphBasedTesting/Tests/TestWriterGraph.java)

### 5.2.3 Exploratory Test Cases

#### 5.2.3.1 Back Alley Tour - Part I

**Test Description**

| Item                               | Details                                                                                                                  |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------------ |
| Test Tour                          | Back Alley Tour                                                                                                          |
| Description                        | To test several of more minor features of the program, specifically those involving compressed files and indexed reading |
| Test Duration                      | 90 minutes                                                                                                               |
| Tester                             | Adam Slager                                                                                                              |
| Further Testing <br> Opportunities | Potential to continue testing additional minor features                                                                  |

**Test Protocol**

| NR       | What Done                                                                               | Status        | Comment                                                                               | Test Artifacts                                                                                                                                                                                               |
| -------- | --------------------------------------------------------------------------------------- | ------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| EXP-B-01 | Wrote test case to read small compressed file and test the output                       | No Exceptions | Read in 4 rows and three columns without exception                                    | - [Input File](/ExploratoryTesting/CsvTestFiles/reader-file-a01.csv.gz) <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlley_1.png) |
| EXP-B-02 | Wrote test case to read very large compressed file and test the output                  | No Exceptions | File contains 10M entries                                                             | - Input File Excluded Due to Size <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlley_1.png)                                       |
| EXP-B-03 | Wrote test case to write compressed file and subsequently read the same compressed file | No Exceptions | Wrote and read in 4 rows and 3 columns without exception                              | - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlley_1.png)                                                                              |
| EXP-B-04 | Tested indexed reading of very large (100M line) file                                   | No Exceptions | - Initial file read under 10000ms<br>- Subequent indexed page reads took 12ms or less | - [Test Script](/ExploratoryTesting/Tests/BackAlleyTourIndex.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlley_2.png)                                                                         |

#### 5.2.3.2 Back Alley Tour - Part II

**Test Description**

| Item                               | Details                                                                                   |
| ---------------------------------- | ----------------------------------------------------------------------------------------- |
| Test Tour                          | Back Alley Tour Part 2                                                                    |
| Description                        | Continue testing minor features, including JavaBeans and handlings white space characters |
| Test Duration                      | 90 minutes                                                                                |
| Tester                             | Adam Slager                                                                               |
| Further Testing <br> Opportunities | N/A                                                                                       |

**Test Protocol**

| NR       | What Done                                                                             | Status        | Comment                                                                    | Test Artifacts                                                                                                                                                                                                         |
| -------- | ------------------------------------------------------------------------------------- | ------------- | -------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| EXP-B-05 | Test mapping of csv file to Java beans                                                | No Exceptions | Read in 3 row, 5 columns of data with mixed types and stored as Java Beans | - [Input File](/ExploratoryTesting/CsvTestFiles/testJavaBeans.csv) <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour2_JB.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlleyJB_1.png)          |
| EXP-B-06 | Test trimming of standard ASCII space characters preceding and following value in CSV | No Exceptions | File contained no unicode spaces. FastCSV successfully eliminated spaces   | - [Input File](/ExploratoryTesting/CsvTestFiles/testTrimSpaces.csv) <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour2_Modify.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlleyTrim_1.png)   |
| EXP-B-07 | Tested trimming of unicode space characters preceding and following value in CSV      | No Exceptions | Program correctly did not remove unicode space characters                  | - [Input File](/ExploratoryTesting/CsvTestFiles/testStripSpaces.csv) <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour2_Modify.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlleyTrim_2.png)  |
| EXP-B-08 | Tested stripping of unicode space characters preceding and following value in CSV     | No Exceptions | Program correctly removed unicode space characters                         | - [Input File](/ExploratoryTesting/CsvTestFiles/testStripSpaces.csv) <br> - [Test Script](/ExploratoryTesting/Tests/BackAlleyTour2_Modify.java)<br>- [Test Execution](/ExploratoryTesting/Images/BackAlley_Strip1.png) |

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

| NR     | What Done                                                                  | Status        | Comment                                                                                           | Test Artifacts                                                     |
| ------ | -------------------------------------------------------------------------- | ------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------ |
| EXP-C-01 | Read `collector-input.csv`                                                 | No Exceptions | 4 rows, 3 columns; file loaded without error                                                      | [Input File](/ExploratoryTesting/CsvTestFiles/collector-input.csv) |
| EXP-C-02 | Extracted and stored all unique values of `amount` in a set                | No Exceptions | Set contained 3 entries: `500`, `600`, `700`; duplicates correctly removed                        | [Test Script](/ExploratoryTesting/Tests/CollectorsTour.java)       |
| EXP-C-03 | Printed count of unique values to console                                  | No Exceptions | Console log showed expected value count = 3                                                       | TODO(add screenshot)                                               |
| EXP-C-04 | Re-ran test after modifying data to include lowercase and formatted values | No Exceptions | Collector distinguished values with formatting; recommend normalization pass in future iterations | TODO(add screenshot)                                               |

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

| NR                 | What Done                                                      | Status                                                           | Comment                                                                                           | Test Artifacts                                                                                                           |
| ------------------ | -------------------------------------------------------------- | ---------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------ |
| EXP-F-01             | Read `fedex-input.csv` and extracted columns `id` and `amount` | No Exceptions                                                    | Correct values were read from input, verified manually                                            | [Test Script](/ExploratoryTesting/Tests/FedExTour.java)<br>[Input CSV](/ExploratoryTesting/CsvTestFiles/fedex-input.csv) |
| EXP-F-02             | Wrote selected columns to `fedex-output.csv` using `CsvWriter` | No Exceptions                                                    | File format and values were correct in output. Fields aligned and properly quoted where necessary | [Output](/ExploratoryTesting/CsvTestFiles/fedex-output.csv)                                                              |
| FastCSV/app/inputs | 4                                                              | Opened output in spreadsheet viewer and verified layout visually | No Exceptions                                                                                     | Output is viewable and properly delimited by common spreadsheet tools                                                    |

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

| NR     | What Done                                                                          | Status        | Comment                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | Test Artifacts                                                                                                                                                                                         |
| ------ | ---------------------------------------------------------------------------------- | ------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| EXP-I-01 | Tested reading of 100 million line file using CSVReader                            | No Exceptions | - Created 100 million line test file using below website with columns for 'first', 'last', and 'phone' https://www.convertcsv.com/generate-test-data.htm<br>- Wrote and executed Java program to read in file and count number of lines read.<br> - Noted that run time for processing file was 9636ms                                                                                                                                                                                                | - [Test Script](/ExploratoryTesting/Tests/IntellectualTour1.java)<br>- [Test Execution](/ExploratoryTesting/Images/E1_Test1.png)                                                                       |
| EXP-I-02 | Tested reading of ~900 million line file using CSV Reader                          | No Exceptions | - Created ~900 million line test file using below website and manually copying and pasting data with columns for 'first', 'last', and 'phone' https://www.convertcsv.com/generate-test-data.htm<br>- Wrote and executed Java program to read in file and count number of lines read.<br> - Noted that run time for processing file was 85121ms                                                                                                                                                        | - [Test Script](/ExploratoryTesting/Tests/IntellectualTour1.java)<br>- [Test Execution](/ExploratoryTesting/Images/E1_Test2.png)                                                                       |
| EXP-I-03 | Tested reading and writing of 100 million line file using CSVReader and CSVWriter  | No Exceptions | - Wrote Java program to read in lines from 100 million line file created above using CSVReader and immediately write those lines to a new csv file using CSVWriter<br> - Executed program and noted that run time for processing was 20486ms<br>- Used Windows fc to compare files and noted that they were exact duplicates of each other                                                                                                                                                            | - [Test Script](/ExploratoryTesting/Tests/IntellectualTour1.java)<br>- [Test Execution](/ExploratoryTesting/Images/E1_Test3a.png)<br>- [Output Verification](/ExploratoryTesting/Images/E1_Test3c.png) |
| EXP-I-04 | Tested reading and writing of ~900 million line file using CSVReader and CSVWriter | No Exceptions | - Wrote Java program to read in lines from ~900 million line file created above using CSVReader and immediately write those lines to a new csv file using CSVWriter<br> - Executed program and noted that run time for processing was 176041ms<br>- Attemped to use Windows FC to compare files, but file size was too large. This is a limitation of Windows FC and not the program being tested.<br>- Visually compared size of files with Windows Explorer and noted that files appear identifical | - [Test Script](/ExploratoryTesting/Tests/IntellectualTour1.java)<br>- [Test Execution](/ExploratoryTesting/Images/E1_Test4a.png)<br>- [Output Verification](/ExploratoryTesting/Images/E1_Test4b.png) |

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

| NR     | What Done                                                   | Status        | Comment                                                           | Test Artifacts                                                       |
| ------ | ----------------------------------------------------------- | ------------- | ----------------------------------------------------------------- | -------------------------------------------------------------------- |
| EXP-L-01 | Loaded original data from `landmark-input.csv`              | No Exceptions | Verified 4 rows and 3 columns exist                               | [Input CSV](/ExploratoryTesting/CsvTestFiles/landmark-input.csv)     |
| EXP-L-02 | Shuffled rows randomly and wrote to `landmark-temp.csv`     | No Exceptions | Manual comparison confirms sequence change, but data preserved    | [Temp Output](/ExploratoryTesting/CsvTestFiles/landmark-temp.csv)    |
| EXP-L-03 | Read shuffled file and wrote to final `landmark-output.csv` | No Exceptions | Verified that structure remained unchanged after second I/O pass  | [Final Output](/ExploratoryTesting/CsvTestFiles/landmark-output.csv) |
| EXP-L-04 | Compared first and second output using diff tool            | No Exceptions | Differences observed only in row order; values remained identical |                                                                      |

## 5.3 Traceability Matrix

[Create a traceability matrix to map requirements to test cases]

The traceability matrix maps the system’s formal requirements to the test cases designed and executed by the team. This mapping ensures that all functional requirements for the CsvReader and CsvWriter components have been validated through specific and targeted tests. The matrix also provides a means of verifying test completeness and assessing coverage gaps, if any.

**Formal Requirements: CsvReader**

| Requirement ID | Description                                                                                          |
| -------------- | -----------------------------------------------------------------------------------------------------|
| REQ-R-01         | The system shall read CSV files with configurable field separators.                                |
| REQ-R-02         | The system shall handle fields enclosed in configurable quote characters.                          |
| REQ-R-03         | The system shall support comment lines with configurable comment characters.                       |
| REQ-R-04         | The system shall skip or retain comment lines based on the configured comment strategy.            |
| REQ-R-05         | The system shall correctly parse CSV files with inconsistent column counts.                        |
| REQ-R-06         | The system shall skip or retain empty lines based on configuration.                                |
| REQ-R-07         | The system shall detect and handle BOM headers if enabled.                                         |
| REQ-R-08         | The system shall read large CSV files with acceptable performance.                                 |
| REQ-R-09         | The system shall support reading compressed CSV files (gzip).                                      |
| REQ-R-10         | The system shall support mapping CSV records into JavaBeans.                                       |
| REQ-R-11         | The system shall support skipping lines conditionally using a predicate and line limit.            |
| REQ-R-12         | The system shall throw appropriate exceptions for malformed CSV input.                             |
| REQ-R-13         | The system shall read standard CSV files (comma separated) with no additional configuration changes|
| REQ-R-14         | The system shall support the automatic trimming and stripping of space characters                  |

**Formal Requirements: CsvWriter**

| Requirement ID | Description                                                                                            |
| -------------- | ------------------------------------------------------------------------------------------------------ |
| REQ-W-01         | The system shall write CSV records using configurable field separators.                              |
| REQ-W-02         | The system shall apply configurable quote characters when writing CSV fields.                        |
| REQ-W-03         | The system shall implement configurable quote strategies, including "always" and "never".            |
| REQ-W-04         | The system shall handle null and empty values correctly during writing.                              |
| REQ-W-05         | The system shall support different line delimiters (e.g., CRLF, LF).                                 |
| REQ-W-06         | The system shall escape special characters such as commas, quotes, and newlines.                     |
| REQ-W-07         | The system shall write comments using configurable comment characters.                               |
| REQ-W-08         | The system shall throw exceptions for invalid configurations (e.g., overlapping control characters). |
| REQ-W-09         | The system shall support writing to OutputStreams and Writers.                                       |
| REQ-W-10         | The system shall flush output automatically or manually as configured.                               |
| REQ-W-11         | The system shall write standard CSV files (comma separated) with no additional configuration changes |

**Traceability Matrix**

| Test Case ID | Description                                                                  |
| ------------ | ---------------------------------------------------------------------------- |
| TC-101       | Input Domain Modeling (IDM) test cases for CsvReader and CsvWriter           |
| TC-102       | Exploratory testing sessions and tours, including edge case and stress tests |
| TC-103       | Graph-based test cases for CsvReader, especially control flow tests          |
| TC-104       | Cucumber-based acceptance tests for CsvWriter (Given–When–Then)              |

| Requirement                                                         | TC-101 | TC-102 | TC-103 | TC-104 |
| ------------------------------------------------------------------- | ------ | ------ | ------ | ------ |
| REQ-R-01: Read CSV with configurable field separators               | X      |        |        |        |
| REQ-R-02: Handle fields enclosed in quote characters                | X      |        |        |        |
| REQ-R-03: Support comment lines with configurable comment character | X      |        |        |        |
| REQ-R-04: Skip or retain comments based on strategy                 | X      |        |        |        |
| REQ-R-05: Parse inconsistent column counts                          | X      |        |        |        |
| REQ-R-06: Skip or retain empty lines                                | X      |        |        |        |
| REQ-R-07: Handle BOM headers                                        | X      |        |        |        |
| REQ-R-08: Read large CSV files with good performance                | X      |        |        |        |
| REQ-R-09: Read compressed CSV files (gzip)                          |        | X      |        |        |
| REQ-R-10: Map CSV records to JavaBeans                              |        | X      |        |        |
| REQ-R-11: Conditional line skipping via predicate                   | X      |        | X      |        |
| REQ-R-12: Handle malformed input with exceptions                    | X      |        |        |        |
| REQ-W-13: Read "standard" csv files w/no config changes             | X      |        | X      |        |
| REQ-W-01: Write CSV with configurable separators                    | X      |        | X      |        |
| REQ-W-02: Apply configurable quote characters                       | X      |        |        | X      |
| REQ-W-03: Implement quote strategies                                | X      |        | X      | X      |
| REQ-W-04: Handle null and empty values                              | X      |        | X      |        |
| REQ-W-05: Support different line delimiters                         | X      |        |        | X      |
| REQ-W-06: Escape special characters                                 | X      |        |        | X      |
| REQ-W-07: Write comments to output                                  |        |        | X      |        |
| REQ-W-08: Throw exceptions for invalid configurations               |        | X      |        | X      |
| REQ-W-09: Write to OutputStream and Writer                          | X      | X      |        |        |
| REQ-W-10: Control flushing behavior                                 | X      |        |        | X      |
| REQ-W-11: Write "standard" csv files w/no config changes            | X      |        |        |        |

<details>
<summary>Alternate representation</summary>

| Requirement ID   | Validated By Test Cases                                                                  |
| -----------------| ---------------------------------------------------------------------------------------- |
| **REQ-R-01**     | IDM-R-11                                                                                 |
| **REQ-R-02**     | IDM-R-06, IDM-R-07, IDM-R-12                                                             |
| **REQ-R-03**     | IDM-R-13, IDM-R-14                                                                       |
| **REQ-R-04**     | IDM-R-13, IDM-R-14, IDM-R-16                                                             |
| **REQ-R-05**     | IDM-R-08, IDM-R-17                                                                       |
| **REQ-R-06**     | IDM-R-09, IDM-R-18                                                                       |
| **REQ-R-07**     | IDM-R-15                                                                                 |
| **REQ-R-08**     | IDM-R-05                                                                                 |
| **REQ-R-09**     | Exploratory Test - Collector's Tour (Gzip File Parsing)                                  |
| **REQ-R-10**     | Exploratory Test - Landmark Tour (Java Bean Mapping)                                     |
| **REQ-R-11**     | GBT-R-01 to GBT-R-09 (Graph-based tests for `skipLines(Predicate, int)`)                 |
| **REQ-R-12**     | IDM-R-17 (throws `CsvParseException`), GBT-R-06, GBT-R-07, GBT-R-09                      |
| **REQ-W-01**     | IDM-W-01, IDM-W-05                                                                       |
| **REQ-W-02**     | IDM-W-01, IDM-W-06                                                                       |
| **REQ-W-03**     | IDM-W-02, IDM-W-03, IDM-W-07                                                             |
| **REQ-W-04**     | IDM-W-02, IDM-W-03                                                                       |
| **REQ-W-05**     | IDM-W-08                                                                                 |
| **REQ-W-06**     | IDM-W-04                                                                                 |
| **REQ-W-07**     | Exploratory Test - Back Alley Tour (writing comments)                                    |
| **REQ-W-08**     | Exploratory Test - Intellectual Tour (invalid config, e.g., fieldSeparator == quoteChar) |
| **REQ-W-09**     | Exploratory Test - FedEx Tour (writing to Writer and OutputStream)                       |
| **REQ-W-10**     | Exploratory Test - After-Hours Tour (autoFlush behavior validation)                      |

**Notes**:

- IDM refers to Input Domain Modeling test cases, e.g., `IDM-W-01` for CsvWriter or `IDM-R-06` for CsvReader.
- GBT-R-\* refers to Graph-Based test cases for CsvReader's `skipLines()` method.
- Exploratory Tours reference specific session-based tests targeting real-world behavior, error-handling, and performance.
- This matrix helps ensure that every specified requirement is traceable to at least one concrete test execution, confirming test coverage.
</details>

# 6. Test Environment

This section outlines the technical environment in which the testing activities were conducted. It includes hardware and software specifications, testing tools, and an overview of the architectural setup used to execute and validate tests for both CsvReader and CsvWriter components.

## 6.1 Multiple Test Environments

[List the different test environments used if applicable.]

All tests were executed in a local development environment. In order to validate the behavior across typical user setups, tests were run on two separate systems:

- Environment A: NixOS 24.11, Java 17, VSCode 1.83.0, Gradle 8.4.1
- Environment B: TODO:ADAM

This dual-environment testing ensured cross-platform consistency and compatibility.

### 6.2 Schematic Diagram

[Provide a schematic diagram of the test environment setup if applicable.]

The test architecture was relatively straightforward. All JUnit-based tests (IDM and graph) and exploratory tests were implemented as part of a Maven project and Gradle project. The schematic below shows a rough representation of the test setup for both CsvReader and CsvWriter:

<img src="Images/TestEnv.png" alt="Test Environment Diagram" width="500"/>

<details>
<summary>PlantUML</summary>

```plantuml
@startuml
actor Tester
package "Test Environment" {
  node "Development Machine" {
    component "JUnit 5" as J1
    component "Cucumber" as C1
    component "Test Files (.csv)" as F1
    component "FastCSV Library" as L1
    component "CsvReader Tests" as R1
    component "CsvWriter Tests" as W1
  }

  J1 --> R1
  J1 --> W1
  C1 --> R1
  C1 --> W1
  R1 --> F1
  W1 --> F1
  R1 --> L1
  W1 --> L1
}
@enduml
```

</details>

### 6.3 Test Architecture Overview

[Explain the overall test architecture if applicable.]

Tests were implemented in Java using the JUnit 5 testing framework. Input Domain Modeling and Graph-Based Testing were manually derived and implemented through parameterized unit tests. Exploratory testing followed a session-based structure and was documented using a pre-defined charter format. Cucumber was introduced in the final phase for automating acceptance tests based on Given–When–Then user stories.

Test data files were stored in organized subdirectories (e.g., `InputDomainModeling/CsvTestFiles`, `GraphBasedTesting/CsvTestFiles`) and referenced directly from test code. Tests were executed via IDE and command-line using Maven’s Surefire plugin and Gradle’s test task.

### 6.4 Equipment Table

[List the equipment and resources used in the testing environment if applicable.]

| Resource              | Specification/Tool                                     |
| --------------------- | ------------------------------------------------------ |
| IDE                   | IntelliJ IDEA Community Edition 2023.3                 |
| Java Version          | OpenJDK 17                                             |
| Build Tool            | Apache Maven 3.9.3                                     |
| Testing Framework     | JUnit 5                                                |
| Acceptance Testing    | Cucumber + JUnit 5                                     |
| Exploratory Docs Tool | Manual charter documentation using Markdown            |
| OS Platforms          | Windows 11 (x64), macOS Ventura 13.5.1                 |
| Hardware              | Intel Core i7, 16GB RAM (Windows); M1, 8GB RAM (macOS) |

# 7. Testing Results

[Provide a summary of testing results, including passed, failed, and unresolved issues]

The following table represents a summary of the testing results. Please note that these tests and their results are fully detailed in Section 5.2 above, but have been provided here in a consolidated table format for convenience and ease of reference.

| Test ID  | Test Technique      | Test Result | Comments |
| -------- | ------------------- | ----------- | -------- |
| IDM-R-01 | IDM                 | Pass        |          |
| IDM-R-02 | IDM                 | Pass        |          |
| IDM-R-03 | IDM                 | Pass        |          |
| IDM-R-04 | IDM                 | Pass        |          |
| IDM-R-05 | IDM                 | Pass        |          |
| IDM-R-06 | IDM                 | Pass        |          |
| IDM-R-07 | IDM                 | Pass        |          |
| IDM-R-08 | IDM                 | Pass        |          |
| IDM-R-09 | IDM                 | Pass        |          |
| IDM-R-10 | IDM                 | Pass        |          |
| IDM-R-11 | IDM                 | Pass        |          |
| IDM-R-12 | IDM                 | Pass        |          |
| IDM-R-13 | IDM                 | Pass        |          |
| IDM-R-14 | IDM                 | Pass        |          |
| IDM-R-15 | IDM                 | Pass        |          |
| IDM-R-16 | IDM                 | Pass        |          |
| IDM-R-17 | IDM                 | Pass        |          |
| IDM-R-18 | IDM                 | Pass        |          |
| IDM-W-01 | IDM                 | Pass        |          |
| IDM-W-02 | IDM                 | Pass        |          |
| IDM-W-03 | IDM                 | Pass        |          |
| IDM-W-04 | IDM                 | Pass        |          |
| IDM-W-05 | IDM                 | Pass        |          |
| IDM-W-06 | IDM                 | Pass        |          |
| IDM-W-07 | IDM                 | Pass        |          |
| IDM-W-08 | IDM                 | Pass        |          |
| GBT-W-01 | Graph Based Testing | Pass        |          |
| GBT-W-02 | Graph Based Testing | Pass        |          |
| GBT-W-03 | Graph Based Testing | Pass        |          |
| GBT-W-04 | Graph Based Testing | Pass        |          |
| GBT-W-05 | Graph Based Testing | Pass        |          |
| GBT-R-01 | Graph Based Testing | Pass        |          |
| GBT-R-02 | Graph Based Testing | Pass        |          |
| GBT-R-03 | Graph Based Testing | Pass        |          |
| GBT-R-04 | Graph Based Testing | Pass        |          |
| GBT-R-05 | Graph Based Testing | Pass        |          |
| GBT-R-06 | Graph Based Testing | Pass        |          |
| GBT-R-07 | Graph Based Testing | Pass        |          |
| GBT-R-08 | Graph Based Testing | Open        |          |
| GBT-R-09 | Graph Based Testing | Open        |          |
| EXP-B-01 | Exploratory Testing | Pass        |          |
| EXP-B-02 | Exploratory Testing | Pass        |          |
| EXP-B-03 | Exploratory Testing | Pass        |          |
| EXP-B-04 | Exploratory Testing | Pass        |          |
| EXP-B-05 | Exploratory Testing | Pass        |          |
| EXP-B-06 | Exploratory Testing | Pass        |          |
| EXP-B-07 | Exploratory Testing | Pass        |          |
| EXP-B-08 | Exploratory Testing | Pass        |          |
| EXP-C-01 | Exploratory Testing | Pass        |          |
| EXP-C-02 | Exploratory Testing | Pass        |          |
| EXP-C-03 | Exploratory Testing | Pass        |          |
| EXP-C-04 | Exploratory Testing | Pass        |          |
| EXP-F-01 | Exploratory Testing | Pass        |          |
| EXP-F-02 | Exploratory Testing | Pass        |          |
| EXP-I-01 | Exploratory Testing | Pass        |          |
| EXP-I-02 | Exploratory Testing | Pass        |          |
| EXP-I-03 | Exploratory Testing | Pass        |          |
| EXP-I-04 | Exploratory Testing | Pass        |          |
| EXP-L-01 | Exploratory Testing | Pass        |          |
| EXP-L-02 | Exploratory Testing | Pass        |          |
| EXP-L-03 | Exploratory Testing | Pass        |          |
| EXP-L-04 | Exploratory Testing | Pass        |          |

# 8. Recommendations on Software Quality

[Offer recommendations on improving the quality of the software based on testing results]

Based on the testing activities performed throughout the course of this project, the team considers FastCSV to be a well-structured and high-performance library for CSV parsing and writing in Java. The design of the API is flexible and supports a wide range of use cases, from simple read/write operations to complex parsing and quoting configurations.

The following are specific observations and recommendations derived from our testing efforts:

- The CsvWriter component demonstrated strong reliability across varied input scenarios, including null values, special characters, and configurable delimiters. The quoting and escaping logic functioned correctly under all tested conditions. However, it may benefit from clearer error messages when configuration constraints (e.g., duplicate control characters) are violated.
- CsvReader supported a comprehensive set of configurations, and all functional paths exercised during graph-based testing behaved as expected. Advanced features like BOM detection, comment skipping, and field trimming worked correctly even under large input volumes.
- Performance was observed to be consistent and robust across both small and large datasets. During exploratory testing, the library successfully handled files containing up to 100 million rows without memory issues or significant latency.
- Input Domain Modeling helped validate behavior under edge cases. However, additional tests focusing on malformed data (e.g., improperly escaped quotes or unclosed records) could improve fault tolerance coverage.
- The API is designed to be thread-safe in most contexts; however, the documentation does not explicitly address concurrent use. We recommend clarifying the thread safety guarantees in future documentation updates.
- From a usability perspective, the configuration interfaces for both reader and writer are logically organized, but some defaults may not align with certain industry conventions (e.g., not enforcing field count consistency). It may be beneficial to expose predefined profiles (e.g., RFC 4180-compliant, Excel-compatible) to simplify setup for new users.

In conclusion, FastCSV offers a mature and flexible toolset for CSV processing in Java. Our testing did not uncover critical defects, and the tool can be confidently recommended for production use in data processing and integration workflows. Future enhancements may focus on clearer error reporting, expanded documentation, and optional stricter compliance modes.
