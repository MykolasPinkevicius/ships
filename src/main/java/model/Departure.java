package model;

import java.time.LocalDate;

public class Departure {
    private String port;
    private LocalDate date;

    public Departure(String port, LocalDate date) {
        this.port = port;
        this.date = date;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
