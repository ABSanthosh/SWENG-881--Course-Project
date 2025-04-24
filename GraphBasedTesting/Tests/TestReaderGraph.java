import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Paths;

import de.siegmar.fastcsv.reader.CsvParseException;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import de.siegmar.fastcsv.reader.CsvParser;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

public class TestReaderGraph {
    @Test
    public void test() {

        /**************************************************************************************************************
         * Test:        GBT-R-01
         * Description: Test Path 1,3,5,7,8,9,12,13
         *************************************************************************************************************/

        Path file = Paths.get("reader-file-g01.csv");

        Predicate<String> isHeader = line ->
            line.contains("Header Information Here");

        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
            assertEquals(0, csv.skipLines(isHeader, 10));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /**************************************************************************************************************
         * Test:        GBT-R-02
         * Description: Test Path 1,3,5,7,8,9,12,14,16,18,8,9,12,13
         *************************************************************************************************************/

        file = Paths.get("reader-file-g02.csv");

        isHeader = line ->
            line.contains("Header Information Here");

        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
            assertEquals(1, csv.skipLines(isHeader, 10));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /**************************************************************************************************************
         * Test:        GBT-R-03
         * Description: Test Path 1,2
         *************************************************************************************************************/


        final Path filegr03 = Paths.get("reader-file-g01.csv");

        final Predicate<String> isHeaderNull = null;

        assertThrows(NullPointerException.class, () -> {
            try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(filegr03)) {
                final int skippedLines3 = csv.skipLines(isHeaderNull, 10);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        /**************************************************************************************************************
         * Test:        GBT-R-04
         * Description: Test Path 1,3,4
         *************************************************************************************************************/

        final Path filegr04 = Paths.get("reader-file-g01.csv");

        final Predicate<String> isHeader4 = line ->
            line.contains("Header Information Here");

        assertThrows(IllegalArgumentException.class, () -> {

            try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(filegr04)) {
                final int skippedLines4 = csv.skipLines(isHeader4, -1);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        /**************************************************************************************************************
         * Test:        GBT-R-05
         * Description: Test Path 1,3,5,6
         *************************************************************************************************************/

        file = Paths.get("reader-file-g02.csv");

        isHeader = line ->
            line.contains("Header Information Here");

        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
            assertEquals(0, csv.skipLines(isHeader, 0));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /**************************************************************************************************************
         * Test:        GBT-R-06
         * Description: Test Path 1,3,5,7,8,9,12,14,16,18,8,10
         *************************************************************************************************************/

        final Path filegr06 = Paths.get("reader-file-g02.csv");

        final Predicate<String> isHeader6 = line ->
            line.contains("Header Information Here");

        assertThrows(CsvParseException.class, () -> {

            try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(filegr06)) {
                final int skippedLines6 = csv.skipLines(isHeader6, 1);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        /**************************************************************************************************************
         * Test:        GBT-R-07
         * Description: Test Path 1,3,5,7,8,9,12,14,16,18,8,9,12,14,17
         *************************************************************************************************************/

        final Path filegr08 = Paths.get("reader-file-g03.csv");

        final Predicate<String> isHeader8 = line ->
            line.contains("Header Information Here");

        assertThrows(CsvParseException.class, () -> {

            try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(filegr08)) {
                final int skippedLines6 = csv.skipLines(isHeader8, 10);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
