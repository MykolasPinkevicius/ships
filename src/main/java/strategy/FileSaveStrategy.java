package strategy;


import configuration.ConfigurationDAO;
import model.Logbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Stateless
public class FileSaveStrategy implements SavingStrategy {

    @Inject
    ConfigurationDAO configurationDAO;

    public Response create(Logbook logbook) throws IOException {
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
        return Response.ok("Logbook saved to file").build();
    }

    private String getFilePathString(LocalDate ld, UUID random) {
//        TODO fix configurationDao findbykey(String key) inboxPath
        return configurationDAO.findByKey("inboxPath").getValue() + ld + random.toString() + ".json";
    }
}
