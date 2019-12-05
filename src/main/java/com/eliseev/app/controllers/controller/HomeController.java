package com.eliseev.app.controllers.controller;

import com.eliseev.app.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    private StationService stationService;

    @Autowired
    public HomeController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public String getMain(Model model) {
        model.addAttribute("allStations", stationService.list());
        return "main";
    }

}
