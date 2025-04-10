# **Input Domain Modeling for CSVWriter**

## **Function Under Test: `writeRecord(String...)` in `CsvWriter`**

This function takes a variable-length array of strings and writes them as a single CSV record, applying quoting and escaping rules depending on the provided configuration.

## **List of Input Variables**

| Input Variable   | Description                                          | Default |
| ---------------- | ---------------------------------------------------- | ------- |
| `values`         | An array of strings to be written as one CSV record. | -       |
| `fieldSeparator` | A `char` defining the separator between fields.      | `,`     |
| `quoteCharacter` | A `char` used to enclose fields.                     | `"`     |
| `quoteStrategy`  | A `QuoteStrategy` object defining quoting behavior.  | `null`  |
| `lineDelimiter`  | A `LineDelimiter` enum value for line endings.       | `CRLF`  |

## **Characteristics of Input Variables**

| Input Variable   | Type            | Constraints                                                              |
| ---------------- | --------------- | ------------------------------------------------------------------------ |
| `values`         | `String[]`      | May include `null`, empty, normal, or special character strings          |
| `fieldSeparator` | `char`          | Must not be a newline character; must differ from quote and comment char |
| `quoteCharacter` | `char`          | Must not be a newline character; must differ from field and comment char |
| `quoteStrategy`  | `QuoteStrategy` | Can be `null` or an implementation enforcing quoting rules               |
| `lineDelimiter`  | `LineDelimiter` | Must be one of the enum values: `CR`, `LF`, `CRLF`, or `PLATFORM`        |

## **Partitioning Characteristics into Blocks**

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

## **Coverage Criteria**

We use **Each-Choice Coverage** to ensure every block of each input variable is represented in at least one test case.

## **Test Descriptions**

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

## **Test Set Definition**

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

The code for the tests can be found in [TestWriterGraph.java](./tests/TestWriterIDM.java)


[Go Back to table of contents](./Readme.md)

[Next: Graph-Based Testing](./graph-based-testing.md)