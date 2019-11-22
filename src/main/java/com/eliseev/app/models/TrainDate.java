package com.eliseev.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="train_date")
public class TrainDate extends AbstractEntity{


    @ManyToOne
    @JoinColumn(name="train_id")
    private Train train;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "trainDate", cascade = CascadeType.ALL)
    private List<StationStopTime> stationStopTimes = new ArrayList<>();


    public TrainDate() {
    }

    public TrainDate(Train train) {
        this.train = train;
    }

    public TrainDate(Long id, Train train) {
        this.train = train;
        super.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<StationStopTime> getStationStopTimes() {
        return stationStopTimes;
    }

    public void setStationStopTimes(List<StationStopTime> stationStopTimes) {
        this.stationStopTimes = stationStopTimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TrainDate)) return false;

        TrainDate trainDate = (TrainDate) o;

        return new EqualsBuilder()
                .append(train, trainDate.train)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(train)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "TrainDate{" +
                "train=" + train +
                ", id=" + id +
                '}';
    }
}
