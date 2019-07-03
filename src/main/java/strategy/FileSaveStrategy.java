package strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Logbook;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;

public class FileSaveStrategy implements SavingStrategy {

    public Response create(Logbook logbook) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("logbook.log"), logbook);
        return Response.ok("Logbook saved to file").build();
    }
}
