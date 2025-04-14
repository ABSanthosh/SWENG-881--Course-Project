package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


import de.siegmar.fastcsv.reader.*;

public class BackAlleyTour2_Modify {

    public static void main(String[] args) throws IOException {
        testTrimSpace();
        testTrimSpaceUnicode();
        testStripSpaceUnicode();
    }

    public static void testTrimSpace() throws IOException {
        try (FileReader reader = new FileReader("src/testTrimSpaces.csv");
             var csv = CsvReader.builder()
                     .build(CsvRecordHandler.of(c -> c.fieldModifier(FieldModifiers.TRIM)), reader)) {

            csv.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void testTrimSpaceUnicode() throws IOException {
        try (FileReader reader = new FileReader("src/testStripSpaces.csv");
             var csv = CsvReader.builder()
                     .build(CsvRecordHandler.of(c -> c.fieldModifier(FieldModifiers.TRIM)), reader)) {

            csv.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testStripSpaceUnicode() throws IOException {
        try (FileReader reader = new FileReader("src/testStripSpaces.csv");
             var csv = CsvReader.builder()
                     .build(CsvRecordHandler.of(c -> c.fieldModifier(FieldModifiers.STRIP)), reader)) {

            csv.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}