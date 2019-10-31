package com.eliseev.app.controllers;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping
    public String order(@RequestParam("trainId") Train train,
                        @RequestParam("dep_station") String dep_station,
                        @RequestParam("arr_station") String arr_station,
                        @RequestParam("dep_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") String dep_date,
                        Model model) {
    logger.info("user send request GET /order?idTrain={}&dep_station={}&arr_station={}&dep_date={}", train.getId(), dep_station, arr_station, dep_date);
        Ticket ticket = new Ticket();
        ticket.setTrain(train);
        ticket.setArrStation(arr_station);
        ticket.setDepStation(dep_station);
        ticket.setDate(dep_date);
        model.addAttribute("ticket", ticket);
        return "order";
    }

}
