package com.eliseev.app.repository.custom;

import com.eliseev.app.models.Station;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.IDAO;
import com.eliseev.app.services.dto.RouteDTO;

import java.util.List;

public interface TrainDateDAO extends IDAO<TrainDate> {

    List<TrainDate> findDatesByTrainId(long trainId);
    List<RouteDTO> getTrainDates(Station depStation, Station arrStation, String depDateLeftBorder, String depDateRightBorder);
    public List<RouteDTO> getFreePlacesForTrainDateBetweenRoutePieces(RouteDTO routeDTO, long depRoutePiece, long arrRoutePiece);
}
