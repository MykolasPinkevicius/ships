package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class FileTransfer {

    public static void save() {

        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new SimpleRouteBuilder());
        } catch (Exception e) {
            e.printStackTrace();
        }

        camelContext.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        camelContext.stop();
    }
}
