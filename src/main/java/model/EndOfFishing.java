package model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("date", this.date.toString())
                .build();
    }
}