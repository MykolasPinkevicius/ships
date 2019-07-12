package model;

import util.DateStringFormatter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class EndOfFishing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;
    @NotNull

    @Temporal(TemporalType.DATE)
    private Date date;

    public EndOfFishing(Date date) {
        this.date = date;
    }

    public EndOfFishing() {}

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
                .add("date", DateStringFormatter.dateToStringWithFormat(this.date))
                .build();
    }

    @Override
    public String toString() {
        return "EndOfFishing{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
