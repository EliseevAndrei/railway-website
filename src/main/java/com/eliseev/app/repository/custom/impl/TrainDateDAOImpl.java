package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Station;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainDateDAO;
import com.eliseev.app.dto.additional.TrainRouteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.Query;
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
    public List<TrainDate> findDatesByTrainId(long trainId, String graphName) {
        Query query = super.entityManager.createQuery("select s from TrainDate s where s.train.id = :id", TrainDate.class)
                .setParameter("id", trainId);
        if (graphName.length() != 0) {
            EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
            query.setHint("javax.persistence.fetchgraph", entityGraph);
        }
        return query.getResultList();
    }

    @Override
    public List<TrainRouteDTO> getTrainDates(Station depStation, Station arrStation,
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
                        "               having count(t.id) >= 1" +
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

        TrainRouteDTO trainRouteDTO;
        List<TrainRouteDTO> trainRouteDTOS = new ArrayList<>();
        Train train;
        for (Object[] q : objects) {

            train = new Train();
            train.setId(((BigInteger) q[0]).longValue());
            train.setName((String) q[2]);
            trainRouteDTO = new TrainRouteDTO();
            trainRouteDTO.setTrainDateId(((BigInteger) q[1]).longValue());
            trainRouteDTO.setTrain(train);
            trainRouteDTOS.add(trainRouteDTO);
        }

        return trainRouteDTOS;
    }


}
