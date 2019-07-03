package strategy;

import com.google.gson.Gson;
import model.Logbook;

import javax.ws.rs.core.Response;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaveStrategy implements SavingStrategy {

    public Response create(Logbook logbook) throws IOException {
        Gson gson = new Gson();
        gson.toJson(logbook, new FileWriter("logbook.log"));
        return Response.ok("Logbook saved to file").build();
    }
}
