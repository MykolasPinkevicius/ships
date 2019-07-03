package strategy;

import model.Logbook;

import javax.ws.rs.core.Response;
import java.io.IOException;

public interface SavingStrategy {

        Response create(Logbook logbook) throws IOException;
}
