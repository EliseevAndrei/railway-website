package com.eliseev.app.services.dto;

import com.eliseev.app.utils.CustomRestDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Date;

public class StationStopTimeDTO implements Serializable {

    private Long trainStationId;
    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    private Date arriveTime;
    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    private Date departureTime;

    public StationStopTimeDTO() {
    }

    public StationStopTimeDTO(Long trainStationId, Date arriveTime, Date departureTime) {
        this.trainStationId = trainStationId;
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Long getTrainStationId() {
        return trainStationId;
    }

    public void setTrainStationId(Long trainStationId) {
        this.trainStationId = trainStationId;
    }


    @Override
    public String toString() {
        return "StationStopTimeDTO{" +
                "trainStationId=" + trainStationId +
                ", arriveTime=" + arriveTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
