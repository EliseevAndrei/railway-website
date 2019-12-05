package com.eliseev.app.db.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.repository.custom.StationStopTimeDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class StationStopTimeDAOTest {

    @Autowired
    private StationStopTimeDAO stationStopTimeDAO;

    @Test
    @Transactional
    public void orderCoupePlace() {
        stationStopTimeDAO.orderCoupePlace(1L, 1, 3);
    }


}
