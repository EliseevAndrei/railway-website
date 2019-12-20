package com.eliseev.app.service;

import com.eliseev.app.TestConfig;
import com.eliseev.app.services.RouteService;
import com.eliseev.app.services.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = TestConfig.class)
public class RouteServiceTest {

    private Logger logger = LoggerFactory.getLogger(RouteServiceTest.class);

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationService stationService;

    /*@Test
    public void getTrainsOnRoute() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = simpleDateFormat.parse("2019-10-11");
            logger.info("{}", routeService.findTrainsOnRoute(stationService.get(1L), stationService.get(4L), date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

}
