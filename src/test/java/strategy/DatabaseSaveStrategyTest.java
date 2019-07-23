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
    private DatabaseSaveStrategy databaseSaveStrategy;

    private Logbook logbook;

    @Mock
    private EntityManager entityManagerMock;

    @BeforeEach
    void setUp() {
        Date dateForAll = new GregorianCalendar(2019, Calendar.AUGUST, 9).getTime();
        logbook = new Logbook(
                new Departure("portas", dateForAll),
                new Catch("Salmon", 52),
                new Arrival("portas", dateForAll), new EndOfFishing(dateForAll), "online");
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void createLogbookTestResponse() {
        databaseSaveStrategy.create(logbook);
        verify(entityManagerMock, times(1)).merge(logbook);
    }


}
