package model;

import javax.xml.bind.annotation.XmlRootElement;

public class Logbook {

    private Departure departure;
    private Catch aCatch;
    private Arrival arrival;
    private EndOfFishing endOfFishing;

    public Logbook(Departure departure, Catch aCatch, Arrival arrival, EndOfFishing endOfFishing) {
        this.departure = departure;
        this.aCatch = aCatch;
        this.arrival = arrival;
        this.endOfFishing = endOfFishing;
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



}