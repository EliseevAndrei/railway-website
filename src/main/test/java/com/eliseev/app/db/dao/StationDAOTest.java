package com.eliseev.app.db.dao;

import com.eliseev.app.models.Station;
import com.eliseev.app.repository.custom.StationDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class StationDAOTest {

    private Logger logger = LoggerFactory.getLogger(TrainStationDAOTest.class);

    @Autowired
    private StationDAO stationDAO;

    @Test
    @Transactional
    public void getUnusedStations() {
        List<Station> stations = stationDAO.getUnusedStations(1L);
        assertEquals(2, stations.size());
        logger.info("{}", stations);
    }

}
