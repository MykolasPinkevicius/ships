package configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConfigurationDAOTest {

    @InjectMocks
    ConfigurationDAO configurationDAO;

    @Mock
    EntityManager entityManager;

    Configuration configuration = new Configuration("inboxPath", "C:/Dev/wildfly-9.0.2.Final/bin/Savedfiles/");

    private TypedQuery<Configuration> mockedQuerry;

    @BeforeEach
    void setUp() {
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
        when(mockedQuerry.getResultList()).thenReturn(Arrays.asList(configuration));

        Configuration actualConfiguration = configurationDAO.findByKey("inboxPath");

        assertEquals(configuration, actualConfiguration);
    }
}