package locking;

import configuration.Configuration;
import dao.ConfigurationDAO;
import dao.LogbookDAO;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.PessimisticLockException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RunWith(Arquillian.class)
public class PessimisticLockingTest {
    private static final Date DATEFORALL = new GregorianCalendar(
            2019, Calendar.AUGUST, 9).getTime();

    private static final Logbook LOGBOOK = new Logbook(
            new Departure("portas", DATEFORALL),
            new Catch("Salmon", 52),
            new Arrival("portas", DATEFORALL),
            new EndOfFishing(DATEFORALL), "online");

    private static final Logbook LOGBOOK2 = new Logbook(
            new Departure("portasportas", DATEFORALL),
            new Catch("SalmonSalmon", 52),
            new Arrival("portasportas", DATEFORALL),
            new EndOfFishing(DATEFORALL), "online");

    private static Logger logger = LogManager.getLogger(PessimisticLockingTest.class);

    @Inject
    private LogbookDAO logbookDAO;

    @Inject
    private LogbookDAO logbookDAO2;

    @Deployment
    public static Archive createTestArchive() {

        return ShrinkWrap.create(WebArchive.class)
                .addPackage(Logbook.class.getPackage())
                .addPackages(true, "org.apache.logging.log4j")
                .addPackages(true, "strategy")
                .addPackage(Configuration.class.getPackage())
                .addPackage(LogbookDAO.class.getPackage())
                .addPackage(ConfigurationDAO.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testCanPersistLogbookObject() throws IOException {

        logbookDAO.create(LOGBOOK);
        SearchAndLockEntity searchAndLockEntity = new SearchAndLockEntity();
        SearchEntity searchEntity = new SearchEntity();
        Thread t1 = new Thread(searchAndLockEntity);
        Thread t2 = new Thread(searchEntity);

            t1.start();
            t2.start();
            t1.getState();
            t2.getState();
    }

    class SearchAndLockEntity implements Runnable {
        @Override
        public void run() {
            logbookDAO.update(5L, LOGBOOK2);
        }
    }

    class SearchEntity implements Runnable {
        @Override
        public void run() {
            try {
                logbookDAO2.findById(5L);
                logbookDAO2.findById(5L);
            } catch (PessimisticLockException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
