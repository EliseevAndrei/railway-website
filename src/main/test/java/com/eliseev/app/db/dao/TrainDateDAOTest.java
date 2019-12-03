package com.eliseev.app.db.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.repository.custom.TrainDateDAO;
import com.eliseev.app.services.StationService;
import com.eliseev.app.services.dto.RouteDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainDateDAOTest {

    private Logger logger = LoggerFactory.getLogger(TrainDateDAOTest.class);

    @Autowired
    private TrainDateDAO trainDateDAO;

    @Autowired
    private StationService stationService;

    @Test
    @Transactional
    public void getFreePlacesForTrainDate() {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setTrainId(1L);
        routeDTO.setTrainDateId(1L);
        RouteDTO saved = trainDateDAO.getFreePlacesForTrainDateBetweenRoutePieces(routeDTO,  1, 4);
        assertNotNull(saved);
        logger.info("{}",routeDTO);
    }



}
