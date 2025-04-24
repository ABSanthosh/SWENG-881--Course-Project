package stepdefinitions;

import static org.junit.jupiter.api.Assertions.*;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import io.cucumber.java.en.*;
import system.CsvReaderSystem;

import java.util.ArrayList;
import java.util.List;

public class SpaceSepSteps {

    private CsvReaderSystem reader;
    private CsvReader<CsvRecord> readResult;

    private String spaceSepDataWithoutSpacesAsData;
    private String spaceSepDataWithSpacesAsData;

    @Given("the user has input a String of space separated values WITHOUT spaces as data")
    public void the_user_has_input_String_of_space_sep_values_without_spaces_as_data() {
        spaceSepDataWithoutSpacesAsData="First Last State\nJay Smith PA\nBill Davis FL\nRich Morris NJ";
    }

    @Given("the user has input a String of space separated values WITH spaces as data")
    public void the_user_has_input_String_of_space_sep_values_with_spaces_as_data() {
        spaceSepDataWithSpacesAsData="\"Video Game Name\" \"Game Genre\" Developer\n\"The Elder Scrolls: Oblivion\" RPG \"Bethesda Game Studios\"\n\"Fortnite Battle Royale\" Action \"Epic Game Studios\"\n\"Super Mario Bros\" \"Action Adventure\" Nintendo";
    }

    @When("the user runs CsvReader configured with field separator {string} and WITHOUT spaces as data")
    public void the_user_runs_CsvReader_using_spaces_as_the_field_separator_without_spaces_as_data(String separator) {
        reader=new CsvReaderSystem(' ');
        readResult = reader.read(spaceSepDataWithoutSpacesAsData);
    }

    @When("the user runs CsvReader configured with field separator {string} and WITH spaces as data")
    public void the_user_runs_CsvReader_using_spaces_as_the_field_separator_with_spaces_as_data(String separator) {
        reader=new CsvReaderSystem(' ');
        readResult = reader.read(spaceSepDataWithSpacesAsData);
    }

    @Then("the user should be returned a CsvReader<CsvRecord> with elements that match the order specified in the String WITHOUT spaces as data")
    public void the_user_should_be_returned_object_with_elements_that_match_specified_order_without_spaces_as_data() {

        String[][] expectedCSVResults = {{"First", "Last", "State"}, {"Jay", "Smith", "PA"}, {"Bill", "Davis", "FL"}, {"Rich", "Morris", "NJ"}};
        List<CsvRecord> recs = new ArrayList<>();
        for (CsvRecord record : readResult) {
            recs.add(record);
        }
        for (int i = 0; i < expectedCSVResults.length; i++) {
            for (int j = 0; j < expectedCSVResults[i].length; j++) {
                assertEquals(expectedCSVResults[i][j], recs.get(i).getField(j));
            }
        }
    }

    @Then("the user should be returned a CsvReader<CsvRecord> with elements that match the order specified in the String WITH spaces as data")
    public void the_user_should_be_returned_object_with_elements_that_match_specified_order_with_spaces_as_data() {
        String[][] expectedCSVResults = {{"Video Game Name", "Game Genre", "Developer"}, {"The Elder Scrolls: Oblivion", "RPG", "Bethesda Game Studios"}, {"Fortnite Battle Royale", "Action", "Epic Game Studios"}, {"Super Mario Bros", "Action Adventure", "Nintendo"}};
        List<CsvRecord> recs = new ArrayList<>();
        for (CsvRecord record : readResult) {
            recs.add(record);
        }
        for (int i = 0; i < expectedCSVResults.length; i++) {
            for (int j = 0; j < expectedCSVResults[i].length; j++) {
                assertEquals(expectedCSVResults[i][j], recs.get(i).getField(j));
            }
        }
    }

}
