package com.eliseev.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TrainStation extends AbstractEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name="train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name="station_id")
    private Station station;

    @Column(name="station_serial_number")
    private int stationSerialNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "trainStation", cascade = CascadeType.ALL)
    private List<StationStopTime> stationStopTimes = new ArrayList<>();



    public TrainStation() {
    }

    public TrainStation(Train train, Station station, int stationSerialNumber) {
        this.train = train;
        this.station = station;
        this.stationSerialNumber = stationSerialNumber;
    }

    public TrainStation(long id, Train train, Station station, int stationSerialNumber) {
        this.id = id;
        this.train = train;
        this.station = station;
        this.stationSerialNumber = stationSerialNumber;
    }

    public List<StationStopTime> getStationStopTimes() {
        return stationStopTimes;
    }

    public void setStationStopTimes(List<StationStopTime> stationStopTimes) {
        this.stationStopTimes = stationStopTimes;
    }

    public Train getTrain() {
        return train;
    }

    public Station getStation() {
        return station;
    }

    public int getStationSerialNumber() {
        return stationSerialNumber;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setStationSerialNumber(int stationSerialNumber) {
        this.stationSerialNumber = stationSerialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TrainStation that = (TrainStation) o;

        return new EqualsBuilder()
                .append(stationSerialNumber, that.stationSerialNumber)
                .append(train, that.train)
                .append(station, that.station)
                .append(stationStopTimes, that.stationStopTimes)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(train)
                .append(station)
                .append(stationSerialNumber)
                .append(stationStopTimes)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "TrainStation{" +
                "train=" + train +
                ", station=" + station +
                ", stationSerialNumber=" + stationSerialNumber +
                ", id=" + id +
                '}';
    }


}
