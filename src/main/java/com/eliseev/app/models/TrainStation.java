package com.eliseev.app.models;

import java.util.Date;

public class TrainStation extends AbstractEntity{

    private String station;
    private Date arriveTime;
    private Date departureTime;
    private long stationSerialNumber;
    private int coupe_places_amount;
    private int lying_places_amount;
    private int common_places_amount;

    public TrainStation(int id, String station,
                        Date arriveTime, Date departureTime,
                        long stationSerialNumber,
                        int coupe_places_amount,
                        int lying_places_amount,
                        int common_places_amount) {
        super.id = id;
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

    public TrainStation(String station, Date arriveTime, Date departureTime, long stationSerialNumber, int coupe_places_amount, int lying_places_amount, int common_places_amount) {
        this.station = station;
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.stationSerialNumber = stationSerialNumber;
        this.coupe_places_amount = coupe_places_amount;
        this.lying_places_amount = lying_places_amount;
        this.common_places_amount = common_places_amount;
    }



    public void setStation(String station) {
        this.station = station;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

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

    public Date getArriveTime() {
        return arriveTime;
    }

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
    public String toString() {
        return "TrainStation{" +
                "id=" + id +
                ", station='" + station + '\'' +
                ", arriveTime=" + arriveTime +
                ", departureTime=" + departureTime +
                ", stationSerialNumber=" + stationSerialNumber +
                ", coupe_places_amount=" + coupe_places_amount +
                ", lying_places_amount=" + lying_places_amount +
                ", common_places_amount=" + common_places_amount +
                '}';
    }
}
