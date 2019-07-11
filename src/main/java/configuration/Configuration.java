package configuration;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;
    @NotNull
    @Column(unique = true)
    private String key;
    @NotNull
    private String value;

    public Configuration(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Configuration() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("key", this.key)
                .add("value", this.value)
                .build();
    }
}
