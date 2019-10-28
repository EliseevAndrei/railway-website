package com.eliseev.app.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class Train extends AbstractEntity {


    private String name;
    private int countCoupe;
    private int countLying;
    private int countCommon;

    public Train() {
    }

    public Train(long id, String name, int countKupe, int countPlaz, int countGen) {
        super.id = id;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public int getCountCoupe() {
        return countCoupe;
    }

    public int getCountLying() {
        return countLying;
    }

    public int getCountCommon() {
        return countCommon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountCoupe(int countCoupe) {
        this.countCoupe = countCoupe;
    }

    public void setCountLying(int countLying) {
        this.countLying = countLying;
    }

    public void setCountCommon(int countCommon) {
        this.countCommon = countCommon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Train)) return false;

        Train train = (Train) o;

        return new EqualsBuilder()
                .append(countCoupe, train.countCoupe)
                .append(countLying, train.countLying)
                .append(countCommon, train.countCommon)
                .append(name, train.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(countCoupe)
                .append(countLying)
                .append(countCommon)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Train{" +
                "name='" + name + '\'' +
                ", countCoupe=" + countCoupe +
                ", countLying=" + countLying +
                ", countCommon=" + countCommon +
                ", id=" + id +
                '}';
    }
}
