package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.NamedCsvRecord;

public class BackAlleyTour2_JB {

    static Path file = Paths.get("src/testJavaBeans.csv");


    public static void main(final String[] args) throws IOException {
        readVideoGames();
    }

    private static void readVideoGames() throws IOException {
        try (var csv = CsvReader.builder().ofNamedCsvRecord(file)) {
            Stream<VideoGame> games = csv.stream().map(BackAlleyTour2_JB::mapVideoGame);
            games.forEach(System.out::println);
        }
    }

    private static VideoGame mapVideoGame(final NamedCsvRecord rec) {
        return new VideoGame(
                rec.getField(0),
                Double.parseDouble(rec.getField(1)),
                Double.parseDouble(rec.getField(2)),
                Integer.parseInt(rec.getField(3)),
                rec.getField(4)
        );
    }

    private record VideoGame(String gameName, double criticScore, double userScore, int gameID, String gameDescription) {

    }


}
