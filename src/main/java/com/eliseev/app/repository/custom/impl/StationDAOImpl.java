package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Station;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.StationDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StationDAOImpl extends AbstractDAO<Station>
        implements StationDAO {

    public StationDAOImpl() {
        super(Station.class);
    }

    @Override
    public List<Station> getUnusedStations(long trainId) {
        return super.entityManager.createQuery("select s from Station s " +
                "left join TrainStation ts on ts.station.id = s.id and ts.train.id = :idTrain where ts.station.id is null", Station.class)
                .setParameter("idTrain", trainId)
                .getResultList();
    }

}
