package strategy;


import enums.PathEnums;
import model.Logbook;

import javax.ws.rs.core.Response;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class FileSaveStrategy implements SavingStrategy {

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
        return PathEnums.INBOXPATH.getPath() + ld + random.toString() + ".json";
    }
}
