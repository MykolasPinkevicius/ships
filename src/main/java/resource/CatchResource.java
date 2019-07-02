package resource;

import controller.ArrivalController;
import controller.CatchController;
import model.Catch;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

}
