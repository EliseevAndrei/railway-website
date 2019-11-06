package com.eliseev.app.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Train extends AbstractEntity implements Serializable {

    @NotBlank(message = "Название поезда обязательно!")
    private String name;
    @Digits(integer = 4, fraction = 0, message = "Кол-во купе должно быть целым числом!")
    private int countCoupe;
    @Digits(integer = 4, fraction = 0, message = "Кол-во плацкарта должно быть целым числом!")
    private int countLying;
    @Digits(integer = 4, fraction = 0, message = "Кол-во общих должно быть целым числом!")
    private int countCommon;


    public Train() { }

    public Train(long id, String name, int countCoupe, int countLying, int countCommon) {
        super.id = id;
        this.name = name;
        this.countCoupe = countCoupe;
        this.countLying = countLying;
        this.countCommon = countCommon;
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
