package strategy;

import model.Logbook;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;

public class DatabaseSaveStrategy implements SavingStrategy {
    private EntityManager em;

    public DatabaseSaveStrategy(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public Response create(Logbook logbook) {
        em.persist(logbook.getDeparture());
        em.persist(logbook.getArrival());
        em.persist(logbook.getaCatch());
        em.persist(logbook.getEndOfFishing());
        em.persist(logbook);
        return Response.ok("Logbook saved in database").build();
    }
}


