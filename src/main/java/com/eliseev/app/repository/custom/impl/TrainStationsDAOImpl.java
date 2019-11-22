package com.eliseev.app.repository.custom.impl;

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
    public void delete(long id) {

        TrainStation trainStation = super.findOne(id);

        super.delete(id);

        //todo. check it later
        super.entityManager.flush();
        super.entityManager.clear();

        super.entityManager.createQuery("update TrainStation s set s.stationSerialNumber = s.stationSerialNumber - 1 where  s.stationSerialNumber >= :number and s.train.id = :idTrain")
                .setParameter("number", trainStation.getStationSerialNumber())
                .setParameter("idTrain", trainStation.getTrain().getId())
                .executeUpdate();

        List<TrainStation> trainStations = super.findAll();
        System.out.println(trainStations);
    }

    @Override
    public TrainStation save(TrainStation trainStation) {

        if (trainStation.getId() != null && trainStation.getId() == -1){
            trainStation.setId(null);
        }

        if (trainStation.getId() == null) {

            int count = super.entityManager.createQuery("select max(s.stationSerialNumber) from TrainStation s", Integer.class).getSingleResult();

            if (trainStation.getStationSerialNumber() > count) {
                trainStation.setStationSerialNumber(count + 1);
            } else {
                super.entityManager.createQuery("update TrainStation s set s.stationSerialNumber = s.stationSerialNumber + 1 where s.stationSerialNumber >= :number and s.train.id = :idTrain")
                        .setParameter("number", trainStation.getStationSerialNumber())
                        .setParameter("idTrain", trainStation.getTrain().getId())
                        .executeUpdate();
            }
            entityManager.persist(trainStation);
        } else {

            return entityManager.merge(trainStation);

        }
        return trainStation;

    }

    @Override
    public List<TrainStation> findByTrainId(Long trainId) {
        return super.entityManager.createQuery("select s from TrainStation s where s.train.id = :trainId order by s.stationSerialNumber", TrainStation.class)
                .setParameter("trainId", trainId)
                .getResultList();
    }

}
