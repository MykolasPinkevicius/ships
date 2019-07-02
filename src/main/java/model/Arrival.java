package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@Entity
@Table(name = "arrivals")
@XmlAccessorType(XmlAccessType.FIELD)
public class Arrival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;
    @NotNull
    private String Port;
    @NotNull
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
