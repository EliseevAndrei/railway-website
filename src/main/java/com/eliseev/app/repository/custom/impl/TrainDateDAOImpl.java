package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Station;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainDateDAO;
import com.eliseev.app.services.dto.RouteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TrainDateDAOImpl extends AbstractDAO<TrainDate>
        implements TrainDateDAO {

    private Logger logger = LoggerFactory.getLogger(TrainDAOImpl.class);

    public TrainDateDAOImpl() {
        super(TrainDate.class);
    }

    @Override
    public List<TrainDate> findDatesByTrainId(long trainId) {
        return super.entityManager.createQuery("select s from TrainDate s where s.train.id = :id", TrainDate.class)
                .setParameter("id", trainId)
                .getResultList();
    }

    @Override
    public List<RouteDTO> getTrainDates(Station depStation, Station arrStation,
                                                            String depDateLeftBorder, String depDateRightBorder) {

        @SuppressWarnings("unchecked")
        List<Object[]> objects = super.entityManager.createNativeQuery(
                "select trainId, train_date_id as trainDateId, trainName \n" +
                        "from (" +
                        "       select trainId, train_route_piece.id as pieceId, trainName\n" +
                        "       from train_route_piece\n" +
                        "           inner join (" +
                        "               select t.id as trainId, min(td.serial_number), t.name as trainName\n" +
                        "               from train_route_piece td\n" +
                        "                   inner join train t on t.id = td.train_id\n" +
                        "               where td.start_station_id =  :depStationId\n" +
                        "                   or td.end_station_id = :arrStationId\n" +
                        "               group by trainId\n" +
                        "               having count(t.id) >= 2" +
                        "                       ) as trainDepStationSerialNumber on train_route_piece.train_id = trainDepStationSerialNumber.trainId\n" +
                        "       where start_station_id = :depStationId\n" +
                        "       ) as suitableTrains\n" +
                        " left join station_stop_time sst on sst.train_route_piece_id = pieceId\n" +
                        "where sst.dep_time between :depDateLeftBorder and :depDateRightBorder")
                .setParameter("depStationId", depStation.getId())
                .setParameter("arrStationId", arrStation.getId())
                .setParameter("depDateLeftBorder", depDateLeftBorder)
                .setParameter("depDateRightBorder", depDateRightBorder)
                .getResultList();


        RouteDTO routeDTO;
        List<RouteDTO> routeDTOs = new ArrayList<>();
        for (Object[] q : objects) {

            routeDTO = new RouteDTO();
            routeDTO.setTrainId(((BigInteger) q[0]).longValue());
            routeDTO.setTrainDateId(((BigInteger) q[1]).longValue());
            routeDTO.setTrainName((String) q[2]);

            routeDTOs.add(routeDTO);
        }

        return routeDTOs;
    }

    @Override
    public RouteDTO getFreePlacesForTrainDateBetweenRoutePieces(RouteDTO routeDTO,
                                                                      int depRoutePieceSerialNumber,
                                                                      int arrRoutePieceSerialNumber) {

        @SuppressWarnings("unchecked")
        List<Object[]> objects = super.entityManager.createNativeQuery(
                "select  min(common_places_amount), min(coupe_places_amount), min(lying_places_amount)\n" +
                        "from (\n" +
                        "       select id, serial_number, train_id  from train_route_piece trp\n" +
                        "       where trp.train_id = :trainId\n" +
                        "         and trp.serial_number between :depRoutePieceSerialNumber and :arrRoutePieceSerialNumber\n" +
                        "     ) as tab\n" +
                        "       left join station_stop_time sst on sst.train_route_piece_id = tab.id\n" +
                        "where train_date_id = :trainDateId\n" +
                        "group by train_date_id")
                .setParameter("depRoutePieceSerialNumber", depRoutePieceSerialNumber)
                .setParameter("arrRoutePieceSerialNumber", arrRoutePieceSerialNumber)
                .setParameter("trainId", routeDTO.getTrainId())
                .setParameter("trainDateId", routeDTO.getTrainDateId())
                .getResultList();

        Object[] object =  objects.get(0);

        routeDTO.setCommonPlacesAmount((int)object[0]);
        routeDTO.setCoupePlacesAmount((int)object[1]);
        routeDTO.setLyingPlacesAmount((int)object[2]);

        return routeDTO;
    }

}