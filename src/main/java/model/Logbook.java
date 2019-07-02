package model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;

@XmlRootElement
public class Logbook {

    private Departure departure;
    private Catch aCatch;

    public Logbook(Departure departure, Catch aCatch) {
        this.departure = departure;
        this.aCatch = aCatch;
    }
    public Logbook() {};

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Catch getaCatch() {
        return aCatch;
    }

    public void setaCatch(Catch aCatch) {
        this.aCatch = aCatch;
    }
}
