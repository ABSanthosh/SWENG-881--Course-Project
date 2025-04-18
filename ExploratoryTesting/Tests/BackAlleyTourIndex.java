package org.example;
import java.io.IOException;
import java.nio.file.Paths;

import de.siegmar.fastcsv.reader.CsvRecord;
import de.siegmar.fastcsv.reader.IndexedCsvReader;

import java.nio.file.Path;
import java.util.List;

public class BackAlleyTourIndex {


    public static void indexTestReader() throws IOException {
        Path file = Paths.get("src/csvData100m.csv");
        long startTimeBuilder = System.nanoTime();
        final IndexedCsvReader<CsvRecord> csv = IndexedCsvReader.builder()
                .pageSize(5)
                .ofCsvRecord(file);
        long endTimeBuilder =  System.nanoTime();

        try(csv) {

            long startTimeGetPageCount = System.nanoTime();
            int lastPage=csv.getIndex().getPageCount()-1;
            long endTimeGetPageCount = System.nanoTime();

            long startTimeGetLastPageRecords = System.nanoTime();
            List<CsvRecord> lastPageRecords = csv.readPage(lastPage);
            System.out.println("Last Page:");
            lastPageRecords.forEach(System.out::println);
            long endTimeGetLastPageRecords = System.nanoTime();


            long startTimeGetMiddlePageRecords = System.nanoTime();
            int middlePage=lastPage/2;
            List<CsvRecord> middlePageRecords = csv.readPage(middlePage);
            System.out.println();
            System.out.println("Middle Page:");
            middlePageRecords.forEach(System.out::println);
            long endTimeGetMiddlePageRecords = System.nanoTime();

            int quarterPage=lastPage/4;
            long startTimeGetQuarterPageRecords = System.nanoTime();
            List<CsvRecord> quarterPageRecords = csv.readPage(quarterPage);
            System.out.println();
            System.out.println("Quarter Page:");
            quarterPageRecords.forEach(System.out::println);
            long endTimeGetQuarterPageRecords = System.nanoTime();

            System.out.println();

            System.out.println("Duration Builder (ms)"+((endTimeBuilder-startTimeBuilder)/1000000));
            System.out.println("Duration Get Page Count (ms)"+((endTimeGetPageCount-startTimeGetPageCount)/1000000));
            System.out.println("Duration Last Page Records (ms)"+((endTimeGetLastPageRecords-startTimeGetLastPageRecords)/1000000));
            System.out.println("Duration Middle Page Records (ms)"+((endTimeGetMiddlePageRecords-startTimeGetMiddlePageRecords)/1000000));
            System.out.println("Duration Quarter Page Records (ms)"+((endTimeGetQuarterPageRecords-startTimeGetQuarterPageRecords)/1000000));

        }
    }
}
