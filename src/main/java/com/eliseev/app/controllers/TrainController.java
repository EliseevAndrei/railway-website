package com.eliseev.app.controllers;

import com.eliseev.app.models.Train;
import com.eliseev.app.services.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/trains")
public class TrainController {
    private final Logger logger = LoggerFactory.getLogger(TrainController.class);

    private TrainService service;

    @Autowired
    public TrainController(TrainService service) {
        this.service = service;
    }

    @GetMapping
    public String showTrains(Model model) {
        logger.info("User send GET /trains/list request");
        model.addAttribute("trains", service.list());
        model.addAttribute("train", new Train());
        return "trains/trains";
    }

    @GetMapping("/onRoute")
    public String showTrainsOnStations(@ModelAttribute("dep_station") String dep_station,
                                       @ModelAttribute("arr_station") String arr_station,
                                       @ModelAttribute("dep_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") Date date,
                                       Model model) {
        logger.info("user send GET /trains/onRoute with model attributes {}, {}, {}", dep_station, arr_station, date);
        model.addAttribute("dep_station", dep_station);
        model.addAttribute("arr_station", arr_station);
        model.addAttribute("dep_date", date);
        model.addAttribute("trains", service.getTrainsOnStations(dep_station, arr_station, date));
        return "trains/trains_on_route";
    }


}
