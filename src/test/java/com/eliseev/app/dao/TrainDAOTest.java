package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.models.Carriage;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.repository.custom.StationDAO;
import com.eliseev.app.repository.custom.TrainDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Slf4j
public class TrainDAOTest {

    /*private Logger logger = LoggerFactory.getLogger(TrainRoutePieceDAOTest.class);*/

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private StationDAO stationDAO;

    @Test
    @Transactional
    public void findOne() {
        assertNotNull(trainDAO.findOne(23));
        assertNotNull(trainDAO.findOne(25));
    }

    @Test
    @Transactional
    public void save() {
        assertEquals(2, trainDAO.count());
        Train train = new Train("200B");
        trainDAO.save(train);
        assertEquals(3, trainDAO.count());
    }

    @Test
    @Transactional
    public void delete() {
        assertNotNull(trainDAO.findOne(23));
        trainDAO.delete(23);
        assertNull(trainDAO.findOne(23));
    }

    @Test
    @Transactional
    public void update() {
        assertEquals(2, trainDAO.count());
        Train train = new Train(23, "200B");
        trainDAO.save(train);
        assertEquals(2, trainDAO.count());
    }

    @Test
    @Transactional
    public void createTrainWithRoutePieces() {

        TrainRoutePiece trainRoutePiece1 = new TrainRoutePiece(null, stationDAO.findOne(1L), stationDAO.findOne(2),
                1, 100);
        TrainRoutePiece trainRoutePiece2 = new TrainRoutePiece(null, stationDAO.findOne(2L), stationDAO.findOne(3),
                2, 100);
        Train train = new Train("200B");
        trainRoutePiece1.setTrain(train);
        trainRoutePiece2.setTrain(train);
        train.getTrainRoutePieceList().add(trainRoutePiece1);
        train.getTrainRoutePieceList().add(trainRoutePiece2);
        Train saved = trainDAO.save(train);

        assertEquals(2, saved.getTrainRoutePieceList().size());
    }


    @Test
    @Transactional
    public void getFreePlacesAmountForTrainRoute() {
        Map<String, Integer> carriagesWithPlaces =
                trainDAO.getFreePlacesAmountForTrainRoute(25, 32, 1, 6);
        assertEquals(2, carriagesWithPlaces.size());
        assertEquals(2, (int) carriagesWithPlaces.get("Плацкарт"));
        assertEquals(4, (int) carriagesWithPlaces.get("Общий"));

        carriagesWithPlaces =
                trainDAO.getFreePlacesAmountForTrainRoute(25, 32, 1, 3);
        assertEquals(2, carriagesWithPlaces.size());
        assertEquals(3, (int) carriagesWithPlaces.get("Плацкарт"));
        assertEquals(4, (int) carriagesWithPlaces.get("Общий"));

        carriagesWithPlaces =
                trainDAO.getFreePlacesAmountForTrainRoute(25, 32, 4, 4);
        assertEquals(2, carriagesWithPlaces.size());
        assertEquals(2, (int) carriagesWithPlaces.get("Плацкарт"));
        assertEquals(4, (int) carriagesWithPlaces.get("Общий"));

        carriagesWithPlaces =
                trainDAO.getFreePlacesAmountForTrainRoute(25, 32, 4, 6);
        assertEquals(2, carriagesWithPlaces.size());
        assertEquals(2, (int) carriagesWithPlaces.get("Плацкарт"));
        assertEquals(4, (int) carriagesWithPlaces.get("Общий"));

        carriagesWithPlaces =
                trainDAO.getFreePlacesAmountForTrainRoute(25, 32, 5, 6);
        assertEquals(2, carriagesWithPlaces.size());
        assertEquals(3, (int) carriagesWithPlaces.get("Плацкарт"));
        assertEquals(4, (int) carriagesWithPlaces.get("Общий"));

    }

    @Test
    @Transactional
    public void getCarriagesByTrainId() {
        List<Carriage> carriages = trainDAO.getCarriages(25);
        assertEquals(2, carriages.size());
        assertEquals(4, carriages.get(0).getPlaces().size());
        assertEquals(4, carriages.get(1).getPlaces().size());
    }

    @Test
    @Transactional
    public void getCarriagesWithFreePlaces() {

        List<Carriage> carriages = trainDAO.getCarriages(25, 32, 1,6);

        assertEquals(2,  carriages.get(0).getPlaces().size());
        assertTrue(carriages.get(0).getPlaces().stream()
                .noneMatch(e -> e.getNumber() == 4 || e.getNumber() == 8));

        entityManager.clear();

        carriages = trainDAO.getCarriages(25, 32, 1,3);

        assertEquals(3,  carriages.get(0).getPlaces().size());
        assertTrue(carriages.get(0).getPlaces().stream()
                .noneMatch(e -> e.getNumber() == 4));

        entityManager.clear();

        carriages = trainDAO.getCarriages(25, 32, 1,4);

        assertEquals(2,  carriages.get(0).getPlaces().size());
        assertTrue(carriages.get(0).getPlaces().stream()
                .noneMatch(e -> e.getNumber() == 4 || e.getNumber() == 8));

        entityManager.clear();

        carriages = trainDAO.getCarriages(25, 32, 4,6);

        assertEquals(2,  carriages.get(0).getPlaces().size());
        assertTrue(carriages.get(0).getPlaces().stream()
                .noneMatch(e -> e.getNumber() == 4 || e.getNumber() == 8));

        entityManager.clear();

        carriages = trainDAO.getCarriages(25, 32, 5,6);

        assertEquals(3,  carriages.get(0).getPlaces().size());
        assertTrue(carriages.get(0).getPlaces().stream()
                .noneMatch(e -> e.getNumber() == 4));
    }

}
