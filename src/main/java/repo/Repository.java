package repo;

import model.Catch;
import model.Departure;
import model.Logbook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Repository {

    List<Logbook> logbookList;

    public Repository() {
        logbookList = new ArrayList<>();

        Logbook logbook1 = new Logbook(new Departure("Santamania", new Date()), new Catch("Bass", 56.5));
    }

    public List<Logbook> getLogbooks() {
        return logbookList;
    }


}
