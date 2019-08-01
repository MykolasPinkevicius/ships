package dao;

import model.Archive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ArchiveDAOTest {

    @InjectMocks
    private ArchiveDAO archiveDAO;

    @Mock
    private EntityManager entityManager;

    private Archive archive;
    private Date date;
    private TypedQuery<Archive> typedQuery;
    private List<Archive> archiveList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        date = new GregorianCalendar(2018, Calendar.AUGUST, 9).getTime();
        archive = new Archive("", date);
        typedQuery = Mockito.mock(TypedQuery.class);
        archiveList = Arrays.asList(archive);
    }

    @Test
    void shouldCreate() {
        archiveDAO.create(archive);
        verify(entityManager, times(1)).persist(archive);
    }

    @Test
    void shouldFindByIdTest() {
        archiveDAO.findById(5L);
        verify(entityManager, times(1)).find(Archive.class, 5L, LockModeType.PESSIMISTIC_READ);
    }

    @Test
    void shouldRemove() {
        archiveDAO.remove(5L);
        verify(entityManager, times(1)).find(Archive.class, 5L, LockModeType.PESSIMISTIC_READ);
    }

    @Test
    void shouldFindMonthOldArchives() {
        when(entityManager.createNativeQuery(anyString(), eq(Archive.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(archive));

        List<Archive> list = archiveDAO.findMonthOldArchives();

        assertEquals(archiveList, list);
    }
}