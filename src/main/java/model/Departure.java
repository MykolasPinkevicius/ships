package model;

import java.time.LocalDate;
import java.util.Date;

public class Departure {
    private String port;
    private Date date;

    public Departure(String port, Date date) {
        this.port = port;
        this.date = date;
    }
    public Departure() {}

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
