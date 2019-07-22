package dao;


import model.EndOfFishing;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Stateless
public class EndOfFishingDAO {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
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
