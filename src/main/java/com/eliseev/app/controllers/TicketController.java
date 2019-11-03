package com.eliseev.app.controllers;

import com.eliseev.app.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/tickets")
public class TicketController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    private TicketService service;

    @Autowired
    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping
    public String showTickets(Model model) {
        logger.info("User send GET /tickets request");
        model.addAttribute("tickets", service.list());
        return "tickets/tickets";
    }


}
