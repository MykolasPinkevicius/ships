package dao;


import model.EndOfFishing;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class EndOfFishingDAO {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager em;

    public List<EndOfFishing> findAll() {
        return em.createQuery("select e from EndOfFishing e").getResultList();
    }

    public Response create(EndOfFishing endOfFishing) {
        try {
            em.persist(endOfFishing);
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
        return Response.status(Response.Status.CREATED).entity("EndOfFishing saved in database").build();
    }

    public EndOfFishing findById(Long id) {
        return em.find(EndOfFishing.class, id);
    }

    public void remove(Long id) {
        em.remove(id);
    }
}
