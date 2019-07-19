package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
//@XmlAccessorType(XmlAccessType.FIELD)
public class Catch {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @XmlTransient
    private Long id;
    @NotNull
    private String species;
    @NotNull
    private double weight;

    public Catch(String species, double weight) {
        this.species = species;
        this.weight = weight;
    }

    public Catch(Long id, String species, double weight) {
        this.id = id;
        this.species = species;
        this.weight = weight;
    }

    public Catch() {}

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
       .add("species", this.species)
       .add("weight", this.weight)
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
