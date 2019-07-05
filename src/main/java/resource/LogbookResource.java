package resource;

import camel.FileTransfer;
import controller.LogbookDBController;
import model.*;

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
public class LogbookResource {
    @Inject
    LogbookDBController logbookController;

    @POST
    public Response save(@Valid Logbook logbook) throws IOException {
        this.logbookController.create(logbook);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    public JsonObject findById(@PathParam("id") Long id) {
        Logbook logbook = logbookController.findById(id);
        return logbook.toJson();
    }

    @GET
    public List<Logbook> findAll() {
        FileTransfer.save();
        List<Logbook> all = logbookController.findAll();
        return all;
    }

    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") Long id) {
        logbookController.remove(id);
    }

}