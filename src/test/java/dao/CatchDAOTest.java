package dao;

import model.Catch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CatchDAOTest {
    @InjectMocks
    private CatchDAO catchDAO;

    @Mock
    private EntityManager entityManager;

    private Catch aCatch;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        aCatch = new Catch("Salomonas", 52D);
    }

    @Test
    public void create() {
        Response response = null;
        try {
            response = catchDAO.create(aCatch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void remove() {
        Long catchId = 1L;
        catchDAO.remove(catchId);
        verify(entityManager, times(1)).remove(catchId);
    }
}