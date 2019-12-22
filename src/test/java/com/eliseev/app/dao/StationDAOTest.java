package com.eliseev.app.dao;

import com.eliseev.app.models.Station;
import com.eliseev.app.repository.custom.StationDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
public class StationDAOTest {

    @Autowired
    private StationDAO stationDAO;

    @Test
    @Transactional
    public void findOne() {
        assertNotNull(stationDAO.findOne(1));
        assertNotNull(stationDAO.findOne(2));
    }

    @Test
    @Transactional
    public void delete() {
        assertEquals(21, stationDAO.count());
        stationDAO.delete(1);
        assertEquals(20, stationDAO.count());
    }

    @Test
    @Transactional
    public void create() {
        assertEquals(21, stationDAO.count());
        Station station = new Station();
        station.setName("Крычев");
        stationDAO.save(station);
        assertEquals(22, stationDAO.count());
    }

    @Test
    @Transactional
    public void findAll() {
        assertEquals(21, stationDAO.findAll().size());
    }

}
