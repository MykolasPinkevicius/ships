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
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;
    @NotNull
    private String port;
    @NotNull
//    @JsonFormat(pattern="yyyy-MM-dd")
//    @Column(columnDefinition = "DATE")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    @Temporal(TemporalType.DATE)
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
        return "Departure{" +
                "id=" + id +
                ", port='" + port + '\'' +
                ", date=" + date +
                '}';
    }
}
