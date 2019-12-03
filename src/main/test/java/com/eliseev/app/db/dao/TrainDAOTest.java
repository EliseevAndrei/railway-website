package com.eliseev.app.db.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.repository.custom.TrainDAO;
import com.eliseev.app.services.StationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainDAOTest {

    private Logger logger = LoggerFactory.getLogger(TrainRoutePieceDAOTest.class);

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private StationService stationService;

    @Test
    @Transactional
    public void count() {
        assertEquals(2, trainDAO.count());
    }

    @Test
    @Transactional
    public void findOne() {
        Train train = trainDAO.findOne(1);
        assertEquals("10в", train.getName());
        assertEquals(10, train.getCountCommon());
        assertEquals(10, train.getCountCoupe());
        assertEquals(10, train.getCountLying());
    }

    @Test
    @Transactional
    public void save() {
        assertEquals(2, trainDAO.count());
        Train train = new Train("200B", 11, 11, 11);
        trainDAO.save(train);
        assertEquals(3, trainDAO.count());
    }

    @Test
    @Transactional
    public void delete() {
        assertNotNull(trainDAO.findOne(1));
        trainDAO.delete(1);
        assertNull(trainDAO.findOne(1));
    }

    @Test
    @Transactional
    public void update() {
        assertEquals(2, trainDAO.count());
        Train train = new Train("200B", 11, 11, 11);
        trainDAO.save(train);
        assertEquals(3, trainDAO.count());
    }

    @Test
    @Transactional
    public void departmentRepositoryTest() {

        Train train = new Train("200B", 11, 11, 11);

        trainDAO.save(train);

    }

    @Test
    @Transactional
    public void createTrainWithRoutePieces() {

        TrainRoutePiece trainRoutePiece = new TrainRoutePiece(null, stationService.get(1L), stationService.get(2), 1, 100);
        Train train = new Train("200B", 11, 11, 11);
        trainRoutePiece.setTrain(train);
        train.getTrainRoutePieceList().add(trainRoutePiece);
        trainDAO.save(train);

        logger.info("{}", train);
        logger.info("{}", train.getTrainRoutePieceList());
        logger.info("{}", trainRoutePiece);

    }


}
