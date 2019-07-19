package dao;

import model.Departure;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class DepartureDAO {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager em;

    public List<Departure> findAll() {
        return em.createQuery("select d from Departure d").getResultList();
    }

    public Response create(Departure departure) {
        try {
            em.persist(departure);
        } catch (Exception e) {
            e.printStackTrace();
            Response.status(500).build();
        }
        return Response.status(Response.Status.CREATED).entity("Departure saved in database").build();
    }

    public Departure findById(Long id) {
        return em.find(Departure.class, id);
    }

    public void remove(Long id) {
        em.remove(id);
    }
}
