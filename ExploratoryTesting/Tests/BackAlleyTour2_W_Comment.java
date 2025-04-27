package org.example;

import de.siegmar.fastcsv.writer.CsvWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class BackAlleyTour2_W_Comment {

    public static void TestStandardCSVFileComments() throws IOException {
        Path outputFile =Paths.get("src/standardComment.csv");

        try (
                CsvWriter csvWriter = CsvWriter.builder().build(outputFile);
        ) {
            csvWriter.writeRecord("1a", "1b", "1c");
            csvWriter.writeComment("Line 2 Comment");
            csvWriter.writeRecord("3a", "3b", "3c");
            csvWriter.writeComment("Line 4 Comment\nLine 5 Comment");
            csvWriter.writeRecord("6a", "6b", "6c");
        }

    }

    public static void TestCustomCSVFileComments() throws IOException {
        Path outputFile =Paths.get("src/dollarComment.csv");

        try (
                CsvWriter csvWriter = CsvWriter.builder().commentCharacter('$').build(outputFile);
        ) {
            csvWriter.writeRecord("1a", "1b", "1c");
            csvWriter.writeComment("Line 2 Comment");
            csvWriter.writeRecord("3a", "3b", "3c");
            csvWriter.writeComment("Line 4 Comment\nLine 5 Comment");
            csvWriter.writeRecord("6a", "6b", "6c");
        }

    }






}
