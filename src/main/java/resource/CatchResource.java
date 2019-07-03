package resource;

import controller.CatchController;
import model.Catch;

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
@Path("catch")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CatchResource {
    @Inject
    CatchController catchController;

    @POST
    public Response save(@Valid Catch aCatch) {
        this.catchController.create(aCatch);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Long id) {
        Catch aCatch = catchController.findById(id);
        return aCatch.toJson();
    }
    @GET
    public List<Catch> findAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<Catch> all = catchController.findAll();
        return all;
    }

    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") Long id) {
        catchController.remove(id);
    }

}
