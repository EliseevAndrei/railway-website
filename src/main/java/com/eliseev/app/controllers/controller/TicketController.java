package com.eliseev.app.controllers.controller;

import com.eliseev.app.models.User;
import com.eliseev.app.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/forUser")
    public String ticketListForUser(Authentication authentication, Model model) {
        logger.info("User send GET /tickets/list/forUser");
        model.addAttribute("tickets", service.listForUser((User) authentication.getPrincipal()));
        return "tickets/tickets";
    }



}
