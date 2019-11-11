package com.eliseev.app.controllers.controller;

import com.eliseev.app.models.Route;
import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.Train;
import com.eliseev.app.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/users")
@SessionAttributes({"ticket", "route"})
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    private TicketService service;

    @Autowired
    public OrderController(TicketService service) {
        this.service = service;
    }


    @PostMapping(path = "/orderTicket", consumes = "application/json", produces = "application/json")
    public @ResponseBody Ticket setRouteToTicket(@RequestBody Route route, HttpSession session
            /*@RequestParam("trainId") Train train,
                                            @RequestParam("dep_station") String dep_station,
                                            @RequestParam("arr_station") String arr_station,
                                            @RequestParam("depTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME,
                                                    pattern = "yyyy-MM-dd HH:dd")  Date depTime,
                                            @RequestParam("arrTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME,
                                                    pattern = "yyyy-MM-dd HH:dd") Date arrTime,
                                            Model model*/) {

        logger.info("user send request POST /users/orderTicket with body {}", route);

        Ticket ticket = new Ticket();
        ticket.setTrain(route.getTrainName());
        ticket.setArrStation(route.getArrStation());
        ticket.setDepStation(route.getDepStation());
        ticket.setArrTime(route.getArrTime());
        ticket.setDepTime(route.getDepTime());

        session.setAttribute("ticket", ticket);
        session.setAttribute("route", route);

        /*model.addAttribute("ticket", ticket);
        model.addAttribute("common_places_amount", route.getCommon_places_amount());
        model.addAttribute("lying_places_amount", route.getLying_places_amount());
        model.addAttribute("coupe_places_amount", route.getCoupe_places_amount());*/

        return ticket;
    }

    @GetMapping(path = "/{userId}/orderTicket")
    public String getOrderForm(@PathVariable("userId") long id, @SessionAttribute("ticket") Ticket ticket) {
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
