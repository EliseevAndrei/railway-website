package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tickets", produces = "application/json")
public class TicketRestController {

    private Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

    private TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService service) {
        this.ticketService = service;
    }

    @PostMapping("/order")
    public Ticket orderTicket(@RequestBody Ticket ticket) {
        logger.info("{}", ticket);
        return ticket;
    }

}
