## **Introduction**

Part of our selected project for software testing is the `CSVWriter` module from the open-source **FastCSV** library. The `CSVWriter` class is responsible for writing records to CSV files with various configurations such as delimiters, quoting strategies, and comment handling. This component is critical for correct data serialization and export, making it an ideal target for rigorous testing.

## **Software Quality Criteria**

We define the following quality attributes as critical for assessing the CSVWriter:

| **Quality Attribute** | **Description**                                                                          |
| --------------------- | ---------------------------------------------------------------------------------------- |
| **Correctness**       | The writer must accurately produce CSV output according to the RFC 4180 standard.        |
| **Robustness**        | Must handle edge cases (null values, empty fields, special characters) without crashing. |
| **Performance**       | Must efficiently handle large datasets using internal buffering.                         |
| **Configurability**   | Must respect user-defined settings for separators, quoting, and line delimiters.         |
| **Maintainability**   | Code must be modular and well-documented for ease of extension or debugging.             |

## **Selected Component for Testing**

The focus is the [`CsvWriter`](https://github.com/osiegmar/FastCSV/blob/main/lib/src/main/java/de/siegmar/fastcsv/writer/CsvWriter.java) class, located at: [`lib/src/main/java/de/siegmar/fastcsv/writer/CsvWriter.java`](https://github.com/osiegmar/FastCSV/tree/main/lib/src/main/java/de/siegmar/fastcsv/writer)

This includes testing of its:

- Record writing methods (`writeRecord`, `writeComment`)
- Internal quoting and escaping logic
- Interaction with configuration parameters via `CsvWriterBuilder`

## **Testing Strategy**

We will used this as the table of content for our testing strategy for the `CSVWriter` class.

| **Aspect**                                                  | **Approach**                                                                                      |
| ----------------------------------------------------------- | ------------------------------------------------------------------------------------------------- |
| [**Input Domain Modeling**](./input-domain-modeling.md)     | Identify field inputs and boundary conditions (e.g., null, empty, special characters).            |
|                                                             | We will partition inputs based on field values: null, empty, quoted, contains special characters. |
|                                                             | and also focus on record size limits and field lengths near buffer capacity.                      |
| [**Graph-Based Testing**](./graph-based-testing.md)         | Model writer state transitions: Start → Write Field(s) → End Record                               |
| [**Exploratory Testing**](../Exploratory%20Tours/Readme.md) | Execute exploratory sessions using various configurations in real-world CSV writing scenarios.    |

## **Tools and Environment**

| **Tool**      | **Purpose**              |
| ------------- | ------------------------ |
| JUnit 5       | Unit testing             |
| IntelliJ IDEA | IDE for test development |

## **Initial Observations**

CSVWriter relies on:

- `QuoteStrategy` interface to determine quoting behavior
- `Writable` for buffered/unbuffered output streams
- Internal validation to prevent record overlap or malformed content

## **Logic**

The procedural flow can be outlined as follows:

- The CsvWriter is configured using CsvWriterBuilder.
- The CsvWriterBuilder controls the instantiation of the CsvWriter with the given settings and opened CSV file.
- The CsvWriter writes the given records to the CSV file, quoting fields as necessary (depending on the configured QuoteStrategy).
- The CsvWriter closes the CSV file.
