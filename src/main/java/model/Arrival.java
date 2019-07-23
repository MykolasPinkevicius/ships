package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import util.DateStringFormatter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Arrival {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @XmlTransient
    private Long id;
    @NotNull
    private String port;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    public Arrival(String port, Date date) {
        this.port = port;
        this.date = date;
    }

    public Arrival(Long id, String port, Date date) {
        this.id = id;
        this.port = port;
        this.date = date;
    }

    public Arrival() {
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
                .add("id", this.id)
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
            e.printStackTrace();
        }
        return json;
    }
}
