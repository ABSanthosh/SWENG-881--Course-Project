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

## 4.4 Test Categories

[Categorize the test cases, e.g., functional, performance, security, etc.]

# 5. Test Cases

## 5.1 Test Group Definition

[This is where you will define how the test cases will be structured and organized. Define test groups and subgroups for organizing test cases. Specify the objective for each group.]

## 5.2 Test Cases

[List all test cases. Or provide a link to the test cases. Ensure that the link you provide is accessible to the instructor.]

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

# 8. Recommendations on Software Quality

[Offer recommendations on improving the quality of the software based on testing results]
