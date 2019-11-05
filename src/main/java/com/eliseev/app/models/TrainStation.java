package com.eliseev.app.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TrainStation extends AbstractEntity implements Serializable {

    private long idTrain;
    private String station;
    private Date arriveTime;
    private Date departureTime;
    private long stationSerialNumber;
    private int coupe_places_amount;
    private int lying_places_amount;
    private int common_places_amount;

    public TrainStation(long id, long idTrain, String station,
                        Date arriveTime, Date departureTime,
                        long stationSerialNumber,
                        int coupe_places_amount,
                        int lying_places_amount,
                        int common_places_amount) {
        super.id = id;
        this.idTrain = idTrain;
        this.station = station;
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.stationSerialNumber = stationSerialNumber;
        this.coupe_places_amount = coupe_places_amount;
        this.lying_places_amount = lying_places_amount;
        this.common_places_amount = common_places_amount;
    }

    public TrainStation() {
    }


    public long getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(long idTrain) {
        this.idTrain = idTrain;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm", timezone = "Europe/Moscow")
    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm", timezone = "Europe/Moscow")
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setStationSerialNumber(long stationSerialNumber) {
        this.stationSerialNumber = stationSerialNumber;
    }

    public void setCoupe_places_amount(int coupe_places_amount) {
        this.coupe_places_amount = coupe_places_amount;
    }

    public void setLying_places_amount(int lying_places_amount) {
        this.lying_places_amount = lying_places_amount;
    }

    public void setCommon_places_amount(int common_places_amount) {
        this.common_places_amount = common_places_amount;
    }



    public String getStation() {
        return station;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    public Date getArriveTime() {
        return arriveTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    public Date getDepartureTime() {
        return departureTime;
    }

    public long getStationSerialNumber() {
        return stationSerialNumber;
    }

    public int getCoupe_places_amount() {
        return coupe_places_amount;
    }

    public int getLying_places_amount() {
        return lying_places_amount;
    }

    public int getCommon_places_amount() {
        return common_places_amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainStation that = (TrainStation) o;
        return idTrain == that.idTrain &&
                stationSerialNumber == that.stationSerialNumber &&
                Objects.equals(station, that.station) &&
                Objects.equals(arriveTime, that.arriveTime) &&
                Objects.equals(departureTime, that.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrain, station, arriveTime, departureTime, stationSerialNumber);
    }

    @Override
    public String toString() {
        return "TrainStation{" +
                "idTrain=" + idTrain +
                ", station='" + station + '\'' +
                ", arriveTime=" + arriveTime +
                ", departureTime=" + departureTime +
                ", stationSerialNumber=" + stationSerialNumber +
                ", coupe_places_amount=" + coupe_places_amount +
                ", lying_places_amount=" + lying_places_amount +
                ", common_places_amount=" + common_places_amount +
                ", id=" + id +
                '}';
    }
}
