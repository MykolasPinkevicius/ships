package dao;

import model.Catch;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Stateless
public class CatchDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public List<Catch> findAll() {
        return em.createQuery("select c from Catch c")
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultList();
    }

    public void create(Catch aCatch) {
        em.persist(aCatch);
    }

    public Catch findById(Long id) {
        return em.find(Catch.class, id, LockModeType.PESSIMISTIC_READ);
    }

    public void remove(Long id) {
        em.remove(id);
    }
}
