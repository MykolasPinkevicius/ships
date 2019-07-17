package camel;

import enums.PathEnums;
import model.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private static List<Arrival> arrivals = new ArrayList<>();
    private static List<Departure> departures = new ArrayList<>();
    private static List<Catch> catches = new ArrayList<>();
    private static List<EndOfFishing> endOfFishings = new ArrayList<>();
    private static List<Logbook> logbooks = new ArrayList<>();

    public static List<Arrival> arrivalCSVParser() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(PathEnums.CSVZIPPATH.getPath()+"Arrival.csv"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader("ID", "logbookID", "port", "date")
                .withSkipHeaderRecord()
                .withIgnoreHeaderCase()
                .withTrim()
                .withDelimiter(';'));

        for (CSVRecord record : csvParser) {
            String iD = record.get("ID");
            String logbookID = record.get("logbookID");
            String port = record.get("port");
            String date = record.get("date");
            Arrival arrival = new Arrival(port, Date.valueOf(date));
            arrival.setId(Long.valueOf(iD));
            arrivals.add(arrival);
        }
        return arrivals;
    }

    public static List<Departure> departureCSVParser() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(PathEnums.CSVZIPPATH.getPath()+"Departure.csv"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader("ID", "logbookID", "port", "date")
                .withSkipHeaderRecord()
                .withIgnoreHeaderCase()
                .withTrim()
                .withDelimiter(';'));

        for (CSVRecord record : csvParser) {
            String iD = record.get("ID");
            String logbookID = record.get("logbookID");
            String port = record.get("port");
            String date = record.get("date");
            Departure departure = new Departure(port, Date.valueOf(date));
            departure.setId(Long.valueOf(iD));
            departures.add(departure);
        }
        return departures;
    }

    public static List<Catch> catchCSVParser() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(PathEnums.CSVZIPPATH.getPath()+"Catch.csv"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader("ID", "logbookID", "species", "weight")
                .withSkipHeaderRecord()
                .withIgnoreHeaderCase()
                .withTrim()
                .withDelimiter(';'));

        for (CSVRecord record : csvParser) {
            String iD = record.get("ID");
            String logbookID = record.get("logbookID");
            String species = record.get("port");
            String weight = record.get("date");
            Catch aCatch = new Catch(species, Double.valueOf(weight));
            aCatch.setId(Long.valueOf(iD));
            catches.add(aCatch);
        }
        return catches;
    }

    public static List<EndOfFishing> endOfFishingCSVParser() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(PathEnums.CSVZIPPATH.getPath()+"EndOfFishing.csv"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader("ID", "logbookID", "date")
                .withSkipHeaderRecord()
                .withIgnoreHeaderCase()
                .withTrim()
                .withDelimiter(';'));

        for (CSVRecord record : csvParser) {
            String iD = record.get("ID");
            String logbookID = record.get("logbookID");
            String date = record.get("date");
            EndOfFishing endOfFishing = new EndOfFishing(Date.valueOf(date));
            endOfFishing.setId(Long.valueOf(iD));
            endOfFishings.add(endOfFishing);
        }
        return endOfFishings;
    }

    public static List<Logbook> logbookCommunicationTypeCSVParser() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(PathEnums.CSVZIPPATH.getPath()+"Logbook.csv"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader("ID", "communicationType")
                .withSkipHeaderRecord()
                .withIgnoreHeaderCase()
                .withTrim()
                .withDelimiter(';'));

        for (CSVRecord record : csvParser) {
            String iD = record.get("ID");
            String communicationType = record.get("communicationType");
            Logbook logbook = new Logbook(null, null, null, null, communicationType);
            logbook.setId(Long.valueOf(iD));
            logbooks.add(logbook);
        }
        return logbooks;
    }

}
