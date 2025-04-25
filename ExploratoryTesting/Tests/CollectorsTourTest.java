package org.fastcsv;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import de.siegmar.fastcsv.writer.CsvWriter;
import de.siegmar.fastcsv.writer.QuoteStrategies;

public class CollectorsTourTest {

    private static final Path outputPath = Path.of("./inputs/collectors-output.csv");

    @Test
    public void testWriteAllNullAndEmptyCombinations() throws IOException {
        try (Writer writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8);
                CsvWriter csvWriter = CsvWriter.builder().quoteStrategy(QuoteStrategies.ALWAYS).build(writer)) {

            csvWriter.writeRecord(null, "", "normal", "\"quoted\"", "comma,separated", "line\nbreak");
        }

        String output = Files.readString(outputPath);
        assertTrue(output.contains("\"\""), "Empty string should be quoted");
        assertTrue(output.contains("\"\"\"quoted\"\"\""), "Quote characters should be escaped");
        assertTrue(output.contains("\"comma,separated\""), "Comma-containing field should be quoted");
        assertTrue(output.contains("\"line\nbreak\""), "Newline-containing field should be quoted and preserved");
    }

    @Test
    public void testFieldVariationsAcrossRows() throws IOException {
        Path multiRowOutput = Path.of("./inputs/collectors-output-multivariant.csv");

        try (Writer writer = Files.newBufferedWriter(multiRowOutput, StandardCharsets.UTF_8);
                CsvWriter csvWriter = CsvWriter.builder().quoteStrategy(QuoteStrategies.ALWAYS).build(writer)) {

            csvWriter.writeRecord("null", null);
            csvWriter.writeRecord("empty", "");
            csvWriter.writeRecord("special", "quote\"value");
            csvWriter.writeRecord("newline", "line\nbreak");
        }

        String output = Files.readString(multiRowOutput);

        assertTrue(output.contains("\"null\",\"\""), "Expected null field to be quoted empty");
        assertTrue(output.contains("\"empty\",\"\""), "Expected empty string to be quoted empty");
        assertFalse(output.contains("\"special\",\"quote\"\"value\"\"\""), "Escaped quotes not found as expected");
        assertTrue(output.contains("\"newline\",\"line\nbreak\""), "Newline-containing field not quoted correctly");

    }
}
