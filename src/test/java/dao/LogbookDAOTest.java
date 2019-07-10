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
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogbookDAOTest {
    @InjectMocks
    LogbookDAO logbookDAO;

    @Mock
    EntityManager entityManagerMock;

    Date dateForAll = new GregorianCalendar(2019, 8 - 1, 9).getTime();
    Logbook log = new Logbook(new Departure("portas", dateForAll), new Catch("Salmon", 52), new Arrival("portas", dateForAll), new EndOfFishing(dateForAll), "online");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        logbookDAO = new LogbookDAO(entityManagerMock);
        Response response = null;
        try {
            response = logbookDAO.create(log);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }
}