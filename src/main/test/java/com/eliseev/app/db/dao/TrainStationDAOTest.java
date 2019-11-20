package com.eliseev.app.db.dao;

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
    public void create() {
        TrainStation trainStation = new TrainStation(trainDAO.findOne(1L), stationDAO.findOne(5L), 2);

        TrainStation saved = trainStationsDAO.save(trainStation);
        assertEquals(2, saved.getStationSerialNumber());

        List<TrainStation> trainStations = trainStationsDAO.findByTrainId(1L);
        int[] arr = new int[trainStations.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = trainStations.get(i).getStationSerialNumber();
        }

        logger.info("{}", trainStations);
        logger.info("{}", arr);

        assertArrayEquals(new int[] {1, 2, 3, 4}, arr);

    }

}
