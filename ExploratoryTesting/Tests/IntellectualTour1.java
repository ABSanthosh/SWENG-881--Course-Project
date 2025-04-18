package org.example;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import de.siegmar.fastcsv.writer.CsvWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IntellectualTour1 {

    //Test 1
    public static void largeFileTest(){
        long startTime = System.nanoTime();
        Path file = Paths.get("src/csvData100m.csv");
        int a=0;
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {

        for (CsvRecord record : csv) {
            a++;
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Duration (ms): "+duration/1000000);
        System.out.println("Lines read: "+a);
    }

    //Test 2
    public static void veryLargeFileTest() {
        long startTime = System.nanoTime();
        Path file = Paths.get("src/csvData900m.csv");
        int a = 0;
        try (CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(file)) {

            for (CsvRecord record : csv) {
                a++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Duration (ms): "+duration/1000000);
        System.out.println("Lines read: " + a);
    }

    //Test 3
    public static void readAndWriteLargeFileTest()  {
        long startTime = System.nanoTime();
        Path inputFile = Paths.get("src/csvData100m.csv");
        Path outputFile =Paths.get("src/outputLarge.csv");
        try (
            CsvWriter csvWriter = CsvWriter.builder().build(outputFile);
            CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(inputFile);
        ) {
            for (CsvRecord record : csv) {
                csvWriter.writeRecord(record.getField(0),record.getField(1),record.getField(2));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Duration (ms): "+duration/1000000);
    }

    //Test 4
    public static void readAndWriteVeryLargeFileTest()  {
        long startTime = System.nanoTime();
        Path inputFile = Paths.get("src/csvData900m.csv");
        Path outputFile =Paths.get("src/outputVeryLarge.csv");
        try (
                CsvWriter csvWriter = CsvWriter.builder().build(outputFile);
                CsvReader<CsvRecord> csv = CsvReader.builder().ofCsvRecord(inputFile);
        ) {
            for (CsvRecord record : csv) {
                csvWriter.writeRecord(record.getField(0),record.getField(1),record.getField(2));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Duration (ms): "+duration/1000000);
    }

}
