package com.eliseev.app.service;

import com.eliseev.app.TestConfig;
import com.eliseev.app.services.TrainService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TrainServiceTest {

    @Autowired
    private TrainService trainService;

}
