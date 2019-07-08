package dao;


import model.EndOfFishing;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EndOfFishingDAO {
    @PersistenceContext(name = "prod")
    //TODO padaryti dar vieną lėjerį Entity manageriui.
    EntityManager em;

    public List<EndOfFishing> findAll() {
        return em.createQuery("select e from EndOfFishing e").getResultList();
    }

    public void create(EndOfFishing endOfFishing) {
        em.persist(endOfFishing);
    }

    public EndOfFishing findById(Long id) {
        return em.find(EndOfFishing.class, id);
    }

    public void remove(Long id) {
        em.remove(id);
    }
}
