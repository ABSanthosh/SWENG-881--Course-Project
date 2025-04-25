Feature: Writing CSV data using CSVWriter

  Scenario: Write multiple records and validate file content
    Given a CSVWriter is initialized with the file path "output.csv"
    And the following records are prepared:
      | name  | email               |
      | alice | alice@example.com   |
      | bob   | bob@example.com     |
    When the CSVWriter writes the records to the file
    Then the file "output.csv" should contain:
      | name,email               |
      | alice,alice@example.com |
      | bob,bob@example.com     |
