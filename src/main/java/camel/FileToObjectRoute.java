package camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.PathEnums;
import enums.TimerConfiguration;
import model.Logbook;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.io.File;

public class FileToObjectRoute extends RouteBuilder {

    public void configure() {
        from(TimerConfiguration.TIMER.getTimerConfig())
                .pollEnrich(PathEnums.SCANPATH.getPath())
                .process(exchange -> {
                    File file = exchange.getIn().getBody(File.class);
                    ObjectMapper mapper = new ObjectMapper();
                    Logbook logbook;
                    logbook = mapper.readValue(file, Logbook.class);
                    exchange.getOut().setBody(logbook.toJson().toString());
                })
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http://localhost:8080/project/app/logbooks/");
    }
}
