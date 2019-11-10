package com.eliseev.app.controllers.controller;

import com.eliseev.app.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users/{id}/tickets")
public class TicketController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    private TicketService service;

    @Autowired
    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping
    public String showTickets(@PathVariable long id,  Model model) {
        logger.info("User send GET /users/{}/tickets request", id);
        model.addAttribute("tickets", service.list(id));
        return "tickets/tickets";
    }

}
