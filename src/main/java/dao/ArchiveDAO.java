package dao;

import model.Archive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ArchiveDAO {

    @PersistenceContext
    EntityManager entityManager;

    private Logger logger = LogManager.getLogger(ArchiveDAO.class);

    public void create(Archive archive) {
        entityManager.persist(archive);
    }
}
