package com.eliseev.app.controllers;

import com.eliseev.app.models.TrainStation;
import com.eliseev.app.services.StationService;
import com.eliseev.app.services.TrainStationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/trains/list/{trainId}/stations")
public class TrainStationsController {

    private Logger logger = LoggerFactory.getLogger(TrainStationsController.class);
    private TrainStationsService service;
    private StationService stationService;

    @Autowired
    public TrainStationsController(TrainStationsService service,
                                   StationService stationService) {
        this.service = service;
        this.stationService = stationService;
    }

    @GetMapping
    public String findAll(@PathVariable("trainId") long trainId, Model model) {
        logger.info("User send /trains/list/{}/stations", trainId);
        model.addAttribute("allStations", stationService.list());
        model.addAttribute("stations", service.list(trainId));
        /*model.addAttribute("station", new TrainStation());*/
        return "stations/trainStations";

    }

}
