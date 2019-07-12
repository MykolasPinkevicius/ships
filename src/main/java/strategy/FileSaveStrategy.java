package strategy;


import configuration.ConfigurationDAO;
import model.Logbook;

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
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }
    }

    private String getFilePathString(LocalDate ld, UUID random) {
        return fileSavingPath + ld + random.toString() + ".json";
    }
}
