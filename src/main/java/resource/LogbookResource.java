package resource;

import model.Catch;
import model.Departure;
import model.Logbook;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("logbooks")
public class LogbookResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Logbook getLogbook() {
        Logbook logbook1 = new Logbook(new Departure("Santamania", new Date(2019)), new Catch("Bass", 56.5));
        return logbook1;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Logbook createLogBook(Logbook logbook) {
        return logbook;
    }

}
