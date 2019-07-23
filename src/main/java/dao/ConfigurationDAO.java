package dao;


import configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ConfigurationDAO {

    @PersistenceContext(name = "prod")
    private EntityManager em;

    private static final Logger logger = LogManager.getLogger(ConfigurationDAO.class);

    public ConfigurationDAO(EntityManager entityManager) { this.em = entityManager; }

    public ConfigurationDAO() {
    }

    public void create(Configuration configuration) {
            em.persist(configuration);
            logger.info("Configuration {} was saved to database", configuration);
    }

    public Configuration findByKey(String key) {
        List<Configuration> con = em.createNativeQuery("SELECT c.ID,c.KEY, c.VALUE from CONFIGURATION c where c.key = ?1", Configuration.class)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .setParameter(1, key)
                .getResultList();
        return con.get(0);
    }
}
