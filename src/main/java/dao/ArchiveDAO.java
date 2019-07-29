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

//    public ArchiveDAO(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

//    public ArchiveDAO() {}

    public void create(Archive archive) {
        entityManager.persist(archive);
    }

    public Archive findById(Long id) {
        return entityManager.find(Archive.class, id, LockModeType.PESSIMISTIC_READ);
    }
}
