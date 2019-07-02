package model;

public class Logbook {

    private Departure departure;
    private Catch aCatch;

    public Logbook(Departure departure, Catch aCatch) {
        this.departure = departure;
        this.aCatch = aCatch;
    }

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
