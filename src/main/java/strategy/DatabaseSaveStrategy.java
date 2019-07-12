package strategy;

import model.Logbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DatabaseSaveStrategy implements SavingStrategy {

    Logger logger = LoggerFactory.getLogger(DatabaseSaveStrategy.class);

    @PersistenceContext(name = "prod")
    private EntityManager em;

    public DatabaseSaveStrategy(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public void create(Logbook logbook) {
        try {
            em.persist(logbook);
            logger.info("Logger {} was saved to database", logger);
        } catch(Exception e){
            logger.info(e.getMessage());
        }
    }
}


