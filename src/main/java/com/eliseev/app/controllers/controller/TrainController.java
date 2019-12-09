package com.eliseev.app.controllers.controller;

import com.eliseev.app.models.Station;
import com.eliseev.app.models.Train;
import com.eliseev.app.services.RouteService;
import com.eliseev.app.services.StationService;
import com.eliseev.app.services.TrainService;
import com.eliseev.app.services.dto.TrainRouteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/trains")
@Validated
public class TrainController {
    private final Logger logger = LoggerFactory.getLogger(TrainController.class);

    private TrainService service;
    private RouteService routeService;
    private StationService stationService;

    @Autowired
    public TrainController(TrainService service,
                           RouteService routeService,
                           StationService stationService) {
        this.service = service;
        this.routeService = routeService;
        this.stationService = stationService;
    }

    @GetMapping
    public String showTrains(Model model) {
        logger.info("User send GET /trains request");
        model.addAttribute("trains", service.list());
        model.addAttribute("train", new Train());
        return "trains/trains";
    }

    @GetMapping("/onRoute")
    public String showTrainsOnStations(@RequestParam("dep_station_id") long dep_station_id,
                                       @RequestParam("arr_station_id") long arr_station_id,
                                       @RequestParam("dep_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") @Valid Date date,
                                       Model model) {
        logger.info("user send GET /trains/onRoute with model attributes {}, {}, {}", dep_station_id, arr_station_id, date);
        Station depStation = stationService.get(dep_station_id);
        Station arrStation = stationService.get(arr_station_id);
        model.addAttribute("dep_station", depStation);
        model.addAttribute("arr_station", arrStation);
        model.addAttribute("dep_date", date);
        List<TrainRouteDTO> trainRouteDTO = routeService.findTrainsOnRoute(depStation, arrStation, date);
        logger.info("{}", trainRouteDTO);
        model.addAttribute("routes", trainRouteDTO);
        return "trains/trains_on_route";
    }


}
