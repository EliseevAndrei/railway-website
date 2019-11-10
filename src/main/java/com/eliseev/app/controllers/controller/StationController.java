package com.eliseev.app.controllers.controller;

import com.eliseev.app.services.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stations")
public class StationController {

    private final Logger logger = LoggerFactory.getLogger(StationController.class);

    private StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public String getStations(Model model) {
        logger.info("user send GET /stations");
        model.addAttribute("stations", stationService.list());
        return "stations/stations";
    }

}
