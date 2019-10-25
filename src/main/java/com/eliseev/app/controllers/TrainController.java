package com.eliseev.app.controllers;

import com.eliseev.app.models.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/trains")
public class TrainController {
    final Logger logger = LoggerFactory.getLogger(TrainController.class);

    @GetMapping("/list")
    public String showTrains(Model model) {
        List<Train> trains = Arrays.asList(
                new Train(0,"1",10 , 10, 10),
                new Train(1,"2", 20, 20, 20)
        );
        logger.info("User send GET /trains/list request");
        model.addAttribute("trains", trains);
        return "trains/trains-list";
    }

    @GetMapping("/adding-form")
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
    }

}
