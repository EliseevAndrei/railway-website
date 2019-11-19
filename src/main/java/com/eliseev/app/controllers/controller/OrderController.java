package com.eliseev.app.controllers.controller;

import com.eliseev.app.models.Route;
import com.eliseev.app.models.Ticket;
import com.eliseev.app.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@SessionAttributes("route")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    private TicketService service;

    @Autowired
    public OrderController(TicketService service) {
        this.service = service;
    }


    @PostMapping(path = "/orderTicket", consumes = "application/json", produces = "application/json")
    public @ResponseBody Route setRouteToTicket(@RequestBody Route route, HttpSession session) {

        logger.info("user send request POST /users/orderTicket with body {}", route);

        session.setAttribute("route", route);

        return route;
    }

    @GetMapping(path = "/{userId}/orderTicket")
    public String getOrderForm(@PathVariable("userId") long id, Model model) {

        logger.info("user send GET /users/{}/orderTicket", id);
        model.addAttribute("ticket", new Ticket());

        return "order";
    }

    @PostMapping(path = "/{userId}/orderTicket")
    public String setOrder(@Valid Ticket ticket, @SessionAttribute("route") Route route,
                           Errors errors,
                           SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            logger.warn("some errors occured: {}", errors);
            return "order";
        }

        ticket.setRoute(route);
        sessionStatus.setComplete();
        logger.info("user create ticket = {}", ticket);

        service.create(ticket);

        return "redirect:/users/" + ticket.getUser().getId() + "/tickets";
    }

}
