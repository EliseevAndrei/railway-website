package com.eliseev.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
public class TrainStationController {
    final Logger logger = LoggerFactory.getLogger(TrainStationController.class);
    @GetMapping("/trains/available/onRoute")
    public String showAvailableTrains(@PathVariable("dep_station") String dep_station,
                                            @PathVariable("arr_station") String arr_station,
                                            @PathVariable("dep_date") String dep_date,
                                            Model model) {
        logger.info("User send GET /trains/available/onRoute?dep_station={}&arr_station={}&dep_date={} request",
                dep_station, arr_station, dep_date);
        return null;
    }

}
