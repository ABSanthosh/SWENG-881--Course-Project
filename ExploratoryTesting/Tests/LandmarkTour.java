
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import de.siegmar.fastcsv.writer.CsvWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LandmarkTour {

    public static void Landmark() throws IOException {
        var input = Paths.get("./inputs/landmark-input.csv");
        var temp = Paths.get("./inputs/landmark-temp.csv");
        var output = Paths.get("./inputs/landmark-output.csv");

        List<String[]> records = new ArrayList<>();

        try (var reader = CsvReader.builder().ofCsvRecord(input)) {
            for (CsvRecord record : reader) {
                records.add(record.getFields().toArray(new String[0]));
            }
        }

        Collections.shuffle(records);  // sequence variation

        try (var writer = CsvWriter.builder().build(new FileWriter(temp.toFile()))) {
            for (String[] row : records) {
                writer.writeRecord(row); // row is String[], matches String... method
            }
        }

        // Read back and write to final file
        try (var reader = CsvReader.builder().ofCsvRecord(temp); var writer = CsvWriter.builder().build(new FileWriter(output.toFile()))) {
            for (CsvRecord record : reader) {
                writer.writeRecord(record.getFields());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            Landmark();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
