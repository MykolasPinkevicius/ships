package timer;

import dao.ArchiveDAO;
import dao.LogbookDAO;
import model.Logbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.LogbookSerializer;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class Archivator {
    @Inject
    private LogbookDAO logbookDAO;
    private ArchiveDAO archiveDAO;

    private Logger logger = LogManager.getLogger(Archivator.class);

//    TODO change to realistic schedule each day or week.
    @Schedule(second = "*/10", minute = "*", hour = "*")
    public void archiveYearOldArchives() {
        List<Logbook> logbookList;
        logbookList = logbookDAO.findOneYearOldLogbooks();
        logger.info(logbookList.size());
        for (Logbook logbook: logbookList) {
            String serializedLogbook = LogbookSerializer.serializeToString(logbook);

        }
    }
}