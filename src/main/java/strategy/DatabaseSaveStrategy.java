package strategy;

import model.Logbook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DatabaseSaveStrategy implements SavingStrategy {

    @PersistenceContext(name = "prod")
    private EntityManager em;

    public DatabaseSaveStrategy(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public void create(Logbook logbook) {
        try {
            em.persist(logbook);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}


