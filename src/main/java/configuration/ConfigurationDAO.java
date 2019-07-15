package configuration;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ConfigurationDAO {

    @PersistenceContext
    private EntityManager em;

    public ConfigurationDAO(EntityManager entityManager) { this.em = entityManager; }

    public ConfigurationDAO() {
    }

    public void create(Configuration configuration) {
            em.persist(configuration);
    }

    public Configuration findByKey(String key) {
        List<Configuration> con = em.createNativeQuery("SELECT c.ID,c.KEY, c.VALUE from CONFIGURATION c where c.key = ?1", Configuration.class)
                .setParameter(1, key)
                .getResultList();
        return con.get(0);
    }
}
