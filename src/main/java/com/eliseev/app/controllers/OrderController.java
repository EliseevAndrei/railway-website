package com.eliseev.app.controllers;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.User;
import com.eliseev.app.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/users/{userId}/orderTicket")
@SessionAttributes("ticket")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    private TicketService service;

    @Autowired
    public OrderController(TicketService service) {
        this.service = service;
    }

    @GetMapping(params = {"trainId", "dep_station", "arr_station", "dep_date"})
    public String getOrderFormNotRegistered(@RequestParam("trainId") Train train,
                        @RequestParam("dep_station") String dep_station,
                        @RequestParam("arr_station") String arr_station,
                        @RequestParam("dep_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy") Date dep_date,
                        Model model) {
        logger.info("user send request GET /order?idTrain={}&dep_station={}&arr_station={}&dep_date={}", train.getId(), dep_station, arr_station, dep_date);
        Ticket ticket = new Ticket();
        ticket.setTrain(train);
        ticket.setArrStation(arr_station);
        ticket.setDepStation(dep_station);
        ticket.setDate(dep_date);
        model.addAttribute("ticket", ticket);
        return "redirect:/users/authoriseForm";
    }

    @GetMapping
    public String getOrderFormRegistered(@PathVariable("userId") long id, @SessionAttribute("ticket") Ticket ticket) {
        ticket.setUserId(id);
        logger.info("user send GET /users/{}/orderTicket", id);
        return "order";
    }

    @PostMapping
    public String setOrder(@ModelAttribute("ticket") Ticket ticket,
                           SessionStatus sessionStatus) {
        logger.info("user create ticket = {}", ticket);
        service.create(ticket);
        sessionStatus.setComplete();
        return "redirect:/users/" + ticket.getUserId() + "/tickets";
    }

}
