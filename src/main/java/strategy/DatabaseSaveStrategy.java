package strategy;

import model.Logbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DatabaseSaveStrategy implements SavingStrategy {


    private Logger logger = LogManager.getLogger(DatabaseSaveStrategy.class);

    @PersistenceContext(name = "prod")
    private EntityManager em;


    public DatabaseSaveStrategy(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public void create(Logbook logbook) {
        try {
            em.merge(logbook);
            logger.info("Logbook {} was saved to database", logbook);
        } catch(Exception e){
            logger.info(e.getMessage());
        }
    }
}


