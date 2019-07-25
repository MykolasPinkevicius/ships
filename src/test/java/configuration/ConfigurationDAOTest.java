package configuration;

import dao.ConfigurationDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConfigurationDAOTest {

    @InjectMocks
    private ConfigurationDAO configurationDAO;

    @Mock
    private EntityManager entityManager;

    private Configuration configuration;

    private TypedQuery<Configuration> mockedQuerry;

    @BeforeEach
    void setUp() {
        configuration = new Configuration("inboxPath", "C:/Dev/SavedFiles/");
        MockitoAnnotations.initMocks(this);
        mockedQuerry = Mockito.mock(TypedQuery.class);

    }

    @Test
    void create() {
        configurationDAO.create(configuration);
        verify(entityManager, times(1)).persist(configuration);
    }

    @Test
    void findByKey() {
        when(entityManager.createNativeQuery(anyString(), eq(Configuration.class))).thenReturn(mockedQuerry);
        when(mockedQuerry.setParameter(anyInt(), anyString())).thenReturn(mockedQuerry);
        when(mockedQuerry.setLockMode(LockModeType.PESSIMISTIC_READ)).thenReturn(mockedQuerry);
        when(mockedQuerry.getResultList()).thenReturn(Arrays.asList(configuration));

        Configuration actualConfiguration = configurationDAO.findByKey("inboxPath");

        assertEquals(configuration, actualConfiguration);
    }
}