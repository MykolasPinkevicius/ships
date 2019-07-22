package controller;

import dao.LogbookDAO;
import model.Logbook;

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
    private LogbookDAO logbookDAO;

    @POST
    public Response save(@Valid Logbook logbook) throws IOException {
        logbookDAO.create(logbook);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response updateLogbook(@PathParam("id") Long id, @Valid Logbook logbook) {
        logbookDAO.update(id, logbook);
        return Response.ok().build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/allLogbooks")
    public Response saveAll(@Valid List<Logbook> logbookList) {
        logbookDAO.createAll(logbookList);
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
    @Path("/search/byDeparturePort/{departurePort}")
    public List<Logbook> findByDeparturePort(@PathParam("departurePort") String departurePort) {
        return logbookDAO.findByDeparturePort(departurePort);
    }

    @GET
    @Path("/search/byDepartureDate/{departureDate}")
    public List<Logbook> findByDepartureDate(@PathParam("departureDate") String departureDate) {
        return logbookDAO.findByDepartureDate(departureDate);
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

    @GET
    @Path("/search/byArrivalPort/{arrivalDate}")
    public List<Logbook> findByArrivalDate(@PathParam("arrivalDate") String arrivalDate) {
        return logbookDAO.findByArrivalDate(arrivalDate);
    }

    @GET
    @Path("/search/byEndOfFishingDate/{endOfFishingDate}")
    public List<Logbook> findByEndOfFishingDate(@PathParam("endOfFishingDate") String endOfFishingDate) {
        return logbookDAO.findByEndOfFishingDate(endOfFishingDate);
    }


    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") Long id) {
        logbookDAO.remove(id);
    }

}