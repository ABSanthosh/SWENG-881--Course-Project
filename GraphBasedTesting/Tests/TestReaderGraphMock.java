import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Paths;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import de.siegmar.fastcsv.reader.CsvParser;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TestReaderGraphMock {
    @Test
    public void GBTR08() throws IOException {

        /**************************************************************************************************************
         * Test:        GBT-R-08
         * Description: Test Path 1,3,5,7,8,9,11
         * Comments: Had to make CSVParser.peekLine class public in order to test; altered method to accept injection
         * op mocked parser
         *************************************************************************************************************/

        CsvParser parser = mock(CsvParser.class);

        when(parser.peekLine()).thenThrow(new IOException("This is an IO Exception peekLine"));

        final Path filegr08 = Paths.get("reader-file-g02.csv");

        final Predicate<String> isHeader8 = line ->
            line.contains("Header Information Here");

        assertThrows(UncheckedIOException.class, () -> {

            try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(filegr08)) {
                final int skippedLines8 = csv.skipLines(isHeader8, 10, parser);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**************************************************************************************************************
     * Test:        GBT-R-09
     * Description: Test Path 1,3,5,7,8,9,12,14,15
     * Comments: Had to make CSVParser.peekLine class public in order to test; altered method to accept injection
     * op mocked parser
     *************************************************************************************************************/

    @Test
    public void GBTR09() throws IOException {

        CsvParser parser = mock(CsvParser.class);

        when(parser.peekLine()).thenReturn("Data Here");
        when(parser.skipLine(anyInt())).thenThrow(new IOException("This is an IO Exception skipLine"));

        final Path filegr08 = Paths.get("reader-file-g02.csv");

        final Predicate<String> isHeader9 = line ->
            line.contains("Header Information Here");

        assertThrows(UncheckedIOException.class, () -> {

            try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(filegr08)) {
                final int skippedLines9 = csv.skipLines(isHeader9, 10, parser);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });









    }
}
