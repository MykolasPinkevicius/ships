package strategy;

import model.Logbook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

public class DatabaseSaveStrategy implements SavingStrategy {

    @PersistenceContext(name = "prod")
    private EntityManager em;

    public DatabaseSaveStrategy(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public Response create(Logbook logbook) {
        try {
            em.persist(logbook);
        } catch(Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
        return Response.status(Response.Status.CREATED).entity("Logbook saved in database").build();
    }
}


