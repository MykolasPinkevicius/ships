package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class FileTransfer {

    static CamelContext camelContext = new DefaultCamelContext();

    private static Logger logger = LogManager.getLogger(FileTransfer.class);

    @PostConstruct
    public static void save() {
        try {
            camelContext.start();
            camelContext.addRoutes(new FileToObjectRoute());
            camelContext.addRoutes(new CsvToEntityRoute());
        } catch (Exception e) {
            logger.error("Error was in camelContext starting {}", e.getMessage());
        }
    }

    @PreDestroy
    public void shutdown() {
        try {
            camelContext.stop();
        } catch (Exception e) {
            logger.error("Error was in camelContext shutting down {}", e.getMessage());
        }
    }
}
