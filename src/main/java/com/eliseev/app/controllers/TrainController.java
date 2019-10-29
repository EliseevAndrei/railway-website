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

    /*@GetMapping("/adding-form")
    public String showAddingForm() {
        logger.info("User send GET /trains/adding-form request");
        return "trains/add-train";
    }

    @PostMapping("/add")
    public String addTrain(Train train, Model model) {
        logger.info("User send POST /trains/add request with body {}", train);
        return "redirect:/list";
    }

    @GetMapping("/editing-form/{id}")
    public String showEditingForm(@PathVariable("id") long id, Model model) {
        logger.info("User send GET /trains/editing-form/{} request", id);
        model.addAttribute("id");
        return "trains/edit-train";
    }

    @PostMapping("/update/{id}")
    public String updateTrain(@PathVariable("id") long id, Train train,
                              Model model) {
        logger.info("User send POST /trains/update/{} request", id);
        return "redirect:/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteTrain(@PathVariable("id") long id, Model model) {
        logger.info("User send POST /trains/delete/{} request", id);
        return "redirect:/list";
    }*/

}
