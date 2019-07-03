package resource;

import controller.EndOfFishingController;
import model.EndOfFishing;

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
@Path("endoffishing")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class EndOfFishingResource {
    @Inject
    EndOfFishingController endOfFishingController;

    @POST
    public Response save(@Valid EndOfFishing endOfFishing) {
        this.endOfFishingController.create(endOfFishing);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Long id) {
        EndOfFishing endOfFishing = endOfFishingController.findById(id);
        return endOfFishing.toJson();
    }

    @GET
    public List<EndOfFishing> findAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<EndOfFishing> all = endOfFishingController.findAll();
        return all;
    }

    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") Long id) {
        endOfFishingController.remove(id);
    }
}
