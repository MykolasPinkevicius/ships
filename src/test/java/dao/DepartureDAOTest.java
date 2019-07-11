package dao;

import model.Departure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DepartureDAOTest {

    @InjectMocks
    DepartureDAO departureDAO;

    @Mock
    EntityManager entityManager;

    Date dateForAll = new GregorianCalendar(2019, Calendar.AUGUST, 9).getTime();
    Departure departure = new Departure("Portas", dateForAll);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        Response response = null;
        try {
            response = departureDAO.create(departure);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    void remove() {
        Long departureId = 1L;
        departureDAO.remove(departureId);
        verify(entityManager, times(1)).remove(departureId);
    }
}