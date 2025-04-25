package stepdefinitions;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.siegmar.fastcsv.writer.CsvWriter;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CSVWriterSteps {

    private Path filePath;
    private List<String[]> records;

    @Given("a CSVWriter is initialized with the file path {string}")
    public void a_csv_writer_is_initialized_with_the_file_path(String path) {
        filePath = Paths.get(path);
        records = new ArrayList<>();
    }

    @Given("the following records are prepared:")
    public void the_following_records_are_prepared(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists();
        for (List<String> row : rows) {
            records.add(row.toArray(new String[0]));
        }
    }

    @When("the CSVWriter writes the records to the file")
    public void the_csv_writer_writes_the_records_to_the_file() throws IOException {
        try (Writer out = Files.newBufferedWriter(filePath);
             CsvWriter csvWriter = CsvWriter.builder().build(out)) {
            for (String[] record : records) {
                csvWriter.writeRecord(record);
            }
        }
    }

    @Then("the file {string} should contain:")
    public void the_file_should_contain(String path, DataTable expectedTable) throws IOException {
        List<String> actualLines = Files.readAllLines(Paths.get(path));
        List<String> expectedLines = new ArrayList<>();
        for (List<String> row : expectedTable.asLists()) {
            expectedLines.add(String.join(",", row));
        }
        assertEquals(expectedLines, actualLines);
    }
}
