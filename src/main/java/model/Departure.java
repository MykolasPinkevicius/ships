package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DateStringFormatter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@Entity
public class Departure {

    private static Logger logger = LogManager.getLogger(Departure.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    private Long id;
    @NotNull
    private String port;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    public Departure(String port, Date date) {
        this.port = port;
        this.date = date;
    }

    public Departure() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("port", this.port)
                .add("date", DateStringFormatter.dateToStringWithFormat(this.date))
                .build();
    }

    @Override
    public String toString() {
        ObjectMapper mapperObj = new ObjectMapper();
        String json = null;
        try {
            json = mapperObj.writeValueAsString(this);
        } catch (Exception e) {
            logger.error("Error during toString Departure {}", e);
        }
        return json;
    }
}
