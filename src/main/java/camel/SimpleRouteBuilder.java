package camel;

import model.Logbook;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
import org.apache.camel.model.dataformat.JsonDataFormat;

public class SimpleRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
       JsonDataFormat jsonDataFormat = new JsonDataFormat();
    }
}
