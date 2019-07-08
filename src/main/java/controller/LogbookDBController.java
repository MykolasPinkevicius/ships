package controller;

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
public class LogbookDBController {
    @PersistenceContext(name = "prod")
    EntityManager em;

    private SavingStrategy savingStrategy;

    public List<Logbook> findAll() {
        return em.createQuery("select l from Logbook l").getResultList();
    }

    public List<Logbook> findBySearch(String search) {
        return em.createNativeQuery("SELECT * FROM LOGBOOK WHERE LOGBOOK.COMMUNICATIONTYPE = :search").getResultList();
    }

    public Response create(Logbook logbook) throws IOException {
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
