package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TicketDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class TicketDAOImpl extends AbstractDAO<Ticket>
        implements TicketDAO {

    public TicketDAOImpl() {
        super(Ticket.class);
    }

    @Override
    public List<Ticket> listByUserId(Long id, String graphName) {
        Query query = entityManager.createQuery("select t from Ticket t where t.user.id = :id", Ticket.class)
                .setParameter("id", id);
        if (graphName.length() != 0) {
            EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
            query.setHint("javax.persistence.fetchgraph", entityGraph);
        }
        return query.getResultList();
    }

    @Override
    public List<Ticket> findByPlaceId(Long placeId,
                                      Long trainId,
                                      Long depTrainRoutePiece,
                                      Long arrTrainRoutePiece,
                                      Long trainDateId,
                                      String graphName) {

        Query query = entityManager.createQuery(
                "select t from Ticket t\n" +
                        "join TrainRoutePiece trpL on trpL.id = t.depTrainRoutePiece.id\n" +
                        "join TrainRoutePiece trpR on trpR.id = t.arrTrainRoutePiece.id\n" +
                        "where t.trainDate.id = :trainDateId \n" +
                        "and t.place.id = :placeId\n" +
                        "and ((trpL.serialNumber <= (select trp1.serialNumber from TrainRoutePiece trp1 where trp1.train.id = :trainId and trp1.id = :leftBorderSerialNumber)\n" +
                        "and trpR.serialNumber >= (select trp2.serialNumber from TrainRoutePiece trp2 where trp2.train.id = :trainId and trp2.id = :rightBorderSerialNumber))\n" +
                        "or (\n" +
                        "trpL.serialNumber >= (select trp1.serialNumber from TrainRoutePiece trp1 where trp1.train.id = 25 and trp1.id = :leftBorderSerialNumber)\n" +
                        "and trpR.serialNumber <= (select trp2.serialNumber from TrainRoutePiece trp2 where trp2.train.id = 25 and trp2.id = :rightBorderSerialNumber)\n" +
                        "))", Ticket.class)
                .setParameter("trainDateId", trainDateId)
                .setParameter("leftBorderSerialNumber", depTrainRoutePiece)
                .setParameter("rightBorderSerialNumber", arrTrainRoutePiece)
                .setParameter("trainId", trainId)
                .setParameter("placeId", placeId);
        if (graphName.length() != 0) {
            EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
            query.setHint("javax.persistence.fetchgraph", entityGraph);
        }
        return query.getResultList();
    }

    @Override
    public Long ticketCountWithTrainIdAndDate(Long trainId, Date date) {
        return (Long) super.entityManager.createQuery(
                "select count(t) from Ticket t where t.trainDate.train.id = :trainId and t.arrTime >= :date")
                .setParameter("date", date)
                .setParameter("trainId", trainId)
                .getSingleResult();
    }

    @Override
    public Long ticketAmountOnStationIdAndDate(Long stationId, Date date) {
        return (Long) super.entityManager.createQuery(
                "select count(t) from Ticket t where (t.depTrainRoutePiece.startStation.id = :stationId or t.arrTrainRoutePiece.endStation.id = :stationId) and t.arrTime >= :date")
                .setParameter("date", date)
                .setParameter("stationId", stationId)
                .getSingleResult();
    }

    @Override
    public long ticketAmountOnTrainDateIdAndDate(Long trainDateId, Date date) {
        return (Long) super.entityManager.createQuery(
                "select count(t) from Ticket t where t.trainDate.id = :trainDateId and t.arrTime >= :date")
                .setParameter("date", date)
                .setParameter("trainDateId", trainDateId)
                .getSingleResult();
    }
}
