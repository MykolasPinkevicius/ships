package timer;

import dao.ArchiveDAO;
import dao.LogbookDAO;
import model.Archive;
import model.Logbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.LogbookSerializer;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Stateless
public class Archivator {

    @Inject
    private LogbookDAO logbookDAO;
    @Inject
    private ArchiveDAO archiveDAO;

    private Logger logger = LogManager.getLogger(Archivator.class);

    @Schedule(second = "*", minute = "*", hour = "*/1")
    public void archiveYearOldArchives() {
        List<Logbook> logbookList;
        logbookList = logbookDAO.findOneYearOldLogbooks();
        logger.info(logbookList.size());
        for (Logbook logbook : logbookList) {
            String serializedLogbook = LogbookSerializer.serializeToString(logbook);
            Date date = new Date();
            Archive archive = new Archive(serializedLogbook, date);
            archiveDAO.create(archive);
            logbookDAO.remove(logbook.getId());
        }
    }
}
