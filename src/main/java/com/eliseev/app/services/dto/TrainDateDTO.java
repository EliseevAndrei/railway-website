package com.eliseev.app.services.dto;

import com.eliseev.app.models.StationStopTime;

import java.io.Serializable;

public class TrainDateDTO implements Serializable {

    private StationStopTime firstStation;
    private StationStopTime lastStation;

    public TrainDateDTO(StationStopTime firstStation, StationStopTime lastStation) {
        this.firstStation = firstStation;
        this.lastStation = lastStation;
    }

    public StationStopTime getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(StationStopTime firstStation) {
        this.firstStation = firstStation;
    }

    public StationStopTime getLastStation() {
        return lastStation;
    }

    public void setLastStation(StationStopTime lastStation) {
        this.lastStation = lastStation;
    }

    @Override
    public String toString() {
        return "TrainDateDTO{" +
                "firstStation=" + firstStation +
                ", lastStation=" + lastStation +
                '}';
    }
}
