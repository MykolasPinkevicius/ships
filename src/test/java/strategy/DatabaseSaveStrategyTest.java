package strategy;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DatabaseSaveStrategyTest {
    @InjectMocks
    DatabaseSaveStrategy databaseSaveStrategy;

    @Mock
    EntityManager entityManagerMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    Date dateForAll = new GregorianCalendar(2019, Calendar.AUGUST, 9).getTime();
    Logbook log = new Logbook(new Departure("portas", dateForAll), new Catch("Salmon", 52), new Arrival("portas", dateForAll), new EndOfFishing(dateForAll), "online");

    @Test
    void createLogbookTestResponse() {
        databaseSaveStrategy.create(log);
        verify(entityManagerMock, times(1)).persist(log);
    }


}
