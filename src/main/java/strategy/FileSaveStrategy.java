package strategy;


import model.Logbook;

import javax.ws.rs.core.Response;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FileSaveStrategy implements SavingStrategy {

    public Response create(Logbook logbook) throws IOException {
        LocalDate ld = LocalDateTime.now().toLocalDate();
        String filePath = "C:\\Dev\\wildfly-9.0.2.Final\\bin\\logbook\\" + ld + ".json";
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(logbook.toJson().toString());
        fileWriter.flush();
        fileWriter.close();
        return Response.ok("Logbook saved to file").build();
    }
}
