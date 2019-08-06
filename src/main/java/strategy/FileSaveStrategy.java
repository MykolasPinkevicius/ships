package strategy;


import dao.ConfigurationDAO;
import dao.LogbookDAO;
import model.Logbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Stateless
public class FileSaveStrategy implements SavingStrategy {

    @Inject
    ConfigurationDAO configurationDAO;

    private Logger logger = LogManager.getLogger(LogbookDAO.class);

    private String fileSavingPath;

    public FileSaveStrategy(String fileSavingPath) {
        this.fileSavingPath = fileSavingPath;
    }

    public FileSaveStrategy() {
    }

    public void create(Logbook logbook) throws IOException {
        LocalDate ld = LocalDateTime.now().toLocalDate();
        UUID random = UUID.randomUUID();
        String filePath = getFilePathString(ld, random);
        FileWriter fileWriter = new FileWriter(filePath);
        try {
            fileWriter.write(logbook.toJson().toString());
            logger.info("logbook {} was created on file", logbook);
            fileWriter.flush();
        } catch (IOException e) {
            logger.error("Error creating file to File system {}", e.getMessage());
        } finally {
            fileWriter.close();
        }
    }

    private String getFilePathString(LocalDate ld, UUID random) {
        return fileSavingPath + ld + random.toString() + ".json";
    }
}
