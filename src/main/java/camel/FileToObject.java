package camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Logbook;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.io.File;

public class FileToObject extends RouteBuilder {

    public void configure() throws Exception {
        from("file:C:\\Dev\\wildfly-9.0.2.Final\\?fileName=logbook.log")
//                .setHeader(Exchange.FILE_NAME, constant("logbook.log"))
                .process(exchange -> {
                    File file = exchange.getIn().getBody(File.class);
                    ObjectMapper mapper = new ObjectMapper();
                    Logbook logbook;
                    logbook = mapper.readValue(file, Logbook.class);
                    logbook.setCommunicationType("online");
                    exchange.getOut().setBody(logbook.toJson().toString());
                })
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http://localhost:8080/project/api/logbooks");

    }
}
