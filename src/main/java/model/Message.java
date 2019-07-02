package model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class Message {
    @PersistenceContext(name = "prod")
    EntityManager em;

    public List<Arrival> findAll() {
        return em.createQuery("select a from Arrival a").getResultList();
    }

    public void create(Arrival arrival) {
        em.persist(arrival);
    }
}
