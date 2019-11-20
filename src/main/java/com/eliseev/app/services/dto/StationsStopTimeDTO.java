package com.eliseev.app.services.dto;

import com.eliseev.app.models.StationStopTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StationsStopTimeDTO implements Serializable {

    private List<StationStopTime> stationStopTimes = new ArrayList<>();

    public StationsStopTimeDTO() {
    }

    public StationsStopTimeDTO(List<StationStopTime> stationStopTimes) {
        this.stationStopTimes = stationStopTimes;
    }

    public List<StationStopTime> getStationStopTimes() {
        return stationStopTimes;
    }

    public void setStationStopTimes(List<StationStopTime> stationStopTimes) {
        this.stationStopTimes = stationStopTimes;
    }

    @Override
    public String toString() {
        return "StationsStopTimeDTO{" +
                "stationStopTimes=" + stationStopTimes +
                '}';
    }
}
