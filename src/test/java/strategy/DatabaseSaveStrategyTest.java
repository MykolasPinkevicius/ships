package strategy;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.GregorianCalendar;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DatabaseSaveStrategyTest {

        EntityManager entityManager;

        DatabaseSaveStrategy databaseSaveStrategy = new DatabaseSaveStrategy(entityManager.getEntityManagerFactory().createEntityManager());

        @BeforeAll
        public void setUp() {
            this.databaseSaveStrategy = new DatabaseSaveStrategy(entityManager);
        }

        @Test
        void createLogbookTestResponse() {

        Date dateForAll = new GregorianCalendar(2019,8-1,9).getTime();
        Logbook log = new Logbook(new Departure("portas",dateForAll), new Catch("Salmon", 52), new Arrival("portas",dateForAll), new EndOfFishing(dateForAll), "online");

        Response response = databaseSaveStrategy.create(log);
        Assertions.assertEquals(Response.status(Response.Status.CREATED),response);
    }


}
