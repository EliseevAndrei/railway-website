package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainStation;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainStationsDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainStationsDAOImpl extends AbstractDAO<TrainStation>
        implements TrainStationsDAO {

    public TrainStationsDAOImpl() {
        super(TrainStation.class);
    }

    @Override
    public List<TrainStation> findByTrainId(Long trainId) {
        return super.entityManager.createQuery("select s from TrainStation s where s.train.id = :trainId", TrainStation.class)
                .setParameter("trainId", trainId)
                .getResultList();
    }

}
