package strategy;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatabaseSaveStrategyTest {
        @InjectMocks
        DatabaseSaveStrategy databaseSaveStrategy;

        @Mock
        EntityManager entityManagerMock;

        @BeforeEach
                void setUp() {
                MockitoAnnotations.initMocks(this);
        }

        Date dateForAll = new GregorianCalendar(2019,8-1,9).getTime();
        Logbook log = new Logbook(new Departure("portas",dateForAll), new Catch("Salmon", 52), new Arrival("portas",dateForAll), new EndOfFishing(dateForAll), "online");
        @Test
        void createLogbookTestResponse() {
                databaseSaveStrategy = new DatabaseSaveStrategy(entityManagerMock);
        Response response = databaseSaveStrategy.create(log);
        Assertions.assertEquals(Response.Status.CREATED.getStatusCode(),response.getStatus());
    }


}
