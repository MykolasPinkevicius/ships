package resource;

import controller.DepartureController;
import model.Arrival;
import model.Departure;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("departure")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class DepartureResource {
    @Inject
    DepartureController departureController;

    @POST
    public Response save(@Valid Departure departure) {
        this.departureController.create(departure);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Long id) {
        Departure departure = departureController.findById(id);
        return departure.toJson();
    }

    @GET
    public List<Departure> findAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<Departure> all = departureController.findAll();
        return all;
    }

    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") Long id) {
        departureController.remove(id);
    }
}
