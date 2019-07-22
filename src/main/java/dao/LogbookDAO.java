package dao;

import model.Logbook;
import strategy.DatabaseSaveStrategy;
import strategy.FileSaveStrategy;
import strategy.SavingStrategy;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Stateless
public class LogbookDAO {
    @Inject
    ConfigurationDAO configurationDAO;

    @PersistenceContext(name = "prod")
    private EntityManager em;

    public LogbookDAO(EntityManager entityManager) {
        this.em = entityManager;
    }

    public LogbookDAO() {
    }

    public List<Logbook> findAll() {
        return em.createQuery("select l from Logbook l").getResultList();
    }

    public List<Logbook> findByDeparturePort(String departurePort) {
        return em.createNativeQuery("SELECT U.ID,COMMUNICATIONTYPE,ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join DEPARTURE D on U.DEPARTURE_ID = D.ID where D.PORT = ?1", Logbook.class)
                .setParameter(1, departurePort)
                .getResultList();
    }

    public List<Logbook> findByDepartureDate(String departureDate) {
        return em.createNativeQuery("SELECT U.ID,COMMUNICATIONTYPE,ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join DEPARTURE D on U.DEPARTURE_ID = D.ID where D.DATE = ?1", Logbook.class)
                .setParameter(1, departureDate)
                .getResultList();
    }

    public List<Logbook> findByCatchSpecies(String catchSpecies) {
        return em.createNativeQuery("SELECT U.ID, COMMUNICATIONTYPE, ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join CATCH C on U.ACATCH_ID = C.ID where C.SPECIES = ?1", Logbook.class)
                .setParameter(1, catchSpecies)
                .getResultList();
    }

    public List<Logbook> findByArrivalPort(String arrivalPort) {
        return em.createNativeQuery("SELECT U.ID, COMMUNICATIONTYPE, ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join ARRIVAL A on U.ARRIVAL_ID = A.ID where A.PORT = ?1", Logbook.class)
                .setParameter(1,arrivalPort)
                .getResultList();
    }

    public List<Logbook> findByArrivalDate(String arrivalDate) {
        return em.createNativeQuery("SELECT U.ID, COMMUNICATIONTYPE, ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join ARRIVAL A on U.ARRIVAL_ID = A.ID where A.DATE = ?1", Logbook.class)
                .setParameter(1,arrivalDate)
                .getResultList();
    }

    public List<Logbook> findByEndOfFishingDate(String endOfFishingDate) {
        return em.createNativeQuery("SELECT U.ID, COMMUNICATIONTYPE, ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join ENDOFFISHING E on U.ENDOFFISHING_ID = E.ID where E.DATE = ?1", Logbook.class)
                .setParameter(1,endOfFishingDate)
                .getResultList();
    }

    public void create(Logbook logbook) throws IOException {
        getStrategy(logbook).create(logbook);
    }

    public void createAll(List<Logbook> logbookList) {
        for (Logbook logbook: logbookList) {
            try {
                getStrategy(logbook).create(logbook);
            } catch (IOException e) {
                e.printStackTrace();
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
        return em.find(Logbook.class, id);
    }

    public void remove(Long id) {
        em.remove(id);
    }

    @Transactional
    public void update(Long id, Logbook logbook) {
        Logbook updatedLogbook = em.find(Logbook.class, id);
        em.lock(updatedLogbook, LockModeType.PESSIMISTIC_WRITE);
        updatedLogbook = Logbook.updateLogbook(updatedLogbook, logbook);
        em.merge(updatedLogbook);
    }
}
