package dao;

import model.Archive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ArchiveDAOTest {

    @InjectMocks
    private ArchiveDAO archiveDAO;

    @Mock
    private EntityManager entityManager;

    private Archive archive;
    private Date date;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        date = new GregorianCalendar(2019, Calendar.AUGUST, 9).getTime();
        archive = new Archive("", date);
    }

    @Test
    void create() {
        archiveDAO.create(archive);
        verify(entityManager, times(1)).persist(archive);
    }

    @Test
    void findById() {
        archiveDAO.findById(5L);
        verify(entityManager, times(1)).find(Archive.class, 5L, LockModeType.PESSIMISTIC_READ);
    }

    @Test
    void delete() {
        archiveDAO.delete(5L);
        verify(entityManager, times(1)).find(Archive.class, 5L, LockModeType.PESSIMISTIC_READ);
    }
}