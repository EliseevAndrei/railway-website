package com.eliseev.app.controllers;

import com.eliseev.app.models.Train;
import com.eliseev.app.services.RouteService;
import com.eliseev.app.services.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.util.Date;


@Controller
@Validated
@RequestMapping("/trains")
public class TrainController {
    private final Logger logger = LoggerFactory.getLogger(TrainController.class);

    private TrainService service;
    private RouteService routeService;

    @Autowired
    public TrainController(TrainService service,
                           RouteService routeService) {
        this.service = service;
        this.routeService = routeService;
    }

    @GetMapping
    public String showTrains(Model model) {
        logger.info("User send GET /trains request");
        model.addAttribute("trains", service.list());
        model.addAttribute("train", new Train());
        return "trains/trains";
    }

    @GetMapping("/onRoute")
    public String showTrainsOnStations(@ModelAttribute("dep_station") String dep_station,
                                       @ModelAttribute("arr_station") @Max(2) String arr_station,
                                       @ModelAttribute("dep_date") @Pattern(regexp = "askdfj") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")  Date date,
                                       Model model) {
        logger.info("user send GET /trains/onRoute with model attributes {}, {}, {}", dep_station, arr_station, date);
        model.addAttribute("dep_station", dep_station);
        model.addAttribute("arr_station", arr_station);
        model.addAttribute("dep_date", date);
        model.addAttribute("routes", routeService.findRoutes(dep_station, arr_station, date));
        return "trains/trains_on_route";
    }


}
