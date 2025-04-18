
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class CollectorsTour {

    public static void CollectorTour() throws IOException {
        var input = Paths.get("./inputs/collector-input.csv");
        Set<String> allUniqueAmounts = new HashSet<>();

        try (var reader = CsvReader.builder().ofCsvRecord(input)) {
            for (CsvRecord record : reader) {
                String field = record.getField(2);  // Collect unique "amount" field
                allUniqueAmounts.add(field);
            }
        }

        System.out.println("Unique 'amount' values collected: " + allUniqueAmounts.size());
    }

    public static void main(String[] args) throws IOException {
        try {
            CollectorTour();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
