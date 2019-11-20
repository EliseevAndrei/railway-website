package com.eliseev.app.repository.custom;

import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.IDAO;

import java.util.List;

public interface TrainDateDAO extends IDAO<TrainDate> {

    List<TrainDate> findDatesByTrainId(long trainId);

}
