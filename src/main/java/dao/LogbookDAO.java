package dao;

import model.Logbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import strategy.DatabaseSaveStrategy;
import strategy.FileSaveStrategy;
import strategy.SavingStrategy;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;

@Stateless
public class LogbookDAO {

    @Inject
    private ConfigurationDAO configurationDAO;

    @PersistenceContext EntityManager em;

    private Logger logger = LogManager.getLogger(Logbook.class);

    public LogbookDAO(EntityManager entityManager) {
        this.em = entityManager;
    }

    public LogbookDAO() {
    }

    public List<Logbook> findAll() {
        return em.createQuery("select l from Logbook l").setLockMode(LockModeType.PESSIMISTIC_READ).getResultList();
    }

    public List<Logbook> findByDeparturePort(String departurePort) {
        return em.createNativeQuery("SELECT U.ID,COMMUNICATIONTYPE,ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join DEPARTURE D on U.DEPARTURE_ID = D.ID where D.PORT = ?1", Logbook.class)
                .setParameter(1, departurePort)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultList();
    }

    public List<Logbook> findByDepartureDate(String departureDate) {
        return em.createNativeQuery("SELECT U.ID,COMMUNICATIONTYPE,ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join DEPARTURE D on U.DEPARTURE_ID = D.ID where D.DATE = ?1", Logbook.class)
                .setParameter(1, departureDate)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultList();
    }

    public List<Logbook> findByCatchSpecies(String catchSpecies) {
        return em.createNativeQuery("SELECT U.ID, COMMUNICATIONTYPE, ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join CATCH C on U.ACATCH_ID = C.ID where C.SPECIES = ?1", Logbook.class)
                .setParameter(1, catchSpecies)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultList();
    }

    public List<Logbook> findByArrivalPort(String arrivalPort) {
        return em.createNativeQuery("SELECT U.ID, COMMUNICATIONTYPE, ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join ARRIVAL A on U.ARRIVAL_ID = A.ID where A.PORT = ?1", Logbook.class)
                .setParameter(1, arrivalPort)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultList();
    }

    public List<Logbook> findByArrivalDate(String arrivalDate) {
        return em.createNativeQuery("SELECT U.ID, COMMUNICATIONTYPE, ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join ARRIVAL A on U.ARRIVAL_ID = A.ID where A.DATE = ?1", Logbook.class)
                .setParameter(1, arrivalDate)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultList();
    }

    public List<Logbook> findByEndOfFishingDate(String endOfFishingDate) {
        return em.createNativeQuery("SELECT U.ID, COMMUNICATIONTYPE, ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join ENDOFFISHING E on U.ENDOFFISHING_ID = E.ID where E.DATE = ?1", Logbook.class)
                .setParameter(1, endOfFishingDate)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultList();
    }

    public void create(Logbook logbook) throws IOException {
        getStrategy(logbook).create(logbook);
    }

    public void createAll(List<Logbook> logbookList) {
        for (Logbook logbook : logbookList) {
            try {
                getStrategy(logbook).create(logbook);
            } catch (IOException e) {
                logger.error("Saving logbooks failed on service layer {}", e);
            }
        }
    }

    private SavingStrategy getStrategy(Logbook logbook) {
        if (logbook.getCommunicationType().equals("offline")) {
            String filePath = configurationDAO.findByKey("inboxPath").getValue();
            return new FileSaveStrategy(filePath);
        }
        return new DatabaseSaveStrategy(em);
    }

    public Logbook findById(Long id) {
        return em.find(Logbook.class, id, LockModeType.PESSIMISTIC_READ);

    }

    public void remove(Long id) {
        em.remove(id);
    }

    public void update(Long id, Logbook logbook) {
        Logbook updatedLogbook = em.find(Logbook.class, id, LockModeType.PESSIMISTIC_READ);
        updatedLogbook = Logbook.updateLogbook(updatedLogbook, logbook);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        em.flush();
        em.merge(updatedLogbook);
    }

}
