package dao;

import model.Arrival;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class ArrivalDAO {
    @PersistenceContext(name = "prod")
    EntityManager em;

    public List<Arrival> findAll() {
        return em.createQuery("select a from Arrival a").getResultList();
    }

    public Response create(Arrival arrival) {
        try {
            em.persist(arrival);
        } catch(Exception e) {
            e.printStackTrace();
            Response.status(500).build();
        }
        return Response.status(Response.Status.CREATED).entity("Arrival saved in database").build();
    }

    public Arrival findById(Long id) {
        return em.find(Arrival.class, id);
    }

    public void remove(Long id) {
        em.remove(id);
    }
}
