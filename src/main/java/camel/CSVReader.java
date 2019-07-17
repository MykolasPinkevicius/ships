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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CSVReader {

    private static Map<String, Map<String, Object>> logbookMap = new HashMap<>();

    public static void arrivalCSVParser() throws IOException {
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
            if (!logbookMap.containsKey(logbookID)) {
                logbookMap.put(logbookID, new HashMap<>());
                logbookMap.get(logbookID).put("arrival", arrival);
            } else {
                logbookMap.get(logbookID).put("arrival", arrival);
            }
        }
    }

    public static void departureCSVParser() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(PathEnums.CSVZIPPATH.getPath() + "Departure.csv"));
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
            if (!logbookMap.containsKey(logbookID)) {
                logbookMap.put(logbookID, new HashMap<>());
                logbookMap.get(logbookID).put("departure", departure);
            } else {
                logbookMap.get(logbookID).put("departure", departure);
            }
        }
    }

    public static void catchCSVParser() throws IOException {
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
            if (!logbookMap.containsKey(logbookID)) {
                logbookMap.put(logbookID, new HashMap<>());
                logbookMap.get(logbookID).put("catch", aCatch);
            } else {
                logbookMap.get(logbookID).put("catch", aCatch);
            }
        }
    }

    public static void endOfFishingCSVParser() throws IOException {
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
            if (!logbookMap.containsKey(logbookID)) {
                logbookMap.put(logbookID, new HashMap<>());
                logbookMap.get(logbookID).put("endOfFishing", endOfFishing);
            } else {
                logbookMap.get(logbookID).put("endOfFishing", endOfFishing);
            }
        }
    }

    public static void logbookCommunicationTypeCSVParser() throws IOException {
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
            if (!logbookMap.containsKey(iD)) {
                logbookMap.put(iD, new HashMap<>());
                logbookMap.get(iD).put("communicationType", communicationType);
            } else {
                logbookMap.get(iD).put("communicationType", communicationType);
            }
        }
    }

    public List<Logbook> logbookListCSVParser() {
        List<Logbook> logbookList = new ArrayList<>();
        for (int i = 1; i < logbookMap.size()+1; i++) {
            Map<String, Object> logbookObjects = logbookMap.get(i);
            Logbook logbook = new Logbook();
            logbook.setId(Long.valueOf(i));
            logbook.setaCatch((Catch)logbookObjects.get("catch"));
            logbook.setArrival((Arrival)logbookObjects.get("arrival"));
            logbook.setDeparture((Departure)logbookObjects.get("departure"));
            logbook.setEndOfFishing((EndOfFishing)logbookObjects.get("endOfFishing"));
            logbook.setCommunicationType((String)logbookObjects.get("communicationType"));
            logbookList.add(logbook);
        }
        return logbookList;
    }
}
