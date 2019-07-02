package model;

        import java.util.Date;

public class Arrival {
    private String Port;
    private Date date;

    public String getPort() {
        return Port;
    }

    public void setPort(String port) {
        Port = port;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Arrival(String port, Date date) {
        Port = port;
        this.date = date;
    }

    public Arrival() {}
}
