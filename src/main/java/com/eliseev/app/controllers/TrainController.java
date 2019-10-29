package com.eliseev.app.controllers;

import com.eliseev.app.models.Train;
import com.eliseev.app.services.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


}
