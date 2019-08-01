package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DateStringFormatter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Archive {

    private static Logger logger = LogManager.getLogger(Archive.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Lob
    private String deserializedLogbook;
    @NotNull
    private Date dateDeleted;

    public Archive(String deserializedLogbook, Date dateDeleted) {
        this.deserializedLogbook = deserializedLogbook;
        this.dateDeleted = dateDeleted;
    }

    public Archive() {}

    public String getDeserializedLogbook() {
        return deserializedLogbook;
    }

    public void setDeserializedLogbook(String deserializedLogbook) {
        this.deserializedLogbook = deserializedLogbook;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date deletedDate) {
        this.dateDeleted = deletedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("deserializedLogbook", this.deserializedLogbook)
                .add("dateDeleted", DateStringFormatter.dateToStringWithFormat(this.dateDeleted))
                .build();
    }

    @Override
    public String toString() {
        ObjectMapper mapperObj = new ObjectMapper();
        String json = null;
        try {
            json = mapperObj.writeValueAsString(this);
        } catch (Exception e) {
            logger.error("Error during toString Archieve {}", e);
        }
        return json;
    }
}
