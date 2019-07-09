package dao;

import model.Logbook;
import strategy.DatabaseSaveStrategy;
import strategy.FileSaveStrategy;
import strategy.SavingStrategy;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Stateless
public class LogbookDAO {
    @PersistenceContext(name = "prod")
    //TODO padaryti dar vieną lėjerį Entity manageriui.
    EntityManager em;

    public List<Logbook> findAll() {
        return em.createQuery("select l from Logbook l").getResultList();
    }

    public List<Logbook> findByDeparturePort(String departurePort) {
        return em.createNativeQuery("SELECT U.ID,COMMUNICATIONTYPE,ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join DEPARTURE D on U.DEPARTURE_ID = D.ID where D.PORT = ?1", Logbook.class)
                .setParameter(1, departurePort)
                .getResultList();
    }

    public List<Logbook> findByCatchSpecies(String catchSpecies) {
        return em.createNativeQuery("SELECT U.ID, COMMUNICATIONTYPE, ACATCH_ID, ARRIVAL_ID, DEPARTURE_ID, ENDOFFISHING_ID from LOGBOOK U join CATCH C on U.ACATCH_ID = C.ID where C.SPECIES = ?1", Logbook.class)
                .setParameter(1, catchSpecies)
                .getResultList();
    }

    public Response create(Logbook logbook) throws IOException {

        SavingStrategy savingStrategy;

        if (logbook.getCommunicationType().equals("offline")) {
            savingStrategy = new FileSaveStrategy();
        } else if(logbook.getCommunicationType().equals("online")) {
            savingStrategy = new DatabaseSaveStrategy(em);
        } else {
            return Response.serverError().build();
        }

        return savingStrategy.create(logbook);
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
