package com.eliseev.app.repository.custom;

import com.eliseev.app.models.Carriage;
import com.eliseev.app.models.Train;
import com.eliseev.app.repository.IDAO;

import java.util.List;
import java.util.Map;


public interface TrainDAO extends IDAO<Train> {
    Map<String, Integer> getFreePlacesAmountForTrainRoute(long trainId,
                                                          long trainDateId,
                                                          int depRoutePieceSerialNumber,
                                                          int arrRoutePieceSerialNumber);
    List<Carriage> getCarriages(long trainId, long trainDateId,
                                int depRoutePieceSerialNumber,
                                int arrRoutePieceSerialNumber,
                                String graphName);
    List<Carriage> getCarriages(long trainId, String graphName);
}
