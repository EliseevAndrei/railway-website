package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.repository.custom.TrainDateDAO;
import com.eliseev.app.services.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = TestConfig.class)
public class TrainDateDAOTest {

    private Logger logger = LoggerFactory.getLogger(TrainDateDAOTest.class);

    @Autowired
    private TrainDateDAO trainDateDAO;

    @Autowired
    private StationService stationService;

    /*@Test
    @Transactional
    public void getFreePlacesForTrainDate() {
        TrainRouteDTO routeDTO = new TrainRouteDTO();
        routeDTO.setTrainId(1L);
        routeDTO.setTrainDateId(1L);
        TrainRouteDTO saved = trainDateDAO.getFreePlacesForTrainDateBetweenRoutePieces(routeDTO,  1, 4);
        assertNotNull(saved);
        logger.info("{}",routeDTO);
    }*/

    /*@Test
    @Transactional
    public void getFreePlaces() {
        trainDateDAO.getFreePlacesForTrain(new Train(), 0, 1, 3);
    }*/



}
