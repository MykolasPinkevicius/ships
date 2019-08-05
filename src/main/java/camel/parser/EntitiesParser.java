package camel.parser;

import enums.PathEnums;
import model.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntitiesParser {

    private static final String ARRIVAL = "arrival";
    private static final String DEPARTURE = "departure";
    private static final String CATCH = "catch";
    private static final String ENDOFFISHING = "endOfFishing";
    private static final String COMMUNICATIONTYPE = "communicationType";
    private static final String LOGBOOKID = "logbookID";
    private static Logger logger = LogManager.getLogger(EntitiesParser.class);

    protected EntitiesParser() {
    }

    public static void arrivalCSVParser(Map<String, Map<String, Object>> logbookMap) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(PathEnums.CSVZIPTESTPATH.getPath() + "Arrival.csv"));
            try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withHeader("ID", LOGBOOKID, "port", "date")
                    .withSkipHeaderRecord()
                    .withIgnoreHeaderCase()
                    .withTrim()
                    .withDelimiter(';'))) {

                for (CSVRecord record : csvParser) {
                    String id = record.get("ID");
                    String logbookID = record.get(LOGBOOKID);
                    String port = record.get("port");
                    String date = record.get("date");
                    Arrival arrival = new Arrival(port, Date.valueOf(date));
                    arrival.setId(Long.valueOf(id));
                    if (!logbookMap.containsKey(logbookID)) {
                        logbookMap.put(logbookID, new HashMap<>());
                        logbookMap.get(logbookID).put(ARRIVAL, arrival);
                    } else {
                        logbookMap.get(logbookID).put(ARRIVAL, arrival);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error happened during parsing arrival {}", e.getMessage());
        }
    }

    public static void departureCSVParser(Map<String, Map<String, Object>> logbookMap) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(PathEnums.CSVZIPTESTPATH.getPath() + "Departure.csv"));
            try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withHeader("ID", LOGBOOKID, "port", "date")
                    .withSkipHeaderRecord()
                    .withIgnoreHeaderCase()
                    .withTrim()
                    .withDelimiter(';'))) {

                for (CSVRecord record : csvParser) {
                    String id = record.get("ID");
                    String logbookID = record.get(LOGBOOKID);
                    String port = record.get("port");
                    String date = record.get("date");
                    Departure departure = new Departure(port, Date.valueOf(date));
                    departure.setId(Long.valueOf(id));
                    if (!logbookMap.containsKey(logbookID)) {
                        logbookMap.put(logbookID, new HashMap<>());
                        logbookMap.get(logbookID).put(DEPARTURE, departure);
                    } else {
                        logbookMap.get(logbookID).put(DEPARTURE, departure);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error happened during parsing departure {}", e.getMessage());
        }
    }

    public static void catchCSVParser(Map<String, Map<String, Object>> logbookMap) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(PathEnums.CSVZIPTESTPATH.getPath() + "Catch.csv"));
            try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withHeader("ID", LOGBOOKID, "species", "weight")
                    .withSkipHeaderRecord()
                    .withIgnoreHeaderCase()
                    .withTrim()
                    .withDelimiter(';'))) {

                for (CSVRecord record : csvParser) {
                    String id = record.get("ID");
                    String logbookID = record.get(LOGBOOKID);
                    String species = record.get("species");
                    String weight = record.get("weight");
                    Catch aCatch = new Catch(species, Double.valueOf(weight));
                    aCatch.setId(Long.valueOf(id));
                    if (!logbookMap.containsKey(logbookID)) {
                        logbookMap.put(logbookID, new HashMap<>());
                        logbookMap.get(logbookID).put(CATCH, aCatch);
                    } else {
                        logbookMap.get(logbookID).put(CATCH, aCatch);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error happened during parsing catch {}", e.getMessage());
        }
    }

    public static void endOfFishingCSVParser(Map<String, Map<String, Object>> logbookMap) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(PathEnums.CSVZIPTESTPATH.getPath() + "EndOfFishing.csv"));
            try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withHeader("ID", LOGBOOKID, "date")
                    .withSkipHeaderRecord()
                    .withIgnoreHeaderCase()
                    .withTrim()
                    .withDelimiter(';'))) {

                for (CSVRecord record : csvParser) {
                    String id = record.get("ID");
                    String logbookID = record.get(LOGBOOKID);
                    String date = record.get("date");
                    EndOfFishing endOfFishing = new EndOfFishing(Date.valueOf(date));
                    endOfFishing.setId(Long.valueOf(id));
                    if (!logbookMap.containsKey(logbookID)) {
                        logbookMap.put(logbookID, new HashMap<>());
                        logbookMap.get(logbookID).put(ENDOFFISHING, endOfFishing);
                    } else {
                        logbookMap.get(logbookID).put(ENDOFFISHING, endOfFishing);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error happened during parsing endOfFishing {}", e.getMessage());
        }
    }

    public static void logbookCommunicationTypeCSVParser(Map<String, Map<String, Object>> logbookMap) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(PathEnums.CSVZIPTESTPATH.getPath() + "Logbook.csv"));
            try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withHeader("ID", COMMUNICATIONTYPE)
                    .withSkipHeaderRecord()
                    .withIgnoreHeaderCase()
                    .withTrim()
                    .withDelimiter(';'))) {

                for (CSVRecord record : csvParser) {
                    String id = record.get("ID");
                    String communicationType = record.get(COMMUNICATIONTYPE);
                    Logbook logbook = new Logbook(null, null, null, null, communicationType);
                    logbook.setId(Long.valueOf(id));
                    if (!logbookMap.containsKey(id)) {
                        logbookMap.put(id, new HashMap<>());
                        logbookMap.get(id).put(COMMUNICATIONTYPE, communicationType);
                    } else {
                        logbookMap.get(id).put(COMMUNICATIONTYPE, communicationType);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error happened during parsing logbook CommunicationType {}", e.getMessage());
        }
    }

    public static List<Logbook> logbookListCSVParser(Map<String, Map<String, Object>> logbookMap) {
        return logbookMap.entrySet().stream().map(stringMapEntry ->
                new Logbook.Builder()
                        .withID(stringMapEntry.getKey())
                        .withArrival((Arrival) stringMapEntry.getValue().get(ARRIVAL))
                        .withDeparture((Departure) stringMapEntry.getValue().get(DEPARTURE))
                        .withCatch((Catch) stringMapEntry.getValue().get(CATCH))
                        .withEndOfFishing((EndOfFishing) stringMapEntry.getValue().get(ENDOFFISHING))
                        .withCommunicationType((String) stringMapEntry.getValue().get(COMMUNICATIONTYPE))
                        .build())
                .collect(Collectors.toList());
    }

}
