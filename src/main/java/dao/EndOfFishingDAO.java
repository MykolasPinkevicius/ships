package dao;


import model.EndOfFishing;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Stateless
public class EndOfFishingDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public List<EndOfFishing> findAll() {
        return em.createQuery("select e from EndOfFishing e").setLockMode(LockModeType.PESSIMISTIC_READ).getResultList();
    }

    public void create(EndOfFishing endOfFishing) {
            em.persist(endOfFishing);
    }

    public EndOfFishing findById(Long id) {
        return em.find(EndOfFishing.class, id, LockModeType.PESSIMISTIC_READ);
    }

    public void remove(Long id) {
        em.remove(id);
    }
}
