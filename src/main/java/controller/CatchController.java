package controller;

import model.Catch;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CatchController {
    @PersistenceContext(name = "prod")
    EntityManager em;

    public List<Catch> findAll() {
        return em.createQuery("select c from Catch c").getResultList();
    }
    public void create(Catch aCatch) {
        em.persist(aCatch);
    }

    public Catch findById(Long id) {
        return em.find(Catch.class, id);
    }

    public void remove(Long id) {
        em.remove(id);
    }
}
