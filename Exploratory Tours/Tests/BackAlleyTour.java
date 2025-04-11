import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import de.siegmar.fastcsv.writer.CsvWriter;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class BackAlleyTour {

    @BeforeAll
    public static void deleteOutputBefore() throws IOException {
        Files.deleteIfExists(Paths.get("outputStandard.gz"));
    }

    @AfterAll
    public static void deleteOutputAfter() throws IOException {
        Files.deleteIfExists(Paths.get("outputStandard.gz"));
    }

    @Test
    public void testCompressed() throws IOException {

        //Test small size compressed csv file
        Path file = Paths.get("reader-file-a01.csv.gz");

        String[][] expectedCSVResults = {{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(new GZIPInputStream(Files.newInputStream(file)))) {
            List<CsvRecord> recs = new ArrayList<>();
            for (CsvRecord record : csv) {
                recs.add(record);
            }
            for (int i = 0; i < expectedCSVResults.length; i++) {
                for (int j = 0; j < expectedCSVResults[i].length; j++) {
                    assertEquals(expectedCSVResults[i][j], recs.get(i).getField(j));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Test large size compressed csv file (10M Lines)

        file = Paths.get("reader-file-a05.csv.gz");

        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(new GZIPInputStream(Files.newInputStream(file)))) {
            List<CsvRecord> recs = new ArrayList<>();
            for (CsvRecord record : csv) {
                recs.add(record);
            }

            assertEquals("Anne", recs.get(0).getField(0));
            assertEquals("Mack", recs.get(0).getField(1));
            assertEquals("(907) 789-3686", recs.get(0).getField(2));

            assertEquals("Sallie", recs.get(191783).getField(0));
            assertEquals("Moss", recs.get(191783).getField(1));
            assertEquals("(838) 455-8563", recs.get(191783).getField(2));

            assertEquals("Bill", recs.get(405480).getField(0));
            assertEquals("Lee", recs.get(405480).getField(1));
            assertEquals("(443) 584-2867", recs.get(405480).getField(2));

            assertEquals("Inez", recs.get(652054).getField(0));
            assertEquals("Foster", recs.get(652054).getField(1));
            assertEquals("(557) 675-1730", recs.get(652054).getField(2));

            assertEquals("Carolyn", recs.get(999999).getField(0));
            assertEquals("Todd", recs.get(999999).getField(1));
            assertEquals("(604) 860-4898", recs.get(999999).getField(2));

        } catch (IOException e) {
            throw new RuntimeException(e);

        }

        //Test writing and reading zipped csv file

        Path outputFile = Paths.get("outputStandard.gz");

        try (
            CsvWriter csv = CsvWriter.builder()
                .build(new GZIPOutputStream(Files.newOutputStream(outputFile)));
        ) {
            csv.writeRecord("Record 1", "Record 2", "Record 3");
            csv.writeRecord("Apples", "Cranberries", "Banana");
            csv.writeRecord("Blue Cheese", "Brie", "Swiss");
            csv.writeRecord("Water", "Soda", "Juice");
        }

        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(new GZIPInputStream(Files.newInputStream(outputFile)))) {
            List<CsvRecord> recs = new ArrayList<>();
            for (CsvRecord record : csv) {
                recs.add(record);
            }
            assertEquals("Record 1", recs.get(0).getField(0));
            assertEquals("Record 2", recs.get(0).getField(1));
            assertEquals("Record 3", recs.get(0).getField(2));

            assertEquals("Apples", recs.get(1).getField(0));
            assertEquals("Cranberries", recs.get(1).getField(1));
            assertEquals("Banana", recs.get(1).getField(2));

            assertEquals("Blue Cheese", recs.get(2).getField(0));
            assertEquals("Brie", recs.get(2).getField(1));
            assertEquals("Swiss", recs.get(2).getField(2));

            assertEquals("Water", recs.get(3).getField(0));
            assertEquals("Soda", recs.get(3).getField(1));
            assertEquals("Juice", recs.get(3).getField(2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

