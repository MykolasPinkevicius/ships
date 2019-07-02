package resource;

import model.Catch;
import model.Departure;
import model.Logbook;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@Path("logbooks")
public class LogbookResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Logbook getLogbook() {
        Logbook logbook1 = new Logbook(new Departure("Santamania", LocalDate.now()), new Catch("Bass", 56.5));
        return logbook1;
    }
}
