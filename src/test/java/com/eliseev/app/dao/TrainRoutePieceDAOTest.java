package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.repository.custom.StationDAO;
import com.eliseev.app.repository.custom.TrainDAO;
import com.eliseev.app.repository.custom.TrainRoutePieceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = TestConfig.class)
public class TrainRoutePieceDAOTest {

    private Logger logger = LoggerFactory.getLogger(TrainRoutePieceDAOTest.class);

    @Autowired
    private TrainRoutePieceDAO trainRoutePieceDAO;

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private StationDAO stationDAO;

    /*@Test
    @Transactional
    public void listByTrainId() {
        List<TrainRoutePiece> trainRoutePieces = trainRoutePieceDAO.findByTrainId(1L);
        assertEquals(3, trainRoutePieces.size());
    }

    @Test
    @Transactional
    public void addStationInMiddleOfRoute() {

        TrainRoutePiece trainRoutePiece = new TrainRoutePiece(trainDAO.findOne(1L), stationDAO.findOne(5L), stationDAO.findOne(1L), 2, 100);

        TrainRoutePiece saved = trainRoutePieceDAO.save(trainRoutePiece);
        *//*assertEquals(2, saved.getStationSerialNumber());*//*



        List<TrainRoutePiece> trainRoutePieces = trainRoutePieceDAO.findByTrainId(1L);
        assertEquals(trainRoutePieceDAO.findOne(saved.getId()), trainRoutePieces.get(1));

        int[] arr = getSequenceOfStation(trainRoutePieces);

        logger.info("{}", trainRoutePieces);

        assertArrayEquals(new int[] {1, 2, 3, 4}, arr);

    }


    @Test
    @Transactional
    public void correctSequenceOfStationsWhenDeleting() {

        List<TrainRoutePiece> stations =  trainRoutePieceDAO.findByTrainId(1L);
        logger.info("{}", stations);
        assertArrayEquals(new int[] {1, 2, 3}, getSequenceOfStation(stations));

        TrainRoutePiece station = stations.get(1);
        trainRoutePieceDAO.delete(station.getId());

        stations = trainRoutePieceDAO.findByTrainId(1L);
        logger.info("{}", stations);
        assertArrayEquals(new int[] {1, 2}, getSequenceOfStation(stations));

    }

    @Test
    @Transactional
    public void addStationToEndOfRoute() {
        List<TrainRoutePiece> stations =  trainRoutePieceDAO.findByTrainId(1L);
        assertArrayEquals(new int[] {1, 2, 3}, getSequenceOfStation(stations));

        logger.info("{}", stations);

        TrainRoutePiece newStation1 = new TrainRoutePiece(trainDAO.findOne(1L), stationDAO.findOne(3L), stationDAO.findOne(2L), 10, 100);
        TrainRoutePiece saved = trainRoutePieceDAO.save(newStation1);

        stations = trainRoutePieceDAO.findByTrainId(1L);
        logger.info("{}", stations);
        assertArrayEquals(new int[]{1, 2, 3, 4}, getSequenceOfStation(stations));

        assertEquals(saved, stations.get(3));

    }

    private int[] getSequenceOfStation(List<TrainRoutePiece> stations) {
        int[] arr = new int[stations.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stations.get(i).getSerialNumber();
        }
        return arr;
    }

    @Test
    @Transactional
    public void update() {
        *//*TrainRoutePiece trainRoutePiece = new TrainRoutePiece(1,new Train(1, null, 0, 0, 0), new Station(""), 1);
        trainStationsDAO.save(trainRoutePiece);
        TrainRoutePiece dbValue =  trainStationsDAO.findOne(1);
        logger.info("{}", dbValue);*//*
    }*/

}
