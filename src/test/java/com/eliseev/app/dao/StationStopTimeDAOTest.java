package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.repository.custom.StationStopTimeDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class StationStopTimeDAOTest {

    @Autowired
    private StationStopTimeDAO stationStopTimeDAO;

    @Test
    @Transactional
    public void findStationsStopTimeByTrainDateId() {

        List<StationStopTime> stationsStopTime = stationStopTimeDAO.findStationsStopTimeByTrainDateId(31L);
        assertEquals(6, stationsStopTime.size());
        assertEquals(2, stationStopTimeDAO.findStationsStopTimeByTrainDateId(34L).size());

    }

    @Test
    @Transactional
    public void getStopTimeByTrainRouteIdAndTrainDateId() {

        StationStopTime stationStopTime = stationStopTimeDAO.getStopTimeByTrainRouteIdAndTrainDateId(31,34);
        assertEquals(87, (long) stationStopTime.getId());
        assertEquals(80, (long) stationStopTimeDAO.getStopTimeByTrainRouteIdAndTrainDateId(38, 32).getId());

    }

    @Test
    @Transactional
    public void findOne() {
        StationStopTime stationStopTime = stationStopTimeDAO.findOne(73);
        assertNotNull(stationStopTime);
    }

    @Test
    @Transactional
    public void delete() {
        assertEquals(16, stationStopTimeDAO.count());
        stationStopTimeDAO.delete(73);
        assertEquals(15, stationStopTimeDAO.count());
    }

}
