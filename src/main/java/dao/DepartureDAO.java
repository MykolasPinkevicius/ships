package dao;

import model.Departure;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DepartureDAO {
    @PersistenceContext(name = "prod")
    //TODO padaryti dar vieną lėjerį Entity manageriui.
    EntityManager em;

    public List<Departure> findAll() {
        return em.createQuery("select d from Departure d").getResultList();
    }

    public void create(Departure departure) {
        em.persist(departure);
    }

    public Departure findById(Long id) {
        return em.find(Departure.class, id);
    }

    public void remove(Long id) {
        em.remove(id);
    }
}
