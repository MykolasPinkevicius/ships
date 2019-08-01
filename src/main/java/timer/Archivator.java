package timer;

import dao.ArchiveDAO;
import dao.LogbookDAO;
import model.Archive;
import model.Logbook;
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

    @Schedule(hour = "12/10")
    public void archiveYearOldLogbooks() {
        List<Logbook> logbookList;
        logbookList = logbookDAO.findOneYearOldLogbooks();

        for (Logbook logbook : logbookList) {
            String serializedLogbook = LogbookSerializer.serializeToString(logbook);
            Date date = new Date();
            Archive archive = new Archive(serializedLogbook, date);
            archiveDAO.create(archive);
            logbookDAO.remove(logbook.getId());
        }
    }

    @Schedule(hour = "12/10")
    public void archiveMonthOldArchives() {
        List<Archive> archiveList;
        archiveList = archiveDAO.findMonthOldArchives();

        for (Archive archive : archiveList) {
            archiveDAO.remove(archive.getId());
        }
    }
}
