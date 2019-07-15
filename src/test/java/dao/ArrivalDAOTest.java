package dao;

import model.Arrival;
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

class ArrivalDAOTest {

    @InjectMocks
    private ArrivalDAO arrivalDAO;

    @Mock
    private EntityManager entityManager;

    private Date dateForAll;
    private Arrival arrival;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dateForAll = new GregorianCalendar(2019, Calendar.AUGUST, 9).getTime();
        arrival = new Arrival("Portas", dateForAll);
    }

    @Test
    public void create() {
        Response response = null;
        try {
            response = arrivalDAO.create(arrival);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void remove() {
        Long arrivalId = 1L;
        arrivalDAO.remove(arrivalId);
        verify(entityManager, times(1)).remove(arrivalId);
    }
}