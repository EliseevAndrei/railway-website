package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
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
@ContextConfiguration(classes = TestConfig.class)
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

}
