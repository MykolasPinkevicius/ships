package locking;

import configuration.Configuration;
import dao.ConfigurationDAO;
import dao.LogbookDAO;
import model.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RunWith(Arquillian.class)
public class PessimisticLockingTest {

    private static final Date DATEFORALL = new GregorianCalendar(2019, Calendar.AUGUST, 9).getTime();
    private static final Logbook LOGBOOK = new Logbook(
            new Departure("portas", DATEFORALL),
            new Catch("Salmon", 52),
            new Arrival("portas", DATEFORALL),
            new EndOfFishing(DATEFORALL), "online");
    @Inject
    private LogbookDAO logbookDAO;

    @Deployment
    public static Archive<?> createTestArchive() {

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
        Thread t3 = new Thread(searchEntity);
        t1.start();
        t2.start();
        t3.start();
    }

    class SearchAndLockEntity implements Runnable {
        @Override
        public void run() {
            logbookDAO.update(5L,LOGBOOK);
            System.out.println(LOGBOOK);
        }
    }

    class SearchEntity implements  Runnable {
        @Override
        public void run() {
            System.out.println(logbookDAO.findById(5L));
        }
    }
}
