package controller;

import dao.LogbookDAO;
import model.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Stateless
@Path("logbooks")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class LogbookController {
    @Inject
    LogbookDAO logbookDAO;

    @POST
    public Response save(@Valid Logbook logbook) throws IOException {
        logbookDAO.create(logbook);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Long id) {
        Logbook logbook = logbookDAO.findById(id);
        return logbook.toJson();
    }

    @GET
    public List<Logbook> findAll() {
        return logbookDAO.findAll();
    }

    @GET
    @Path("/search/byDeparture/{departurePort}")
    public List<Logbook> findByDeparturePort(@PathParam("departurePort") String departurePort) {
        return logbookDAO.findByDeparturePort(departurePort);
    }

    @GET
    @Path("/search/bySpecies/{catchSpecies}")
    public List<Logbook> findByCatchSpecies(@PathParam("catchSpecies") String catchSpecies) {
        return logbookDAO.findByCatchSpecies(catchSpecies);
    }

    @GET
    @Path("/search/byArrivalPort/{arrivalPort}")
    public List<Logbook> findByArrivalPort(@PathParam("arrivalPort") String arrivalPort) {
        return logbookDAO.findByArrivalPort(arrivalPort);
    }

    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") Long id) {
        logbookDAO.remove(id);
    }

}