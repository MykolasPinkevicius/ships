package strategy;


import dao.ConfigurationDAO;
import dao.LogbookDAO;
import model.Logbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Stateless
public class FileSaveStrategy implements SavingStrategy {

    private String fileSavingPath;

    Logger logger = LoggerFactory.getLogger(LogbookDAO.class);

    public FileSaveStrategy(String fileSavingPath) {
        this.fileSavingPath = fileSavingPath;
    }

    public FileSaveStrategy() {
    }



    @Inject
    ConfigurationDAO configurationDAO;

    public void create(Logbook logbook) throws IOException {
        LocalDate ld = LocalDateTime.now().toLocalDate();
        UUID random = UUID.randomUUID();
        String filePath = getFilePathString(ld, random);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(logbook.toJson().toString());
            logger.info("logbook {} was created on file", logbook);
            fileWriter.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            fileWriter.close();
        }
    }

    private String getFilePathString(LocalDate ld, UUID random) {
        return fileSavingPath + ld + random.toString() + ".json";
    }
}
