package camel;

import camel.parser.EntitiesParser;
import enums.PathEnums;
import model.Logbook;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.zipfile.ZipSplitter;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CsvToEntityRoute extends RouteBuilder {

    private static final String CONFIGURE_HEADER_NAME = "zipFileName";


    @Override
    public void configure() {
        from(PathEnums.ZIPSCANPATH.getPath())
                .split(new ZipSplitter())
                    .streaming()
                        .choice()
                            .when(header(CONFIGURE_HEADER_NAME).isEqualTo("Arrival.csv"))
                                .to(PathEnums.ZIPINBOXPATH.getPath() + "?fileName=Arrival.csv")
                                .process().exchange(exchange -> {
                                    EntitiesParser.arrivalCSVParser();
                                }).endChoice()
                            .when(header(CONFIGURE_HEADER_NAME).isEqualTo("Departure.csv"))
                                .to(PathEnums.ZIPINBOXPATH.getPath() + "?fileName=Departure.csv")
                                .process().exchange(exchange -> {
                                    EntitiesParser.departureCSVParser();
                                }).endChoice()
                            .when(header(CONFIGURE_HEADER_NAME).isEqualTo("Catch.csv"))
                                .to(PathEnums.ZIPINBOXPATH.getPath() + "?fileName=Catch.csv")
                                .process().exchange(exchange -> {
                                    EntitiesParser.catchCSVParser();
                                }).endChoice()
                            .when(header(CONFIGURE_HEADER_NAME).isEqualTo("EndOfFishing.csv"))
                                .to(PathEnums.ZIPINBOXPATH.getPath() + "?fileName=EndOfFishing.csv")
                                .process().exchange(exchange -> {
                                    EntitiesParser.endOfFishingCSVParser();
                                }).endChoice()
                            .when(header(CONFIGURE_HEADER_NAME).isEqualTo("Logbook.csv"))
                                .to(PathEnums.ZIPINBOXPATH.getPath() + "?fileName=Logbook.csv")
                                .process().exchange(exchange -> {
                                    EntitiesParser.logbookCommunicationTypeCSVParser();
                                    }).endChoice()
                            .end()
                        .end()
                .process().exchange(exchange -> {
                List <Logbook> logbookList = EntitiesParser.logbookListCSVParser();
                List <String> logbookListToString = new ArrayList<>();
                for(Logbook logbook : logbookList) {
                    String logbookString = String.valueOf(logbook.toJson().toString());
                    logbookListToString.add(logbookString);
                }
                exchange.getOut().setBody(logbookListToString.toString());
        })

                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http://localhost:8080/project/app/logbooks/allLogbooks/");
    }
}
