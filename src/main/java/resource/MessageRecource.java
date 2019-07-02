package resource;

import model.Arrival;
import model.Message;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("messages")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class MessageRecource {
    @Inject
    Message message;

    @POST
    public Response save(@Valid Arrival arrival) {
        this.message.create(arrival);
        return Response.ok().build();
    }

    @GET
    public List<Arrival> findAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<Arrival> all = message.findAll();
        return all;
    }


}
