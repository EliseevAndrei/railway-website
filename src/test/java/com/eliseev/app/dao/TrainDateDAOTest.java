package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.repository.custom.TrainDateDAO;
import com.eliseev.app.services.StationService;
import com.eliseev.app.dto.TrainRouteDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainDateDAOTest {

    @Autowired
    private TrainDateDAO trainDateDAO;

    @Autowired
    private StationService stationService;

    @Test
    @Transactional
    public void delete() {
        assertEquals(5, trainDateDAO.count());
        trainDateDAO.delete(31);
        assertEquals(4, trainDateDAO.count());
    }

    @Test
    @Transactional
    public void findOne() {
        assertNotNull(trainDateDAO.findOne(31));
        assertNotNull(trainDateDAO.findOne(33));
    }

    @Test
    @Transactional
    public void findTrainDateByTrainId() {
        assertEquals(2, trainDateDAO.findDatesByTrainId(25).size());
        assertEquals(3, trainDateDAO.findDatesByTrainId(23).size());
    }

    @Test
    @Transactional
    public void findTrainDates() {
        List<TrainRouteDTO> trainRouteDTOs = trainDateDAO.getTrainDates(stationService.get(4), stationService.get(22),
                "2019-11-11 00:00:00", "2019-11-11 23:59:59");
        assertEquals(2, trainRouteDTOs.size());
        assertNotNull(trainRouteDTOs.stream()
                .filter(e -> e.getTrain().getId() == 23)
                .findAny()
                .orElse(null));
        assertNotNull(trainRouteDTOs.stream()
                .filter(e -> e.getTrain().getId() == 25)
                .findAny()
                .orElse(null));

        trainRouteDTOs = trainDateDAO.getTrainDates(stationService.get(4), stationService.get(22),
                "2019-12-11 00:00:00", "2019-12-11 23:59:59");
        assertEquals(1, trainRouteDTOs.size());
        assertEquals(23, (long) trainRouteDTOs.get(0).getTrain().getId());


    }
}
