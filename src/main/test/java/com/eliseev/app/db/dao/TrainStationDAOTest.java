package com.eliseev.app.db.dao;

import com.eliseev.app.models.Station;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainStation;
import com.eliseev.app.repository.custom.StationDAO;
import com.eliseev.app.repository.custom.TrainDAO;
import com.eliseev.app.repository.custom.TrainStationsDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainStationDAOTest {

    private Logger logger = LoggerFactory.getLogger(TrainStationDAOTest.class);

    @Autowired
    private TrainStationsDAO trainStationsDAO;

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private StationDAO stationDAO;

    @Test
    @Transactional
    public void listByTrainId() {
        List<TrainStation> trainStations = trainStationsDAO.findByTrainId(1L);
        assertEquals(3, trainStations.size());
    }

    @Test
    @Transactional
    public void addStationInMiddleOfRoute() {

        TrainStation trainStation = new TrainStation(trainDAO.findOne(1L), stationDAO.findOne(5L), 2);

        TrainStation saved = trainStationsDAO.save(trainStation);
        assertEquals(2, saved.getStationSerialNumber());



        List<TrainStation> trainStations = trainStationsDAO.findByTrainId(1L);
        assertEquals(trainStationsDAO.findOne(saved.getId()), trainStations.get(1));

        int[] arr = getSequenceOfStation(trainStations);

        logger.info("{}", trainStations);

        assertArrayEquals(new int[] {1, 2, 3, 4}, arr);

    }


    @Test
    @Transactional
    public void correctSequenceOfStationsWhenDeleting() {

        List<TrainStation> stations =  trainStationsDAO.findByTrainId(1L);
        logger.info("{}", stations);
        assertArrayEquals(new int[] {1, 2, 3}, getSequenceOfStation(stations));

        TrainStation station = stations.get(1);
        trainStationsDAO.delete(station.getId());

        stations = trainStationsDAO.findByTrainId(1L);
        logger.info("{}", stations);
        assertArrayEquals(new int[] {1, 2}, getSequenceOfStation(stations));

    }

    @Test
    @Transactional
    public void addStationToEndOfRoute() {
        List<TrainStation> stations =  trainStationsDAO.findByTrainId(1L);
        assertArrayEquals(new int[] {1, 2, 3}, getSequenceOfStation(stations));

        logger.info("{}", stations);

        TrainStation newStation1 = new TrainStation(trainDAO.findOne(1L), stationDAO.findOne(3L), 10);
        TrainStation saved = trainStationsDAO.save(newStation1);

        stations = trainStationsDAO.findByTrainId(1L);
        logger.info("{}", stations);
        assertArrayEquals(new int[]{1, 2, 3, 4}, getSequenceOfStation(stations));

        assertEquals(saved, stations.get(3));

    }

    private int[] getSequenceOfStation(List<TrainStation> stations) {
        int[] arr = new int[stations.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stations.get(i).getStationSerialNumber();
        }
        return arr;
    }


    @Test
    @Transactional
    public void update() {
        TrainStation trainStation = new TrainStation(1,new Train(1, null, 0, 0, 0), new Station(3, null), 1);
        trainStationsDAO.save(trainStation);
        TrainStation dbValue =  trainStationsDAO.findOne(1);
        logger.info("{}", dbValue);
    }

}
