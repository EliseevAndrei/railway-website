package com.eliseev.app.db.dao;

import com.eliseev.app.db.TestConfig;
import com.eliseev.app.models.Station;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainStation;
import com.eliseev.app.repository.custom.StationDAO;
import com.eliseev.app.repository.custom.TrainDAO;
import com.eliseev.app.repository.custom.TrainStationsDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainStationDAOTest {

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
        assertEquals(3, trainStationsDAO.findByTrainId(1L).size());
        Train train = trainDAO.findOne(1L);
        Station station = stationDAO.findOne(1L);
        TrainStation trainStation = new TrainStation(train, station, new Date(), new Date(),
                4, 10, 10, 10);
        trainStationsDAO.save(trainStation);
        assertEquals(4, trainStationsDAO.findByTrainId(1L).size());

    }

}
