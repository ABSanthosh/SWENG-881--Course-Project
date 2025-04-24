import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Paths;

import de.siegmar.fastcsv.reader.CommentStrategy;
import de.siegmar.fastcsv.reader.CsvParseException;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class TestReaderIDM {
    @Test
    public void test() {

        /**************************************************************************************************************
         * Test:        IDM-R-01
         * Description: Test a valid csv file of strings with default settings
         *************************************************************************************************************/

        Path file = Paths.get("reader-file-a01.csv");

        String[][] expectedCSVResults = {{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-02
         * Description: Test a csv file of special characters with default settings
         *************************************************************************************************************/

        file = Paths.get("reader-file-a02.csv");

        expectedCSVResults = new String[][]{{"!@#^(&@$(*@", "*#$&(@$@", "+)_(@*&)(&"}, {"<>//;[>", "~~~~~~", "/*-*-+"}, {"(*&*#(@$)", "#$@$@#$#", "+_)(*&^%$#@!~"}, {"~!@#$%^&", "{}{}{}", "///"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-03
         * Description: Test a csv file with one column per row with default settings
         *************************************************************************************************************/

        file = Paths.get("reader-file-a03.csv");

        expectedCSVResults = new String[][]{{"Adam"}, {"Santhosh"}, {"Bill"}, {"Ted"}, {"George"}, {"Thomas"}, {"Heather"}, {"Jane"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-04
         * Description: Test a csv file with one row and many columns with default settings
         *************************************************************************************************************/

        file = Paths.get("reader-file-a04.csv");

        String[] expectedCSVResults1Row = {"Adam", "Santhosh", "Bill", "Ted", "George", "Thomas", "Heather", "Jane"};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
            List<CsvRecord> recs = new ArrayList<>();
            for (CsvRecord record : csv) {
                recs.add(record);
            }
            for (int j = 0; j < expectedCSVResults1Row.length; j++) {
                assertEquals(expectedCSVResults1Row[j], recs.get(0).getField(j));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /**************************************************************************************************************
         * Test:        IDM-R-05
         * Description: Test a very large csv file with one million rows with default settings
         *************************************************************************************************************/

        file = Paths.get("reader-file-a05.csv");

        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-06
         * Description: Test a csv file with commas as data with default settings including quoteCharacter
         *************************************************************************************************************/

        file = Paths.get("reader-file-a06.csv");

        expectedCSVResults = new String[][]{{"Adam", "24,324"}, {"Santhosh", "56,434"}, {"Bill", "23,145"}, {"Ted", ",,,,,"}, {"George", "1,"}, {",", ","}, {"Heather", "12,111"}, {"1,", "1,1,1,1,1"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-07
         * Description: Test a csv File that has quotes as data with default settings including quoteCharacter
         *************************************************************************************************************/

        file = Paths.get("reader-file-a07.csv");

        expectedCSVResults = new String[][]{{"\"Adam\"", "1"}, {"\"Santhosh\"", "\"2\""}, {"\"\"", "\"\""}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-08
         * Description: Test a malformed csv file with uneven columns in rows with default settings including
         *              default ignoreDifferentFieldCount value
         *************************************************************************************************************/

        file = Paths.get("reader-file-a08.csv");

        expectedCSVResults = new String[][]{{"Adam", "1", "2"}, {"Santhosh", "3"}, {"Pennsylvania", "1", "3", "4"}, {"Penn State", "1", "2", "3", "4", "5", "6"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-09
         * Description: Test a csv File with empty rows with default settings except for alternative skipEmptyLines
         *              option
         *************************************************************************************************************/

        file = Paths.get("reader-file-a09.csv");

        try (CsvReader<CsvRecord> csv = CsvReader.builder().skipEmptyLines(false).ofCsvRecord(file)) {
            List<CsvRecord> recs = new ArrayList<>();
            for (CsvRecord record : csv) {
                recs.add(record);
            }

            assertEquals("", recs.get(2).getField(0));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /**************************************************************************************************************
         * Test:        IDM-R-10
         * Description: Test a blank csv file with default settings
         *************************************************************************************************************/

        file = Paths.get("reader-file-a10.csv");

        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
            List<CsvRecord> recs = new ArrayList<>();
            for (CsvRecord record : csv) {
                recs.add(record);
            }
            assertEquals(0, recs.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /**************************************************************************************************************
         * Test:        IDM-R-11
         * Description: Test a csv file with ';' as separators with default settings and alternative fieldSeparator ';'
         *************************************************************************************************************/

        file = Paths.get("reader-file-a11.csv");

        expectedCSVResults = new String[][]{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().fieldSeparator(';').ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-12
         * Description: Test a csv file with '^' as quote character with default settings and alternative
         *              quoteCharacter '^'
         *************************************************************************************************************/

        file = Paths.get("reader-file-a12.csv");

        expectedCSVResults = new String[][]{{"\"Adam\"", "1"}, {"\"Santhosh\"", "\"2\""}, {"\"\"", "\"\""}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().quoteCharacter('^').ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-13
         * Description: Test a csv file with blank lines with default settings except for CommentStrategy of 'SKIP'
         *************************************************************************************************************/

        file = Paths.get("reader-file-a13.csv");

        expectedCSVResults = new String[][]{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().commentStrategy(CommentStrategy.SKIP).ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-14
         * Description: Test a csv file with default settings except for using an alternate comment character '@' and
         *              CommentStrategy of 'SKIP'
         *************************************************************************************************************/

        file = Paths.get("reader-file-a14.csv");

        expectedCSVResults = new String[][]{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().commentStrategy(CommentStrategy.SKIP).commentCharacter('@').ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-15
         * Description: Test a csv file that has a BOM header with default settings except detectBOMHeader set to true.
         *************************************************************************************************************/

        file = Paths.get("reader-file-a15.csv");

        expectedCSVResults = new String[][]{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().detectBomHeader(true).ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-16
         * Description: Test a csv file with comments and default settings
         *************************************************************************************************************/

        file = Paths.get("reader-file-a13.csv");

        expectedCSVResults = new String[][]{{"apple", "banana", "cantaloupe"}, {"#This is a comment"}, {"11", "22", "33"}, {"#This is another comment"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
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


        /**************************************************************************************************************
         * Test:        IDM-R-17
         * Description: Test a malformed csv file with uneven columns in rows with default settings including alternate
         *              ignoreDifferentFieldCount value of 'false'
         *************************************************************************************************************/

        assertThrows(CsvParseException.class, () -> {
            try (CsvReader<CsvRecord> csv = CsvReader.builder().ignoreDifferentFieldCount(false).ofCsvRecord(Paths.get("reader-file-a08.csv"))) {
                List<CsvRecord> recs = new ArrayList<>();
                for (CsvRecord record : csv) {
                    recs.add(record);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        /**************************************************************************************************************
         * Test:        IDM-R-18
         * Description: Test a csv file with empty lines and default settings
         *************************************************************************************************************/

        file = Paths.get("reader-file-a09.csv");

        expectedCSVResults = new String[][]{{"apple", "banana", "cantaloupe"}, {"11", "22", "33"}, {"xray", "yogurt", "zebra"}, {"44", "55", "66"}};
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {
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
    }
}






