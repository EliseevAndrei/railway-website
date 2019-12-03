package com.eliseev.app.service;

import com.eliseev.app.TestConfig;
import com.eliseev.app.services.RouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class RouteServiceTest {

    private Logger logger = LoggerFactory.getLogger(RouteServiceTest.class);

    @Autowired
    private RouteService routeService;

    @Test
    public void getTrainsOnRoute() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = simpleDateFormat.parse("2019-10-11");
            logger.info("{}", routeService.findTrainsOnRoute("Брест", "Минск", date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
