package com.eliseev.app.repository.custom;

import com.eliseev.app.models.Route;
import com.eliseev.app.models.Station;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.IDAO;
import com.eliseev.app.services.dto.TrainDateDTO;

import java.util.List;

public interface TrainDateDAO extends IDAO<TrainDate> {

    List<TrainDate> findDatesByTrainId(long trainId);
    List<TrainDateDTO> getTrainDates(Station depStation, Station arrStation, String depDateLeftBorder, String depDateRightBorder);
    List<Route> getFreePlacesForTrainDateBetweenStations(long trainId, long trainDateId, Station depStation, Station arrStation);

}
