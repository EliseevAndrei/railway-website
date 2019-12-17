package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.repository.custom.StationDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class StationDAOTest {

    private Logger logger = LoggerFactory.getLogger(TrainRoutePieceDAOTest.class);

    @Autowired
    private StationDAO stationDAO;

    @Test
    @Transactional(readOnly = true)
    public void getStationByName() {
        assertNotNull(stationDAO.findStationByName("Брест"));
        assertNull(stationDAO.findStationByName("asdf"));
    }
    /*@Test
    @Transactional
    public void getUnusedStations() {
        List<Station> stations = stationDAO.getUnusedStations(1L);
        assertEquals(2, stations.size());
        logger.info("{}", stations);
    }*/

}
