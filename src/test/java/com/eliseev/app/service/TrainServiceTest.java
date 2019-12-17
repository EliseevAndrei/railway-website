package com.eliseev.app.service;

import com.eliseev.app.TestConfig;
import com.eliseev.app.models.Carriage;
import com.eliseev.app.models.Place;
import com.eliseev.app.models.Train;
import com.eliseev.app.services.TrainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainServiceTest {

    private Logger logger = LoggerFactory.getLogger(TrainServiceTest.class);

    @Autowired
    private TrainService trainService;

    @Test
    public void createTrain() {
        Train train = new Train();
        List<Place> places = new ArrayList<>();
        Place place;
        for (int i = 0; i < 10; i++) {
            place = new Place();
            place.setType("Купе");
            place.setNumber(i + 1);
            places.add(place);
        }
        List<Carriage> carriages = new ArrayList<>();
        Carriage carriage = new Carriage();
        carriage.setType("Купе");
        carriage.setNumber(1);
        carriage.setPlaces(places);
        carriages.add(carriage);
        train.setCarriages(carriages);
        train.setName("фыв");
        Train saved = trainService.create(train);
        logger.info("{}",saved);
        logger.info("{}", saved.getCarriages());

    }

}
