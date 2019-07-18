package camel;

import dao.LogbookDAO;
import enums.PathEnums;
import model.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.zipfile.ZipSplitter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class CSVReader extends RouteBuilder {

    @Inject
    private LogbookDAO logbookDAO;

    private static Map<String, Map<String, Object>> logbookMap = new HashMap<>();

    public static void arrivalCSVParser(){
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void departureCSVParser(){
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void catchCSVParser(){
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void endOfFishingCSVParser() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logbookCommunicationTypeCSVParser() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Logbook> logbookListCSVParser() {
        return logbookMap.entrySet().stream().map(stringMapEntry ->
                new Logbook.Builder()
                        .withID(stringMapEntry.getKey())
                        .withArrival((Arrival) stringMapEntry.getValue().get("arrival"))
                        .withDeparture((Departure) stringMapEntry.getValue().get("departure"))
                        .withCatch((Catch) stringMapEntry.getValue().get("catch"))
                        .withEndOfFishing((EndOfFishing) stringMapEntry.getValue().get("endOfFishing"))
                        .withCommunicationType((String) stringMapEntry.getValue().get("communicationType"))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void configure(){
        from(PathEnums.ZIPSCANPATH.getPath())
                .beanRef("LogbookDAO")
                .split(new ZipSplitter())
                .streaming()
                .choice()
                    .when(header("zipFileName").isEqualTo("Arrival.csv"))
                        .to(PathEnums.ZIPINBOXPATH.getPath())
                        .process().exchange(exchange -> arrivalCSVParser())
                            .endChoice()
                    .when(header("zipFileName").isEqualTo("Departure.csv"))
                        .to(PathEnums.ZIPINBOXPATH.getPath())
                        .process().exchange(exchange -> departureCSVParser())
                            .endChoice()
                    .when(header("zipFileName").isEqualTo("EndOfFishing.csv"))
                        .to(PathEnums.ZIPINBOXPATH.getPath())
                        .process().exchange(exchange -> endOfFishingCSVParser())
                            .endChoice()
                    .when(header("zipFileName").isEqualTo("Catch.csv"))
                        .to(PathEnums.ZIPINBOXPATH.getPath())
                        .process().exchange(exchange -> catchCSVParser())
                            .endChoice()
                    .when(header("zipFileName").isEqualTo("Logbook.csv"))
                        .to(PathEnums.ZIPINBOXPATH.getPath())
                        .process().exchange(exchange -> logbookCommunicationTypeCSVParser())
                            .endChoice()
                    .end()
                .end()
                .process().exchange(exchange ->
                exchange.getOut().setBody(logbookListCSVParser())).bean(logbookDAO, "createAll");
    }
}
