package ru.ifellow.model.rickandmorty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterMod {
    private int id;
    private String name;
    private String status;
    private String species;
    private LocationMod location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public LocationMod getLocation() {
        return location;
    }

    public void setLocation(LocationMod location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterMod character)) return false;
        return getId() == character.getId() && Objects.equals(getName(), character.getName()) && Objects.equals(getStatus(), character.getStatus()) && Objects.equals(getSpecies(), character.getSpecies()) && Objects.equals(getLocation(), character.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStatus(), getSpecies(), getLocation());
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", species='" + species + '\'' +
                ", location=" + location +
                '}';
    }
}
