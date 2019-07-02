package resource;

import controller.ArrivalController;
import model.Arrival;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.*;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("arrival")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ArrivalResource {
    @Inject
    ArrivalController arrivalController;

    @POST
    public Response save(@Valid Arrival arrival) {
        this.arrivalController.create(arrival);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Long id) {
        Arrival arrival = arrivalController.findById(id);
        return arrival.toJson();
    }

    @GET
    public List<Arrival> findAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<Arrival> all = arrivalController.findAll();
        return all;
    }

    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") Long id) {
        arrivalController.remove(id);
    }


}
