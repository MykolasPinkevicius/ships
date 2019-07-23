package dao;

import model.Departure;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Stateless
public class DepartureDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public List<Departure> findAll() {
        return em.createQuery("select d from Departure d")
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultList();
    }

    public void create(Departure departure) {
        em.persist(departure);
    }

    public Departure findById(Long id) {
        return em.find(Departure.class, id, LockModeType.PESSIMISTIC_READ);
    }

    public void remove(Long id) {
        em.remove(id);
    }
}
