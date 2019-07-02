package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Logbook {

    private Departure departure;
    private Catch aCatch;
    private Arrival arrival;
    private EndOfFishing endOfFishing;

    public Logbook() {};

    public Logbook(Departure departure, Catch aCatch, Arrival arrival, EndOfFishing endOfFishing) {
        this.departure = departure;
        this.aCatch = aCatch;
        this.arrival = arrival;
        this.endOfFishing = endOfFishing;
    }
}