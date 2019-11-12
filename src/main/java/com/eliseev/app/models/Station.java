package com.eliseev.app.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Station extends AbstractEntity {

    @NotBlank(message = "Название станции обязательно")
    private String name;

    public Station() {}

    public Station(long id, String name) {
        super.id=id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        return new EqualsBuilder()
                .append(name, station.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
