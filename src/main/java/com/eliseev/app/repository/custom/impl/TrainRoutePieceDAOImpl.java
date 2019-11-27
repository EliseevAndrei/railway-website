package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainRoutePieceDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainRoutePieceDAOImpl extends AbstractDAO<TrainRoutePiece>
        implements TrainRoutePieceDAO {

    public TrainRoutePieceDAOImpl() {
        super(TrainRoutePiece.class);
    }

    /*@Override
    public void delete(long id) {

        TrainRoutePiece trainRoutePiece = super.findOne(id);

        super.delete(id);

        //todo. check it later
        super.entityManager.flush();
        super.entityManager.clear();

        super.entityManager.createQuery("update TrainStation s set s.stationSerialNumber = s.stationSerialNumber - 1 where  s.stationSerialNumber >= :number and s.train.id = :idTrain")
                .setParameter("number", trainRoutePiece.getStationSerialNumber())
                .setParameter("idTrain", trainRoutePiece.getTrain().getId())
                .executeUpdate();

        List<TrainRoutePiece> trainRoutePieces = super.findAll();
        System.out.println(trainRoutePieces);
    }

    @Override
    public TrainRoutePiece save(TrainRoutePiece trainRoutePiece) {

        if (trainRoutePiece.getId() != null && trainRoutePiece.getId() == -1){
            trainRoutePiece.setId(null);
        }

        if (trainRoutePiece.getId() == null) {

            int count = super.entityManager.createQuery("select max(s.stationSerialNumber) from TrainStation s", Integer.class).getSingleResult();

            if (trainRoutePiece.getStationSerialNumber() > count) {
                trainRoutePiece.setStationSerialNumber(count + 1);
            } else {
                super.entityManager.createQuery("update TrainStation s set s.stationSerialNumber = s.stationSerialNumber + 1 where s.stationSerialNumber >= :number and s.train.id = :idTrain")
                        .setParameter("number", trainRoutePiece.getStationSerialNumber())
                        .setParameter("idTrain", trainRoutePiece.getTrain().getId())
                        .executeUpdate();
            }
            entityManager.persist(trainRoutePiece);
        } else {

            return entityManager.merge(trainRoutePiece);

        }
        return trainRoutePiece;

    }*/

    @Override
    public List<TrainRoutePiece> findByTrainId(Long trainId) {
        return super.entityManager.createQuery("select s from TrainStation s where s.train.id = :trainId order by s.stationSerialNumber", TrainRoutePiece.class)
                .setParameter("trainId", trainId)
                .getResultList();
    }

}
