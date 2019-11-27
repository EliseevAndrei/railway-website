package com.eliseev.app.repository.custom;

import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.repository.IDAO;

import java.util.List;

public interface TrainRoutePieceDAO extends IDAO<TrainRoutePiece> {

    List<TrainRoutePiece> findByTrainId(Long trainId);
}
