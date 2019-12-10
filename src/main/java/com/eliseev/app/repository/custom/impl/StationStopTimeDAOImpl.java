package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.StationStopTimeDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class StationStopTimeDAOImpl extends AbstractDAO<StationStopTime>
        implements StationStopTimeDAO {

    public StationStopTimeDAOImpl() {
        super(StationStopTime.class);
    }

    @Override
    public List<StationStopTime> findStationsStopTimeByTrainDateId(Long id) {
        return super.entityManager.createQuery("select s from StationStopTime s where s.trainDate.id = :id", StationStopTime.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public StationStopTime getStopTimeByTrainRouteIdAndTrainDateId(long trainRouteId, long trainDateId) {
        try{
            return super.entityManager.createQuery("select s from StationStopTime s where s.trainRoutePiece.id = :trainRouteId and s.trainDate.id = :trainDateId", StationStopTime.class)
                    .setParameter("trainRouteId", trainRouteId)
                    .setParameter("trainDateId", trainDateId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /*@Override
    public void orderCoupePlace(Long trainDateId, int startRoutePieceSerialNumber, int endRoutePieceSerialNumber) {
        super.entityManager.createNativeQuery(
                "update station_stop_time\n" +
                "set coupe_places_amount = coupe_places_amount - 1\n" +
                "where id in (\n" +
                "select tab.stopTimeId  from (\n" +
                "                   select sst.id as stopTimeID, sst.train_route_piece_id from station_stop_time sst\n" +
                "                   where sst.train_date_id = :trainDateId\n" +
                "               ) as tab inner join train_route_piece trp on trp.id = tab.train_route_piece_id\n" +
                "where trp.serial_number between :startSerialNumber and :endSerialNumber\n" +
                ")")
                .setParameter("trainDateId", trainDateId)
                .setParameter("startSerialNumber", startRoutePieceSerialNumber)
                .setParameter("endSerialNumber", endRoutePieceSerialNumber)
                .executeUpdate();
    }

    @Override
    public void orderLyingPlace(Long trainDateId, int startRoutePieceSerialNumber, int endRoutePieceSerialNumber) {
        super.entityManager.createNativeQuery(
                "update station_stop_time\n" +
                        "set lying_places_amount = lying_places_amount - 1\n" +
                        "where id in (\n" +
                        "select tab.stopTimeId  from (\n" +
                        "                   select sst.id as stopTimeID, sst.train_route_piece_id from station_stop_time sst\n" +
                        "                   where sst.train_date_id = :trainDateId\n" +
                        "               ) as tab inner join train_route_piece trp on trp.id = tab.train_route_piece_id\n" +
                        "where trp.serial_number between :startSerialNumber and :endSerialNumber\n" +
                        ")")
                .setParameter("trainDateId", trainDateId)
                .setParameter("startSerialNumber", startRoutePieceSerialNumber)
                .setParameter("endSerialNumber", endRoutePieceSerialNumber)
                .executeUpdate();
    }

    @Override
    public void orderCommonPlace(Long trainDateId, int startRoutePieceSerialNumber, int endRoutePieceSerialNumber) {
        super.entityManager.createNativeQuery(
                "update station_stop_time\n" +
                        "set common_places_amount = common_places_amount - 1\n" +
                        "where id in (\n" +
                        "select tab.stopTimeId  from (\n" +
                        "                   select sst.id as stopTimeID, sst.train_route_piece_id from station_stop_time sst\n" +
                        "                   where sst.train_date_id = :trainDateId\n" +
                        "               ) as tab inner join train_route_piece trp on trp.id = tab.train_route_piece_id\n" +
                        "where trp.serial_number between :startSerialNumber and :endSerialNumber\n" +
                        ")")
                .setParameter("trainDateId", trainDateId)
                .setParameter("startSerialNumber", startRoutePieceSerialNumber)
                .setParameter("endSerialNumber", endRoutePieceSerialNumber)
                .executeUpdate();
    }*/


}
