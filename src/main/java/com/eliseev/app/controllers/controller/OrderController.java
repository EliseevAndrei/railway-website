package com.eliseev.app.controllers.controller;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.Train;
import com.eliseev.app.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/users")
@SessionAttributes("ticket")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    private TicketService service;

    @Autowired
    public OrderController(TicketService service) {
        this.service = service;
    }

    @GetMapping(path = "/orderTicket", params = {"trainId", "dep_station", "arr_station", "depTime", "arrTime"})
    public String getOrderFormNotRegistered(@RequestParam("trainId") Train train,
                                            @RequestParam("dep_station") String dep_station,
                                            @RequestParam("arr_station") String arr_station,
                                            @RequestParam("depTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME,
                                                    pattern = "yyyy-MM-dd HH:dd")  Date depTime,
                                            @RequestParam("arrTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME,
                                                    pattern = "yyyy-MM-dd HH:dd") Date arrTime,
                                            Model model) {

        logger.info("user send request GET /users/orderTicket?idTrain={}&dep_station={}&arr_station={}&depTime={}&arrTime={}",
                train.getId(), dep_station, arr_station, depTime, arrTime);

        Ticket ticket = new Ticket();
        ticket.setTrain(train);
        ticket.setArrStation(arr_station);
        ticket.setDepStation(dep_station);
        ticket.setArrTime(arrTime);
        ticket.setDepTime(depTime);

        model.addAttribute("ticket", ticket);
        return "redirect:/users/authoriseForm";
    }

    @GetMapping(path = "/{userId}/orderTicket")
    public String getOrderFormRegistered(@PathVariable("userId") long id, @SessionAttribute("ticket") Ticket ticket) {
        ticket.setUserId(id);
        logger.info("user send GET /users/{}/orderTicket", id);
        return "order";
    }

    @PostMapping(path = "/{userId}/orderTicket")
    public String setOrder(@Valid Ticket ticket,
                           Errors errors,
                           SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            logger.warn("some errors occured: {}", errors);
            return "order";
        }
        logger.info("user create ticket = {}", ticket);
        service.create(ticket);
        sessionStatus.setComplete();
        return "redirect:/users/" + ticket.getUserId() + "/tickets";
    }

}
