package com.eliseev.app.repository.custom;

import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.repository.IDAO;

import java.util.List;

public interface StationStopTimeDAO extends IDAO<StationStopTime> {

    List<StationStopTime> findStationsStopTimeByTrainDateId(Long id);

    StationStopTime getStopTimeByTrainRouteIdAndTrainDateId(long trainRouteId, long trainDateId);

    /*void orderCoupePlace(Long trainDateId, int startRoutePieceSerialNumber, int endRoutePieceSerialNumber);

    void orderLyingPlace(Long trainDateId, int startRoutePieceSerialNumber, int endRoutePieceSerialNumber);

    void orderCommonPlace(Long trainDateId, int startRoutePieceSerialNumber, int endRoutePieceSerialNumber);*/
}
