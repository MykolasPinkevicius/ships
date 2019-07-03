package controller;

import model.Logbook;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class LogbookController {
    @PersistenceContext(name = "prod")
    EntityManager em;

    public List<Logbook> findAll() {
        return em.createQuery("select l from Logbook l").getResultList();
    }

    public void create(Logbook logbook) {
        em.persist(logbook.getDeparture());
        em.persist(logbook.getaCatch());
        em.persist(logbook.getArrival());
        em.persist(logbook.getEndOfFishing());
        em.persist(logbook);
    }

    public Logbook findById(Long id) {
        return em.find(Logbook.class, id);
    }

    public void remove(Long id) {
        em.remove(id);
    }

    public void update(Long id, Logbook logbook) {
        Logbook updatedLogbook = em.find(Logbook.class, id);
        updatedLogbook.setaCatch(logbook.getaCatch());
        updatedLogbook.setArrival(logbook.getArrival());
        updatedLogbook.setDeparture(logbook.getDeparture());
        updatedLogbook.setEndOfFishing(logbook.getEndOfFishing());
        em.merge(updatedLogbook);
    }
}
