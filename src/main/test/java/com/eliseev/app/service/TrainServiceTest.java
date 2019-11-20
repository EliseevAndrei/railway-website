package com.eliseev.app.service;

import com.eliseev.app.db.dao.TestConfig;
import com.eliseev.app.models.Train;
import com.eliseev.app.services.TrainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainServiceTest {

    @Autowired
    private TrainService trainService;

    @Test
    public void create() {
        Train train = new Train(-1, "sakdf", 10, 10, 10);
        Train saved = trainService.create(train);
        assertNotNull(saved);
    }

}
