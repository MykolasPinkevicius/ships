package model;

import java.util.Date;

public class EndOfFishing {
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EndOfFishing(Date date) {
        this.date = date;
    }

    public EndOfFishing() {}
}
