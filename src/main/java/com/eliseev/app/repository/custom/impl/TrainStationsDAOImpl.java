package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.TrainStation;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainStationsDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TrainStationsDAOImpl extends AbstractDAO<TrainStation>
        implements TrainStationsDAO {

    public TrainStationsDAOImpl() {
        super(TrainStation.class);
    }

    @Override
    public TrainStation save(TrainStation trainStation) {


        //todo 1.if adding number more than that in db
        //todo 2.if there is already exists same station(unique pair train-station)
        /*int number = super.entityManager.createQuery("select max(s.stationSerialNumber) from TrainStation s", TrainStation.class).getSingleResult();*/

        Query query =  super.entityManager.createQuery("update TrainStation s set s.stationSerialNumber = s.stationSerialNumber + 1 where s.stationSerialNumber >= :number");
        query.setParameter("number", trainStation.getStationSerialNumber());
        query.executeUpdate();
        return super.save(trainStation);

    }

    @Override
    public List<TrainStation> findByTrainId(Long trainId) {
        return super.entityManager.createQuery("select s from TrainStation s where s.train.id = :trainId order by s.stationSerialNumber", TrainStation.class)
                .setParameter("trainId", trainId)
                .getResultList();
    }

}
