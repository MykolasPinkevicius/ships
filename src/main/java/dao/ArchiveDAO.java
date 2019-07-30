package dao;

import model.Archive;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

@Stateless
public class ArchiveDAO {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Archive archive) {
        entityManager.persist(archive);
    }

    public Archive findById(Long id) {
        return entityManager.find(Archive.class, id, LockModeType.PESSIMISTIC_READ);
    }

    public void delete(Long id) {
        Archive archive = entityManager.find(Archive.class, id, LockModeType.PESSIMISTIC_READ);
        entityManager.remove(archive);
    }

}
