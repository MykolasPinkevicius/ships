package camel;

import enums.PathEnums;
import model.*;
import org.apache.camel.builder.RouteBuilder;

import java.io.IOException;
import java.util.List;

public class CSVToObjectRoute extends RouteBuilder{

    private List<Arrival> listOfArrivals;
    private List<Departure> listOfDeparture;
    private List<Catch> listOfCatch;
    private List<EndOfFishing> listOfEndOfFishing;
    private List<Logbook> listOfLogbook;

    @Override
    public void configure() {
        from(PathEnums.ZIPINBOXPATH.getPath())
        .process().exchange( e -> {
            try {
                listOfArrivals = CSVReader.arrivalCSVParser();
                listOfDeparture = CSVReader.departureCSVParser();
                listOfCatch = CSVReader.catchCSVParser();
                listOfEndOfFishing = CSVReader.endOfFishingCSVParser();
                listOfLogbook = CSVReader.logbookCommunicationTypeCSVParser();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        })
        ;
    }
}
