package controller;

import dao.EndOfFishingDAO;
import model.EndOfFishing;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
public class EndOfFishingController {

    @Inject
    private EndOfFishingDAO endOfFishingDAO;

    @POST
    public Response save(@Valid EndOfFishing endOfFishing) {
        endOfFishingDAO.create(endOfFishing);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Long id) {
        EndOfFishing endOfFishing = endOfFishingDAO.findById(id);
        return endOfFishing.toJson();
    }

    @GET
    public List<EndOfFishing> findAll() {
        return endOfFishingDAO.findAll();
    }

    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") Long id) {
        endOfFishingDAO.remove(id);
    }
}
