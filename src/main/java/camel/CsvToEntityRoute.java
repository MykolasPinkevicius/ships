package camel;

import camel.parser.EntitiesParser;
import enums.PathEnums;
import model.Logbook;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.zipfile.ZipSplitter;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class CsvToEntityRoute extends RouteBuilder {

    private static final String CONFIGURE_HEADER_NAME = "zipFileName";
    private static Map<String, Map<String, Object>> logbookMap = new HashMap<>();

    @Override
    public void configure() {
        from(PathEnums.ZIPSCANPATH.getPath())
                .split(new ZipSplitter())
                .streaming()
                .choice()
                .when(header(CONFIGURE_HEADER_NAME).isEqualTo("Arrival.csv"))
                .to(PathEnums.CSVDELETEPATH.getPath() + "?fileName=Arrival.csv")
                .process().exchange(exchange ->
                EntitiesParser.arrivalCSVParser(logbookMap)
        ).endChoice()
                .when(header(CONFIGURE_HEADER_NAME).isEqualTo("Departure.csv"))
                .to(PathEnums.CSVDELETEPATH.getPath() + "?fileName=Departure.csv")
                .process().exchange(exchange ->
                EntitiesParser.departureCSVParser(logbookMap)
        ).endChoice()
                .when(header(CONFIGURE_HEADER_NAME).isEqualTo("Catch.csv"))
                .to(PathEnums.CSVDELETEPATH.getPath() + "?fileName=Catch.csv")
                .process().exchange(exchange ->
                EntitiesParser.catchCSVParser(logbookMap)
        ).endChoice()
                .when(header(CONFIGURE_HEADER_NAME).isEqualTo("EndOfFishing.csv"))
                .to(PathEnums.CSVDELETEPATH.getPath() + "?fileName=EndOfFishing.csv")
                .process().exchange(exchange ->
                EntitiesParser.endOfFishingCSVParser(logbookMap)
        ).endChoice()
                .when(header(CONFIGURE_HEADER_NAME).isEqualTo("Logbook.csv"))
                .to(PathEnums.CSVDELETEPATH.getPath() + "?fileName=Logbook.csv")
                .process().exchange(exchange ->
                EntitiesParser.logbookCommunicationTypeCSVParser(logbookMap)
        ).endChoice()
                .end()
                .end()
//                TODO 5 times invoke
                .process().exchange(exchange -> {
            List<Logbook> logbookList = EntitiesParser.logbookListCSVParser(logbookMap);
            List<String> logbookListToString = new ArrayList<>();
            for (Logbook logbook : logbookList) {
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
