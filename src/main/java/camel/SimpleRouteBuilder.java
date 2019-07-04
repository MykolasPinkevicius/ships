package camel;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:C:\\Program Files\\wildfly-9.0.2.Final\\wildfly-9.0.2.Final\\bin\\?fileName=logbook.log")
                .to("file:C:\\Program Files\\wildfly-9.0.2.Final\\wildfly-9.0.2.Final\\");
    }
}
