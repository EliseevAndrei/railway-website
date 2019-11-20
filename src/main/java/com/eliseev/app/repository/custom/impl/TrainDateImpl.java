package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainDateDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainDateImpl extends AbstractDAO<TrainDate>
        implements TrainDateDAO {

    public TrainDateImpl() {
        super(TrainDate.class);
    }

    @Override
    public List<TrainDate> findDatesByTrainId(long trainId) {
        return super.entityManager.createQuery("select s from TrainDate s where s.train.id = :id", TrainDate.class)
                .setParameter("id", trainId)
                .getResultList();
    }
}
