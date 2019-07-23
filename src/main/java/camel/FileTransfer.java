package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.converter.stream.CachedOutputStream;
import org.apache.camel.impl.DefaultCamelContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class FileTransfer {
    static CamelContext camelContext = new DefaultCamelContext();


    @PostConstruct
    public static void save() {
        try {
            camelContext.start();
            camelContext.setStreamCaching(true);
            camelContext.addRoutes(new FileToObjectRoute());
            camelContext.addRoutes(new CsvToEntityRoute());
            camelContext.getProperties().put(CachedOutputStream.THRESHOLD, "750000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void shutdown() {
        try {
            camelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
