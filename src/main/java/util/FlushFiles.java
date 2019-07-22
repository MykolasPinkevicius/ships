package util;

import enums.PathEnums;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FlushFiles {

    public static void flushFiles() {
        try {
            Files.deleteIfExists(Paths.get(PathEnums.CSVZIPPATH.getPath()+"Arrival.csv"));
            Files.deleteIfExists(Paths.get(PathEnums.CSVZIPPATH.getPath()+"Departure.csv"));
            Files.deleteIfExists(Paths.get(PathEnums.CSVZIPPATH.getPath()+"Catch.csv"));
            Files.deleteIfExists(Paths.get(PathEnums.CSVZIPPATH.getPath()+"EndOfFishing.csv"));
            Files.deleteIfExists(Paths.get(PathEnums.CSVZIPPATH.getPath()+"Logbook.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
