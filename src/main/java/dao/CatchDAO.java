package dao;

import model.Catch;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class CatchDAO {
    @PersistenceContext(name = "prod")
    EntityManager em;

    public List<Catch> findAll() {
        return em.createQuery("select c from Catch c").getResultList();
    }
    public Response create(Catch aCatch) {
        try {
            em.persist(aCatch);
        } catch(Exception e) {
            e.printStackTrace();
            Response.status(500).build();
        }
        return Response.status(Response.Status.CREATED).entity("Catch saved in database").build();
    }

    public Catch findById(Long id) {
        return em.find(Catch.class, id);
    }

    public void remove(Long id) {
        em.remove(id);
    }
}
