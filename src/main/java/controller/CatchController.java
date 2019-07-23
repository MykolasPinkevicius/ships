package controller;

import dao.CatchDAO;
import model.Catch;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
public class CatchController {

    @Inject
    private CatchDAO catchDAO;

    @POST
    public Response save(@Valid Catch aCatch) {
        catchDAO.create(aCatch);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Long id) {
        Catch aCatch = catchDAO.findById(id);
        return aCatch.toJson();
    }

    @GET
    public List<Catch> findAll() {
        return catchDAO.findAll();
    }

    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") Long id) {
        catchDAO.remove(id);
    }

}
