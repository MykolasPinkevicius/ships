package configuration;


import dao.ConfigurationDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static Logger logger = LogManager.getLogger(ConfigurationController.class);

    @Inject
    private ConfigurationDAO configurationDAO;

    @POST
    public void save(@Valid Configuration configuration) {
        try {
            configurationDAO.create(configuration);
        } catch (Exception e) {
            logger.error("Configuration saving failed {} ", e.getMessage());
        }
    }

    @GET
    @Path("/search/byKey/{key}")
    public Configuration findByKey(@PathParam("key") String key) {
        return configurationDAO.findByKey(key);
    }

}
