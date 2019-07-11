package dao;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LogbookDAOTest {
    @InjectMocks
    LogbookDAO logbookDAO;

    @Mock
    EntityManager entityManager;

    Date dateForAll = new GregorianCalendar(2019, Calendar.AUGUST, 9).getTime();
    Logbook log = new Logbook(new Departure("portas", dateForAll), new Catch("Salmon", 52), new Arrival("portas", dateForAll), new EndOfFishing(dateForAll), "online");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        Response response = null;
        try {
            response = logbookDAO.create(log);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    void delete() {
        Long logbookId = 1L;

        logbookDAO.remove(logbookId);

        verify(entityManager, times(1)).remove(logbookId);
    }

}