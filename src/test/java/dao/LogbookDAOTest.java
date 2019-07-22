package dao;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LogbookDAOTest {



    @InjectMocks
    private LogbookDAO logbookDAO;

    @Mock
    private EntityManager entityManager;

    private TypedQuery<Logbook> typedQuery;
    private Date dateForAll;
    private Logbook logger;
    private List<Logbook> logList;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dateForAll = new GregorianCalendar(2019, Calendar.AUGUST, 9).getTime();
        logger = new Logbook(
                new Departure("portas", dateForAll),
                new Catch("Salmon", 52),
                new Arrival("portas", dateForAll),
                new EndOfFishing(dateForAll), "online");
        typedQuery = Mockito.mock(TypedQuery.class);
        logList = Arrays.asList(logger);
    }

    @Test
    public void create() throws IOException {
        logbookDAO.create(logger);
        verify(entityManager, times(1)).persist(logger);
    }

    @Test
    void delete() {
        Long logbookId = 1L;

        logbookDAO.remove(logbookId);

        verify(entityManager, times(1)).remove(logbookId);
    }

    @Test
    public void findByArrivalDateTest() {
        when(entityManager.createNativeQuery(anyString(), eq(Logbook.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyInt(), anyString())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(logger));

        List<Logbook> list = logbookDAO.findByArrivalDate("1999-03-15");

        assertEquals(logList, list);
    }
    @Test
    public void findByArrivalPortTest() {
        when(entityManager.createNativeQuery(anyString(), eq(Logbook.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyInt(), anyString())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(logger));

        List<Logbook> list = logbookDAO.findByArrivalPort("Portas");

        assertEquals(logList, list);
    }
    @Test
    public void findByCatchSpeciesTest() {
        when(entityManager.createNativeQuery(anyString(), eq(Logbook.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyInt(), anyString())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(logger));

        List<Logbook> list = logbookDAO.findByCatchSpecies("Salmon");

        assertEquals(logList, list);
    }
    @Test
    public void findByDeparturePort() {
        when(entityManager.createNativeQuery(anyString(), eq(Logbook.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyInt(), anyString())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(logger));

        List<Logbook> list = logbookDAO.findByDeparturePort("Portas");

        assertEquals(logList, list);
    }
    @Test
    public void findByDepartureDate() {
        when(entityManager.createNativeQuery(anyString(), eq(Logbook.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyInt(), anyString())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(logger));

        List<Logbook> list = logbookDAO.findByDepartureDate("1999-05-13");

        assertEquals(logList, list);
    }
    @Test
    public void findByEndOfFishingDateTest() {
        when(entityManager.createNativeQuery(anyString(), eq(Logbook.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyInt(), anyString())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(logger));

        List<Logbook> list = logbookDAO.findByDepartureDate("2222-11-13");

        assertEquals(logList, list);

    }
//    TODO pessimistic lock test
    @Test
    public void pessimisticLockingTest() {

    }


}