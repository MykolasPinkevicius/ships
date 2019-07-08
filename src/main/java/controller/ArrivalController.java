package controller;

import dao.ArrivalDAO;
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
public class ArrivalController {
    @Inject
    ArrivalDAO arrivalDAO;

    @POST
    public Response save(@Valid Arrival arrival) {
        arrivalDAO.create(arrival);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Long id) {
        Arrival arrival = arrivalDAO.findById(id);
        return arrival.toJson();
    }

    @GET
    public List<Arrival> findAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<Arrival> all = arrivalDAO.findAll();
        return all;
    }

    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") Long id) {
        arrivalDAO.remove(id);
    }


}
