package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class FileTransfer {

    public static void save() {

        CamelContext camelContext = new DefaultCamelContext();
        try {
//      File saving
//            camelContext.addRoutes(new SimpleRouteBuilder());
            camelContext.addRoutes(new FileToObjectRoute());
            camelContext.start();
            Thread.sleep(6000);
            camelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
