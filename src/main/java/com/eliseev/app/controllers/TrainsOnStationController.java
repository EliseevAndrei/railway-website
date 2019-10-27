package com.eliseev.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class TrainsOnStationController {
    private final Logger logger = LoggerFactory.getLogger(TrainsOnStationController.class);
    @GetMapping("/trains-on-station/available")
    public String showAvailableTrains(@ModelAttribute("dep_station") String dep_station,
                                            @ModelAttribute("arr_station") String arr_station,
                                            @ModelAttribute("dep_date") String dep_date,
                                            Model model) {
        logger.info("User send GET /trains/available request");
        return null;
    }

}
