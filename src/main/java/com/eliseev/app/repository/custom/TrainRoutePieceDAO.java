package com.eliseev.app.repository.custom;

import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.repository.IDAO;

import java.util.List;

public interface TrainRoutePieceDAO extends IDAO<TrainRoutePiece> {

    List<TrainRoutePiece> findByTrainId(Long trainId, String graphName);
    TrainRoutePiece findByTrainIdAndStartStationId(long trainId, long startStationId, String graphName);
    TrainRoutePiece findByTrainIdAndEndStationId(long trainId, long endStationId, String graphName);
}
