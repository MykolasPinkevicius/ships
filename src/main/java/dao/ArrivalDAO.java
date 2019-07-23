package dao;

import model.Arrival;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Stateless
public class ArrivalDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public List<Arrival> findAll() {
        return em.createQuery("select a from Arrival a").getResultList();
    }

    public void create(Arrival arrival) {
            em.persist(arrival);
    }

    public Arrival findById(Long id) {
        return em.find(Arrival.class, id);
    }

    public void remove(Long id) {
        em.remove(id);
    }
}
