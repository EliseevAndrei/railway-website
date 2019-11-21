package com.eliseev.app.models;

import com.eliseev.app.utils.CustomRestDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

@Embeddable
public class Route {

    @Transient
    private long trainId;

    @Column(name="train")
    private String trainName;
    @Column(name="dep_station")
    private String depStation;
    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dep_time")
    private Date depTime;
    @Column(name="arr_station")
    private String arrStation;
    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @Column(name="arr_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrTime;

    @Transient
    private int coupe_places_amount;
    @Transient
    private int lying_places_amount;
    @Transient
    private int common_places_amount;

    public Route(long trainId,String trainName, String depStation,
                 Date depTime, String arrStation,
                 Date arrTime, int coupe_places_amount,
                 int lying_places_amount, int common_places_amount) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.depStation = depStation;
        this.depTime = depTime;
        this.arrStation = arrStation;
        this.arrTime = arrTime;
        this.coupe_places_amount = coupe_places_amount;
        this.lying_places_amount = lying_places_amount;
        this.common_places_amount = common_places_amount;
    }

    public Route() { }



    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public void setDepStation(String depStation) {
        this.depStation = depStation;
    }

    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }

    public void setArrStation(String arrStation) {
        this.arrStation = arrStation;
    }

    public void setArrTime(Date arrTime) {
        this.arrTime = arrTime;
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

    public String getTrainName() {
        return trainName;
    }

    public String getDepStation() {
        return depStation;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    public Date getDepTime() {
        return depTime;
    }

    public String getArrStation() {
        return arrStation;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    public Date getArrTime() {
        return arrTime;
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
        return "Route{" +
                "trainId=" + trainId +
                ", trainName='" + trainName + '\'' +
                ", depStation='" + depStation + '\'' +
                ", depTime=" + depTime +
                ", arrStation='" + arrStation + '\'' +
                ", arrTime=" + arrTime +
                ", coupe_places_amount=" + coupe_places_amount +
                ", lying_places_amount=" + lying_places_amount +
                ", common_places_amount=" + common_places_amount +
                '}';
    }


}
