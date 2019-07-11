package dao;

import model.EndOfFishing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class EndOfFishingDAOTest {

    @InjectMocks
    EndOfFishingDAO endOfFishingDAO;

    @Mock
    EntityManager entityManager;

    Date dateForAll = new GregorianCalendar(2019, 8 - 1, 9).getTime();
    EndOfFishing endOfFishing = new EndOfFishing(dateForAll);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        Response response = null;
        try {
            response = endOfFishingDAO.create(endOfFishing);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    void remove() {
        Long endOfFishingId = 1L;

        endOfFishingDAO.remove(endOfFishingId);

        verify(entityManager, times(1)).remove(endOfFishingId);
    }
}