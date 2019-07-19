package configuration;


import dao.ConfigurationDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("configuration")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ConfigurationController {
    @Inject
    ConfigurationDAO configurationDAO;

    @POST
    public void save(@Valid Configuration configuration) {
        try {
            configurationDAO.create(configuration);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/search/byKey/{key}")
    public Configuration findByKey(@PathParam("key") String key) {
        return configurationDAO.findByKey(key);
    }

}
