
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import de.siegmar.fastcsv.writer.CsvWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class FedExTour {

    public static void FedExTour() throws IOException {
        var input = Paths.get("./inputs/fedex-input.csv");
        var output = Paths.get("./inputs/fedex-output.csv");

        // Read input and find total column
        try (var reader = CsvReader.builder().ofCsvRecord(input); var writer = CsvWriter.builder().build(new FileWriter(output.toFile()))) {

            for (CsvRecord record : reader) {
                String id = record.getField(0);
                String amount = record.getField(2);  // tracking "amount" field
                writer.writeRecord(id, amount);
            }
        }

        System.out.println("FedEx tour completed. Output written to: " + output);
    }

    public static void main(String[] args) throws IOException {
        try {
            FedExTour();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
