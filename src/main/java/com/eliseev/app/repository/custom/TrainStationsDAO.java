package com.eliseev.app.repository.custom;

import com.eliseev.app.models.TrainStation;
import com.eliseev.app.repository.IDAO;

import java.util.List;

public interface TrainStationsDAO extends IDAO<TrainStation> {

    List<TrainStation> findByTrainId(Long trainId);
}
