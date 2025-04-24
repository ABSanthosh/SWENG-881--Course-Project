Feature: Read Space-Separated Data From String

  Scenario: Successful read - Space Separated String WITHOUT Spaces as Data
    Given the user has input a String of space separated values WITHOUT spaces as data
    When the user runs CsvReader configured with field separator " " and WITHOUT spaces as data
    Then the user should be returned a CsvReader<CsvRecord> with elements that match the order specified in the String WITHOUT spaces as data

  Scenario: Successful read - Space Separated String WITH Spaces as Data
    Given the user has input a String of space separated values WITH spaces as data
    When the user runs CsvReader configured with field separator " " and WITH spaces as data
    Then the user should be returned a CsvReader<CsvRecord> with elements that match the order specified in the String WITH spaces as data
