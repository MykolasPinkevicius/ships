package resource;

import model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("logbooks")
public class LogbookResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Logbook getLogbook() {
        Logbook logbook1 = new Logbook(new Departure("Santamania", new Date(2019)), new Catch("Bass", 56.5), new Arrival("Santamania", new Date(2019)), new EndOfFishing(new Date(2019)));
        return logbook1;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Logbook createLogBook(Logbook logbook) {
        return logbook;
    }

}