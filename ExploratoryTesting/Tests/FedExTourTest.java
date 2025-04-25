package org.fastcsv;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import de.siegmar.fastcsv.writer.CsvWriter;
import de.siegmar.fastcsv.writer.LineDelimiter;
import de.siegmar.fastcsv.writer.QuoteStrategies;

public class FedExTourTest {

  private static final Path inputFile = Path.of("./inputs/fedex-input.csv");
  private static final Path outputFile = Path.of("./inputs/fedex-output.csv");

  @Test
  public void testWriteFedExCSVWithDefaults() throws IOException {
    List<String> lines = Files.readAllLines(inputFile);
    try (Writer writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8);
        CsvWriter csvWriter = CsvWriter.builder().build(writer)) {
      for (String line : lines) {
        String[] fields = line.split(",");
        csvWriter.writeRecord(fields);
      }
    }

    List<String> outputLines = Files.readAllLines(outputFile);
    assertEquals(lines.size(), outputLines.size(), "Row count mismatch between input and output");
  }

  @Test
  public void testWriteWithSemicolonSeparator() throws IOException {
    List<String> lines = Files.readAllLines(inputFile);
    Path customSepOutput = Path.of("./inputs/fedex-output-semicolon.csv");

    try (Writer writer = Files.newBufferedWriter(customSepOutput, StandardCharsets.UTF_8);
        CsvWriter csvWriter = CsvWriter.builder().fieldSeparator(';').build(writer)) {
      for (String line : lines) {
        String[] fields = line.split(",");
        csvWriter.writeRecord(fields);
      }
    }

    assertTrue(Files.exists(customSepOutput), "Output file with semicolon separator not created");
  }

  @Test
  public void testNewlinesInFieldWithoutQuotingThrows() {
    IOException exception = assertThrows(IOException.class, () -> {
      try (Writer writer = new StringWriter();
          CsvWriter csvWriter = CsvWriter.builder().quoteStrategy(QuoteStrategies.ALWAYS)
              .lineDelimiter(LineDelimiter.LF).build(writer)) {
        csvWriter.writeRecord("123 Main St", "New York\nNY", "USA");
      }
    }, "Expected exception when writing unquoted field with newline");

    assertTrue(exception.getMessage() != null && !exception.getMessage().isEmpty(), "Exception should have a message");
  }

  @Test
  public void testQuoteAllFields() throws IOException {
    Path quotedOutput = Path.of("./inputs/fedex-output-quoted.csv");

    try (Writer writer = Files.newBufferedWriter(quotedOutput, StandardCharsets.UTF_8);
        CsvWriter csvWriter = CsvWriter.builder().quoteStrategy(QuoteStrategies.ALWAYS).build(writer)) {
      csvWriter.writeRecord("FedEx", "123 Main St", "New York, NY");
    }

    List<String> lines = Files.readAllLines(quotedOutput);
    assertEquals(1, lines.size());
    assertTrue(lines.get(0).startsWith("\"FedEx\""), "Fields not quoted as expected");
  }
}
