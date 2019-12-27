package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainRoutePieceDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TrainRoutePieceDAOImpl extends AbstractDAO<TrainRoutePiece>
        implements TrainRoutePieceDAO {

    public TrainRoutePieceDAOImpl() {
        super(TrainRoutePiece.class);
    }


    @Override
    public List<TrainRoutePiece> findByTrainId(Long trainId, String graphName) {
        Query query = super.entityManager.createQuery("select s from TrainRoutePiece s where s.train.id = :trainId order by s.serialNumber", TrainRoutePiece.class)
                .setParameter("trainId", trainId);
        if (graphName.length() != 0) {
            EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
            query.setHint("javax.persistence.fetchgraph", entityGraph);
        }
        return query.getResultList();
    }

    @Override
    public TrainRoutePiece findByTrainIdAndStartStationId(long trainId, long startStationId, String graphName) {
        try {
            Query query = super.entityManager.createQuery("select s from TrainRoutePiece s where s.train.id = :trainId and s.startStation.id = :startStationId", TrainRoutePiece.class)
                    .setParameter("trainId", trainId)
                    .setParameter("startStationId", startStationId);
            if (graphName.length() != 0) {
                EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
                query.setHint("javax.persistence.fetchgraph", entityGraph);
            }
            return (TrainRoutePiece) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public TrainRoutePiece findByTrainIdAndEndStationId(long trainId, long endStationId, String graphName) {
        TrainRoutePiece trainRoutePiece;
        try {
            Query query = super.entityManager.createQuery("select s from TrainRoutePiece s where s.train.id = :trainId and s.endStation.id = :endStationId", TrainRoutePiece.class)
                    .setParameter("trainId", trainId)
                    .setParameter("endStationId", endStationId);
            if (graphName.length() != 0) {
                EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
                query.setHint("javax.persistence.fetchgraph", entityGraph);
            }
            return (TrainRoutePiece) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void updateAllAboveSerialNumber(long trainId, int serialNumber) {
        entityManager.createQuery("update TrainRoutePiece trp set trp.serialNumber = trp.serialNumber - 1 where trp.train.id = :trainId and trp.serialNumber >= :serialNumber")
                .setParameter("trainId", trainId)
                .setParameter("serialNumber", serialNumber)
                .executeUpdate();
    }

    @Override
    public List<TrainRoutePiece> findByStartStationId(long startStationId) {
        return entityManager.createQuery("select trp from TrainRoutePiece trp where trp.startStation.id = :startStationId",
                TrainRoutePiece.class)
                .setParameter("startStationId", startStationId)
                .getResultList();
    }

    @Override
    public List<TrainRoutePiece> findByEndStationId(long endStationId) {
        return entityManager.createQuery("select trp from TrainRoutePiece trp where trp.endStation.id = :endStationId",
                TrainRoutePiece.class)
                .setParameter("endStationId", endStationId)
                .getResultList();
    }
}
