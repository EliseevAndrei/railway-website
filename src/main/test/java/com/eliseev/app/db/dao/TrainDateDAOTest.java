package com.eliseev.app.db.dao;

import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.custom.TrainDateDAO;
import com.eliseev.app.services.StationService;
import com.eliseev.app.services.TrainDateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainDateDAOTest {

    @Autowired
    private TrainDateDAO trainDateDAO;

    @Autowired
    private StationService stationService;

    @Test
    @Transactional
    public void getFreePlacesForTrainDate() {
        trainDateDAO.getFreePlacesForTrainDateBetweenStations(1, 1, stationService.get(1L), stationService.get(4L));
    }

}
