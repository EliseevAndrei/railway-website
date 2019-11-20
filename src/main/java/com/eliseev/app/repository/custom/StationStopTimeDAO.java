package com.eliseev.app.repository.custom;

import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.repository.IDAO;

import java.util.List;

public interface StationStopTimeDAO extends IDAO<StationStopTime> {


    List<StationStopTime> findStationsStopTimeByTrainDateId(Long id);
}
