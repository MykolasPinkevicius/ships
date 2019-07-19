package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
//@XmlRootElement
public class Logbook {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @XmlTransient
    private Long id;
    @NotNull
    @OneToOne(cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private Departure departure;
    @NotNull
    @OneToOne(cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private Catch aCatch;
    @NotNull
    @OneToOne(cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private Arrival arrival;
    @NotNull
    @OneToOne(cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private EndOfFishing endOfFishing;
    @NotNull
    private String communicationType;

    public Logbook(Departure departure, Catch aCatch, Arrival arrival, EndOfFishing endOfFishing, String communicationType) {
        this.departure = departure;
        this.aCatch = aCatch;
        this.arrival = arrival;
        this.endOfFishing = endOfFishing;
        this.communicationType = communicationType;
    }

    public Logbook(Long id, Departure departure, Catch aCatch, Arrival arrival, EndOfFishing endOfFishing, String communicationType) {
        this.id = id;
        this.departure = departure;
        this.aCatch = aCatch;
        this.arrival = arrival;
        this.endOfFishing = endOfFishing;
        this.communicationType = communicationType;
    }

    public Logbook() {};

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                        .add("id", this.id)
                        .add("departure", departure.toJson())
                        .add("aCatch", aCatch.toJson())
                        .add("arrival", arrival.toJson())
                        .add("endOfFishing", endOfFishing.toJson())
                        .add("communicationType", this.communicationType)
                        .build();
    }

    @Override
    public String toString() {
        ObjectMapper mapperObj = new ObjectMapper();
        String json = null;
        try {
            json = mapperObj.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static class Builder {
        private String id;
        private Arrival arrival;
        private Departure departure;
        private EndOfFishing endOfFishing;
        private Catch aCatch;
        private String communicationType;

        public Builder withID(String id) {
            this.id = id;
            return this;
        }
        public Builder withArrival(Arrival arrival) {
            this.arrival = arrival;
            return this;
        }
        public Builder withDeparture(Departure departure) {
            this.departure = departure;
            return this;
        }
        public Builder withEndOfFishing(EndOfFishing endOfFishing) {
            this.endOfFishing = endOfFishing;
            return this;
        }
        public Builder withCatch(Catch aCatch) {
            this.aCatch = aCatch;
            return this;
        }
        public Builder withCommunicationType(String communicationType) {
            this.communicationType = communicationType;
            return this;
        }

        public Logbook build() {
            Logbook logbook = new Logbook();
            logbook.arrival = this.arrival;
            logbook.departure = this.departure;
            logbook.endOfFishing = this.endOfFishing;
            logbook.aCatch = this.aCatch;
            logbook.communicationType = this.communicationType;
            logbook.setId(Long.valueOf(this.id));
            return logbook;
        }
    }


}