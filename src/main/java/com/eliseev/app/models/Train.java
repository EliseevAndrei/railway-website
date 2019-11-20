package com.eliseev.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Train extends AbstractEntity implements Serializable {

    @NotBlank(message = "{train.name.notBlank}")
    @Size(min=1, max = 5, message = "{train.name.size}")
    private String name;
    @Digits(integer = 4, fraction = 0, message = "{train.countCoupe.digits}")
    @Column(name="coupe_places_amount")
    private int countCoupe;
    @Digits(integer = 4, fraction = 0, message = "{train.countLying.digits}")
    @Column(name="lying_places_amount")
    private int countLying;
    @Digits(integer = 4, fraction = 0, message = "{train.countCommon.digits}")
    @Column(name="common_places_amount")
    private int countCommon;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL)
    private List<TrainStation> trainStationList = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<TrainDate> trainDates = new ArrayList<>();


    public Train() { }

    public Train(long id, String name, int countCoupe, int countLying, int countCommon) {
        super.id = id;
        this.name = name;
        this.countCoupe = countCoupe;
        this.countLying = countLying;
        this.countCommon = countCommon;
    }

    public Train(String name, int countCoupe, int countLying, int countCommon) {
        this.name = name;
        this.countCoupe = countCoupe;
        this.countLying = countLying;
        this.countCommon = countCommon;
    }

    public List<TrainDate> getTrainDates() {
        return trainDates;
    }

    public void setTrainDates(List<TrainDate> trainDates) {
        this.trainDates = trainDates;
    }

    public List<TrainStation> getTrainStationList() {
        return trainStationList;
    }

    public void setTrainStationList(List<TrainStation> trainStationList) {
        this.trainStationList = trainStationList;
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
