package model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Logbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;
    @NotNull
    @OneToOne
    private Departure departure;
    @NotNull
    @OneToOne
    private Catch aCatch;
    @NotNull
    @OneToOne
    private Arrival arrival;
    @NotNull
    @OneToOne
    private EndOfFishing endOfFishing;
    @NotNull
    @Transient
    private String communicationType;

    public Logbook(Departure departure, Catch aCatch, Arrival arrival, EndOfFishing endOfFishing, String communicationType) {
        this.departure = departure;
        this.aCatch = aCatch;
        this.arrival = arrival;
        this.endOfFishing = endOfFishing;
        this.communicationType = communicationType;
    }

    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    public Departure getDeparture() {{
    }
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

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public EndOfFishing getEndOfFishing() {
        return endOfFishing;
    }

    public void setEndOfFishing(EndOfFishing endOfFishing) {
        this.endOfFishing = endOfFishing;
    }

    public Logbook() {};

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("_logbook", Json.createObjectBuilder()
                        .add("departure", departure.toJson())
                        .add("catch", aCatch.toJson())
                        .add("arrival", arrival.toJson())
                        .add("endoffishing", endOfFishing.toJson())
                        .add("communicationtype", this.communicationType)
                        .build())
                .build();
    }


}