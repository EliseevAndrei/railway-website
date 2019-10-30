package com.eliseev.app.controllers;

import com.eliseev.app.models.TrainStation;
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

    private TrainStationsService service;

    @Autowired
    public TrainStationsController(TrainStationsService service) {
        this.service = service;
    }

    @GetMapping
    public String findAll(@PathVariable("trainId") long trainId, Model model) {

        model.addAttribute("stations", service.list(trainId));
        model.addAttribute("station", new TrainStation());

        return "stations/stations";

    }

}
