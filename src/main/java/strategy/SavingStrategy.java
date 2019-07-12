package strategy;

import model.Logbook;

import java.io.IOException;

public interface SavingStrategy {

    void create(Logbook logbook) throws IOException;
}
