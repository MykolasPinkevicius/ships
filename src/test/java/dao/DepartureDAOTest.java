package dao;

import model.Departure;
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

class DepartureDAOTest {

    @InjectMocks
    private DepartureDAO departureDAO;

    @Mock
    private EntityManager entityManager;

    private Date dateForAll;
    private Departure departure;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dateForAll = new GregorianCalendar(2019, Calendar.AUGUST, 9).getTime();
        departure = new Departure("Portas", dateForAll);
    }

    @Test
    public void create() {
       departureDAO.create(departure);
       verify(entityManager, times(1)).persist(departure);
    }

    @Test
    public void remove() {
        Long departureId = 1L;
        departureDAO.remove(departureId);
        verify(entityManager, times(1)).remove(departureId);
    }
}